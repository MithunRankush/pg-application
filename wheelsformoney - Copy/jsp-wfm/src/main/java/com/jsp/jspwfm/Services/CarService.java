package com.jsp.jspwfm.Services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.jspwfm.Dao.CarDao;
import com.jsp.jspwfm.Dao.ImageDao;
import com.jsp.jspwfm.Models.Entities.Car;
import com.jsp.jspwfm.Models.Entities.Image;



@Service
@Component
public class CarService {
	 @Autowired
     private CarDao cardao;   
     @Autowired
     private ImageDao imagedao;

	public List<Car> getAll() {
		return cardao.findAll();
	}

	public Car getById(Long id) {
		return cardao.findById(id).get();
	}

	public List<Car> removeId(Long id) {
		if(cardao.existsById(id))
		{
			cardao.deleteById(id);
			return getAll();
		}
		return null;
	}

	public Object getbytype(String type) {
		if(cardao.getbytype(type)!=null)
		{
			return cardao.getbytype(type);
		}
		return null;
	}

	public boolean Update(Long id, Car car) {
		Optional <Car> ck = cardao.findById(id);
		if(ck!=null)
		{
			Car c = ck.get();
			c.setCarname(car.getCarname());
			
		}
		return false;
	}

	public Car saveCar(Car car) {
		return cardao.save(car);
	}

	public Image saveimage(MultipartFile file) throws IOException{
		Image car = new Image(file.getBytes(),file.getOriginalFilename(),file.getContentType());
		return imagedao.save(car);
	}

}
