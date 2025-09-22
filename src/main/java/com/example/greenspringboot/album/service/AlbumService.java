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

//    void albumRead(Integer ano, Model m);

    AlbumDto albumRead(Integer ano);

    void write(AlbumDto albumDto, Integer aId);

    void albumRemove(Integer ano);

    // 여러개 게시글 한번에
    List<AlbumDto> toDtoList(List<Album> albumList);

    List<AlbumDto> getSearchResultPage(SearchCondition sc);

    int getSearchResultCnt(SearchCondition sc);

    void albumModify(AlbumDto albumDto, Integer aId, Integer ano, MultipartFile imgFile, HttpSession session) throws IOException;

    AlbumDto toDto(Album album);

    void toEntity(Album album, AlbumDto albumDto, MultipartFile imgFile) throws IOException;

    List<AlbumDto> getAllAlbums();
}
