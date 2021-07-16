package com.blogapi.model;

import com.blogapi.model.audit.UserDateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@Table(name = "photos", uniqueConstraints = { @UniqueConstraint(columnNames = { "title" }) })
public class Photo extends UserDateAudit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "title")
	private String title;

	@NotBlank
	@Column(name = "url")
	private String url;

	@NotBlank
	@Column(name = "thumbnail_url")
	private String thumbnailUrl;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "album_id")
	private Album album;

	public Photo(@NotBlank String title, @NotBlank String url, @NotBlank String thumbnailUrl, Album album) {
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
		this.album = album;
	}

	@JsonIgnore
	public Album getAlbum() {
		return album;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Photo(Long id, @NotBlank String title, @NotBlank String url, @NotBlank String thumbnailUrl, Album album) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.thumbnailUrl = thumbnailUrl;
		this.album = album;
	}
	
}
