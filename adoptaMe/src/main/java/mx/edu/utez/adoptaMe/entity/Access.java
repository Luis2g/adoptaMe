package mx.edu.utez.adoptaMe.entity;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accesses")
public class Access {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private LocalDateTime date;
	private String role;
	public long getId() {
		return id;
	}
	
	public Access() {}
	
	public Access(String username, String roles, LocalDateTime now) {
		this.username = username;
		this.role = roles;
		this.date = now;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Access [id=" + id + ", username=" + username + ", date=" + date + ", role=" + role + "]";
	}
	
	
	
}
