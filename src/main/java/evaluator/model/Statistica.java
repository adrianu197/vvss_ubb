package evaluator.model;

import java.util.HashMap;
import java.util.Map;

public class Statistica {

	private Map<String, Integer> intrebariDomenii;
	
	public Statistica() {
		intrebariDomenii = new HashMap<String, Integer>();
	}
	
	public void add(String key, Integer value){
		intrebariDomenii.put(key, value);
	}

	public Map<String, Integer> getIntrebariDomenii() {
		return intrebariDomenii;
	}

	public void setIntrebariDomenii(Map<String, Integer> intrebariDomenii) {
		this.intrebariDomenii = intrebariDomenii;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(String domeniu : intrebariDomenii.keySet()){
			sb.append(domeniu + ": " + intrebariDomenii.get(domeniu) + "\n");
		}
		
		return sb.toString();
	}

}
