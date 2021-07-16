package com.blogapi.service;

import com.blogapi.model.Post;
import com.blogapi.security.UserPrincipal;
import com.payload.ApiResponse;
import com.payload.PagedResponse;
import com.payload.PostRequest;
import com.payload.PostResponse;

public interface PostService {

	PagedResponse<Post> getAllPosts(int page, int size);

	PagedResponse<Post> getPostsByCreatedBy(String username, int page, int size);

	PagedResponse<Post> getPostsByCategory(Long id, int page, int size);

	PagedResponse<Post> getPostsByTag(Long id, int page, int size);

	Post updatePost(Long id, PostRequest newPostRequest, UserPrincipal currentUser);

	ApiResponse deletePost(Long id, UserPrincipal currentUser);

	PostResponse addPost(PostRequest postRequest, UserPrincipal currentUser);

	Post getPost(Long id);

}
