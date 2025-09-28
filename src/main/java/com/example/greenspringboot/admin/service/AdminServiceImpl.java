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
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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


//    @Override
//    public Boolean adminLogin(String aLoginId, String aPwd, HttpServletRequest request, Model model) {
//        System.out.println("관리자 로그인 아이디: " + aLoginId);
//
//        Admin admin = adminRepository.findByaLoginId(aLoginId);
//
//        if (admin == null) {
//            return false;
//        }
//
//            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//            if (!encoder.matches(aPwd, admin.getAPwd())) {
//                return false;
//            }
//
//            HttpSession session = request.getSession();
//            session.setAttribute("aId", admin.getAId());
//
//            return true;
//    }

//    @Override
//    public Boolean adminLogin(String aLoginId, String aPwd) {
//        System.out.println("관리자 로그인 아이디: " + aLoginId);
//
//        Admin admin = adminRepository.findByaLoginId(aLoginId);
//
//        if (admin == null) {
//            return false;
//        }
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (!encoder.matches(aPwd, admin.getAPwd())) {
//            return false;
//        }
//        return true;
//    }

    // adminId 리턴하도록 수정
    @Override
    public Integer adminLogin(String aLoginId, String aPwd) {
        Admin admin = adminRepository.findByaLoginId(aLoginId);
        if (admin == null) return null;

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(aPwd, admin.getAPwd())) return null;

        return admin.getAId(); // 로그인 성공 시 adminId 반환
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

}
