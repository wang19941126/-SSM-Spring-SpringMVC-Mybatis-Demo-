package com.nuo.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nuo.model.User;
import com.nuo.service.UserService;

@Controller
@RequestMapping
public class UserController {
	@Resource
	private UserService userService;

	@RequestMapping("/getUserById.action")
	public String getUserById(HttpServletRequest request, Model model) {
		User user = userService.getUserById(Integer.parseInt(request.getParameter("id")));
		model.addAttribute("user", user);

		return "success";

	}

	@RequestMapping("/getAllUser.action")
	public String getAllUser(Model model) {
		List<User> list = userService.getAllUser();
		model.addAttribute("user", list);
		return "successsample";
	}

	@RequestMapping("/getSample.action")
	public String getSample(HttpServletRequest request, Model model) {
		List<User> list = userService.getSample(request.getParameter("PatientId"));
		model.addAttribute("user", list);
		return "successsample";
	}

}
