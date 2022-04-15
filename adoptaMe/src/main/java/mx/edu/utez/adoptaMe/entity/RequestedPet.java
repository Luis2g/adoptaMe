package mx.edu.utez.adoptaMe.entity;

import java.util.List;

public class RequestedPet {

	private Pet pet;
	private List<User> users;
	private User adopter;
	private String jsonForFront;
	
	
	public RequestedPet() {
		
	}
	
	public RequestedPet(Pet pet, List<User> users) {
		this.pet = pet;
		this.users = users;
	}
	
	public RequestedPet(Pet pet, User adopter) {
		this.pet = pet;
		this.adopter = adopter;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getJsonForFront() {
		return jsonForFront;
	}

	public void setJsonForFront(String jsonForFront) {
		this.jsonForFront = jsonForFront;
	}

	public User getAdopter() {
		return adopter;
	}

	public void setAdopter(User adopter) {
		this.adopter = adopter;
	}
	
}
