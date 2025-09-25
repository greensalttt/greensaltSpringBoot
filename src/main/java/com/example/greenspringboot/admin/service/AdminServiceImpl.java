package com.example.greenspringboot.admin.service;
import com.example.greenspringboot.admin.entity.Admin;
import com.example.greenspringboot.admin.repository.AdminRepository;
import com.example.greenspringboot.album.repository.AlbumRepository;
import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.board.dto.BoardHistDto;
import com.example.greenspringboot.board.entity.Board;
import com.example.greenspringboot.board.entity.BoardHist;
import com.example.greenspringboot.board.repository.BoardHistRepository;
import com.example.greenspringboot.board.repository.BoardRepository;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.comment.dto.CommentHistDto;
import com.example.greenspringboot.comment.entity.Comment;
import com.example.greenspringboot.comment.entity.CommentHist;
import com.example.greenspringboot.comment.repository.CommentHistRepository;
import com.example.greenspringboot.comment.repository.CommentRepository;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.dto.CustHistDto;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.entity.CustHist;
import com.example.greenspringboot.cust.repository.CustHistRepository;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.notice.dto.NoticeDto;
import com.example.greenspringboot.notice.entity.Notice;
import com.example.greenspringboot.notice.repository.NoticeRepository;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustRepository custRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentHistRepository commentHistRepository;

    @Autowired
    private CustHistRepository custHistRepository;

    @Autowired
    private BoardHistRepository boardHistRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private NoticeRepository noticeRepository;


    @Override
    public Boolean adminLogin(String aLoginId, String aPwd, HttpServletRequest request, Model model) {
        System.out.println("관리자 로그인 아이디: " + aLoginId);

        Admin admin = adminRepository.findByaLoginId(aLoginId);

        if (admin == null) {
            return false;
        }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (!encoder.matches(aPwd, admin.getAPwd())) {
                return false;
            }

            HttpSession session = request.getSession();
            session.setAttribute("aId", admin.getAId());

            return true;
    }

    @Override
    public Map<String, Long> getAdminStats() {

        long custCount = custRepository.countByStatCd("M");
        long boardCount = boardRepository.countByDeletedFalse();
        long commentCount = commentRepository.countByDeletedFalse();
        long albumCount = albumRepository.countByDeletedFalse();
        long performanceCount = performanceRepository.countByDeletedFalse();

        Map<String, Long> stats = new HashMap<>();
        stats.put("custCount", custCount);
        stats.put("boardCount", boardCount);
        stats.put("commentCount", commentCount);
        stats.put("albumCount", albumCount);
        stats.put("performanceCount", performanceCount);

        return stats;
    }

//    @Override
//    public void adminPage(Model m){
//        long custCount = custRepository.countByStatCd("M");
//        long boardCount = boardRepository.countByDeletedFalse();
//        long commentCount = commentRepository.countByDeletedFalse();
//        long albumCount = albumRepository.countByDeletedFalse();
//        long performanceCount = performanceRepository.countByDeletedFalse();
//
//
//        m.addAttribute("custCount", custCount);
//        m.addAttribute("boardCount", boardCount);
//        m.addAttribute("commentCount", commentCount);
//        m.addAttribute("albumCount", albumCount);
//        m.addAttribute("performanceCount", performanceCount);
//
//
//        System.out.println("회원수:" + custCount);
//        System.out.println("게시글수:" + boardCount);
//    }
//

//    @Override
//    public void custList(Model m){
//    List<Cust> custs = custRepository.findAll();
//
//        if (!custs.isEmpty()) {
//            List<CustDto> custDtos = custs.stream()
//                    .map(cust -> CustDto.builder()
//                            .cId(cust.getCId())
//                            .statCd(cust.getStatCd())
//                            .cEmail(cust.getCEmail())
//                            .cNick(cust.getCNick())
//                            .cZip(cust.getCZip())
//                            .cRoadA(cust.getCRoadA())
//                            .cJibunA(cust.getCJibunA())
//                            .cDetA(cust.getCDetA())
//                            .loginDt(cust.getLoginDt())
//                            .createdAt(cust.getCreatedAt())
//                            .build())
//                    .collect(Collectors.toList());
//
//            m.addAttribute("custDtos", custDtos);
//            System.out.println("custDtos:" + custDtos);
//
//
//        } else {
//            System.out.println("회원 정보를 찾을 수 없습니다.");
//        }
//    }


