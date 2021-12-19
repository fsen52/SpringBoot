package springboot_kurs_controller;
import org.springframework.stereotype.Component;

@Component //Class'tan bir obje olusturur. new StudentBean01(); gibi çalışır.
public class StudentBean01 implements StudentInterface {
	private String name;
	private int age;
	private String id;

	public StudentBean01() {
		System.out.println("Constructor without parameter is used");
	}

	public StudentBean01(String name, int age, String id) {
		this.name = name;
		this.age = age;
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "StudentBean01: name=" + name + ", age=" + age + ", id=" + id;
	}
	
	@Override
	public String study() {
		return "StudentBean01 class'ından geliyorum.";
	}


		
		
}
