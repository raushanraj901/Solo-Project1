package com.project.services;

import javax.security.auth.login.LoginException;

import com.project.customException.AdminExecption;
import com.project.models.Admin;
import com.project.models.AdminLoginDTO;

public interface AdminServices {
	
	public String registerAdmin(Admin admin) throws AdminExecption;
	public String deleteAdminById(Integer id) throws AdminExecption;
	public Admin findAdminById(Integer id) throws AdminExecption;
	public String updateAdminDetails(Admin admin,String key) throws AdminExecption;
	
	public String adminLogInToAccount(AdminLoginDTO adminLoginDTO) throws LoginException;
	
	public String adminLogOutFromAccount(String key) throws LoginException;

}
