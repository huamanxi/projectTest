package cn.jane.service;

//import org.springframework.stereotype.Service;

import cn.jane.entity.Blog;

import java.util.*;

import org.springframework.stereotype.Service;

public interface BlogService {
	public void addBlog(Blog blog);
	public ArrayList<Blog> queryBlog(String phone_num); 
	public Blog findBlog(String title);
	public Blog findBlogByd(String description);
	public void updateCount(String title);
	public ArrayList<Blog> byState(int state);
	public void changeState(String blog_id);
	public void deleteBlog(String blog_id);
}
