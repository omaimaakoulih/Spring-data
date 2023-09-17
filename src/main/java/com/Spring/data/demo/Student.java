package com.Spring.data.demo;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Student")
@Table(name="student",
        uniqueConstraints = {
        @UniqueConstraint(name = "student_email_unique", columnNames = "email")
        }
)
public class Student {
    @Id
    @SequenceGenerator(
            name="Student_sequence",
            sequenceName ="Student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_sequence"
    )
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "first_name",nullable = false, columnDefinition = "TEXT")
    private String firstName;
    @Column(name = "last_name",nullable = false, columnDefinition = "TEXT")
    private String lastName;
    @Column(name = "email",nullable = false)
    private String email;
    @Column(name = "age",nullable = false)
    private int age;

    @OneToOne(
            mappedBy = "student",
            orphanRemoval = true
    )
    private StudentCard studentCard;

    @OneToMany(
            mappedBy = "student",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},  // cascade operations to be applied when changes are made to the Student entity
            fetch = FetchType.LAZY
    )
    private List<Book> books = new ArrayList<>();

    public Student( String firstName, String lastName, String email, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
    }

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }

    public List<Book> getBooks() {
        return books;
    }
    public void addBook(Book book){
        if(!this.books.contains(book)){
            this.books.add(book);
            book.setStudent(this);
        }
        else{
            this.books.remove(book);
            book.setStudent(null);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
