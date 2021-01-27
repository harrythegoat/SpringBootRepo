package com.convertium.blog.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.convertium.blog.entity.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {

	// that's it ... no need to write any code LOL!

	@Modifying
	@Transactional
	@Query(value = "UPDATE blog_posts bp SET bp.post_status = ?2 WHERE bp.id = ?1", nativeQuery = true)
	void setStatusForPublished(int id, String postStatus);
	
	@Modifying
	@Transactional
	@Query(value = "SELECT * FROM blog_posts bp WHERE bp.username = ?1 ORDER BY ID DESC", nativeQuery = true)
	List<Content> findAllByName(String theUsername);
	
//	@Modifying
//	@Transactional
//	@Query(value = "SELECT * FROM blog_posts bp WHERE bp.username = ?1 ORDER BY ID DESC", nativeQuery = true)
//	Page<Content> findAllByNamePageable(String theUsername, Pageable pageable);

}
