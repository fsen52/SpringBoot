package springboot_kurs_controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController
public class StudentBean01Controller {
	
//	@RequestMapping(method=RequestMethod.GET, path="/getRequest")
//	@ResponseBody //Return edilen datayı görmek için bu annotation kullanılır.
//	public String getMethod1() {
//		return "Get Request Method1 calistirildi";
//	}

	
	@GetMapping(path="/getString")
	public String getMethod1() {
		return "Get Request Method1 calistirildi";
	}
	
	//Tight Coupling

	@GetMapping(path="/getObject")
	public StudentBean01 getMethod2() {
		return new StudentBean01("Ali Can", 13, "AC2113");
	}

	//Loose Coupling

	@Autowired //atama operatörü gibi davranır, @Component in oluşturduğu objeyi atar.
	StudentBean01 s;
	
	@GetMapping(path="/getObjectLoose")
	public StudentBean01 getMethod3() {
		s.setName("Ali Han");
		s.setAge(14);
		s.setId("AH2114");
		
		return s;
	}

	@GetMapping(path="/getObjectParametreli/{school}")
	public StudentBean01 getMethod4 (@PathVariable String school ) {
		s.setName("Faruk Tan");
		s.setAge(15);
		s.setId(String.format("FT21%s15", school));
		
		return s;
	}

	@GetMapping(path="/getObjectList")
	public List<StudentBean01> getMethod5 (){
		
	return	List.of(
				new StudentBean01("Ali Can", 13, "AC2113"),
				new StudentBean01("Ali Tan", 23, "AT2123"),
				new StudentBean01("Veli Dan", 43, "VD2143")
				);
		
	}

	@Autowired
	StudentBean02 t;
	
	
	@GetMapping(path="/getObjectStudy")
	public String getMethod7() {
		return t.study();
	}


	@Autowired
	@Qualifier(value="studentBean02") //@Autowired anotasyonu data tipine bakar @Qualifier ise obje ismine bakar.
	StudentInterface u;
	
	
	@GetMapping(path="/getObjectStudy2")
	public String getMethod8() {
		return u.study();
	}

	
	
	
	
}
