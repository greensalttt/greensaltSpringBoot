package com.example.greenspringboot.performance.service;
import com.example.greenspringboot.board.paging.SearchCondition12;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.entity.Performance;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PerformanceServiceImpl implements PerformanceService{

    @Autowired
    private PerformanceRepository performanceRepository;

    @Override
    public void performanceRead(Integer pno, Model m) {
        Optional<Performance> optionalPerformance = performanceRepository.findByPno(pno);

        if (optionalPerformance.isPresent()) {
            Performance performance = optionalPerformance.get();

            Integer ppno = performance.getPno();
            String title = performance.getTitle();
            String artist = performance.getArtist();
            String region = performance.getRegion();
            String genre = performance.getGenre();
            String venue = performance.getVenue();
            String duration = performance.getDuration();
            String rating = performance.getRating();
            String date = performance.getDate();
            String content = performance.getContent() != null ? performance.getContent().replace("\n", "<br/>") : null;
            String img = performance.getImg();

            PerformanceDto performanceDto = PerformanceDto.builder()
                    .pno(ppno)
                    .title(title)
                    .artist(artist)
                    .region(region)
                    .genre(genre)
                    .venue(venue)
                    .duration(duration)
                    .rating(rating)
                    .date(date)
                    .content(content)
                    .img(img)
                    .build();

            m.addAttribute("performanceDto", performanceDto);

            System.out.println("performanceDto:" + performanceDto);

        } else {
            System.out.println("공연 정보를 찾을 수 없습니다.");
        }
    }


    @Override
    public boolean write(PerformanceDto performanceDto, HttpSession session) {
        try {

            MultipartFile file = performanceDto.getImgFile();
            String img = uploadImage(file);

            performanceDto.setImg(img); // 이미지 경로를 저장할 img 필드에 넣음
            performanceDto.setAId((Integer) session.getAttribute("aId"));

            Performance performance = Performance.builder()
                    .aId(performanceDto.getAId())
                    .title(performanceDto.getTitle())
                    .artist(performanceDto.getArtist())
                    .region(performanceDto.getRegion())
                    .genre(performanceDto.getGenre())
                    .venue(performanceDto.getVenue())
                    .duration(performanceDto.getDuration())
                    .rating(performanceDto.getRating())
                    .date(performanceDto.getDate())
                    .content(performanceDto.getContent())
                    .img(performanceDto.getImg())
                    .build();

            performanceRepository.save(performance);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String uploadDir = "C:/performance/";


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
                        .region(performance.getRegion())
                        .genre(performance.getGenre())
                        .venue(performance.getVenue())
                        .duration(performance.getDuration())
                        .rating(performance.getRating())
                        .date(performance.getDate())
                        .content(performance.getContent())
                        .img(performance.getImg())
                        .deleted(performance.getDeleted())
                        .regDt(performance.getRegDt())
                        .upDt(performance.getUpDt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void performanceList(Model m) {
        List<Performance> performances = performanceRepository.findAllByOrderByPnoDesc(); // 모든 앨범 목록을 조회
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




}
