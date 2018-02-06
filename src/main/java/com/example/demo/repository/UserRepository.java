package com.example.demo.repository;

import com.example.demo.repository.data.User;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveCassandraRepository<User, String> {
}
