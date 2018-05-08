package org.zeromem.spring.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.zeromem.spring.mongo.documents.User;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String>, UserRepositoryCustom {
	public User findByFirstName(String firstName);
	public List<User> findByLastName(String lastName);
}
