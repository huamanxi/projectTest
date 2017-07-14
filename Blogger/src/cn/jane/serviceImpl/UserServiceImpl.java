package cn.jane.serviceImpl;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jane.entity.User;
import cn.jane.mapper.UserMapper;
import cn.jane.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper um;
	public void register(User user) {
		// TODO Auto-generated method stub
		um.register(user);
	}
	@Override
	public boolean isRegistered(String phone_num) {
		// TODO Auto-generated method stub
		ArrayList<String> a = new ArrayList<String>();
		a = um.all_num();
		Iterator<String> it = a.iterator();
		while(it.hasNext()){
			String s = it.next();
			if(phone_num.equals(s)){
				return true;
			}
			
		}	
				return false;//代表没有注册过
	}
	@Override
	public boolean isRight(String phone_num,String password) {
		// TODO Auto-generated method stub
		User user = um.findPass(phone_num);
		String repassword = user.getPassword();
		if(repassword.equals(password)){
			return true;
		}
		return false;
	}
	@Override
	public User getUser(String phone_num) {
		// TODO Auto-generated method stub
		return um.findPass(phone_num);
	}
}
