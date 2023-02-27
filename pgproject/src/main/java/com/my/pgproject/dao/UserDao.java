package com.my.pgproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.my.pgproject.entity.User;
@Repository
public interface UserDao extends JpaRepository<User,Integer> {
  @Query(value="SELECT * FROM User u WHERE username=:user OR email=:user", nativeQuery=true)
  public User findByUsernameOrEmail(String user);

}
