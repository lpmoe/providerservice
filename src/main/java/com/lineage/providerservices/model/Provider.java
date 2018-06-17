package com.lineage.providerservices.model;

import java.util.List;

public class Provider {
	private String id;
	private String name;
	private String description;
	private List<OfferedService> courses;

	public Provider(String id, String name, String description,
			List<OfferedService> courses) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.courses = courses;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OfferedService> getOfferedServices() {
		return courses;
	}

	public void setCourses(List<OfferedService> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return String.format(
				"Provider [id=%s, name=%s, description=%s, courses=%s]", id,
				name, description, courses);
	}
}