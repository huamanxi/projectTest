package cn.jane.service;

import org.springframework.stereotype.Service;

import cn.jane.entity.User;

public interface UserService {
	public void register(User user);
	public boolean isRegistered(String phone_num);
	public boolean isRight(String phone_num,String password);
	public User getUser(String phone_num);
}
