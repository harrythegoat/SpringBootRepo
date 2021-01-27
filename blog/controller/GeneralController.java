package com.convertium.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GeneralController {

	@GetMapping("/home")
	public String homePage(Model theModel) {
		theModel.addAttribute("theDate", new java.util.Date());
		return "home";
	}
}
