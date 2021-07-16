package com.blogapi.model.user;

import com.blogapi.model.Album;
import com.blogapi.model.Comment;
import com.blogapi.model.Post;
import com.blogapi.model.Todo;
import com.blogapi.model.audit.DateAudit;
import com.blogapi.model.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }),
		@UniqueConstraint(columnNames = { "email" }) })
public class User extends DateAudit {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotBlank
	@Column(name = "first_name")
	@Size(max = 40)
	private String firstName;

	@NotBlank
	@Column(name = "last_name")
	@Size(max = 40)
	private String lastName;

	@NotBlank
	@Column(name = "username")
	@Size(max = 15)
	private String username;

	@NotBlank
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Size(max = 100)
	@Column(name = "password")
	private String password;

	@NotBlank
	@NaturalId
	@Size(max = 40)
	@Column(name = "email")
	@Email
	private String email;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "website")
	private String website;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Todo> todos;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Album> albums;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Post> posts;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Comment> comments;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "company_id")
	private Company company;

	public User(String firstName, String lastName, String username, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
	}



	public List<Todo> getTodos() {

		return todos == null ? null : new ArrayList<>(todos);
	}

	public void setTodos(List<Todo> todos) {

		if (todos == null) {
			this.todos = null;
		} else {
			this.todos = Collections.unmodifiableList(todos);
		}
	}

	public List<Album> getAlbums() {

		return albums == null ? null : new ArrayList<>(albums);
	}

	public void setAlbums(List<Album> albums) {

		if (albums == null) {
			this.albums = null;
		} else {
			this.albums = Collections.unmodifiableList(albums);
		}
	}


	public List<Post> getPosts() {

		return posts == null ? null : new ArrayList<>(posts);
	}

	public void setPosts(List<Post> posts) {

		if (posts == null) {
			this.posts = null;
		} else {
			this.posts = Collections.unmodifiableList(posts);
		}
	}

	public List<Role> getRoles() {

		return roles == null ? null : new ArrayList<>(roles);
	}

	public void setRoles(List<Role> roles) {

		if (roles == null) {
			this.roles = null;
		} else {
			this.roles = Collections.unmodifiableList(roles);
		}
	}

	public List<Comment> getComments() {
		return comments == null ? null : new ArrayList<>(comments);
	}

	public void setComments(List<Comment> comments) {

		if (comments == null) {
			this.comments = null;
		} else {
			this.comments = Collections.unmodifiableList(comments);
		}
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getWebsite() {
		return website;
	}



	public void setWebsite(String website) {
		this.website = website;
	}



	public Company getCompany() {
		return company;
	}



	public void setCompany(Company company) {
		this.company = company;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public User() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", address=" + address + ", phone=" + phone
				+ ", website=" + website + ", roles=" + roles + ", todos=" + todos + ", albums=" + albums + ", posts="
				+ posts + ", comments=" + comments + ", company=" + company + "]";
	}



	public User(Long id, @NotBlank @Size(max = 40) String firstName, @NotBlank @Size(max = 40) String lastName,
			@NotBlank @Size(max = 15) String username, @NotBlank @Size(max = 100) String password,
			@NotBlank @Size(max = 40) @Email String email, Address address, String phone, String website,
			List<Role> roles, List<Todo> todos, List<Album> albums, List<Post> posts, List<Comment> comments,
			Company company) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.website = website;
		this.roles = roles;
		this.todos = todos;
		this.albums = albums;
		this.posts = posts;
		this.comments = comments;
		this.company = company;
	}
	
	
}
