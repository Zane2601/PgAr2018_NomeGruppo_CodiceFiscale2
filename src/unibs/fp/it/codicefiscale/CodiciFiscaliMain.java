package unibs.fp.it.codicefiscale;

import java.io.FileInputStream;
import java.util.*;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import it.infoJava.CodiceFiscale;
import it.infoJava.Metodi;

public class CodiciFiscaliMain {

	public static void main(String[] args) {
		Dati letti = new Dati();
		Controllore controllore = new Controllore();
		
		ArrayList<CodiceFiscale> codici;
		codici = letti.leggiCodiciFiscali();
		int contatore = 0; 
		
		for (int i = 0; i < codici.size(); i++) {
			if (controllore.controllaFormato(codici.get(i))) {
				// scrivi su nuovo xml
				contatore ++; // conta i codici fiscali validi, nel caso dovesse servire
			}
			
		}
		
		
		
		
		
		
		//d.leggiInputPersone(p);
	    Persona p= new Persona();
		//System.out.println("messaggio" + d.leggiInputPersone() );
		//Persona p = new Persona();
		/*p.setCognome("giordani");
		ArrayList<Persona> persone = new ArrayList<>();
		persone.add(p);
		p.setCognome("ponzin");
		persone.add(p);*/
		//p.stampa(d.);
		
	    Scanner scanner = new Scanner (System.in);
	    Controllore controllore = new Controllore();
	    String provaCodice = scanner.next();
	    
	    if (controllore.controllaFormato(provaCodice)) {
	      System.out.printf("Formato del codice fiscale VALIDO");
	    }else {
	    	System.out.printf("Formato del codice fiscale NON VALIDO");
	    }
		
		
		
		}
}