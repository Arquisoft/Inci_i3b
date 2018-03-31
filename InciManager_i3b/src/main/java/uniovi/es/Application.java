package uniovi.es;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniovi.es.entities.Operator;
import uniovi.es.repositories.OperatorRepository;



@SpringBootApplication
public class Application implements CommandLineRunner{

	@Autowired
    private OperatorRepository operatorRepository;
	    
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String...strings) throws Exception {
		if(operatorRepository.count()==0)
    		addMockOperators();
	}
	
	
	private void addMockOperators() {
		Operator o1 = new Operator("operator1", "asd");
		Operator o2 = new Operator("operator2", "asd");
		Operator o3 = new Operator("operator3", "asd");
		Operator o4 = new Operator("operator4", "asd");
		operatorRepository.insert(o1);
		operatorRepository.insert(o2);
		operatorRepository.insert(o3);
		operatorRepository.insert(o4);
		
	}
}