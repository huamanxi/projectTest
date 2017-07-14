package cn.jane.mapper;

import cn.jane.entity.Blog;
import cn.jane.entity.User;

import java.util.*;
public interface UserMapper extends SqlMapper {
	public void addBlog(Blog blog);
	public ArrayList<Blog> queryBlog(String phone_num);
	public Blog findBlog(String title);
	public Blog findBlogByd(String description);
	public void updateCount(String title);
	public ArrayList<Blog> byState(int state);
	public void changeState(String blog_id);
	public void deleteBlog(String blog_id);
	
	public void register(User user);
	public ArrayList<String> all_num();
	public User findPass(String phone_num);
	
}
