package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import com.example.greenspringboot.album.dto.AlbumDto;
import com.example.greenspringboot.album.service.AlbumService;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.dto.BoardHistDto;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.dto.CommentHistDto;
import com.example.greenspringboot.config.JwtUtil;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.dto.CustHistDto;
import com.example.greenspringboot.notice.dto.NoticeDto;
import com.example.greenspringboot.notice.service.NoticeService;
import com.example.greenspringboot.performance.dto.PerformanceDto;
import com.example.greenspringboot.performance.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/admin/login")
    public ResponseEntity<?> apiAdminLogin(@RequestBody Map<String, String> loginData) {
        String aLoginId = loginData.get("aLoginId");
        String aPwd = loginData.get("aPwd");

        Integer adminId = adminService.adminLogin(aLoginId, aPwd);

        if (adminId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다."));
        }

        String token = jwtUtil.generateToken(adminId); // ✅ 토큰 생성

        return ResponseEntity.ok(Map.of(
                "message", "로그인 성공",
                "token", token
        ));
    }


//    @PostMapping("/admin/login")
//    public ResponseEntity<?> apiAdminLogin(@RequestBody Map<String, String> loginData) {
//        String aLoginId = loginData.get("aLoginId");
//        String aPwd = loginData.get("aPwd");
//
//        boolean success = adminService.adminLogin(aLoginId, aPwd);
//
//        if (!success) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body("로그인 실패: 아이디 또는 비밀번호가 올바르지 않습니다.");
//        }
//
//        return ResponseEntity.ok("로그인 성공");
//    }

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
//        session.setAttribute("aId", 1);

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

    @GetMapping("/album/{ano}/edit")
    public ResponseEntity<?> getAlbum(@PathVariable Integer ano) {
        AlbumDto albumDto = albumService.albumEditRead(ano);
        return ResponseEntity.ok(albumDto);
    }

    @PutMapping("/album/{ano}/edit")
    public ResponseEntity<?> albumModify(@PathVariable Integer ano, @ModelAttribute AlbumDto albumDto, MultipartFile imgFile, HttpSession session)throws IOException {

        //        Vue 개발 환경일때 아니면 주석처리하기
//        session.setAttribute("aId", 1);

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("수정 권한이 없습니다.");
        }

        albumService.albumModify(albumDto, aId, ano, imgFile);
        return ResponseEntity.ok("앨범 수정 완료");
    }


    @DeleteMapping("/album/{ano}/remove")
    public ResponseEntity<?> albumRemove(@PathVariable Integer ano, HttpSession session) {

        // Vue 개발 환경일때 아니면 주석처리
//        session.setAttribute("aId", 1);
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("삭제 권한이 없습니다.");
        }

        albumService.albumRemove(ano);
        return ResponseEntity.ok("앨범 삭제 완료");
    }


    @PostMapping("/performance/write")
    public ResponseEntity<?> performanceWrite(@ModelAttribute PerformanceDto performanceDto, HttpSession session) {

//        Vue 개발 환경일때 아니면 주석처리하기
//        session.setAttribute("aId", 1);


        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("테스트 아이디는 등록할 수 없습니다.");
        }
        performanceService.write(performanceDto, aId);
        return ResponseEntity.ok().build();
    }

    //    공연 리스트 가져오기
    @GetMapping("/performances")
    public ResponseEntity<?> getPerformanceList() {
        List<PerformanceDto> performanceList = performanceService.getAllPerformances();
        return ResponseEntity.ok(performanceList);
    }

    // 공연 상세 조회
    @GetMapping("/performance/{pno}/edit")
    public ResponseEntity<?> getPerformance(@PathVariable Integer pno) {
        PerformanceDto performanceDto = performanceService.performanceEditRead(pno);
        return ResponseEntity.ok(performanceDto);
    }

    // 공연 수정
    @PutMapping("/performance/{pno}/edit")
    public ResponseEntity<?> performanceModify(@PathVariable Integer pno,
                                               @ModelAttribute PerformanceDto performanceDto,
                                               MultipartFile imgFile,
                                               HttpSession session) throws IOException {
        // Vue 개발 환경일 경우, 임시 aId 세팅
//        session.setAttribute("aId", 1);
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("수정 권한이 없습니다.");
        }

        performanceService.performanceModify(performanceDto, aId, pno, imgFile);
        return ResponseEntity.ok("공연 수정 완료");
    }

    // 공연 삭제
    @DeleteMapping("/performance/{pno}/remove")
    public ResponseEntity<?> performanceRemove(@PathVariable Integer pno, HttpSession session) {
        // Vue 개발 환경일 경우, 임시 aId 세팅
//        session.setAttribute("aId", 1);
        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("삭제 권한이 없습니다.");
        }

        performanceService.performanceRemove(pno);
        return ResponseEntity.ok("공연 삭제 완료");
    }

    @GetMapping("/board/manage")
    public ResponseEntity<?> boardManage(){
        List<BoardDto> boardDtoList = adminService.boardList();
        return ResponseEntity.ok(boardDtoList);
    }

    @DeleteMapping("/board/{bno}/remove")
    public ResponseEntity<?> boardRemove(@PathVariable Integer bno, HttpSession session) {

//        session.setAttribute("aId", 1);

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("테스트 아이디는 삭제할 수 없습니다.");
        }

        adminService.adminRemove(bno);
        return ResponseEntity.ok("게시글 삭제 완료");
    }

    @GetMapping("/board/hist")
    public ResponseEntity<List<BoardHistDto>> boardHistories() {
        List<BoardHistDto> boardHistDto = adminService.boardHist();
        return ResponseEntity.ok(boardHistDto);
    }

    // 댓글 전체 조회 (관리용)
    @GetMapping("/comments/manage")
    public ResponseEntity<List<CommentDto>> commentManage() {
        List<CommentDto> commentDtoList = adminService.commentList();
        return ResponseEntity.ok(commentDtoList);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{cno}/remove")
    public ResponseEntity<String> commentRemove(@PathVariable Integer cno, @RequestBody CommentDto commentDto, HttpSession session) {
//        session.setAttribute("aId", 1);

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("테스트 아이디는 삭제할 수 없습니다.");
        }

        adminService.commentRemove(commentDto, cno);
        return ResponseEntity.ok("댓글 삭제 완료");
    }

    // 댓글 히스토리 조회
    @GetMapping("/comments/hist")
    public ResponseEntity<List<CommentHistDto>> commentHistory() {
        List<CommentHistDto> commentHistList = adminService.commentHist(); // Model 제거된 버전 사용
        return ResponseEntity.ok(commentHistList);
    }


    // 회원 전체 조회 (관리용)
    @GetMapping("/cust/list")
    public ResponseEntity<List<CustDto>> custList() {
        List<CustDto> custList = adminService.custList(); // Model 없이 List 반환하도록 서비스 수정 필요
        return ResponseEntity.ok(custList);
    }

    // 회원 이력 조회
    @GetMapping("/cust/hist")
    public ResponseEntity<List<CustHistDto>> custHistory() {
        List<CustHistDto> custHistList = adminService.custHist(); // Model 없이 List 반환하도록 서비스 수정 필요
        return ResponseEntity.ok(custHistList);
    }

    @PostMapping("/notice/write")
    public ResponseEntity<?> noticeWrite(@RequestBody NoticeDto noticeDto, HttpSession session) {
//        session.setAttribute("aId", 1); // Vue 개발 시 테스트용

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("등록 권한이 없습니다.");
        }

        noticeService.write(noticeDto, session, aId);
        return ResponseEntity.ok("공지사항 등록 완료");
    }

    @GetMapping("/notice/manage")
    public ResponseEntity<List<NoticeDto>> noticeManage() {
        List<NoticeDto> noticeList = noticeService.noticeList(); // 서비스 수정 필요
        return ResponseEntity.ok(noticeList);
    }

    @DeleteMapping("/notice/{nno}/remove")
    public ResponseEntity<?> noticeRemove(@PathVariable Integer nno, HttpSession session) {
//        session.setAttribute("aId", 1); // Vue 개발 시 테스트용

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("삭제 권한이 없습니다.");
        }

        noticeService.noticeRemove(nno);
        return ResponseEntity.ok("공지사항 삭제 완료");
    }

    @GetMapping("/notice/{nno}/edit")
    public ResponseEntity<NoticeDto> getNotice(@PathVariable Integer nno) {
        NoticeDto noticeDto = noticeService.noticeEditRead(nno); // 서비스에서 NoticeDto 반환하도록 수정
        return ResponseEntity.ok(noticeDto);
    }

    @PutMapping("/notice/{nno}/edit")
    public ResponseEntity<?> noticeModify(@PathVariable Integer nno,
                                          @RequestBody NoticeDto noticeDto,
                                          HttpSession session) {
//        session.setAttribute("aId", 1); // Vue 개발 시 테스트용

        Integer aId = (Integer) session.getAttribute("aId");

        if (aId == null || aId != 1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("수정 권한이 없습니다.");
        }

        noticeService.modify(nno, noticeDto);
        return ResponseEntity.ok("공지사항 수정 완료");
    }


}


