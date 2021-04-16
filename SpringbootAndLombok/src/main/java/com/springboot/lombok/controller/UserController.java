package com.springboot.lombok.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


import com.springboot.lombok.dto.UserInfoDto;
import com.springboot.lombok.service.UserService;


@RequiredArgsConstructor
@Controller
public class UserController {

	  private final UserService userService;
	  

	  @GetMapping(value = "/login")
	  public String loginPage(HttpServletRequest request, HttpServletResponse response) {
	    //new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
	    //return "redirect:/login";
	    return "thymeleaf/login";
	  }
	  
	  @GetMapping(value = "/loginmain")
	  public String loginMain(HttpServletRequest request, HttpServletResponse response) {
	    //new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
	    //return "redirect:/login";
	    return "thymeleaf/main";
	  }	  
	  
	  
	  @GetMapping(value = "/signup")
	  public String signupPage(HttpServletRequest request, HttpServletResponse response) {
	    //new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
	    //return "redirect:/login";
	    return "thymeleaf/signup";
	  }		  
	  
	
	  @PostMapping("/user")
	  public String signup(UserInfoDto infoDto) { // 회원 추가
	    userService.save(infoDto);
	    return "redirect:/login";
	    //return "thymeleaf/login";
	  }

	  @GetMapping(value = "/logout")
	  public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
	    new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
	    return "redirect:/login";
	    //return "thymeleaf/login";
	  }	
	
}
