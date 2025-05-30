package com.example.greenspringboot.album.service;

import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.entity.Album;
import com.example.greenspringboot.board.paging.SearchCondition;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public interface AlbumService {

    void albumList(Model m);

    void albumRead(Integer ano, Model m);

//    boolean write(AlbumDto albumDto, MultipartFile imgFile);

    boolean write(AlbumDto albumDto, HttpSession session);

    boolean albumRemove(Integer ano);

    // 여러개 게시글 한번에
    List<AlbumDto> toDtoList(List<Album> albumList);

//    boolean albumModify(AlbumDto albumDto, MultipartFile imgFile, AlbumDto oldData);

    List<AlbumDto> getSearchResultPage(SearchCondition sc);

    int getSearchResultCnt(SearchCondition sc);

    boolean albumModify(AlbumDto albumDto, MultipartFile imgFile, HttpSession session) throws IOException;

    AlbumDto toDto(Album album);


    void toEntity(Album album, AlbumDto albumDto, MultipartFile imgFile)  throws IOException;




    }
