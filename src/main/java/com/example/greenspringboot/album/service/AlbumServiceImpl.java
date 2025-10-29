package com.example.greenspringboot.album.service;
import com.example.greenspringboot.admin.repository.AdminRepository;
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumHistRepository albumHistRepository;


    @Autowired
    private AdminRepository adminRepository;



    //    인덱스 화면에서 보여주기
    @Override
    public void albumList(Model m) {
        List<Album> albums = albumRepository.findAllByDeletedFalseOrderByAnoDesc(); // 모든 앨범 목록을 조회

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

        } else {
            System.out.println("앨범 정보를 찾을 수 없습니다.");
        }
    }

//    관리자에서 수정할때 앨범 목록 가져오기
    public List<AlbumDto> getAllAlbums() {
        List<Album> albums = albumRepository.findAllByDeletedFalseOrderByAnoDesc();

        return albums.stream()
                .map(album -> AlbumDto.builder()
                        .ano(album.getAno())
                        .title(album.getTitle())
                        .artist(album.getArtist())
                        .released(album.getReleased())
                        .img(album.getImg())
                        .build())
                .collect(Collectors.toList());
    }

//일반 앨범 상세페이지
    @Override
    public void albumRead(Integer ano, Model m) {
        Album album = albumRepository.findById(ano)
                .orElseThrow(() -> new IllegalArgumentException("앨범 정보 없음"));

        String content = album.getContent().replace("\n", "<br/>");
        AlbumDto albumDto = AlbumDto.builder()
                .ano(album.getAno())
                .type(album.getType())
                .genre(album.getGenre())
                .title(album.getTitle())
                .artist(album.getArtist())
                .released(album.getReleased())
                .content(content)
                .img(album.getImg())
                .build();

        m.addAttribute("albumDto", albumDto);
    }

//    관리자 페이지 앨범 수정 페이지
    @Override
    public AlbumDto albumEditRead(Integer ano) {
        Album album = albumRepository.findById(ano)
                .orElseThrow(() -> new IllegalArgumentException("앨범 정보 없음"));

        return AlbumDto.builder()
                .ano(album.getAno())
                .type(album.getType())
                .genre(album.getGenre())
                .title(album.getTitle())
                .artist(album.getArtist())
                .released(album.getReleased())
                .content(album.getContent())
                .img(album.getImg())
                .build();
    }


    @Override
    public void write(AlbumDto albumDto, Integer aId) {
        // 존재하는 관리자 확인
        adminRepository.findById(aId)
                .orElseThrow(() -> new IllegalArgumentException("관리자 정보 없음"));

        String img;
        try {
            img = uploadImage(albumDto.getImgFile());
        } catch (IOException e) {
            throw new IllegalArgumentException("이미지 업로드 실패", e);
        }

        Album album = Album.builder()
                .type(albumDto.getType())
                .genre(albumDto.getGenre())
                .title(albumDto.getTitle())
                .artist(albumDto.getArtist())
                .content(albumDto.getContent())
                .released(albumDto.getReleased())
                .img(img)
                .createdBy(aId)
                .updatedBy(aId)
                .build();

        albumRepository.save(album);
    }

    // 배포시 수정
    public String uploadImage(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) return null;

//        String uploadDir = "C:/album/";
        String uploadDir = "/home/ubuntu/greensalt/album/";

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
    public void albumRemove(Integer ano) {
        Album album = albumRepository.findById(ano)
                .orElseThrow(() -> new IllegalArgumentException("앨범 정보 없음"));

        album.setDeleted(true);
        albumRepository.save(album);
    }

    @Override
    public void albumModify(AlbumDto albumDto, Integer aId, Integer ano, MultipartFile imgFile) throws IOException{
        // 기존 앨범 가져오기 (예외 처리 방식 개선)
        Album oldAlbum = albumRepository.findById(ano)
                .orElseThrow(() -> new IllegalArgumentException("해당 앨범이 존재하지 않습니다"));

        AlbumDto oldData = toDto(oldAlbum);

        adminRepository.findById(aId)
                .orElseThrow(() -> new IllegalArgumentException("관리자 정보 없음"));


        // 이미지 변경 안 했으면 이전 값 유지
        if (imgFile == null || imgFile.isEmpty()) {
            albumDto.setImg(oldData.getImg());
        }

        // 기존 엔티티에 새 값 반영
        toEntity(oldAlbum, albumDto, imgFile);
        albumRepository.save(oldAlbum);

        // 변경 이력 저장
        List<AlbumHist> albumHistList = new ArrayList<>();
        addAlbumHist(albumHistList, ano, aId, "IMG", oldData.getImg(), albumDto.getImg());
        addAlbumHist(albumHistList, ano, aId, "TITLE", oldData.getTitle(), albumDto.getTitle());
        addAlbumHist(albumHistList, ano, aId, "ARTIST", oldData.getArtist(), albumDto.getArtist());
        addAlbumHist(albumHistList, ano, aId, "TYPE", oldData.getType(), albumDto.getType());
        addAlbumHist(albumHistList, ano, aId, "GENRE", oldData.getGenre(), albumDto.getGenre());
        addAlbumHist(albumHistList, ano, aId, "RELEASED", oldData.getReleased(), albumDto.getReleased());
        addAlbumHist(albumHistList, ano, aId, "CONTENT", oldData.getContent(), albumDto.getContent());

        for (AlbumHist albumHist : albumHistList) {
            albumHistRepository.save(albumHist);
        }
    }


    private void addAlbumHist(List<AlbumHist> albumHistList, Integer ano, Integer aId,
                              String changeCode, String oldValue, String newValue) {
        if (!oldValue.equals(newValue)) {
            AlbumHist albumHist = new AlbumHist();
            albumHist.setAno(ano);
            albumHist.setACngCd(changeCode);
            albumHist.setABf(oldValue);
            albumHist.setAAf(newValue);
            albumHist.setCreatedBy(aId);
            albumHistList.add(albumHist);

            System.out.println(">>> 변경 감지: " + changeCode + " / old: " + oldValue + " / new: " + newValue);
        }
    }

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
                        .type(album.getType())
                        .genre(album.getGenre())
                        .title(album.getTitle())
                        .artist(album.getArtist())
                        .content(album.getContent())
                        .img(album.getImg())
                        .released(album.getReleased())
                        .deleted(album.getDeleted())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public AlbumDto toDto(Album album) {
        return AlbumDto.builder()
                .ano(album.getAno())
                .type(album.getType())
                .genre(album.getGenre())
                .title(album.getTitle())
                .artist(album.getArtist())
                .content(album.getContent())
                .img(album.getImg())
                .released(album.getReleased())
                .deleted(album.getDeleted())
                .createdBy(album.getCreatedBy())
                .build();
    }


    @Override
    public void toEntity(Album album, AlbumDto albumDto, MultipartFile imgFile) throws IOException {

        if (albumDto.getTitle() != null) {
            album.setTitle(albumDto.getTitle());
        }
        if (albumDto.getArtist() != null) {
            album.setArtist(albumDto.getArtist());
        }
        if (albumDto.getType() != null) {
            album.setType(albumDto.getType());
        }
        if (albumDto.getGenre() != null) {
            album.setGenre(albumDto.getGenre());
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



