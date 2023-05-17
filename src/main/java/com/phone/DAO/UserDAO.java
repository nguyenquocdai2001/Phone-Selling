package com.phone.DAO;

import java.util.List;

import com.phone.model.User;

public interface UserDAO {
	void saveUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int id);
}
