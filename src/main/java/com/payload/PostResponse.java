package com.payload;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class PostResponse {
	private String title;
	private String body;
	private String category;
	private List<String> tags;



	public List<String> getTags() {

		return tags == null ? null : new ArrayList<>(tags);
	}

	public void setTags(List<String> tags) {

		if (tags == null) {
			this.tags = null;
		} else {
			this.tags = Collections.unmodifiableList(tags);
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
