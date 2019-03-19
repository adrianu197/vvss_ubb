package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import evaluator.exception.DuplicateIntrebareException;
import evaluator.exception.InputValidationFailedException;
import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.AppController;
import evaluator.exception.NotAbleToCreateStatisticsException;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

	private static final String file = "/home/adrian/Documents/school/VVSS/Lab/lab1/src/main/resources/intrebari.txt";
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		
		AppController appController = new AppController(file);
		
		boolean activ = true;
		String optiune;
		
		while(activ){
			
			System.out.println("");
			System.out.println("1.Adauga intrebare");
			System.out.println("2.Creeaza test");
			System.out.println("3.Statistica");
			System.out.println("4.Exit");
			System.out.println("");
			
			optiune = console.readLine();
			
			switch(optiune){
				case "1" :
					String enunt,varianta1,varianta2,varianta3,variantaCorecta,domeniu;
					System.out.print("Introduceti enuntul intrebarii:");
					enunt = console.readLine();
					System.out.print("Introduceti varianta 1:");
					varianta1 = console.readLine();
					System.out.print("Introduceti varianta 2:");
					varianta2 = console.readLine();
					System.out.print("Introduceti varianta 3:");
					varianta3 = console.readLine();
					System.out.print("Introduceti varianta corecta:");
					variantaCorecta = console.readLine();
					System.out.print("Introduceti domeniul intrebarii:");
					domeniu = console.readLine();
					try {
						appController.addNewIntrebare(enunt,
								varianta1,
								varianta2,
								varianta3,
								variantaCorecta,
								domeniu);
					} catch (DuplicateIntrebareException | InputValidationFailedException e) {
						System.out.println("A aparut urmatoarea eroare:" + e.getMessage());
					}
					break;
				case "2" :
					try {
						appController.createNewTest();
					} catch (NotAbleToCreateTestException e) {
						System.out.println("A aparut urmatoarea eroare:" + e.getMessage());
					}
					break;
				case "3" :
					appController.loadIntrebariFromFile(file);
					Statistica statistica;
					try {
						statistica = appController.getStatistica();
						System.out.println(statistica);
					} catch (NotAbleToCreateStatisticsException e) {
						System.out.println("A aparut urmatoarea eroare:" + e.getMessage());
					}

					break;
				case "4" :
					activ = false;
					break;
				default:
					break;
			}
		}
		
	}

}
