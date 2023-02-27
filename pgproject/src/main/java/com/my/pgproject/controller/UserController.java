package com.my.pgproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.pgproject.entity.User;
import com.my.pgproject.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
@Autowired
private UserService userv;

   @RequestMapping("/login")
   public ResponseEntity<Object> login(@RequestHeader String user,@RequestHeader String password)
   {
	   if( userv.login(user, password) instanceof User) {
		   return ResponseEntity.status(200).body( userv.login(user, password));
	   }
	   return ResponseEntity.status(400).body( userv.login(user, password));
   }
}
