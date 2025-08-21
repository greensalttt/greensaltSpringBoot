package com.example.greenspringboot.cust.service;

import com.example.greenspringboot.board.dto.BoardDto;
import com.example.greenspringboot.comment.dto.CommentDto;
import com.example.greenspringboot.cust.entity.Cust;
import com.example.greenspringboot.cust.dto.CustDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CustService {
    String emailCheck(String cEmail);

    String nickCheck(String cNick);

    Boolean save(Cust cust, HttpServletRequest request, @RequestParam("emailCode") String userInputCode);

    String joinEmail(String cEmail) throws Exception;
    String ResetEmail(String cEmail) throws Exception;

    Boolean forgotPwdCId(String cEmail, HttpServletRequest request);

    String pwdEncrypt(String cPwd);

    void validatePassword(String cPwd);

    Boolean login(String email, String rawPassword, HttpServletRequest request);

    boolean pwdChange(int cId, CustDto custDto, String curPwd);

    boolean forgotPwdChange(int cId, CustDto custDto);

    boolean custDrop(int cId, String dropPwd);


    CustDto toDto(Cust cust);

    void toEntity(Cust cust, CustDto custDto);

    CustDto toPwdDto(Cust cust);

    CustDto toPwdDto2(Cust cust);


    Cust toPwdEntity(Cust cust, CustDto custDto);

    void myPage(Integer cId, Model model);
     void myPageInfo(int cId, Model model);

    void custModify(int cId, CustDto custDto);


//    void myBoardList(Model m, Integer createdBy);


    List<BoardDto> findMyBoardList(Integer createdBy);

    List<CommentDto> findMyCommentList(Integer cId);
}
