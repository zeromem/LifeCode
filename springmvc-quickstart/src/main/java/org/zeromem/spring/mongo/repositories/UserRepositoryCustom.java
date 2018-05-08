package org.zeromem.spring.mongo.repositories;

import org.zeromem.spring.mongo.documents.User;

import java.util.List;

/**
 * Created by zeromem on 2017/6/21.
 */
public interface UserRepositoryCustom {
	List<User> customFind(String first);

	List<User> aggregateTest();
}
