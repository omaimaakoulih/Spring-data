package com.Spring.data.demo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCardRepo extends JpaRepository<StudentCard, Long> {

}
