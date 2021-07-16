package com.blogapi.service;

import com.blogapi.model.Album;
import com.blogapi.security.UserPrincipal;
import com.payload.AlbumResponse;
import com.payload.ApiResponse;
import com.payload.PagedResponse;
import com.payload.request.AlbumRequest;

import org.springframework.http.ResponseEntity;

public interface AlbumService {

	PagedResponse<AlbumResponse> getAllAlbums(int page, int size);

	ResponseEntity<Album> addAlbum(AlbumRequest albumRequest, UserPrincipal currentUser);

	ResponseEntity<Album> getAlbum(Long id);

	ResponseEntity<AlbumResponse> updateAlbum(Long id, AlbumRequest newAlbum, UserPrincipal currentUser);

	ResponseEntity<ApiResponse> deleteAlbum(Long id, UserPrincipal currentUser);

	PagedResponse<Album> getUserAlbums(String username, int page, int size);

}
