package unibs.fp.it.codicefiscale;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class CodiciFiscaliMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dati d = new Dati();
	    
		System.out.println("messaggio" + d.leggiInputPersone() );
		Persona p = new Persona();
		p.setCognome("giordani");
		ArrayList<Persona> persone = new ArrayList<>();
		persone.add(p);
		p.setCognome("ponzin");
		persone.add(p);
	}

}