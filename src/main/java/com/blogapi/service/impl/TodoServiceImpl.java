package com.blogapi.service.impl;

import com.blogapi.exception.BadRequestException;
import com.blogapi.exception.ResourceNotFoundException;
import com.blogapi.exception.UnauthorizedException;
import com.blogapi.model.Todo;
import com.blogapi.model.user.User;
import com.blogapi.repository.TodoRepository;
import com.blogapi.repository.UserRepository;
import com.blogapi.security.UserPrincipal;
import com.blogapi.service.TodoService;
import com.blogapi.utils.AppConstants;
import com.blogapi.utils.AppUtils;
import com.payload.ApiResponse;
import com.payload.PagedResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import static com.blogapi.utils.AppConstants.CREATED_AT;
import static com.blogapi.utils.AppConstants.ID;
import static com.blogapi.utils.AppConstants.TODO;
import static com.blogapi.utils.AppConstants.YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION;

import java.util.Collections;
import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

	
	private AppUtils apputils;
	
	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Todo completeTodo(Long id, UserPrincipal currentUser) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));

		User user = userRepository.getUser(currentUser);

		if (todo.getUser().getId().equals(user.getId())) {
			todo.setCompleted(Boolean.TRUE);
			return todoRepository.save(todo);
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION);

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public Todo unCompleteTodo(Long id, UserPrincipal currentUser) {
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));
		User user = userRepository.getUser(currentUser);
		if (todo.getUser().getId().equals(user.getId())) {
			todo.setCompleted(Boolean.FALSE);
			return todoRepository.save(todo);
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION);

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public PagedResponse<Todo> getAllTodos(UserPrincipal currentUser, int page, int size) {
		apputils.validatePageNumberAndSize(page, size);
		Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);

		Page<Todo> todos = todoRepository.findByCreatedBy(currentUser.getId(), pageable);

		List<Todo> content = todos.getNumberOfElements() == 0 ? Collections.emptyList() : todos.getContent();

		return new PagedResponse<>(content, todos.getNumber(), todos.getSize(), todos.getTotalElements(),
				todos.getTotalPages(), todos.isLast());
	}

	@Override
	public Todo addTodo(Todo todo, UserPrincipal currentUser) {
		User user = userRepository.getUser(currentUser);
		todo.setUser(user);
		return todoRepository.save(todo);
	}

	@Override
	public Todo getTodo(Long id, UserPrincipal currentUser) {
		User user = userRepository.getUser(currentUser);
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));

		if (todo.getUser().getId().equals(user.getId())) {
			return todo;
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION);

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public Todo updateTodo(Long id, Todo newTodo, UserPrincipal currentUser) {
		User user = userRepository.getUser(currentUser);
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));
		if (todo.getUser().getId().equals(user.getId())) {
			todo.setTitle(newTodo.getTitle());
			todo.setCompleted(newTodo.getCompleted());
			return todoRepository.save(todo);
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION);

		throw new UnauthorizedException(apiResponse);
	}

	@Override
	public ApiResponse deleteTodo(Long id, UserPrincipal currentUser) {
		User user = userRepository.getUser(currentUser);
		Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(TODO, ID, id));

		if (todo.getUser().getId().equals(user.getId())) {
			todoRepository.deleteById(id);
			return new ApiResponse(Boolean.TRUE, "You successfully deleted todo");
		}

		ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, YOU_DON_T_HAVE_PERMISSION_TO_MAKE_THIS_OPERATION);

		throw new UnauthorizedException(apiResponse);
	}

//	private void validatePageNumberAndSize(int page, int size) {
//		if (page < 0) {
//			throw new BadRequestException("Page number cannot be less than zero.");
//		}
//
//		if (size < 0) {
//			throw new BadRequestException("Size number cannot be less than zero.");
//		}
//
//		if (size > AppConstants.MAX_PAGE_SIZE) {
//			throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
//		}
//	}
}
