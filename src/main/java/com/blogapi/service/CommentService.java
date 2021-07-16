package com.blogapi.service;

import com.blogapi.model.Comment;
import com.blogapi.security.UserPrincipal;
import com.payload.ApiResponse;
import com.payload.CommentRequest;
import com.payload.PagedResponse;

public interface CommentService {

	PagedResponse<Comment> getAllComments(Long postId, int page, int size);

	Comment addComment(CommentRequest commentRequest, Long postId, UserPrincipal currentUser);

	Comment getComment(Long postId, Long id);

	Comment updateComment(Long postId, Long id, CommentRequest commentRequest, UserPrincipal currentUser);

	ApiResponse deleteComment(Long postId, Long id, UserPrincipal currentUser);

}
