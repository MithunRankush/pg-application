package com.jsp.jspwfm.Services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.jsp.jspwfm.Dao.MerchantDao;
import com.jsp.jspwfm.Dao.OtpRepository;
import com.jsp.jspwfm.Exception.MerchantAlreadyExitsException;
import com.jsp.jspwfm.Exception.MerchantNotFoundException;
import com.jsp.jspwfm.Exception.PasswordInvalidException;
import com.jsp.jspwfm.Models.Entities.MailMessage;
import com.jsp.jspwfm.Models.Entities.Merchant;
import com.jsp.jspwfm.Models.Entities.Otp;



@Service
@Component
public class MerchantService {
	
	  @Autowired
	    MerchantDao merchantdao;
	   
	    public boolean handleSignUp(Merchant merchant)
	    {
	    	Merchant reslut=merchantdao.getMerchantByMerchantname(merchant.getMerchantname());
			try
			{
				if(reslut!=null)
				{
					throw new MerchantAlreadyExitsException();
					
				}
				else
				{
					merchantdao.save(merchant);
					return true;
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
				return false;
			}
	    }
	    @Autowired
	    JavaMailSender mailsender;
	    @Value("${spring.mail.username}") private String fromemail;
	  
	    
	    
	    	
	    	
	    	
	    	@Autowired
	    	OtpRepository Otprepository;

	    	public String verifymail(String email, String type) {
	    		Merchant m1 = null;
	         Otp o=new Otp();
	         
	    		if (type.equals("signup")) {
	    			m1 = merchantdao.getMerchantByemail(email);

	    			if (m1 == null) {
	    				Random random = new Random();
	    				int OTP = random.nextInt(100000, 999999);
	    				MailMessage mm=new MailMessage();
	    	    		String str=mm.setotpTemplate(OTP,type);
	    				SimpleMailMessage mailMessage = new SimpleMailMessage();
	    				mailMessage.setFrom(fromemail);
	    				mailMessage.setTo(email);
	    				mailMessage.setText(str);
	    				mailMessage.setSubject("signup OTP");
	    				mailsender.send(mailMessage);
	    				o.setEmail(email);
	    				o.setOtp(OTP);
	    				Otprepository.save(o);
	    				String otp=Integer.toString(OTP);
	    				return otp;
	    			} else {
	    				try {
	    					throw new MerchantAlreadyExitsException();
	    				} catch (MerchantAlreadyExitsException exception) {
	    					return exception.getMessage();

	    				}

	    			}
	    		} else {
	    			m1 = merchantdao.getMerchantByemail(email);

	    			if (m1 != null) {
	    				Random random = new Random();
	    				int OTP = random.nextInt(100000, 999999);
	    				MailMessage mm=new MailMessage();
	    	    		String str=mm.setotpTemplate(OTP,type);
	    	    		o.setEmail(email);
	    				o.setOtp(OTP);
	    				SimpleMailMessage mailMessage = new SimpleMailMessage();
	    				o.setEmail(email);
	    				o.setOtp(OTP);
	    				Otprepository.save(o);
	    				mailMessage.setFrom(fromemail);
	    				mailMessage.setTo(m1.getEmail());
	    				if (type.equals("forget")) {
	    					mailMessage.setText(str);
	    					mailMessage.setSubject("Reset password");
	    				} else {
	    					mailMessage.setText(str);
	    					mailMessage.setSubject("Login OTP");
	    				}

	    				mailsender.send(mailMessage);
	    				String otp=Integer.toString(OTP);
	    				return otp;
	    			} else {
	    				try {
	    					throw new MerchantNotFoundException();
	    				} catch (MerchantNotFoundException exception) {
	    					return exception.getMessage();

	    				}

	    			}
	    		}

	    	}
	    
	    	public boolean verifyOTP(String email,int otp) {
	    		
	    		Otp o=Otprepository.findByEmail(email);
	      
	    		if (otp == o.getOtp()) {
	    			
	    			Otprepository.delete(o);
	    			return true;
	    		} else 
	    		{
	    			//Otprepository.delete(o);
	    			return false;
	    		}

	    	}

		public Object login(String merchantnameOrEmail, String password) {

			Merchant merchant = merchantdao.findByMerchantnameOrEmail(merchantnameOrEmail);

			if (merchant != null) {
				if (merchant.getPassword().equals(password))

				{
					return merchant;
				} 
				else {
					try {
						throw new PasswordInvalidException();
					} catch (PasswordInvalidException exception) {
						return exception.getMessage();

					}
				}
			}

			try

			{
				throw new MerchantNotFoundException();
			} catch (MerchantNotFoundException exception) {
				return exception.getMessage();

			}
		}
		 public String pswdrequest(String newpassword,String email)
		    {
		    	Merchant m1=merchantdao.getMerchantByemail(email);
		    	if(m1!=null)
		    	{
		    		m1.setPassword(newpassword);
		    		merchantdao.save(m1);
		    		return "password updated sucessfully....!!!!!!";
		    		
		    	}
		    	else
		    	{
		    		return "There is no merchant found with "+email+" signup first please!!.... ";
		    	}
		    
		    }

}
