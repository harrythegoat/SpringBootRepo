package com.convertium.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.convertium.blog.dao.ContentRepository;
import com.convertium.blog.entity.Content;

@Service
public class ContentServiceImpl implements ContentService {

	private ContentRepository contentRepository;

	@Autowired
	public ContentServiceImpl(ContentRepository theContentRepository) {
		contentRepository = theContentRepository;
	}

	@Override
	public List<Content> findAll() {
		return contentRepository.findAll();
	}

	@Override
	public Content findById(int theId) {
		Optional<Content> result = contentRepository.findById(theId);

		Content theContent = null;

		if (result.isPresent()) {
			theContent = result.get();
		} else {
			// we didn't find the employee
			throw new RuntimeException("Did not find content id - " + theId);
		}

		return theContent;
	}

	@Override
	public void save(Content theContent) {
		contentRepository.save(theContent);
	}

	@Override
	public void deleteById(int theId) {
		contentRepository.deleteById(theId);
	}

	@Override
	public void updateStatus(int theId, String theStatus) {
		contentRepository.setStatusForPublished(theId, theStatus);
	}

	@Override
	public Page<Content> findPaginated(int pageNo, int pageSize) {

		Sort sortById = Sort.by("id").descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sortById);

		return this.contentRepository.findAll(pageable);
	}
	
	@Override
	public List<Content> findAllByName(String theUsername){
		return contentRepository.findAllByName(theUsername);
	}
	
//	@Override
//	public Page<Content> findPaginatedByNamePageable(String theUsername, int pageNo, int pageSize) {
//
//		Sort sortById = Sort.by("id").descending();
//
//		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sortById);
//
//		return this.contentRepository.findAllByNamePageable(theUsername, pageable);
//	}
	
	

}
