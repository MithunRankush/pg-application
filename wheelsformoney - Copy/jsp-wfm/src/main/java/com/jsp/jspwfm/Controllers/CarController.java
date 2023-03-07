package com.jsp.jspwfm.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.jspwfm.Models.Entities.Car;
import com.jsp.jspwfm.Models.Entities.Image;
import com.jsp.jspwfm.Services.CarService;





@RestController
@CrossOrigin
@RequestMapping("/car")
public class CarController {
	 @Autowired
	    private CarService carService;
	 
	 @RequestMapping("/getallcars")
	    public ResponseEntity<List<Car>> getAll() 
	    {
		 List<Car> obj=carService.getAll();
			if(obj!=null && obj.size()>0) 
			{
	    	return ResponseEntity.status(200).body(obj);
			}
			return ResponseEntity.status(400).body(obj); 
	    }
	 @RequestMapping("/getcar/{id}")
	    public ResponseEntity<Car> getcar(@PathVariable("id") Long id)
	    {
		  Car car= carService.getById(id);
			if(car!=null) {
	    	return ResponseEntity.status(200).body(car);
			}
			return ResponseEntity.status(400).body(car);
	    }

	 @RequestMapping("/deletecar/{id}")
	    public ResponseEntity<List<Car>> removeId(@PathVariable("id") Long id)
	    {
	    	List<Car> list = carService.removeId(id);
			if(list!=null) {
	    	return ResponseEntity.status(200).body(list);
			}
			return ResponseEntity.status(400).body(list);
	    }
	 @RequestMapping("/save")
	 public Image saveimage(@RequestParam("image") MultipartFile file) throws IOException
	 {
		 return (carService.saveimage(file));
	 }
	 
	 @PostMapping(value= {"/addcar"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	    public ResponseEntity<Car> saveCar(@RequestPart("Car")Car car,@RequestPart("image") MultipartFile file) 
	    {
		 Image o;
		 try {
			 o= saveimage(file);
			 car.setImage(o);
		 }catch (IOException e) {
			 e.printStackTrace();
			 return ResponseEntity.status(400).body(null);
		 }
		 return ResponseEntity.status(400).body(carService.saveCar(car));
	    }
	 @PutMapping("/updatecar/{id}")
	    public ResponseEntity<Car> update(@PathVariable("id") Long id ,@RequestBody Car car) 
	    {
			boolean value= carService.Update(id ,car);
			if(value) {
	    	return ResponseEntity.status(200).body(car);
			}
			return ResponseEntity.status(400).body(car);
	    }
	 @RequestMapping("/getbytype/{type}")
	 public ResponseEntity<Object> getbytype(@PathVariable String type)
	 {
		 if(carService.getbytype(type)!=null)
		 {
			 return ResponseEntity.status(200).body(carService.getbytype(type));
		 }
		 return ResponseEntity.status(404).body(null);
	 }
}
