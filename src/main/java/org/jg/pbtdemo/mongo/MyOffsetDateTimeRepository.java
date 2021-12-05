package org.jg.pbtdemo.mongo;

import org.jg.pbtdemo.MyOffsetDateTime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyOffsetDateTimeRepository extends MongoRepository<MyOffsetDateTime, String> {

}
