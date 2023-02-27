package com.my.pgproject.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.my.pgproject.entity.PgEntity;
@Repository
public interface PgDao extends JpaRepository<PgEntity,Integer> {

}
