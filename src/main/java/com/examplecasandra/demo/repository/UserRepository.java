package com.examplecasandra.demo.repository;

import com.examplecasandra.demo.entity.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import java.util.UUID;

public interface UserRepository extends CassandraRepository<User, UUID> {
}
