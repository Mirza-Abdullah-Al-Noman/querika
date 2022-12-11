package com.management.querika.Querika.service;

import com.management.querika.Querika.model.UserDtls;

public interface UserService {

	public UserDtls createUser(UserDtls user);

	public boolean checkEmail(String email);

}