//    관리자 페이지 게시글
//    @Override
//    public void boardList(Model m) {
//        List<Board> boards = boardRepository.findAllByDeletedFalseOrderByBnoDesc(); // 삭제되지 않은 게시글 전체 조회
//
//        if (!boards.isEmpty()) {
//            List<BoardDto> boardDtos = boards.stream()
//                    .map(board -> BoardDto.builder()
//                            .bno(board.getBno())
//                            .title(board.getTitle())
//                            .content(board.getContent())
//                            .writer(board.getWriter())
//                            .viewCnt(board.getViewCnt())
//                            .commentCnt(board.getCommentCnt())
//                            .deleted(board.getDeleted())
//                            .createdAt(board.getCreatedAt())
//                            .createdBy(board.getCreatedBy())
//                            .build())
//                    .collect(Collectors.toList());
//
//            m.addAttribute("boardDtos", boardDtos); // 모델에 boardDtos 추가
//        } else {
//            System.out.println("게시판 정보를 찾을 수 없습니다.");
//        }
//    }

    @Override
    public List<BoardDto> boardList() {
        List<Board> boards = boardRepository.findAllByDeletedFalseOrderByBnoDesc();

        return boards.stream()
                .map(board -> BoardDto.builder()
                        .bno(board.getBno())
                        .title(board.getTitle())
                        .content(board.getContent())
                        .writer(board.getWriter())
                        .viewCnt(board.getViewCnt())
                        .commentCnt(board.getCommentCnt())
                        .deleted(board.getDeleted())
                        .createdAt(board.getCreatedAt())
                        .createdBy(board.getCreatedBy())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean adminRemove(Integer bno) {
        Board board = boardRepository.findByBno(bno);

        board.setDeleted(true);
        board.setUpdatedBy("admin");
        boardRepository.save(board);

        Integer boardCreatedBy = board.getCreatedBy();
        String oldValue = "제목: " + board.getTitle() + "\n내용: " + board.getContent();
        String newValue = "null";
        String changeCode = "DELETE";
        String createdBy = "admin";

        if (!oldValue.equals(newValue)) {
            BoardHist boardHist = BoardHist.builder()
                    .bno(board.getBno())
                    .cId(boardCreatedBy) // 여기 중요!
                    .bCngCd(changeCode)
                    .bBf(oldValue)
                    .bAf(newValue)
                    .createdBy(createdBy)
                    .build();

            boardHistRepository.save(boardHist);
        }

        return true;
    }


//    @Override
//    public boolean adminRemove(BoardDto boardDto, Integer bno){
//        Board board = boardRepository.findByBno(bno);
//        board.setDeleted(true);
//        board.setUpdatedBy("admin");
//        boardRepository.save(board);
//
//        String oldValue = "제목: " + board.getTitle() + "\n내용: " + board.getContent();
//        String newValue = "null";
//        String changeCode = "DELETE";
//        String createdBy = "admin";
//
//
//        if (!oldValue.equals(newValue)) {
//            BoardHist boardHist = BoardHist.builder()
//                    .bno(boardDto.getBno())
//                    .cId(boardDto.getCreatedBy())
//                    .bCngCd(changeCode)
//                    .bBf(oldValue)
//                    .bAf(newValue)
//                    .createdBy(createdBy)
//                    .build();
//            boardHistRepository.save(boardHist);
//        }
//
//        return true;
//    }
//


    //    관리자페이지 댓글
//    @Override
//    public void commentList(Model m) {
//        List<Comment> comments = commentRepository.findAllByDeletedFalseOrderByCnoDesc(); // 삭제되지 않은 댓글 전체 조회
//
//        if (!comments.isEmpty()) {
//            List<CommentDto> commentDtos = comments.stream()
//                    .map(comment -> CommentDto.builder()
//                            .cno(comment.getCno())
//                            .bno(comment.getBno())
//                            .comment(comment.getComment())
//                            .commenter(comment.getCommenter())
//                            .createdAt(comment.getCreatedAt())
//                            .createdBy(comment.getCreatedBy())
//                            .deleted(comment.getDeleted())
//                            .build())
//                    .collect(Collectors.toList());
//
//            m.addAttribute("commentDtos", commentDtos); // 모델에 commentDtos 추가
//        } else {
//            System.out.println("댓글 정보를 찾을 수 없습니다.");
//        }
//    }

    @Override
    public List<CommentDto> commentList() {
        List<Comment> comments = commentRepository.findAllByDeletedFalseOrderByCnoDesc();

        return comments.stream()
                .map(comment -> CommentDto.builder()
                        .cno(comment.getCno())
                        .bno(comment.getBno())
                        .comment(comment.getComment())
                        .commenter(comment.getCommenter())
                        .createdAt(comment.getCreatedAt())
                        .createdBy(comment.getCreatedBy())
                        .deleted(comment.getDeleted())
                        .build())
                .collect(Collectors.toList());
    }


//    @Override
//    public boolean commentRemove(CommentDto commentDto, Integer cno){
//        Comment comment = commentRepository.findByCno(cno);
//        comment.setDeleted(true);
//        comment.setUpdatedBy("admin");
//        commentRepository.save(comment);
//
//        String oldValue = comment.getComment();  // 예를 들어, content 필드를 수정한다고 가정
//        String newValue = "null";  // 수정된 댓글 내용
//        String changeCode = "DELETE";  // 예를 들어, content 필드를 수정한다고 가정
//        String createdBy = "admin";
//        addCommentHist(commentDto, oldValue, newValue, changeCode, createdBy);
//        return true;
//    }
//
//    private void addCommentHist(CommentDto commentDto, String oldValue, String newValue, String changeCode, String createdBy) {
//        if (!oldValue.equals(newValue)) {
//            CommentHist commentHist = CommentHist.builder()
//                    .cno(commentDto.getCno())
//                    .bno(commentDto.getBno())
//                    .cId(commentDto.getCreatedBy())
//                    .coCngCd(changeCode)
//                    .coBf(oldValue)
//                    .coAf(newValue)
//                    .createdBy(createdBy)
//                    .build();
//            // 이력 저장
//            commentHistRepository.save(commentHist);
//            System.out.println("코멘트디티오 =" + commentDto);
//        }}


    @Override
    public boolean commentRemove(CommentDto commentDto, Integer cno) {
        Comment comment = commentRepository.findByCno(cno);
        comment.setDeleted(true);
        comment.setUpdatedBy("admin");
        commentRepository.save(comment);

        String oldValue = comment.getComment();   // 기존 댓글 내용
        String newValue = "null";                 // 삭제된 상태를 나타내는 값
        String changeCode = "DELETE";             // 변경 코드
        String createdBy = "admin";

        if (!oldValue.equals(newValue)) {
            CommentHist commentHist = CommentHist.builder()
                    .cno(commentDto.getCno())
                    .bno(commentDto.getBno())
                    .cId(commentDto.getCreatedBy())
                    .coCngCd(changeCode)
                    .coBf(oldValue)
                    .coAf(newValue)
                    .createdBy(createdBy)
                    .build();
            commentHistRepository.save(commentHist);
            System.out.println("코멘트디티오 = " + commentDto);
        }

        return true;
    }


    @Override
    public List<BoardHistDto> boardHist() {
        List<BoardHist> boardHistList = boardHistRepository.findAll();

        return boardHistList.stream()
                .map(boardHist -> BoardHistDto.builder()
                        .bHistNum(boardHist.getBHistNum())
                        .bno(boardHist.getBno())
                        .cId(boardHist.getCId())
                        .bCngCd(boardHist.getBCngCd())
                        .bBf(boardHist.getBBf())
                        .bAf(boardHist.getBAf())
                        .createdAt(boardHist.getCreatedAt())
                        .createdBy(boardHist.getCreatedBy())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentHistDto> commentHist() {
        List<CommentHist> commentHistList = commentHistRepository.findAll();

        return commentHistList.stream()
                .map(commentHist -> CommentHistDto.builder()
                        .coHistNum(commentHist.getCoHistNum())
                        .cno(commentHist.getCno())
                        .bno(commentHist.getBno())
                        .cId(commentHist.getCId())
                        .coCngCd(commentHist.getCoCngCd())
                        .coBf(commentHist.getCoBf())
                        .coAf(commentHist.getCoAf())
                        .createdAt(commentHist.getCreatedAt())
                        .createdBy(commentHist.getCreatedBy())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CustHistDto> custHist() {
        List<CustHist> custHistList = custHistRepository.findAll();

        return custHistList.stream()
                .map(custHist -> CustHistDto.builder()
                        .cHistNum(custHist.getCHistNum())
                        .cId(custHist.getCId())
                        .cCngCd(custHist.getCCngCd())
                        .cBf(custHist.getCBf())
                        .cAf(custHist.getCAf())
                        .createdAt(custHist.getCreatedAt())
                        .createdBy(custHist.getCreatedBy())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CustDto> custList() {
        List<Cust> custs = custRepository.findAll();

        return custs.stream()
                .map(cust -> CustDto.builder()
                        .cId(cust.getCId())
                        .statCd(cust.getStatCd())
                        .cEmail(cust.getCEmail())
                        .cNick(cust.getCNick())
                        .cZip(cust.getCZip())
                        .cRoadA(cust.getCRoadA())
                        .cJibunA(cust.getCJibunA())
                        .cDetA(cust.getCDetA())
                        .loginDt(cust.getLoginDt())
                        .createdAt(cust.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
    }

//
//    @Override
//    public List<NoticeDto> noticeList() {
//        List<Notice> notices = noticeRepository.findAllByDeletedFalseOrderByNnoDesc();
//
//        return notices.stream()
//                .map(notice -> NoticeDto.builder()
//                        .nno(notice.getNno())
//                        .title(notice.getTitle())
//                        .content(notice.getContent())
//                        .writer(notice.getWriter())
//                        .viewCnt(notice.getViewCnt())
//                        .createdAt(notice.getCreatedAt())
//                        .createdBy(notice.getCreatedBy())
//                        .deleted(notice.getDeleted())
//                        .build())
//                .collect(Collectors.toList());
//    }




//    @Override
//    public void commentHist(Model m) {
//        List<CommentHist> commentHistList = commentHistRepository.findAll();
//
//        List<CommentHistDto> commentHistDtoList = commentHistList.stream()
//                .map(commentHist -> CommentHistDto.builder()
//                        .coHistNum(commentHist.getCoHistNum())
//                        .cno(commentHist.getCno())
//                        .bno(commentHist.getBno())
//                        .cId(commentHist.getCId())
//                        .coCngCd(commentHist.getCoCngCd())
//                        .coBf(commentHist.getCoBf())
//                        .coAf(commentHist.getCoAf())
//                        .createdAt(commentHist.getCreatedAt())
//                        .createdBy(commentHist.getCreatedBy())
//                        .build())
//                .collect(Collectors.toList());
//
//        m.addAttribute("commentHistList", commentHistDtoList);
//    }
//
//    @Override
//    public void custHist(Model m) {
//        List<CustHist> custHistList = custHistRepository.findAll();
//
//        List<CustHistDto> custHistDtoList = custHistList.stream()
//                .map(custHist -> CustHistDto.builder()
//                        .cHistNum(custHist.getCHistNum())
//                        .cId(custHist.getCId())
//                        .cCngCd(custHist.getCCngCd())
//                        .cBf(custHist.getCBf())
//                        .cAf(custHist.getCAf())
//                        .createdAt(custHist.getCreatedAt())
//                        .createdBy(custHist.getCreatedBy())
//                        .build())
//                .collect(Collectors.toList());
//
//        m.addAttribute("custHistList", custHistDtoList);
//    }
//
//
//    @Override
//    public void noticeList(Model m) {
//        List<Notice> notices = noticeRepository.findAllByDeletedFalseOrderByNnoDesc();
//
//        if (!notices.isEmpty()) {
//            List<NoticeDto> noticeDtos = notices.stream()
//                    .map(notice -> NoticeDto.builder()
//                            .nno(notice.getNno())
//                            .title(notice.getTitle())
//                            .content(notice.getContent())
//                            .writer(notice.getWriter())
//                            .viewCnt(notice.getViewCnt())
//                            .createdAt(notice.getCreatedAt())
//                            .createdBy(notice.getCreatedBy())
//                            .deleted(notice.getDeleted())
//                            .build())
//                    .collect(Collectors.toList());
//
//            m.addAttribute("noticeDtos", noticeDtos); // 모델에 commentDtos 추가
//        } else {
//            System.out.println("공지사항을 찾을 수 없습니다.");
//        }
//    }

}
