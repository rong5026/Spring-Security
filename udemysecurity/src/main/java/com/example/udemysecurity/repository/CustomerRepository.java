package com.example.udemysecurity.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.udemysecurity.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> { // 자동으로 CRUD 해주는 JPA

	List<Customer> findByEmail(String email);
}
