package com.blogapi.service;

import com.blogapi.model.user.User;
import com.blogapi.security.UserPrincipal;
import com.payload.ApiResponse;
import com.payload.InfoRequest;
import com.payload.UserIdentityAvailability;
import com.payload.UserProfile;
import com.payload.UserSummary;

public interface UserService {

	UserSummary getCurrentUser(UserPrincipal currentUser);

	UserIdentityAvailability checkUsernameAvailability(String username);

	UserIdentityAvailability checkEmailAvailability(String email);

	UserProfile getUserProfile(String username);

	User addUser(User user);

	User updateUser(User newUser, String username, UserPrincipal currentUser);

	ApiResponse deleteUser(String username, UserPrincipal currentUser);

	ApiResponse giveAdmin(String username);

	ApiResponse removeAdmin(String username);

	UserProfile setOrUpdateInfo(UserPrincipal currentUser, InfoRequest infoRequest);

}