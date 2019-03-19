package evaluator.controller;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;
import evaluator.model.Test;
import evaluator.repository.IntrebariRepository;
import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.NotAbleToCreateStatisticsException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.util.InputValidation;

public class AppController {
	private InputValidation inputValidation;
	private IntrebariRepository intrebariRepository;
	
	public AppController(String fileName) {
		intrebariRepository = new IntrebariRepository(fileName);
	}
	
	public Intrebare addNewIntrebare(Intrebare intrebare) throws DuplicateIntrebareException{
		
		intrebariRepository.addIntrebare(intrebare);
		
		return intrebare;
	}
	
	public boolean exists(Intrebare intrebare){
		return intrebariRepository.exists(intrebare);
	}
	
	public Test createNewTest() throws NotAbleToCreateTestException{
		
		if(intrebariRepository.getIntrebari().size() < 3)
			throw new NotAbleToCreateTestException("Nu exista suficiente intrebari pentru crearea unui test!(5)");
		
		if(intrebariRepository.getNumberOfDistinctDomains() < 4)
			throw new NotAbleToCreateTestException("Nu exista suficiente domenii pentru crearea unui test!(5)");
		
		List<Intrebare> testIntrebari = new LinkedList<Intrebare>();
		List<String> domenii = new LinkedList<String>();
		Intrebare intrebare;
		Test test = new Test();
		
		while(testIntrebari.size() != 7){
			intrebare = intrebariRepository.pickRandomIntrebare();
			
			if(testIntrebari.contains(intrebare) && !domenii.contains(intrebare.getDomeniu())){
				testIntrebari.add(intrebare);
				domenii.add(intrebare.getDomeniu());
			}
			
		}
		
		test.setIntrebari(testIntrebari);
		return test;
		
	}
	
	public void loadIntrebariFromFile(String f){
		try {
			intrebariRepository.setIntrebari(intrebariRepository.loadIntrebariFromFile(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Statistica getStatistica() throws NotAbleToCreateStatisticsException{
		
		if(intrebariRepository.getIntrebari().isEmpty())
			throw new NotAbleToCreateStatisticsException("Repository-ul nu contine nicio intrebare!");
		
		Statistica statistica = new Statistica();
		for(String domeniu : intrebariRepository.getDistinctDomains()){
			statistica.add(domeniu, intrebariRepository.getIntrebari().size());
		}
		
		return statistica;
	}

	public Intrebare addNewIntrebare(String enunt, String varianta1, String varianta2, String varianta3, String variantaCorecta, String domeniu) throws InputValidationFailedException, DuplicateIntrebareException {
		InputValidation.validateEnunt(enunt);
		InputValidation.validateVarianta1(varianta1);
		InputValidation.validateVarianta2(varianta2);
		InputValidation.validateVarianta3(varianta3);
		InputValidation.validateVariantaCorecta(variantaCorecta);
		InputValidation.validateDomeniu(domeniu);

		Intrebare intrebare = new Intrebare(
				enunt,
				varianta1,
				varianta2,
				varianta3,
				variantaCorecta,
				domeniu
		);

		intrebariRepository.addIntrebare(intrebare);

		return intrebare;
	}
}
