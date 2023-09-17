package com.Spring.data.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findsStudentByEmail(String email);

    @Query("SELECT s from Student s where s.firstName =  ?1 and s.age >= ?2")
    List<Student> selectStudentsWhereFirstNameAndAgeGreaterOrEqual(String firstName, int age);

    @Query(value = "SELECT * from student WHERE first_name = :firstName and age>= :age", nativeQuery = true)
    List<Student> selectStudentsWhereFirstNameAndAgeGreaterOrEqualNative(@Param("firstName") String firstName, @Param("age") int age);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student s where s.id = ?1")
    int deleteStudentById(Long id);
}
