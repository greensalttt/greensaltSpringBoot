package com.example.greenspringboot.album.service;

import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.entity.Album;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.entity.Cust;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService{

    @Autowired
    private AlbumRepository albumRepository;

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
    public boolean write(AlbumDto albumDto, MultipartFile imgFile) {
        try {

            MultipartFile file = albumDto.getImgFile();
            String img = uploadImage(file);
            albumDto.setImg(img); // 이미지 경로를 저장할 img 필드에 넣음

            Album album = Album.builder()
                    .ano(albumDto.getAno())
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

}



