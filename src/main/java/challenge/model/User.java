package challenge.model;

import java.nio.charset.StandardCharsets;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import challenge.validator.EmailNotRegistered;
import com.google.common.hash.Hashing;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Nome é obrigatório")
	private String name;

	@Email(message = "Email inválido")
	@EmailNotRegistered
	@NotBlank(message = "Email é obrigatório")
	private String email;

	@JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "Senha é obrigatório")
	private String password;

	private boolean enabled = true;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private Set<Post> posts;

	public User() {

	}

	public User(Long id, @NotBlank String name, @Email String email, @NotBlank String password, boolean enabled,
			Set<Post> posts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.posts = posts;
	}

	public static User getPasswordEncryptedUser(User user) {
		user.setPassword(Hashing.sha256().hashString(user.getPassword(), StandardCharsets.UTF_8).toString());
		return user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void hidePassword() {
		this.setPassword("");
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<Post> getPosts() {
		return posts;
	}

	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}

}
