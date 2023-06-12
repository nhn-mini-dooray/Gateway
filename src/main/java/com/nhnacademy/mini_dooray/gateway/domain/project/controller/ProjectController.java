package com.nhnacademy.mini_dooray.gateway.domain.project.controller;

import com.nhnacademy.mini_dooray.gateway.auth.model.GatewayUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {

    @GetMapping
    public String projectList(@AuthenticationPrincipal GatewayUser user, Model model) {
        model.addAttribute("loginId", user.getUsername());
        return "project";
    }
}
