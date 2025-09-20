package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminApiController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Long>> getAdminStats() {
        Map<String, Long> stats = adminService.getAdminStats();
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/admin/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/album/write")
    public ResponseEntity<?> albumWrite(@ModelAttribute AlbumDto albumDto, HttpSession session) {

//        Vue 개발 환경일때 아니면 주석처리하기
        session.setAttribute("aId", 1);

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("테스트 아이디는 등록할 수 없습니다.");
        }
        albumService.write(albumDto, aId);
        return ResponseEntity.ok().build();
    }


//    앨범 리스트 가져오기
    @GetMapping("/albums")
    public ResponseEntity<?> getAlbumList() {
        List<AlbumDto> albumList = albumService.getAllAlbums();
        return ResponseEntity.ok(albumList);
    }

    @PostMapping("/performance/write")
    public ResponseEntity<?> performanceWrite(@ModelAttribute PerformanceDto performanceDto, HttpSession session) {

//        Vue 개발 환경일때 아니면 주석처리하기
        session.setAttribute("aId", 1);

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("테스트 아이디는 등록할 수 없습니다.");
        }
        performanceService.write(performanceDto, aId);
        return ResponseEntity.ok().build();
    }

}

