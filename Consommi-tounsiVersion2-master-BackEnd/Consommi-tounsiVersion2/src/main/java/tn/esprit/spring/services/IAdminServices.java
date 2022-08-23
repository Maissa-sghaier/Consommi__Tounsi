package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entity.Admin;
import tn.esprit.spring.entity.UserErrors;

public interface IAdminServices {

	void deleteAdmin(int id);

	Admin retrieveAdmin(int id);

	UserErrors addAdmin(Admin a);

	Admin updateAdmin(Admin a);

	Admin getadminByUsername(String username);

	Admin getadminByEmail(String email);

	List<Admin> retrieveAllAdmins();

	Admin authenticate(String login, String password);

}
