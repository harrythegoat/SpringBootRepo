package com.convertium.blog.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="blog_posts")
public class Content {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="post_title")
	private String postTitle;
	
	@Column(name="post_content")
	private String postContent;	
	
	@Column(name="post_status")
	private String postStatus;
	
	@Column(name="post_banner")
	private Blob postBanner;
	
	@Column(name="username")
	private String username;
		
	// define constructors
	
	public Content() {
		
	}

	
	public Content(int id, String postStatus) {
		this.id = id;
		this.postStatus = postStatus;
	}

	
	public Content(int id, String postTitle, String postContent, String postStatus, Blob postBanner, String username) {
		this.id = id;
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.postStatus = postStatus;
		this.postBanner = postBanner;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	
	public String getPostStatus() {
		return postStatus;
	}

	public void setPostStatus(String postStatus) {
		this.postStatus = postStatus;
	}
	
	public Blob getPostBanner() {
		return postBanner;
	}

	public void setPostBanner(Blob postBanner) {
		this.postBanner = postBanner;
	}
	

	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	@Override
	public String toString() {
		return "Content [id=" + id + ", postTitle=" + postTitle + ", postContent=" + postContent + ", postStatus="
				+ postStatus + ", postBanner=" + postBanner + ", username=" + username + "]";
	}

	
	
		
}











