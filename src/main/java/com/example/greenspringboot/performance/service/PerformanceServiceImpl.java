package com.example.greenspringboot.performance.service;
import com.example.greenspringboot.admin.repository.AdminRepository;
import com.example.greenspringboot.board.paging.SearchCondition12;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.entity.PerformanceHist;
import com.example.greenspringboot.performance.repository.PerformanceHistRepository;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PerformanceServiceImpl implements PerformanceService{

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PerformanceHistRepository performanceHistRepository;

    @Override
    public void performanceRead(Integer pno, Model m) {

        Performance performance = performanceRepository.findById(pno)
                .orElseThrow(()-> new IllegalArgumentException("공연 데이터 없음"));

            Integer ppno = performance.getPno();
            String title = performance.getTitle();
            String artist = performance.getArtist();
            String genre = performance.getGenre();
            String venue = performance.getVenue();
            String duration = performance.getDuration();
            String rating = performance.getRating();
            String date = performance.getDate();
            String content = performance.getContent() != null ? performance.getContent().replace("\n", "<br/>") : null;
            String img = performance.getImg();
            Integer price = performance.getPrice();

            PerformanceDto performanceDto = PerformanceDto.builder()
                    .pno(ppno)
                    .title(title)
                    .artist(artist)
                    .genre(genre)
                    .venue(venue)
                    .duration(duration)
                    .rating(rating)
                    .date(date)
                    .content(content)
                    .img(img)
                    .price(price)
                    .build();

            m.addAttribute("performanceDto", performanceDto);

            System.out.println("performanceDto:" + performanceDto);
    }

    @Override
    public PerformanceDto performanceEditRead(Integer pno) {
        Performance performance = performanceRepository.findById(pno)
                .orElseThrow(() -> new IllegalArgumentException("공연 정보 없음"));

        return PerformanceDto.builder()
                .pno(performance.getPno())
                .title(performance.getTitle())
                .artist(performance.getArtist())
                .genre(performance.getGenre())
                .venue(performance.getVenue())
                .duration(performance.getDuration())
                .rating(performance.getRating())
                .date(performance.getDate())
                .content(performance.getContent())
                .img(performance.getImg())
                .price(performance.getPrice())
                .build();
    }



    @Override
    public void write(PerformanceDto performanceDto, Integer aId) {

        adminRepository.findById(aId)
                .orElseThrow(() -> new IllegalArgumentException("관리자 정보 없음"));

        String img;
        try {
            img = uploadImage(performanceDto.getImgFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 업로드 실패", e);
        }

            Performance performance = Performance.builder()
                    .title(performanceDto.getTitle())
                    .artist(performanceDto.getArtist())
                    .genre(performanceDto.getGenre())
                    .venue(performanceDto.getVenue())
                    .duration(performanceDto.getDuration())
                    .rating(performanceDto.getRating())
                    .date(performanceDto.getDate())
                    .content(performanceDto.getContent())
                    .img(performanceDto.getImg())
                    .img(img)
                    .price(performanceDto.getPrice())
                    .createdBy(aId)
                    .updatedBy(aId)
                    .build();

            performanceRepository.save(performance);
    }


    // 배포시 수정
    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String uploadDir = "C:/performance/";
//        String uploadDir = "/home/ubuntu/performance/";

        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + "_" + originalFilename;

        File destination = new File(uploadDir + newFileName);
        file.transferTo(destination);

        return "/images/" + newFileName;
    }

    @Override
    public List<PerformanceDto> getSearchResultPage(SearchCondition12 sc) {
        // 게시글 조회 메서드, 게시글 목록 조회니까 그 게시글의 작성자도 같이 가져와야되는게 아닐까?
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        Sort sort = Sort.by(Sort.Order.desc("pno")); // 등록일 기준으로 내림차순 정렬
        List<Performance> performanceList;

        if ("title".equals(option)) {
            performanceList = performanceRepository.findByTitleContainingAndDeletedFalse(keyword, sort);
        } else if ("artist".equals(option)) {
            performanceList = performanceRepository.findByArtistContainingAndDeletedFalse(keyword, sort);
        } else {
            performanceList = performanceRepository.findByTitleContainingOrArtistContainingAndDeletedFalse(keyword, sort);
        }
        // 검색된 게시글 리스트를 페이지에 맞게 잘라서 반환
        int startIdx = (sc.getPage() - 1) * sc.getPageSize();
        int endIdx = Math.min(startIdx + sc.getPageSize(), performanceList.size());

        List<Performance> pagedPerformanceList = performanceList.subList(startIdx, endIdx);

        // DTO로 변환해서 반환
        return toDtoList(pagedPerformanceList);
    }

    @Override
    public int getSearchResultCnt(SearchCondition12 sc) {
        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        if ("title".equals(option)) {
            return performanceRepository.countByTitleContainingAndDeletedFalse(keyword);
        } else if ("artist".equals(option)) {
            return performanceRepository.countByArtistContainingAndDeletedFalse(keyword);
        } else {
            return performanceRepository.countByTitleContainingOrArtistContainingAndDeletedFalse(keyword, keyword);
        }
    }


    @Override
    public List<PerformanceDto> toDtoList(List<Performance> performanceList) {
        return performanceList.stream()
                .map(performance -> PerformanceDto.builder()
                        .pno(performance.getPno())
                        .title(performance.getTitle())
                        .artist(performance.getArtist())
                        .genre(performance.getGenre())
                        .venue(performance.getVenue())
                        .duration(performance.getDuration())
                        .rating(performance.getRating())
                        .date(performance.getDate())
                        .content(performance.getContent())
                        .img(performance.getImg())
                        .price(performance.getPrice())
                        .deleted(performance.getDeleted())
                        .createdAt(performance.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

//   인덱스 화면에서 보여주기
    @Override
    public void performanceList(Model m) {
        List<Performance> performances = performanceRepository.findAllByDeletedFalseOrderByPnoDesc(); // 모든 앨범 목록을 조회
        if (!performances.isEmpty()) {
            List<PerformanceDto> performanceDtos = performances.stream() // 앨범을 DTO로 변환
                    .map(performance -> PerformanceDto.builder()
                            .pno(performance.getPno())
                            .title(performance.getTitle())
                            .artist(performance.getArtist())
                            .img(performance.getImg())
                            .date(performance.getDate())
                            .venue(performance.getVenue())
                            .build())
                    .collect(Collectors.toList());

            m.addAttribute("performanceDtos", performanceDtos);

        } else {
            System.out.println("공연 정보를 찾을 수 없습니다.");
        }
    }

    //    관리자에서 수정할때 앨범 목록 가져오기
    public List<PerformanceDto> getAllPerformances() {
        List<Performance> performances = performanceRepository.findAllByDeletedFalseOrderByPnoDesc();

        return performances.stream()
                .map(performance -> PerformanceDto.builder()
                        .pno(performance.getPno())
                        .title(performance.getTitle())
                        .artist(performance.getArtist())
                        .img(performance.getImg())
                        .date(performance.getDate())
                        .venue(performance.getVenue())
                        .build())
                .collect(Collectors.toList());
    }



//
//    @Override
//    public boolean performanceModify(PerformanceDto performanceDto, MultipartFile imgFile, HttpSession session) throws IOException {
//        // 기존 앨범 가져오기
//        Optional<Performance> optionalPerformance = performanceRepository.findById(performanceDto.getPno());
//        if (optionalPerformance.isPresent()) {
//            Performance oldPerformance = optionalPerformance.get();
//            PerformanceDto oldData = toDto(oldPerformance);
//
////                이력에는 수정한 사람의 관리자 아이디가 입력
//            Integer modifierAId = (Integer) session.getAttribute("aId");
//
//            // 이미지 변경 안 했으면 이전 값 유지
//            if (imgFile == null || imgFile.isEmpty()) {
//                performanceDto.setImg(oldData.getImg());
//            }
//
//            toEntity(oldPerformance, performanceDto, imgFile);
//            performanceRepository.save(oldPerformance);
//
//            // 변경 이력을 담을 DTO 리스트
//            List<PerformanceHist> performanceHistList = new ArrayList<>();
//            // 기존에 작성된 2개 필드 변경 이력 추가
//
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "IMG", oldData.getImg(), performanceDto.getImg());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "TITLE", oldData.getTitle(), performanceDto.getTitle());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "ARTIST", oldData.getArtist(), performanceDto.getArtist());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "GENRE", oldData.getGenre(), performanceDto.getGenre());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "VENUE", oldData.getVenue(), performanceDto.getVenue());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "DURATION", oldData.getDuration(), performanceDto.getDuration());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "RATING", oldData.getRating(), performanceDto.getRating());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "DATE", oldData.getDate(), performanceDto.getDate());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "CONTENT", oldData.getContent(), performanceDto.getContent());
//            addPerformanceHist(performanceHistList, performanceDto.getPno(), modifierAId, "PRICE", String.valueOf(oldData.getPrice()), String.valueOf(performanceDto.getPrice()));
//
//
//            // 변경 이력이 담긴 DTO 리스트를 순회하면서
//            for (PerformanceHist performanceHist : performanceHistList) {
//                performanceHistRepository.save(performanceHist);
//            }
//            return true;
//
//        }
//        return false;
//    }
//

    @Override
    public void performanceModify(PerformanceDto performanceDto, Integer aId, Integer pno, MultipartFile imgFile) throws IOException {
        // 기존 공연 조회
        Performance oldPerformance = performanceRepository.findById(pno)
                .orElseThrow(() -> new IllegalArgumentException("해당 공연이 존재하지 않습니다"));

        PerformanceDto oldData = toDto(oldPerformance);

        // 관리자 존재 확인
        adminRepository.findById(aId)
                .orElseThrow(() -> new IllegalArgumentException("관리자 정보 없음"));

        // 이미지 변경이 없으면 기존 이미지 유지
        if (imgFile == null || imgFile.isEmpty()) {
            performanceDto.setImg(oldData.getImg());
        }

        // 기존 엔티티에 새 값 반영
        toEntity(oldPerformance, performanceDto, imgFile);
        performanceRepository.save(oldPerformance);

        // 변경 이력 저장
        List<PerformanceHist> performanceHistList = new ArrayList<>();
        addPerformanceHist(performanceHistList, pno, aId, "IMG", oldData.getImg(), performanceDto.getImg());
        addPerformanceHist(performanceHistList, pno, aId, "TITLE", oldData.getTitle(), performanceDto.getTitle());
        addPerformanceHist(performanceHistList, pno, aId, "ARTIST", oldData.getArtist(), performanceDto.getArtist());
        addPerformanceHist(performanceHistList, pno, aId, "GENRE", oldData.getGenre(), performanceDto.getGenre());
        addPerformanceHist(performanceHistList, pno, aId, "VENUE", oldData.getVenue(), performanceDto.getVenue());
        addPerformanceHist(performanceHistList, pno, aId, "DURATION", oldData.getDuration(), performanceDto.getDuration());
        addPerformanceHist(performanceHistList, pno, aId, "RATING", oldData.getRating(), performanceDto.getRating());
        addPerformanceHist(performanceHistList, pno, aId, "DATE", oldData.getDate(), performanceDto.getDate());
        addPerformanceHist(performanceHistList, pno, aId, "PRICE", String.valueOf(oldData.getPrice()), String.valueOf(performanceDto.getPrice()));
        addPerformanceHist(performanceHistList, pno, aId, "CONTENT", oldData.getContent(), performanceDto.getContent());

        for (PerformanceHist performanceHist : performanceHistList) {
            performanceHistRepository.save(performanceHist);
        }
    }


    private void addPerformanceHist(List<PerformanceHist> performanceHistList, Integer pno, Integer modifierAId,
                              String changeCode, String oldValue, String newValue) {
        if (!oldValue.equals(newValue)) {
            PerformanceHist performanceHist = new PerformanceHist();
            performanceHist.setPno(pno);
            performanceHist.setPCngCd(changeCode);
            performanceHist.setPBf(oldValue);
            performanceHist.setPAf(newValue);
            performanceHist.setCreatedBy(modifierAId);
            performanceHistList.add(performanceHist);

            System.out.println(">>> 변경 감지: " + changeCode + " / old: " + oldValue + " / new: " + newValue);
        }
    }

    @Override
    public PerformanceDto toDto(Performance performance) {
        return PerformanceDto.builder()
                .pno(performance.getPno())
                .title(performance.getTitle())
                .artist(performance.getArtist())
                .genre(performance.getGenre())
                .venue(performance.getVenue())
                .duration(performance.getDuration())
                .rating(performance.getRating())
                .date(performance.getDate())
                .content(performance.getContent())
                .img(performance.getImg())
                .price(performance.getPrice())
                .deleted(performance.getDeleted())
                .createdAt(performance.getCreatedAt())
                .createdBy(performance.getCreatedBy())
                .build();
    }

    @Override
    public void toEntity(Performance performance, PerformanceDto performanceDto, MultipartFile imgFile) throws IOException {

        if (performanceDto.getTitle() != null) {
            performance.setTitle(performanceDto.getTitle());
        }
        if (performanceDto.getArtist() != null) {
            performance.setArtist(performanceDto.getArtist());
        }
        if (performanceDto.getGenre() != null) {
            performance.setGenre(performanceDto.getGenre());
        }
        if (performanceDto.getVenue() != null) {
            performance.setVenue(performanceDto.getVenue());
        }
        if (performanceDto.getDuration() != null) {
            performance.setDuration(performanceDto.getDuration());
        }
        if (performanceDto.getRating() != null) {
            performance.setRating(performanceDto.getRating());
        }
        if (performanceDto.getDate() != null) {
            performance.setDate(performanceDto.getDate());
        }
        if (performanceDto.getContent() != null) {
            performance.setContent(performanceDto.getContent());
        }
        if (imgFile != null && !imgFile.isEmpty()) {
            String img = uploadImage(imgFile);
            performanceDto.setImg(img);
            performance.setImg(img);
        }
        if (performanceDto.getPrice() != null) {
            performance.setPrice(performanceDto.getPrice());
        }
        if (performanceDto.getDeleted() != null) {
            performance.setDeleted(performanceDto.getDeleted());
        }

    }


    @Override
    public boolean performanceRemove(Integer pno){
        Performance performance = performanceRepository.findById(pno)
                .orElseThrow(()-> new IllegalArgumentException("공연 데이터 없음"));

        performance.setDeleted(true);
        performanceRepository.save(performance);
        return true;
    }

    @Override
    public int getPriceByPno(Integer pno) {
        Performance performance = performanceRepository.findById(pno)
                .orElseThrow(() -> new IllegalArgumentException("공연을 찾을 수 없습니다."));
        return performance.getPrice();
    }

}
