package unibs.fp.it.codicefiscale;

import java.io.FileInputStream;
import java.util.*;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class CodiciFiscaliMain {

	public static void main(String[] args) {
		
		
		Dati d = new Dati();
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