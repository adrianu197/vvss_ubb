package evaluator.util;


import evaluator.exception.InputValidationFailedException;

public class InputValidation {

	public static void validateEnunt(String enunt) throws InputValidationFailedException{

		enunt = enunt.trim();
		
		if(enunt.equals(""))
			throw new InputValidationFailedException("Enuntul este vid!");
		if(!Character.isUpperCase(enunt.charAt(0)))
			throw new InputValidationFailedException("Prima litera din enunt nu e majuscula!");
		if(!String.valueOf(enunt.charAt(enunt.length()-1)).equals("?"))
			throw new InputValidationFailedException("Ultimul caracter din enunt nu e '?'!");
		if(enunt.length() > 100)
			throw new InputValidationFailedException("Lungimea enuntului depaseste 100 de caractere!");
		
	}
	
	public static void validateVarianta1(String varianta1) throws InputValidationFailedException{
		
		varianta1 = varianta1.trim();
		
		if(varianta1.equals(""))
			throw new InputValidationFailedException("Varianta1 este vida!");
		if(!String.valueOf(varianta1.charAt(0)).equals("1") || !String.valueOf(varianta1.charAt(1)).equals(")"))
			throw new InputValidationFailedException("Varianta1 nu incepe cu '1)'!");
		if(varianta1.length() > 50)
			throw new InputValidationFailedException("Lungimea variantei1 depaseste 50 de caractere!" );
	}
	
	public static void validateVarianta2(String varianta2) throws InputValidationFailedException{
		
		varianta2 = varianta2.trim();
		
		if(varianta2.equals(""))
			throw new InputValidationFailedException("Varianta2 este vida!");
		if(!String.valueOf(varianta2.charAt(0)).equals("2") || !String.valueOf(varianta2.charAt(1)).equals(")"))
			throw new InputValidationFailedException("Varianta2 nu incepe cu '2)'!");
		if(varianta2.length() > 50)
			throw new InputValidationFailedException("Lungimea variantei2 depaseste 50 de caractere!" );
	}
	
	public static void validateVarianta3(String varianta3) throws InputValidationFailedException{
		
		varianta3 = varianta3.trim();
		
		if(varianta3.equals(""))
			throw new InputValidationFailedException("Varianta3 este vida!");
		if(!String.valueOf(varianta3.charAt(0)).equals("3") || !String.valueOf(varianta3.charAt(1)).equals(")"))
			throw new InputValidationFailedException("Varianta3 nu incepe cu '3)'!");
		if(varianta3.length() > 50)
			throw new InputValidationFailedException("Lungimea variantei3 depaseste 50 de caractere!" );
	
	}
	
	public static void validateVariantaCorecta(String variantaCorecta) throws InputValidationFailedException{
		
		variantaCorecta = variantaCorecta.trim();
		
		if(!variantaCorecta.equals("1") && !variantaCorecta.equals("2") && !variantaCorecta.equals("3"))
			throw new InputValidationFailedException("Varianta corecta nu este unul dintre caracterele {'1', '2', '3'}");
	}
	
	public static void validateDomeniu(String domeniu) throws InputValidationFailedException{
		
		domeniu = domeniu.trim();
		
		if(domeniu.equals(""))
			throw new InputValidationFailedException("Domeniul este vid!");
		if(!Character.isUpperCase(domeniu.charAt(0)))
			throw new InputValidationFailedException("Prima litera din domeniu nu e majuscula!");
		if(domeniu.length() > 30)
			throw new InputValidationFailedException("Lungimea domeniului depaseste 30 de caractere!");
		
	}
}
