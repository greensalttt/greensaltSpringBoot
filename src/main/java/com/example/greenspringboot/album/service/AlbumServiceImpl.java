package com.example.greenspringboot.album.service;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.entity.Album;
import com.example.greenspringboot.album.entity.AlbumHist;
import com.example.greenspringboot.album.repository.AlbumHistRepository;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.board.paging.SearchCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumHistRepository albumHistRepository;

    @Override
    public void albumList(Model m) {
        List<Album> albums = albumRepository.findAllByOrderByAnoDesc(); // 모든 앨범 목록을 조회

        if (!albums.isEmpty()) {
            List<AlbumDto> albumDtos = albums.stream() // 앨범을 DTO로 변환
                    .map(album -> AlbumDto.builder()
                            .ano(album.getAno())
                            .title(album.getTitle())
                            .artist(album.getArtist())
                            .img(album.getImg())
                            .released(album.getReleased())
                            .build())
                    .collect(Collectors.toList());

            m.addAttribute("albumDtos", albumDtos); // 모델에 albumDtos 추가

            System.out.println("albumDtos: " + albumDtos);

        } else {
            System.out.println("앨범 정보를 찾을 수 없습니다.");
        }
    }


    @Override
    public void albumRead(Integer ano, Model m) {
        Optional<Album> optionalAlbum = albumRepository.findByAno(ano);

        if (optionalAlbum.isPresent()) {
            Album album = optionalAlbum.get();

            Integer aano = album.getAno();
            String type = album.getType();
            String genre = album.getGenre();
            String title = album.getTitle();
            String artist = album.getArtist();
            String released = album.getReleased();
            String content = album.getContent().replace("\n", "<br/>");
            String img = album.getImg();


            AlbumDto albumDto = AlbumDto.builder()
                    .ano(aano)
                    .type(type)
                    .genre(genre)
                    .title(title)
                    .artist(artist)
                    .released(released)
                    .content(content)
                    .img(img)
                    .build();

            m.addAttribute("albumDto", albumDto);

            System.out.println("albumDto:" + albumDto);

        } else {
            System.out.println("앨범 정보를 찾을 수 없습니다.");
        }
    }

    @Override
    public boolean write(AlbumDto albumDto, HttpSession session) {
        try {

            MultipartFile file = albumDto.getImgFile();
            String img = uploadImage(file);

            albumDto.setImg(img); // 이미지 경로를 저장할 img 필드에 넣음
            albumDto.setAId((Integer) session.getAttribute("aId"));

            Album album = Album.builder()
                    .ano(albumDto.getAno())
                    .aId(albumDto.getAId())
                    .domestic(albumDto.getDomestic())
                    .type(albumDto.getType())
                    .genre(albumDto.getGenre())
                    .title(albumDto.getTitle())
                    .artist(albumDto.getArtist())
                    .content(albumDto.getContent())
                    .released(albumDto.getReleased())
                    .img(albumDto.getImg())
                    .build();

            albumRepository.save(album);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

        String uploadDir = "C:/album/";


        String originalFilename = file.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String newFileName = uuid + "_" + originalFilename;

        File destination = new File(uploadDir + newFileName);
        file.transferTo(destination);

        return "/images/" + newFileName;
    }

    @Override
    public boolean albumRemove(Integer ano){
        albumRepository.deleteById(ano);
        return true;
    }

//    @Override
//    public boolean albumModify(AlbumDto albumDto, MultipartFile imgFile) {
//        try {
//            // 기존 앨범 가져오기
//            Optional<Album> optionalAlbum = albumRepository.findById(albumDto.getAno());
//            if (!optionalAlbum.isPresent()) {
//                return false; // 존재하지 않으면 false
//            }
//
//            Album album = optionalAlbum.get();
//
//            // 새 이미지가 업로드 되었을 경우
//            if (imgFile != null && !imgFile.isEmpty()) {
//                String img = uploadImage(imgFile);
//                album.setImg(img);
//            }
//
//            // 각 필드 null 체크 후 업데이트
//            if (albumDto.getTitle() != null) album.setTitle(albumDto.getTitle());
//            if (albumDto.getArtist() != null) album.setArtist(albumDto.getArtist());
//            if (albumDto.getType() != null) album.setType(albumDto.getType());
//            if (albumDto.getGenre() != null) album.setGenre(albumDto.getGenre());
//            if (albumDto.getReleased() != null) album.setReleased(albumDto.getReleased());
//            if (albumDto.getContent() != null) album.setContent(albumDto.getContent());
//            if (albumDto.getDomestic() != null) album.setDomestic(albumDto.getDomestic());
//
//            albumRepository.save(album);
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    @Override
    public List<AlbumDto> getSearchResultPage(SearchCondition sc) {
        // 게시글 조회 메서드, 게시글 목록 조회니까 그 게시글의 작성자도 같이 가져와야되는게 아닐까?
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        Sort sort = Sort.by(Sort.Order.desc("ano")); // 등록일 기준으로 내림차순 정렬
        List<Album> albumList;

        if ("title".equals(option)) {
            albumList = albumRepository.findByTitleContainingAndDeletedFalse(keyword, sort);
        } else if ("artist".equals(option)) {
            albumList = albumRepository.findByArtistContainingAndDeletedFalse(keyword, sort);
        } else {
            albumList = albumRepository.findByTitleContainingOrArtistContainingAndDeletedFalse(keyword, sort);
        }
        // 검색된 게시글 리스트를 페이지에 맞게 잘라서 반환
        int startIdx = (sc.getPage() - 1) * sc.getPageSize();
        int endIdx = Math.min(startIdx + sc.getPageSize(), albumList.size());

        List<Album> pagedAlbumList = albumList.subList(startIdx, endIdx);

        // DTO로 변환해서 반환
        return toDtoList(pagedAlbumList);
    }

    @Override
    public int getSearchResultCnt(SearchCondition sc) {
        // SearchCondition에서 'keyword'와 'option'을 풀어서 사용
        String keyword = sc.getKeyword();
        String option = sc.getOption();

        if ("title".equals(option)) {
            return albumRepository.countByTitleContainingAndDeletedFalse(keyword);
        } else if ("artist".equals(option)) {
            return albumRepository.countByArtistContainingAndDeletedFalse(keyword);
        } else {
            return albumRepository.countByTitleContainingOrArtistContainingAndDeletedFalse(keyword, keyword);
        }
    }


    // 여러개 게시글 한번에
    @Override
    public List<AlbumDto> toDtoList(List<Album> albumList) {
        return albumList.stream()
                .map(album -> AlbumDto.builder()
                        .ano(album.getAno())
                        .domestic(album.getDomestic())
                        .type(album.getType())
                        .genre(album.getGenre())
                        .title(album.getTitle())
                        .artist(album.getArtist())
                        .content(album.getContent())
                        .img(album.getImg())
                        .released(album.getReleased())
                        .deleted(album.getDeleted())
                        .regDt(album.getRegDt())
                        .upDt(album.getUpDt())
                        .build())
                .collect(Collectors.toList());
    }


//    이게 표본
//    @Override
//    public boolean albumModify(AlbumDto albumDto, MultipartFile imgFile, AlbumDto oldData) {
//        try {
//            // 기존 앨범 가져오기
//            Optional<Album> optionalAlbum = albumRepository.findById(albumDto.getAno());
//            if (!optionalAlbum.isPresent()) {
//                return false; // 존재하지 않으면 false
//            }
//
//            Album album = optionalAlbum.get();
//
//            // 새 이미지가 업로드 되었을 경우
//            if (imgFile != null && !imgFile.isEmpty()) {
//                String img = uploadImage(imgFile);
////                album.setImg(img);
//                albumDto.setImg(img);
//            }

//            // 각 필드 null 체크 후 업데이트
//            if (albumDto.getImg() != null) album.setImg(albumDto.getImg());
//            if (albumDto.getTitle() != null) album.setTitle(albumDto.getTitle());
//            if (albumDto.getArtist() != null) album.setArtist(albumDto.getArtist());
//            if (albumDto.getType() != null) album.setType(albumDto.getType());
//            if (albumDto.getGenre() != null) album.setGenre(albumDto.getGenre());
//            if (albumDto.getReleased() != null) album.setReleased(albumDto.getReleased());
//            if (albumDto.getContent() != null) album.setContent(albumDto.getContent());
//            if (albumDto.getDomestic() != null) album.setDomestic(albumDto.getDomestic());
//
//            albumRepository.save(album);
//
//            // 변경 이력을 담을 DTO 리스트
//            List<AlbumHistDto> albumHistDtoList = new ArrayList<>();
//
//            // 기존에 작성된 2개 필드 변경 이력 추가
//            addAlbumHistDto(albumHistDtoList, albumDto, "IMG", oldData.getImg(), albumDto.getImg());
//            addAlbumHistDto(albumHistDtoList, albumDto, "TITLE", oldData.getTitle(), albumDto.getTitle());
//            addAlbumHistDto(albumHistDtoList, albumDto, "ARTIST", oldData.getArtist(), albumDto.getArtist());
//            addAlbumHistDto(albumHistDtoList, albumDto, "TYPE", oldData.getType(), albumDto.getType());
//            addAlbumHistDto(albumHistDtoList, albumDto, "GENRE", oldData.getGenre(), albumDto.getGenre());
//            addAlbumHistDto(albumHistDtoList, albumDto, "RELEASED", oldData.getReleased(), albumDto.getReleased());
//            addAlbumHistDto(albumHistDtoList, albumDto, "CONTENT", oldData.getContent(), albumDto.getContent());
//            addAlbumHistDto(albumHistDtoList, albumDto, "DOMESTIC", oldData.getDomestic(), albumDto.getDomestic());
//
//
//            // 변경 이력이 담긴 DTO 리스트를 순회하면서
//            for (AlbumHistDto albumHistDto : albumHistDtoList) {
//
////          addAlbumHistDto에 설정한 dto를 엔티티로 옮기기
//                AlbumHist albumHist = new AlbumHist();
//                albumHist.setAno(albumHistDto.getAno());
//                albumHist.setAId(albumHistDto.getAId());
//                albumHist.setACngCd(albumHistDto.getACngCd());
//                albumHist.setABf(albumHistDto.getABf());
//                albumHist.setAAf(albumHistDto.getAAf());
//
//                // 이력 저장
//                albumHistRepository.save(albumHist);
//            }
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


    @Override
    public boolean albumModify(AlbumDto albumDto, MultipartFile imgFile, HttpSession session) throws IOException {
            // 기존 앨범 가져오기
            Optional<Album> optionalAlbum = albumRepository.findById(albumDto.getAno());
            if (optionalAlbum.isPresent()) {
                Album oldAlbum = optionalAlbum.get();
                AlbumDto oldData = toDto(oldAlbum);

//                이력에는 수정한 사람의 관리자 아이디가 입력
                Integer modifierAId = (Integer) session.getAttribute("aId");

                // 이미지 변경 안 했으면 이전 값 유지
                if (imgFile == null || imgFile.isEmpty()) {
                    albumDto.setImg(oldData.getImg());
                }

                toEntity(oldAlbum, albumDto, imgFile);
                albumRepository.save(oldAlbum);

            // 변경 이력을 담을 DTO 리스트
            List<AlbumHist> albumHistList = new ArrayList<>();
            // 기존에 작성된 2개 필드 변경 이력 추가

            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "IMG", oldData.getImg(), albumDto.getImg());
            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "TITLE", oldData.getTitle(), albumDto.getTitle());
            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "ARTIST", oldData.getArtist(), albumDto.getArtist());
            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "TYPE", oldData.getType(), albumDto.getType());
            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "GENRE", oldData.getGenre(), albumDto.getGenre());
            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "RELEASED", oldData.getReleased(), albumDto.getReleased());
            addAlbumHist(albumHistList, albumDto.getAno(), modifierAId, "CONTENT", oldData.getContent(), albumDto.getContent());



                // 변경 이력이 담긴 DTO 리스트를 순회하면서
            for (AlbumHist albumHist : albumHistList) {
                albumHistRepository.save(albumHist);
            }
            return true;

        }
            return false;
        }


    private void addAlbumHist(List<AlbumHist> albumHistList, Integer ano, Integer modifierAId,
                              String changeCode, String oldValue, String newValue) {
        if (!oldValue.equals(newValue)) {
            AlbumHist albumHist = new AlbumHist();
            albumHist.setAno(ano);               // 앨범 번호
            albumHist.setAId(modifierAId);       // 수정자 ID
            albumHist.setACngCd(changeCode);     // 변경 항목
            albumHist.setABf(oldValue);          // 변경 전
            albumHist.setAAf(newValue);          // 변경 후
            albumHistList.add(albumHist);

            System.out.println(">>> 변경 감지: " + changeCode + " / old: " + oldValue + " / new: " + newValue);
        }
    }


    @Override
    public AlbumDto toDto(Album album) {
        return AlbumDto.builder()
                .ano(album.getAno())
                .aId(album.getAId())
                .domestic(album.getDomestic())
                .type(album.getType())
                .genre(album.getGenre())
                .title(album.getTitle())
                .artist(album.getArtist())
                .content(album.getContent())
                .img(album.getImg())
                .released(album.getReleased())
                .deleted(album.getDeleted())
                .regDt(album.getRegDt())
                .upDt(album.getUpDt())
                .build();
    }


    @Override
    public void toEntity(Album album, AlbumDto albumDto, MultipartFile imgFile)  throws IOException{

        if (albumDto.getTitle() != null) {
            album.setTitle(albumDto.getTitle());
        }
        if (albumDto.getDomestic() != null) {
            album.setDomestic(albumDto.getDomestic());
        }
        if (albumDto.getType() != null) {
            album.setType(albumDto.getType());
        }
        if (albumDto.getGenre() != null) {
            album.setGenre(albumDto.getGenre());
        }
        if (albumDto.getArtist() != null) {
            album.setArtist(albumDto.getArtist());
        }
        if (albumDto.getContent() != null) {
            album.setContent(albumDto.getContent());
        }
        if (imgFile != null && !imgFile.isEmpty()) {
            String img = uploadImage(imgFile);
            albumDto.setImg(img);   // DTO에 반영 (이력 비교용)
            album.setImg(img);      // 엔티티에도 반영 (DB 저장용)
        }
        if (albumDto.getReleased() != null) {
            album.setReleased(albumDto.getReleased());
        }
        if (albumDto.getDeleted() != null) {
            album.setDeleted(albumDto.getDeleted());
        }
    }



}



