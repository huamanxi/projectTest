package cn.jane.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jane.entity.Blog;
import cn.jane.mapper.UserMapper;
import cn.jane.service.BlogService;
@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private UserMapper um;
	@Override
	public void addBlog(Blog blog) {
		um.addBlog(blog);
	}
	@Override
	public ArrayList<Blog> queryBlog(String phone_num) {
		List<Blog> list = new ArrayList<Blog>();
		list = um.queryBlog(phone_num);
		return (ArrayList<Blog>) list;
	}
	@Override
	public Blog findBlog(String title) {
		Blog blog = new Blog();
		blog = um.findBlog(title);
		return blog;
	}
	@Override
	public Blog findBlogByd(String description) {
		Blog blog = new Blog();
		blog = um.findBlogByd(description);
		return blog;
	}
	@Override
	public void updateCount(String title) {
		um.updateCount(title);
	}
	@Override
	public ArrayList<Blog> byState(int state) {
		// TODO Auto-generated method stub
		ArrayList<Blog> list = new ArrayList<Blog>();
		list = um.byState(state);
		return list;
	}
	@Override
	public void changeState(String blog_id) {
		// TODO Auto-generated method stub
		um.changeState(blog_id);
	}
	@Override
	public void deleteBlog(String blog_id) {
		// TODO Auto-generated method stub
		um.deleteBlog(blog_id);
	}

}
