package com.example.hrms.biz.request.controller;

import com.example.hrms.biz.request.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  // Dùng @Controller thay vì @RestController nếu trả về view
@RequestMapping("/api/v1/requests")
public class RequestController {

    @RequestMapping("")
    public String openRequestView(Model model) {
        model.addAttribute("request", new Request());
        return "requests";
    }
}
