package springboot_kurs_controller_service_repository_form_based_authentication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentBean04Service {

	private StudentBean04Repository studentRepo;

	@Autowired
	public StudentBean04Service(StudentBean04Repository studentRepo) {
		this.studentRepo = studentRepo;
	}

	public StudentBean04 selectStudentById(Long id) {
		
		if(studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}
		
		return new StudentBean04();

	}

	public List<StudentBean04> selectAllStudents(){
		
		return studentRepo.findAll();
	}
}
