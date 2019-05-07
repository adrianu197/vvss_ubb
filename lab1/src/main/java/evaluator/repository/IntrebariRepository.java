package evaluator.repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


import evaluator.exception.InputValidationFailedException;
import evaluator.model.Intrebare;
import evaluator.exception.DuplicateIntrebareException;

public class IntrebariRepository {

	private List<Intrebare> intrebari;
	private final String fileName;
	public IntrebariRepository(String fileName) {
		this.fileName = fileName;
		setIntrebari(new LinkedList<Intrebare>());
	}
	
	public void addIntrebare(Intrebare i) throws DuplicateIntrebareException{
		if(exists(i))
			throw new DuplicateIntrebareException("Intrebarea deja exista!");
		intrebari.add(i);
	}
	
	public boolean exists(Intrebare i){
		for(Intrebare intrebare : intrebari)
			if(intrebare.equals(i))
				return true;
		return false;
	}
	
	public Intrebare pickRandomIntrebare(){
		Random random = new Random();
		return intrebari.get(random.nextInt(intrebari.size()));
	}
	
	public int getNumberOfDistinctDomains(){
		return getDistinctDomains().size();
		
	}
	
	public Set<String> getDistinctDomains(){
		Set<String> domains = new TreeSet<String>();
		for(Intrebare intrebre : intrebari)
			domains.add(intrebre.getDomeniu());
		return domains;
	}
	
	public List<Intrebare> getIntrebariByDomain(String domain){
		List<Intrebare> intrebariByDomain = new LinkedList<Intrebare>();
		for(Intrebare intrebare : intrebari){
			if(intrebare.getDomeniu().equals(domain)){
				intrebariByDomain.add(intrebare);
			}
		}
		
		return intrebariByDomain;
	}
	
	public int getNumberOfIntrebariByDomain(String domain){
		return getIntrebariByDomain(domain).size();
	}
	
	public List<Intrebare> loadIntrebariFromFile(String f) throws FileNotFoundException {
		
		List<Intrebare> intrebari = new LinkedList<Intrebare>();
		FileReader fr = new FileReader(this.fileName);
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		List<String> intrebareAux;
		
		try{
			br = new BufferedReader(new FileReader(f));
			line = br.readLine();
			while(line != null){
				intrebareAux = new LinkedList<String>();
				while(!line.equals("##")){
					intrebareAux.add(line);
					line = br.readLine();
				}
				for (String intrebareStr:intrebareAux) {
					String[] intrebareAuxSplit = intrebareStr.split(",");
					Intrebare intrebare = null;
					intrebare = new Intrebare(
							intrebareAuxSplit[0],
							intrebareAuxSplit[1],
							intrebareAuxSplit[2],
							intrebareAuxSplit[3],
							intrebareAuxSplit[4],
							intrebareAuxSplit[5]
					);
					intrebari.add(intrebare);
				}

				line = br.readLine();
			}
		
		}
		catch (IOException e) {
			// TODO: handle exception
		}
		finally{
			try {
				br.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
		}
		
		return intrebari;
	}
	
	public List<Intrebare> getIntrebari() {
		return intrebari;
	}

	public void setIntrebari(List<Intrebare> intrebari) {
		this.intrebari = intrebari;
	}
	
}
