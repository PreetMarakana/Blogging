package com.blogapi.service;

import com.blogapi.model.Tag;
import com.blogapi.security.UserPrincipal;
import com.payload.ApiResponse;
import com.payload.PagedResponse;

public interface TagService {

	PagedResponse<Tag> getAllTags(int page, int size);

	Tag getTag(Long id);

	Tag addTag(Tag tag, UserPrincipal currentUser);

	Tag updateTag(Long id, Tag newTag, UserPrincipal currentUser);

	ApiResponse deleteTag(Long id, UserPrincipal currentUser);

}
