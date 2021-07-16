package com.blogapi.model;

import com.blogapi.model.audit.UserDateAudit;
import com.blogapi.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "albums", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Album extends UserDateAudit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "title")
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Photo> photo;

	@JsonIgnore
	public User getUser() {
		return user;
	}

	
	public Album(Long id, @NotBlank String title, User user, List<Photo> photo) {
		super();
		this.id = id;
		this.title = title;
		this.user = user;
		this.photo = photo;
	}


	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}


	public List<Photo> getPhoto() {
		return this.photo == null ? null : new ArrayList<>(this.photo);
	}

	public void setPhoto(List<Photo> photo) {
		if (photo == null) {
			this.photo = null;
		} else {
			this.photo = Collections.unmodifiableList(photo);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
