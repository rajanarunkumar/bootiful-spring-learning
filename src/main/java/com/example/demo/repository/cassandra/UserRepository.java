package com.example.demo.repository.cassandra;

import com.example.demo.repository.data.User;
import org.springframework.context.annotation.Profile;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("cassandra")
public interface UserRepository extends ReactiveCassandraRepository<User, String> {

}
