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

import com.jsp.jspwfm.Models.Entities.Bike;
import com.jsp.jspwfm.Models.Entities.Image;
import com.jsp.jspwfm.Services.BikeService;



@RestController
@CrossOrigin
@RequestMapping("/bike")
public class BikeController {
	  
	 @Autowired
	    private BikeService bikeService;
	 
	 @RequestMapping("/getallbikes")
	    public ResponseEntity<List<Bike>> getAll() 
	    {
		 List<Bike> obj=bikeService.getAll();
			if(obj!=null && obj.size()>0) 
			{
	    	return ResponseEntity.status(200).body(obj);
			}
			return ResponseEntity.status(400).body(obj); 
	    }
	 
	 @RequestMapping("/getbike/{id}")
	    public ResponseEntity<Bike> getbike(@PathVariable("id") Long id)
	    {
		  Bike bike= bikeService.getById(id);
			if(bike!=null) {
	    	return ResponseEntity.status(200).body(bike);
			}
			return ResponseEntity.status(400).body(bike);
	    }
  
	 @RequestMapping("/deletebike/{id}")
	    public ResponseEntity<List<Bike>> removeId(@PathVariable("id") Long id)
	    {
	    	List<Bike> list = bikeService.removeId(id);
			if(list!=null) {
	    	return ResponseEntity.status(200).body(list);
			}
			return ResponseEntity.status(400).body(list);
	    }
	 @RequestMapping("/save")
	 public Image saveimage(@RequestParam("image") MultipartFile file) throws IOException
	 {
		 return (bikeService.saveimage(file));
	 }
	 
	 @PostMapping(value= {"/addbike"},consumes= {MediaType.MULTIPART_FORM_DATA_VALUE})
	    public ResponseEntity<Bike> saveBike(@RequestPart("Bike")Bike bike,@RequestPart("image") MultipartFile file) 
	    {
		 Image o;
		 try {
			 o= saveimage(file);
			 bike.setImage(o);
		 }catch (IOException e) {
			 e.printStackTrace();
			 return ResponseEntity.status(400).body(null);
		 }
		 return ResponseEntity.status(400).body(bikeService.saveBike(bike));
	    }
	 @PutMapping("/updatebike/{id}")
	    public ResponseEntity<Bike> update(@PathVariable("id") Long id ,@RequestBody Bike bike) 
	    {
			boolean value= bikeService.Update(id ,bike);
			if(value) {
	    	return ResponseEntity.status(200).body(bike);
			}
			return ResponseEntity.status(400).body(bike);
	    }
	 @RequestMapping("/getbytype/{type}")
	 public ResponseEntity<Object> getbytype(@PathVariable String type)
	 {
		 if(bikeService.getbytype(type)!=null)
		 {
			 return ResponseEntity.status(200).body(bikeService.getbytype(type));
		 }
		 return ResponseEntity.status(404).body(null);
	 }
}
