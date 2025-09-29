package com.example.greenspringboot.admin.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class SpaForwardController {

    @Autowired
    private ResourceLoader resourceLoader;

//    API가 아닌 Vue 경로만 설정, 8080으로 접속할때 다 처리해야함
    @RequestMapping(value = { "/admin", "/admin/login", "/admin/album","/admin/album/manage", "/admin/performance",
            "/admin/performance/manage","/admin/board/manage", "/admin/board/hist"
            , "/admin/comment/manage", "/admin/comment/hist", "/admin/notice", "/admin/notice/manage"
            , "/admin/cust/list", "/admin/cust/hist"})

    public ResponseEntity<Resource> forwardDashboard(HttpServletRequest request) throws IOException {
        String uri = request.getRequestURI();
        System.out.println("👉 요청 URI: " + uri);

        // 정적 리소스 요청이 오면, 해당 요청은 이 컨트롤러에서 처리하지 않도록 하여
        // Spring에서 자동으로 정적 리소스를 처리하도록 함.
        if (uri.matches(".+\\.(js|css|map|ico|png|jpg|svg|html)$") || uri.contains("favicon.ico")) {
            return ResponseEntity.notFound().build();
        }

        // /admin 경로에 대해서만 index.html 리소스를 읽어서 내려줌
        Resource resource = resourceLoader.getResource("classpath:/static/admin/index.html");

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(resource);
    }
}


