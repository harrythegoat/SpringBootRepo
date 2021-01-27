package com.convertium.blog.controller;

import java.util.Base64;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.convertium.blog.entity.Content;
import com.convertium.blog.handler.CustomImageHandler;
import com.convertium.blog.service.ContentService;


@Controller
@RequestMapping("/article")
public class ArticleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

	@Autowired
	private ContentService contentService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/articleNew")
	public String showMyLoginPage(Model theModel) {

		theModel.addAttribute("contents", new Content());

		return "article";
	}

	@GetMapping("/visit")
	public String getArticleById(@RequestParam("id") int theId, Model theModel) {
		
		LOGGER.debug("SEARCHING FOR" + theId);

		Content theContentModel = contentService.findById(theId);

		theModel.addAttribute("contents", theContentModel);

		return "articleById";
	}

	@PostMapping("/articleProcess")
	public String processArticleForm(@ModelAttribute("contents") Content theContent, BindingResult theBindingResult,
			Model theModel) {
		
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
		
		String redirectUrl = null;

		if (theBindingResult.hasErrors()) {
			LOGGER.debug(theBindingResult.toString());
		}
		
		theContent.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		contentService.save(theContent);

		List<Content> theContentModel = contentService.findAll();

		theModel.addAttribute("contents", theContentModel);
		
		if(SecurityContextHolder.getContext().getAuthentication().getName().equals("admin")) {
			redirectUrl = "redirect:/contents/adminDashboard/1";
		}else {
			redirectUrl = "redirect:/contents/userDashboard";
		}
		return redirectUrl;
	}

	@GetMapping("/deleteProcess")
	public String deleteArticle(@RequestParam("id") int theId) {
		

		contentService.deleteById(theId);

		return "redirect:/contents/adminDashboard/1";
	}
	
	
	@GetMapping("/publishedStatus")
	public String publishedStatus(@RequestParam("id") int theId, @RequestParam("pageNo") int pageNo) {

		contentService.updateStatus(theId, "PUBLISH");
		
		return "redirect:/contents/adminDashboard/" + pageNo;
	}
	
	@GetMapping("/rejectedStatus")
	public String rejectedStatus(@RequestParam("id") int theId, @RequestParam("pageNo") int pageNo) {

		contentService.updateStatus(theId, "REJECTED");

		return "redirect:/contents/adminDashboard/" + pageNo;
	}
	
	@GetMapping("/draftStatus")
	public String draftStatus(@RequestParam("id") int theId, @RequestParam("pageNo") int pageNo) {

		contentService.updateStatus(theId, "DRAFT");

		return "redirect:/contents/adminDashboard/" + pageNo;
	}

}
