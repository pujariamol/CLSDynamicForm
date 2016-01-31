package com.cls.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cls.dao.UserDao;
import com.cls.model.User;

@RestController
@RequestMapping(value="/user")
public class UserController {
	// Private fields

	@Autowired
	private UserDao userDao;

	/**
	 * GET /create --> Create a new user and save it in the database.
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public String create(@RequestBody User user) {
		System.out.println(user.getEmail());
		String userId = "";
		try {
			userDao.save(user);
			userId = String.valueOf(user.getId());
		} catch (Exception ex) {
			return "Error creating the user: " + ex.toString();
		}
		return "User succesfully created with id = " + userId;
	}

	/**
	 * GET /delete --> Delete the user having the passed id.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable long id) {
		try {
			User user = new User(id);
			userDao.delete(user);
		} catch (Exception ex) {
			return "Error deleting the user:" + ex.toString();
		}
		return "User succesfully deleted!";
	}

	/**
	 * GET /get-by-email --> Return the id for the user having the passed email.
	 */
	@RequestMapping(value = "/{email}", method = RequestMethod.GET)
	@ResponseBody
	public User getByEmail(@PathVariable String email) {
		User user;
		try {
			user = userDao.findByEmail(email);
		} catch (Exception ex) {
			return null;
		}
		return user;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getById(@PathVariable String id) {
		User user;
		try {
			user = userDao.findOne(Long.valueOf(id));
		} catch (Exception ex) {
			return null;
		}
		return user;
	}

	/**
	 * GET /update --> Update the email and the name for the user in the
	 * database having the passed id.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String updateUser(long id, String email, String name) {
		try {
			User user = userDao.findOne(id);
			user.setEmail(email);
			userDao.save(user);
		} catch (Exception ex) {
			return "Error updating the user: " + ex.toString();
		}
		return "User succesfully updated!";
	}

}
