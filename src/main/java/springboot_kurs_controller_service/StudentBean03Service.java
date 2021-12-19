package springboot_kurs_controller_service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentBean03Service {

	List<StudentBean03> listofStudents = List.of(

			new StudentBean03(101L, "Ali Can", "a@b.c", LocalDate.of(2008, 8, 8)),
			new StudentBean03(102L, "Veli Han", "d@b.c", LocalDate.of(1996, 6, 6)),
			new StudentBean03(103L, "Ayse Tan", "t@b.c", LocalDate.of(2000, 1, 1)),
			new StudentBean03(104L, "Mary Star", "m@b.c", LocalDate.of(1995, 5, 5))

	);

	public StudentBean03 getStudentById(Long id) {

		if (listofStudents.stream().filter(t -> t.getId() == id).collect(Collectors.toList()).isEmpty()) {

			return new StudentBean03();

		}

		return listofStudents.stream().filter(t -> t.getId() == id).findFirst().get();

	}
	
	public List<StudentBean03> getAllStudents() {
		return listofStudents;
	}

}
