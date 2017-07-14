package cn.jane.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jane.entity.Blog;
import cn.jane.entity.User;
import cn.jane.service.BlogService;

@Controller
@RequestMapping("/blog")
public class BlogController {
	
	@Autowired
	private BlogService bs;
	
	@RequestMapping("/write")
	public String registerPage(HttpServletRequest request){
		return "WEB-INF/page/write";
	}
	@RequestMapping("personal")
	public String registerPage5(HttpServletRequest request){
		ArrayList<Blog> list = new ArrayList<Blog>();
		list = bs.byState(1);
		request.setAttribute("list", list);
		return "personal";
	}
	@RequestMapping("/introduce")
	public String registerPage1(HttpServletRequest request){
		return "WEB-INF/page/introduce";
	}
	@RequestMapping("/bloglist")
	public String registerPage2(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute("user");
		String phone_num = user.getPhone_num();
		int allcount=0;
		List<cn.jane.entity.Blog> list = new  ArrayList<cn.jane.entity.Blog>();
		list = bs.queryBlog(phone_num);
		
		
		Iterator<cn.jane.entity.Blog> it = list.iterator();
		while(it.hasNext()){
			cn.jane.entity.Blog blog = it.next();
			allcount+=blog.getCount();
			System.out.println(blog.getCount());
		}
		System.out.println(allcount+"luhan;uhan");
		request.setAttribute("list", list);
		request.setAttribute("allcount",allcount);
		return "WEB-INF/page/bloglist";
	}
	@RequestMapping("/blogcontext")
	public String registerPage3(HttpServletRequest request){
		return "WEB-INF/page/blogcontext";
	}
	@RequestMapping("/addblog")
	public String addblog(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		User user=(User) request.getSession().getAttribute("user");
		String phone_num = user.getPhone_num();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat();
		String blog_date = s.format(date);
		String description = content.substring(0, 15);
		cn.jane.entity.Blog blog = new cn.jane.entity.Blog();
//		blog.setBlog_id(UUID.randomUUID().toString());
		blog.setTitle(title);
		blog.setPhone_num(phone_num);
		blog.setDescription(description);
		blog.setContent(content);
		blog.setBlog_date(blog_date);
		blog.setState(-1);
		bs.addBlog(blog);
		
		ArrayList<Blog> list = new ArrayList<Blog>();
		list = bs.byState(1);
		request.setAttribute("list", list);
		return "WEB-INF/page/personal";
	}
	@RequestMapping("/titlefind")
	public String titlefind(HttpServletRequest request) throws UnsupportedEncodingException{
		String title = request.getParameter("title");
//		title = new String(title.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(title);
		cn.jane.entity.Blog blog = new cn.jane.entity.Blog();
		cn.jane.entity.Blog bloge = new cn.jane.entity.Blog();
		blog = bs.findBlog(title);
		//blog.setCount(blog.getCount()+1);
		bs.updateCount(blog.getTitle());
		bloge = bs.findBlog(title);
		request.setAttribute("blog", bloge);
		return "WEB-INF/page/blogcontext";
	}
	@RequestMapping("/findBlogByd")
	public String findBlogByd(HttpServletRequest request) throws UnsupportedEncodingException{
		String description = request.getParameter("blog_abstract");
		description = new String(description.getBytes("ISO-8859-1"),"UTF-8");
		cn.jane.entity.Blog blog = new cn.jane.entity.Blog();
		cn.jane.entity.Blog bloge = new cn.jane.entity.Blog();
		//System.out.println("dddddddddd");
		blog = bs.findBlogByd(description);
		bs.updateCount(blog.getTitle());
		bloge = bs.findBlogByd(description);
		//System.out.println("ydhsjk");
		//System.out.println(blog.getCount()+"liu8y7tgyhjkl;");
		//blog.setCount(blog.getCount()+1);
		
		request.setAttribute("blog", bloge);
		return "WEB-INF/page/blogcontext";
	}
	@RequestMapping("/changeState")
	public String changeState(HttpServletRequest request) throws UnsupportedEncodingException{
		String blog_id = request.getParameter("blog_id");
		
		bs.changeState(blog_id);
		System.out.println("鍗氬id涓�"+blog_id);
		
		ArrayList<Blog> list = new ArrayList<Blog>();
		ArrayList<Blog> ulist = new ArrayList<Blog>();
		list = bs.byState(1);
		request.setAttribute("list", list);
		ulist = bs.byState(-1);
		request.setAttribute("ulist", ulist);
		return "WEB-INF/page/manager";
	}
	@RequestMapping("/deleteBlog")
	public String deleteBlog(HttpServletRequest request) throws UnsupportedEncodingException{
		String blog_id = request.getParameter("blog_id");
		bs.deleteBlog(blog_id);
		ArrayList<Blog> list = new ArrayList<Blog>();
		ArrayList<Blog> ulist = new ArrayList<Blog>();
		list = bs.byState(0);
		request.setAttribute("list", list);
		ulist = bs.byState(-1);
		request.setAttribute("ulist", ulist);
		return "WEB-INF/page/manager";
	}
}
