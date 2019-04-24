package unibs.fp.it.codicefiscale;

import java.io.FileInputStream;
import java.util.*;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;



public class CodiciFiscaliMain {

	public static void main(String[] args) {
		Dati letti = new Dati();
		Persona p= new Persona();
		ArrayList<CodiceFiscale> codici;
		codici = letti.leggiCodiciFiscali();
		int contatore = 0; 
		
		for (int i = 0; i < codici.size(); i++) {
			if (Controllore.controllaFormato(codici.get(i))) {
				// scrivi su nuovo xml
				contatore ++; // conta i codici fiscali validi, nel caso dovesse servire
			}
			
		}
		
		
		
		
		
		
		//d.leggiInputPersone(p);
	    
		//System.out.println("messaggio" + d.leggiInputPersone() );
		//Persona p = new Persona();
		/*p.setCognome("giordani");
		ArrayList<Persona> persone = new ArrayList<>();
		persone.add(p);
		p.setCognome("ponzin");
		persone.add(p);*/
		//p.stampa(d.);
		
		
		
		
		}
}