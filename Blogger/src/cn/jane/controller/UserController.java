package cn.jane.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jane.service.BlogService;
import cn.jane.service.UserService;
import cn.jane.entity.Blog;
import cn.jane.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private BlogService bs;
	@RequestMapping("/register")
	public String register(HttpServletRequest request,HttpServletResponse response,@ModelAttribute User user) throws IOException{
		String phone_num = request.getParameter("phone_num");
		if(us.isRegistered(phone_num)){
			PrintWriter out = response.getWriter();
			out.print("���û����Ѿ���ע��");
			return "WEB-INF/page/register";
		}
			String password = request.getParameter("password");
			user.setPhone_num(phone_num);
			user.setPassword(password);
			user.setUser_name(request.getParameter("user_name"));
			user.setSex(request.getParameter("sex"));
			user.setLocal(request.getParameter("local"));
			String age = request.getParameter("age");
			int ag = Integer.parseInt(age);
			user.setState(-1);//-1��ʾδͨ��
			user.setAge(ag);
			us.register(user);
			return "index";
		
		
		
	}
	@RequestMapping("/login")
	public String login(HttpServletRequest request,HttpServletResponse response,@ModelAttribute User user) throws IOException{
		String phone_num = request.getParameter("phone_num");
		System.out.println("�û���Ϊ"+phone_num);
		PrintWriter out = response.getWriter();
		String password = request.getParameter("password");
		System.out.println("��ȡ��������"+password);
		
		if(phone_num.equals("123456") && password.equals("123456")){
			ArrayList<Blog> list = new ArrayList<Blog>();
			ArrayList<Blog> ulist = new ArrayList<Blog>();
			list = bs.byState(1);
			request.setAttribute("list", list);
			ulist = bs.byState(-1);
			request.setAttribute("ulist", ulist);
			return "WEB-INF/page/manager";
		}
		if(!us.isRegistered(phone_num)){
			
			out.print("���û�����δע��");
			return "index";
		}
			user = us.getUser(phone_num);
			if(user.getPassword().equals(password)){	
				ArrayList<Blog> list = new ArrayList<Blog>();
				list = bs.byState(1);
				request.setAttribute("list", list);
				request.getSession().setAttribute("user", user);
				return "WEB-INF/page/personal";
			}else{
				out.print("�û�����������");
				System.out.println("�û�������������");
				return "index";
		
			}
		
	}
}
