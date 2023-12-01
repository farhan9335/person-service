package com.example.mypack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.mypack.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, String> {

}
