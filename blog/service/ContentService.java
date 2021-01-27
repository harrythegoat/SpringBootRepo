package com.convertium.blog.service;

import java.util.Base64;
import java.util.List;

import org.springframework.data.domain.Page;

import com.convertium.blog.entity.Content;

public interface ContentService {

	public List<Content> findAll();
	
	public Content findById(int theId);
	
	public void save(Content theContent);
	
	public void deleteById(int theId);
	
	public void updateStatus(int theId, String theStatus);
	
	Page<Content> findPaginated(int pageNo, int pageSize);
	
	public List<Content> findAllByName(String theUsername);
	
//	Page<Content> findPaginatedByNamePageable(String theUsername, int pageNo, int pageSize);
}
