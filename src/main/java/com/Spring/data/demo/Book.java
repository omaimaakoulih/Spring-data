package com.Spring.data.demo;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "Book")
@Table(
        name = "book",
        uniqueConstraints = {
                @UniqueConstraint(name = "book_unique", columnNames = "book_name")
        }
)
public class Book {
    @Id
    @SequenceGenerator(
            name = "Book_sequence",
            sequenceName = "Book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Book_sequence"
    )
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "book_name", nullable = false)
    private String bookName;

    @ManyToOne
    @JoinColumn(
            name = "student_id",
            nullable = true,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_book_fk"
            )
    )
    private Student student;

    public Book() {
    }

    public Book(LocalDateTime createdAt, String bookName) {
        this.createdAt = createdAt;
        this.bookName = bookName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
