package com.jsp.jspwfm.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.jspwfm.Dao.BikeDao;
import com.jsp.jspwfm.Dao.ImageDao;
import com.jsp.jspwfm.Models.Entities.Bike;
import com.jsp.jspwfm.Models.Entities.Image;


@Service
@Component
public class BikeService {
	
       @Autowired
       private BikeDao bikedao;   
       @Autowired
       private ImageDao imagedao;
       
	public List<Bike> getAll() {
		return bikedao.findAll();
	}
	public Bike getById(Long id) {
		return bikedao.findById(id).get();
	}
	public List<Bike> removeId(Long id) {
		if(bikedao.existsById(id))
		{
			bikedao.deleteById(id);
			return getAll();
		}
		return null;
	}
	public Bike saveBike(Bike bike) {
		return bikedao.save(bike);
	}
	public boolean Update(Long id, Bike bike) {
		Optional <Bike> bk = bikedao.findById(id);
		if(bk!=null)
		{
			Bike b = bk.get();
			
			b.setBikename(bike.getBikename());
			b.setCompanyname(bike.getCompanyname());
			
		}
		return false;
	}
	public Image saveimage(MultipartFile file) throws IOException{
		Image bike = new Image(file.getBytes(),file.getOriginalFilename(),file.getContentType());
		return imagedao.save(bike);
	}
	public Object getbytype(String type) {
		if(bikedao.getbytype(type)!=null)
		{
			return bikedao.getbytype(type);
		}
		return null;
	}

}
