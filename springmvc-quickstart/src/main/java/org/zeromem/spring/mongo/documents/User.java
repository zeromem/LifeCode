package org.zeromem.spring.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;


@Document(collection = "col")
public class User implements Serializable {

	@Id
	public String id;

	@Field("firstName")
	public String firstName;

	@Field("lastName")
	public String lastName;

	public User() {}

	public User(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.format(
				"User[id=%s, firstName='%s', lastName='%s']",
				id, firstName, lastName);
	}
}
