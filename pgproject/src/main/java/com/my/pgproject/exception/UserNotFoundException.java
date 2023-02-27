package com.my.pgproject.exception;

public class UserNotFoundException extends Exception {
   public UserNotFoundException() {
	   super("user already exits");
   }
}
