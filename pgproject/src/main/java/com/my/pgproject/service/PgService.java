package com.my.pgproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.pgproject.dao.PgDao;
import com.my.pgproject.entity.PgEntity;

@Service
public class PgService {
   @Autowired
   private PgDao pdao;

public List<PgEntity> getAll() {
	return pdao.findAll();
}

public PgEntity savedata(PgEntity pg) {
	return pdao.save(pg);
}

public PgEntity getbyId(Integer id) {
	return pdao.findById(id).get();
}

public List<PgEntity> removeId(Integer id) {
	if(pdao.existsById(id)) {
		pdao.deleteById(id);
		return getAll();
	}
	return null;
}

public boolean update(Integer id, PgEntity pg) {
	Optional<PgEntity> op = pdao.findById(id);
	if(op!=null) {
		PgEntity p = op.get();
		p.setPgname(pg.getPgname());
		p.setPrice(pg.getPrice());
		p.setRating(pg.getRating());
		p.setAdd(pg.getAdd());
		savedata(p);
		return true;
	}
	return false;
}
   
}
