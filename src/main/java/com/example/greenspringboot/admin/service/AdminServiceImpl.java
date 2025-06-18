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
import com.example.greenspringboot.comment.service.CommentService;
import com.example.greenspringboot.cust.dto.CustDto;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.repository.CustRepository;
import com.example.greenspringboot.performance.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
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
    private BoardHistRepository boardHistRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PerformanceRepository performanceRepository;

//    @Autowired
//    private CommentService commentService;


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

            long custCount = custRepository.count();
            long boardCount = boardRepository.count();
            long commentCount = commentRepository.count();
            long albumCount = albumRepository.count();

            model.addAttribute("custCount", custCount);
            model.addAttribute("boardCount", boardCount);
            model.addAttribute("commentCount", commentCount);
            model.addAttribute("albumCount", albumCount);

            return true;

    }

    @Override
    public void adminPage(Model m){
        long custCount = custRepository.countByStatCd("M");
        long boardCount = boardRepository.countByDeletedFalse();
        long commentCount = commentRepository.countByDeletedFalse();
        long albumCount = albumRepository.countByDeletedFalse();
        long performanceCount = performanceRepository.countByDeletedFalse();


        m.addAttribute("custCount", custCount);
        m.addAttribute("boardCount", boardCount);
        m.addAttribute("commentCount", commentCount);
        m.addAttribute("albumCount", albumCount);
        m.addAttribute("performanceCount", performanceCount);


        System.out.println("회원수:" + custCount);
        System.out.println("게시글수:" + boardCount);
    }


    @Override
    public void custList(Model m){
    List<Cust> custs = custRepository.findAll();

        if (!custs.isEmpty()) {
            List<CustDto> custDtos = custs.stream()
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

            m.addAttribute("custDtos", custDtos);
            System.out.println("custDtos:" + custDtos);


        } else {
            System.out.println("회원 정보를 찾을 수 없습니다.");
        }
    }


    @Override
    public void boardList(Model m) {
        List<Board> boards = boardRepository.findAllByDeletedFalseOrderByBnoDesc(); // 삭제되지 않은 게시글 전체 조회

        if (!boards.isEmpty()) {
            List<BoardDto> boardDtos = boards.stream()
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

            m.addAttribute("boardDtos", boardDtos); // 모델에 boardDtos 추가
        } else {
            System.out.println("게시판 정보를 찾을 수 없습니다.");
        }
    }

    @Override
    public boolean boardRemove(Integer bno, HttpSession session){
//        Integer aId = (Integer) session.getAttribute("aId");

        Board board = boardRepository.findByBno(bno);
        board.setDeleted(true);
        board.setUpdatedBy("admin");
//        board.setUpdatedByType("admin");
        boardRepository.save(board);
        return true;
    }


    @Override
    public void commentList(Model m) {
        List<Comment> comments = commentRepository.findAllByDeletedFalseOrderByCnoDesc(); // 삭제되지 않은 댓글 전체 조회

        if (!comments.isEmpty()) {
            List<CommentDto> commentDtos = comments.stream()
                    .map(comment -> CommentDto.builder()
                            .cno(comment.getCno())
                            .bno(comment.getBno())
                            .comment(comment.getComment())
                            .commenter(comment.getCommenter())
                            .createdAt(comment.getCreatedAt())
                            .deleted(comment.getDeleted())
                            .build())
                    .collect(Collectors.toList());

            m.addAttribute("commentDtos", commentDtos); // 모델에 commentDtos 추가
        } else {
            System.out.println("댓글 정보를 찾을 수 없습니다.");
        }
    }

//    @Override
//    public boolean commentRemove(Integer cno, HttpSession session){
//        Comment comment = commentRepository.findByCno(cno);
//        comment.setDeleted(true);
//        comment.setUpdatedBy("admin");
//        commentRepository.save(comment);
//        return true;
//    }

    @Override
    public boolean commentRemove(CommentDto commentDto, Integer cno, HttpSession session){
        Comment comment = commentRepository.findByCno(cno);
        comment.setDeleted(true);
        comment.setUpdatedBy("admin");
        commentRepository.save(comment);

        String oldValue = comment.getComment();  // 예를 들어, content 필드를 수정한다고 가정
        String newValue = "null";  // 수정된 댓글 내용
        String changeCode = "delete";  // 예를 들어, content 필드를 수정한다고 가정
        String createdBy = "admin";
        addCommentHist(commentDto, oldValue, newValue, changeCode, createdBy);
        return true;
    }

    private void addCommentHist(CommentDto commentDto, String oldValue, String newValue, String changeCode, String createdBy) {
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
            // 이력 저장
            commentHistRepository.save(commentHist);
            System.out.println("코멘트디티오 =" + commentDto);
        }}


    @Override
    public void boardHist(Model m) {
        List<BoardHist> boardHistList = boardHistRepository.findAll();

        List<BoardHistDto> boardHistDtoList = boardHistList.stream()
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

        m.addAttribute("boardHistList", boardHistDtoList);
    }

    @Override
    public void commentHist(Model m) {
        List<CommentHist> commentHistList = commentHistRepository.findAll();

        List<CommentHistDto> commentHistDtoList = commentHistList.stream()
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

        m.addAttribute("commentHistList", commentHistDtoList);
    }


}
