package springboot_kurs_controller_service_repository_basic_authentication;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentBean05Service {

	private StudentBean05Repository studentRepo;

	@Autowired
	public StudentBean05Service(StudentBean05Repository studentRepo) {
		this.studentRepo = studentRepo;
	}

	public StudentBean05 selectStudentById(Long id) {

		if (studentRepo.findById(id).isPresent()) {
			return studentRepo.findById(id).get();
		}

		return new StudentBean05();

	}

	public List<StudentBean05> selectAllStudents() {

		return studentRepo.findAll();
	}

	public String deleteStudentById(Long id) {

		if (!studentRepo.existsById(id)) {
			throw new IllegalStateException("Id'si " + id + " olan öğrenci yok!");
		}

		studentRepo.deleteById(id);

		return "Id'si " + id + " olan öğrenci silindi...";
	}

	// PUT methodu
	public StudentBean05 updateStudentFully(Long id, StudentBean05 newStudent) {

		StudentBean05 existingStudentById = studentRepo.findById(id)
				.orElseThrow(() -> new IllegalStateException("Id'si " + id + " olan öğrenci yok..!"));

		// name degistiren adımlar

		if (newStudent.getName() == null) {
			existingStudentById.setName(null);
		} else if (existingStudentById.getName() == null) {
			existingStudentById.setName(newStudent.getName());
		} else if (!existingStudentById.getName().equals(newStudent.getName())) {
			existingStudentById.setName(newStudent.getName());
		}

		// E-mail degistiren adımlar

		Optional<StudentBean05> existingStudentByEmail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());

		if (existingStudentByEmail.isPresent()) {
			throw new IllegalStateException(newStudent.getEmail() + " alindi, baska email seciniz...");
		} else if (newStudent.getEmail() == null) {
			throw new IllegalStateException("email olmadan update yapılamaz...");
		} else if (!newStudent.getEmail().contains("@")) {
			throw new IllegalStateException("email geçerli formatta olmalı...");
		} else if (!newStudent.getEmail().equals(existingStudentById.getEmail())) {
			existingStudentById.setEmail(newStudent.getEmail());
		}

		// Dob degistiren adımlar

		if (Period.between(newStudent.getDob(), LocalDate.now()).isNegative()) {
			throw new IllegalStateException(
					newStudent.getDob() + " dogum tarihi olamaz cunku gelecek bir zamanın tarihi...");

		} else if (!newStudent.getDob().equals(existingStudentById.getDob())) {
			existingStudentById.setDob(newStudent.getDob());
		}

		// yası güncellemek gerekir
		existingStudentById.setAge(newStudent.getAge());

		// error message degistiren adımlar

		existingStudentById.setErrMsg("Update basarili bir sekilde yapildi...");

		return studentRepo.save(existingStudentById);
	}

	// PATCH methodu

	public StudentBean05 updateStudentPartially(Long id, StudentBean05 newStudent) {
		StudentBean05 existingStudentById = studentRepo.findById(id)
				.orElseThrow(() -> new IllegalStateException("Id'si " + id + " olan ogrenci yok..."));
		if (newStudent.getName() != null) {
			existingStudentById.setName(newStudent.getName());
		}

		// E-mail degistiren adımlar

		Optional<StudentBean05> existingStudentByEmail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());

		if (existingStudentByEmail.isPresent()) {
			throw new IllegalStateException(newStudent.getEmail() + " alindi, baska email seciniz...");

		} else if (newStudent.getEmail() != null) {
			existingStudentById.setEmail(newStudent.getEmail());

		} else if (newStudent.getEmail() != null && !newStudent.getEmail().contains("@")) {
			throw new IllegalStateException("email geçerli formatta olmalı...");
		}

		if (newStudent.getDob() != null) {
			existingStudentById.setDob(newStudent.getDob());
		}

		// yası güncellemek gerekir
		existingStudentById.setAge(newStudent.getAge());

		// error message degistiren adımlar

		existingStudentById.setErrMsg("Update basarili bir sekilde yapildi...");

		return studentRepo.save(existingStudentById);
	}
	
	//POST islemi
	
	public StudentBean05 addNewStudent(StudentBean05 newStudent) throws ClassNotFoundException, SQLException {
		
	Optional<StudentBean05> existingStudentByEmail = studentRepo.findStudentBean05ByEmail(newStudent.getEmail());
		if(existingStudentByEmail.isPresent()) {
			throw new IllegalStateException("Varolan email kullanilamaz");
		}
		
		if(newStudent.getName()==null) {
			throw new IllegalStateException("Isim girilmeden kayıt yapılamaz");
		}
		
		//id yi almak icin database e baglan
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "hr", "hr");
		
		Statement st = con.createStatement();
		
		String sqlQueryForMaxId = "SELECT MAX(id) FROM students";
		
		ResultSet result = st.executeQuery(sqlQueryForMaxId);
		
		Long maxId = 0L;
		
		while(result.next()) {
			maxId = result.getLong(1);
		}
		
		newStudent.setId(maxId+1);
		
		newStudent.setAge(newStudent.getAge());
		
		newStudent.setErrMsg("Yeni ogrenci kaydı basarılı bir sekilde olusturuldu");
		
		
		return studentRepo.save(newStudent);
		
		
	}
	
	
	
}
