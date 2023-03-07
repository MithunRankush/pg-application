package com.jsp.jspwfm.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.jspwfm.Models.Entities.Merchant;






public interface MerchantDao  extends JpaRepository<Merchant,Long>{
	
	  @Query(value = "SELECT * FROM Merchant  WHERE merchantname = :merchantname", nativeQuery = true)
		public Merchant getMerchantByMerchantname(String merchantname);

	    @Query(value = "SELECT * FROM Merchant WHERE email = :data OR merchantname=:data", nativeQuery = true) 
		public Merchant findByMerchantnameOrEmail(String data);

		@Query(value = "SELECT * FROM Merchant  WHERE    email= :data OR merchantname = :data", nativeQuery = true)
		public Merchant getMerchantBydata(String data);

		@Query(value = "SELECT * FROM Merchant   WHERE email = :email", nativeQuery = true) 
		public Merchant getMerchantByemail(String email);

}
