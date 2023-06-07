package com.phone.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import com.phone.model.User;

public interface UserDAO {
	void saveUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int id);
    
    Optional<User> getUserByEmail(String email);
    
    boolean checkRegister(String name, String email, String password, String confirmPassword, String phone, String address, 
    		String gender, String titlesOfAddress, Model model);
    
    boolean checkLogin(String email, String password, Model model);
    
    boolean editProfile(String name, String email, String phone, String address, Model model);
    
    void saveChangePassword(User user);
    
    boolean changePassword(String OldPassword, String NewPassword, String confirmNewPassword, String email, Model model);
    
}
