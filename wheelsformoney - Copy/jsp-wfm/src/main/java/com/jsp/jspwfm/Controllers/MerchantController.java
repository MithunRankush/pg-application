package com.jsp.jspwfm.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.jspwfm.Models.Entities.Merchant;
import com.jsp.jspwfm.Services.MerchantService;




@RestController
@CrossOrigin
@RequestMapping("/merchant")
public class MerchantController {
	
	   @Autowired
	    private MerchantService marchantService;
	   
	    @PostMapping("/signUp")
		public ResponseEntity signUp(@RequestBody Merchant merchant) throws Exception
		{
			if(marchantService.handleSignUp(merchant))
			{
				return new ResponseEntity<>(HttpStatusCode.valueOf(200));
			}
			return new ResponseEntity<>(HttpStatusCode.valueOf(400));
		}
	    @RequestMapping("/sendotp")
	    public ResponseEntity<Object> verifymail(@RequestHeader String email,@RequestHeader String type)
	    {
	    	Object otp=marchantService.verifymail(email,type);
	    	if(otp.toString().equals("Merchant Not Found")||otp.toString().equals("Merchant Already Exists"))
	    	{
	    		return new ResponseEntity<>(HttpStatusCode.valueOf(400));
	    	}
	    	return new ResponseEntity<>(HttpStatusCode.valueOf(200));

	    }
	    
	    @RequestMapping("/verifyotp")
	    public ResponseEntity verifyotp(@RequestHeader String email,@RequestHeader int otp)
	    {
	    	Boolean verify = marchantService.verifyOTP(email,otp);
			if (verify) {
				return new ResponseEntity<>(HttpStatusCode.valueOf(200));
			} else {
				return new ResponseEntity<>(HttpStatusCode.valueOf(400));
			}

	    }
	    
	    
	    @RequestMapping("/login")
	    public ResponseEntity<Object> login(@RequestHeader String merchant,@RequestHeader String password)
	    { 
	    	if(marchantService.login(merchant, password) instanceof Merchant)
	    	{
	    		return ResponseEntity.status(200).body(marchantService.login(merchant, password));
	    	}
	    	return ResponseEntity.status(400).body(marchantService.login(merchant, password)); 
	    } 
	    @RequestMapping("/setnewpassword")
	    public ResponseEntity<String> pswdrequest(@RequestHeader String newpassword,@RequestHeader String email)
	    {
	    	String s=marchantService.pswdrequest(newpassword, email);
	    	if("password updated sucessfully....!!!!!!".equals(s))
	    	{
	    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(200));
	    	}
	    	else {
	    		return new ResponseEntity<String> (s, HttpStatusCode.valueOf(400));
	    	}
	    	
	    } 
	    
	   

}
