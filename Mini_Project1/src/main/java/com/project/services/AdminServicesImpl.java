package com.project.services;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.customException.AdminExecption;
import com.project.models.Admin;
import com.project.models.AdminLoginDTO;
import com.project.models.CurrentAdminSession;
import com.project.repository.AdminRepo;
import com.project.repository.AdminSessionRepo;


import net.bytebuddy.utility.RandomString;

@Service
public class AdminServicesImpl implements AdminServices{
	
	@Autowired(required = true)
	private AdminRepo aRepo;
	
	@Autowired(required = true)
	private AdminSessionRepo sessionRepo;
	
	@Override
	public String registerAdmin(Admin admin) throws AdminExecption {
		
		Admin existadmin = aRepo.findByMobile(admin.getMobile());
		
		if(existadmin != null)
			throw new AdminExecption("Admin already registered with mobile number "+admin.getMobile());
		Admin regAdmin = aRepo.save(admin);
		return "Admin registered successfully....\nDetails:\n"+regAdmin.toString();
	}

	@Override
	public String deleteAdminById(Integer id) throws AdminExecption {
		
		Optional<Admin> opt= aRepo.findById(id);
		if(opt.isPresent()) {
			Admin admin = opt.get();
			aRepo.delete(admin);
			return "Admin deleted successfully !\nDetails:\n"+admin.toString();
		}else {
			throw new AdminExecption("Account not found with id "+id);
		}
	}

	@Override
	public Admin findAdminById(Integer id) throws AdminExecption {
		
		return aRepo.findById(id).orElseThrow(()->new AdminExecption("Account does not found with id: "+id));
		
	}

	@Override
	public String updateAdminDetails(Admin admin, String key) throws AdminExecption {
		
		CurrentAdminSession loggedAdmin = sessionRepo.findByadKey(key);
		
		if(loggedAdmin == null) {
			throw new AdminExecption("Please provide valid key to update details");
		}
		
		if(admin.getAdminId()==loggedAdmin.getAdminId()) {
			aRepo.save(admin);
			return "Admin updated successfully !!\nDetalis: \n"+aRepo.save(admin).toString();
		}else
		throw new AdminExecption("Invalid admin details, please try again");
	}

	@Override
	public String adminLogInToAccount(AdminLoginDTO adminLoginDTO) throws LoginException {
		
		Admin existingAdmin= aRepo.findByMobile(adminLoginDTO.getMobileNo());
		
		if(existingAdmin == null) {
			throw new LoginException("Please enter a valid moblie number");
		}
		
		Optional<CurrentAdminSession> validOptional = sessionRepo.findById(existingAdmin.getAdminId());
		
		if (validOptional.isPresent()) {
			throw new LoginException("Already logged in with number "+existingAdmin.getMobile());
		}
		
		if (existingAdmin.getPassword().equals(adminLoginDTO.getPassword())) {
			
			String keyString = RandomString.make(6);
			
			CurrentAdminSession currentSession = new CurrentAdminSession(existingAdmin
													.getAdminId(),keyString,LocalDateTime.now());
			
			 sessionRepo.save(currentSession);
			
			return "Login successfull !\nWelcome "+existingAdmin.getAdminName()+"\n"+currentSession.toString();
			
		}else 
			throw new LoginException("Please enter a valid password");
		
	}

	@Override
	public String adminLogOutFromAccount(String key) throws LoginException {
		
		CurrentAdminSession adminCurrentSession = sessionRepo.findByadKey(key);
		
		if(adminCurrentSession == null)
			throw new LoginException("You are not logged in");
		
		sessionRepo.delete(adminCurrentSession);
		
		return "Logged out successfully !";
	}
	
}
