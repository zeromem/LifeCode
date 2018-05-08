package org.zeromem.spring.mongo.repositories;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.zeromem.spring.mongo.documents.User;

import java.util.List;

/**
 * Created by zeromem on 2017/6/21.
 */
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private static final Logger log = LoggerFactory.getLogger(UserRepositoryCustomImpl.class);

	@Autowired
	public MongoTemplate mongoTemplate;

	@Override
	public List<User> customFind(String first) {
		log.info("ZEROMEM: YES! call UserRepositoryCustomImpl.findByFirstName()");
		Query query = new Query();
		query.addCriteria(Criteria.where("firstName").is(first));
		return mongoTemplate.find(query, User.class);
	}

	@Override
	public List<User> aggregateTest() {
		Aggregation agg = newAggregation(
			match(Criteria.where(""))
		);
		return null;
	}
}

