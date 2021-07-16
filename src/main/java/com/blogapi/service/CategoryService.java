package com.blogapi.service;

import com.blogapi.exception.UnauthorizedException;
import com.blogapi.model.Category;
import com.blogapi.security.UserPrincipal;
import com.payload.ApiResponse;
import com.payload.PagedResponse;

import org.springframework.http.ResponseEntity;

public interface CategoryService {

	PagedResponse<Category> getAllCategories(int page, int size);

	ResponseEntity<Category> getCategory(Long id);

	ResponseEntity<Category> addCategory(Category category, UserPrincipal currentUser);

	ResponseEntity<Category> updateCategory(Long id, Category newCategory, UserPrincipal currentUser)
			throws UnauthorizedException;

	ResponseEntity<ApiResponse> deleteCategory(Long id, UserPrincipal currentUser) throws UnauthorizedException;

}
