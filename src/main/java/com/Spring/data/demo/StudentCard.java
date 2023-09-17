package com.Spring.data.demo;

import jakarta.persistence.*;

@Entity(name = "StudentCard")
@Table(name = "student_card",
        uniqueConstraints = {
        @UniqueConstraint(name = "student_card_name_unique",columnNames = "card_name")
        }
    )
public class StudentCard {
    @Id
    @SequenceGenerator(
            name = "Student_card_sequence",
            sequenceName = "Student_card_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "Student_card_sequence"
    )
    @Column(name = "id",updatable = false)
    private Long id;
    @Column(name = "card_name",nullable = false)
    private String cardName;

    @OneToOne
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )
    )
    private Student student;


    public StudentCard() {
    }

    public StudentCard(String cardName) {
        this.cardName = cardName;
    }

    public StudentCard(String cardName, Student student) {
        this.cardName = cardName;
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "StudentCard{" +
                "id=" + id +
                ", cardName='" + cardName + '\'' +
                '}';
    }
}
