package com.my.pgproject.exception;

public class PasswordInvalidException extends Exception {
  public PasswordInvalidException() {
	  super("invaid credentials");
  }
}
