package com.Spring.data.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(StudentRepo studentRepo, StudentCardRepo studentCardRepo){
		return args -> {
			Student omaima = new Student("omaima","lastName","omaima@gmail.com",21);
			Student ahmed = new Student("ahmed","lastName","ahmed@gmail.com",25);
			Student salim = new Student("salim","marker","salim@gmail.com",23);
			Book book1 = new Book(LocalDateTime.now(), "book1");
			omaima.addBook(book1);
			omaima.addBook(new Book(LocalDateTime.now().minusDays(4),"Book2"));

			studentRepo.save(new Student(
					"first",
					"second",
					"test2@gmail.com",
					20));

			studentRepo.saveAll(List.of(ahmed,salim, omaima));
			StudentCard omaimaCard = new StudentCard("omaima Card",omaima);
			studentCardRepo.save(omaimaCard);

			studentRepo.findById(2L).ifPresentOrElse(
					System.out::println,
					()->{
						System.out.println("Student with id = 2 not found!");
					}
			);
			studentRepo.findById(4L).ifPresent(
					s -> {
						System.out.println("fetch student books");
						System.out.println(s);
						//List<Book> books = s.getBooks();
						//books.forEach(System.out::println);
					}
			);

			System.out.println("select all students");
			List<Student> allStudants = studentRepo.findAll();
			allStudants.forEach(System.out::println);

			System.out.println("number of students is : ");
			System.out.println(studentRepo.count());

			System.out.println("delete the Student Ahmed: ");
			studentRepo.deleteById(2L);

			System.out.println("using @Query: ");

			System.out.println("selectStudentsWhereFirstNameAndAgeGreaterOrEqual method : ");
			studentRepo.selectStudentsWhereFirstNameAndAgeGreaterOrEqual("salim",20).forEach(System.out::println);

			System.out.println("selectStudentsWhereFirstNameAndAgeGreaterOrEqualNative method : ");
			studentRepo.selectStudentsWhereFirstNameAndAgeGreaterOrEqualNative("salim",20).forEach(System.out::println);

			System.out.println(studentRepo.deleteStudentById(3L));



		};
	}

}
