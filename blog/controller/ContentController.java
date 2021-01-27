package com.convertium.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.convertium.blog.entity.Content;
import com.convertium.blog.service.ContentService;

@Controller
@RequestMapping("/contents")
public class ContentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ContentController.class);

	private ContentService contentService;

	public ContentController(ContentService theContentService) {
		contentService = theContentService;
	}

	@GetMapping("/adminDashboard/{pageNo}")
	public String listContent(@PathVariable(value = "pageNo") int pageNo, Model theModel) {

		int pageSize = 5;

		Page<Content> page = contentService.findPaginated(pageNo, pageSize);
		List<Content> theContentModel = page.getContent();

		theModel.addAttribute("currentPage", pageNo);
		theModel.addAttribute("totalPages", page.getTotalPages());
		theModel.addAttribute("totalItems", page.getTotalElements());
		theModel.addAttribute("contents", theContentModel);
		return "content";
	}
	
	@GetMapping("/userDashboard")
	public String listContentUser(@ModelAttribute("contents") Content theContent, Model theModel) {
		List<Content> theContentModel = contentService.findAllByName(SecurityContextHolder.getContext().getAuthentication().getName());
		theModel.addAttribute("contents", theContentModel);
		return "content_user";
	}
	
//	@GetMapping("/userDashboard/{pageNo}")
//	public String listContentUser(@PathVariable(value = "pageNo") int pageNo, Model theModel) {
//		int pageSize = 5;
//		Page<Content> page = contentService.findPaginatedByNamePageable(SecurityContextHolder.getContext().getAuthentication().getName(), pageNo, pageSize);
//		List<Content> theContentModel = page.getContent();
//		theModel.addAttribute("currentPage", pageNo);
//		theModel.addAttribute("totalPages", page.getTotalPages());
//		theModel.addAttribute("totalItems", page.getTotalElements());
//		theModel.addAttribute("contents", theContentModel);
//		return "content_user";
//	}

	@GetMapping("/{id}")
	public String getContentById(@PathVariable int id, Model theModel) {

		Content theContentModel = contentService.findById(id);

		theModel.addAttribute("contents", theContentModel);

		return "content";

	}

}