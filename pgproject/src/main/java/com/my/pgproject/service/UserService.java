package com.my.pgproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.pgproject.dao.UserDao;
import com.my.pgproject.entity.User;
import com.my.pgproject.exception.PasswordInvalidException;
import com.my.pgproject.exception.UserNotFoundException;

@Service
public class UserService {
 @Autowired
 private UserDao udao;
 
 public Object login(String usernameOrEmail, String password) {
	User u = udao.findByUsernameOrEmail(usernameOrEmail);
	if(u!=null) {
		if(u.getPassword().equals(password)) {
			return u;
		}else {
			try {
				throw new PasswordInvalidException();
			}catch(PasswordInvalidException e) {
				e.getMessage();
			}
		}
	}
	try {
		throw new UserNotFoundException();
	}catch(UserNotFoundException e) {
		e.getMessage();
	}
	 return null;
 }
}
