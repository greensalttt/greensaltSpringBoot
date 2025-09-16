package com.example.greenspringboot.admin.controller;
import com.example.greenspringboot.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AdminApiController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public ResponseEntity<Map<String, Long>> getAdminStats(HttpServletRequest request) {
        Map<String, Long> stats = adminService.getAdminStats();
        return ResponseEntity.ok(stats);
    }

    @PostMapping("/admin/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

}

