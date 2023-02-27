package com.my.pgproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.my.pgproject.entity.PgEntity;
import com.my.pgproject.service.PgService;
@CrossOrigin
@Controller
@RequestMapping("/pg")
public class PgController {
 @Autowired
 private PgService pser;
 @RequestMapping("/getall")
 public ResponseEntity<List<PgEntity>> getAll()
 {
	 List<PgEntity> list = pser.getAll() ;
	 if(list!=null && list.size()>0) {
		 return ResponseEntity.status(200).body(list);
	 }
	 return ResponseEntity.status(400).body(list);
 }
 @RequestMapping("/create")
 public ResponseEntity<PgEntity> savedata(@RequestBody PgEntity pg)
 {
	PgEntity p = pser.savedata(pg) ;
	 if(p!=null) {
		 return ResponseEntity.status(200).body(p);
	 }
	 return ResponseEntity.status(400).body(p);
 }
 @RequestMapping("/read/{id}")
 public ResponseEntity<PgEntity> getbyId(@PathVariable("id") Integer id)
 {
	PgEntity p = pser.getbyId(id) ;
	 if(p!=null) {
		 return ResponseEntity.status(200).body(p);
	 }
	 return ResponseEntity.status(400).body(p);
 }
 @RequestMapping("/delete/{id}")
 public ResponseEntity<List<PgEntity>> removeId(@PathVariable("id") Integer id)
 {
	List<PgEntity> p = pser.removeId(id) ;
	 if(p!=null) {
		 return ResponseEntity.status(200).body(p);
	 }
	 return ResponseEntity.status(400).body(p);
 }
 @RequestMapping("/update/{id}")
 public ResponseEntity<PgEntity> update(@PathVariable("id") Integer id,@RequestBody PgEntity pg)
 {
      boolean val= pser.update(id,pg) ;
	 if(val) {
		 return ResponseEntity.status(200).body(pg);
	 }
	 return ResponseEntity.status(400).body(pg);
 }
}
