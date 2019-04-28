package unibs.fp.it.codicefiscale;

import java.io.FileInputStream;
import java.util.*;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;



public class CodiciFiscaliMain {

	public static void main(String[] args) {
		ArrayList <Persona> listaDiPersone;
		ArrayList<String> codiciPersone = new ArrayList<String>();
		
		Dati d = new Dati();
		
		listaDiPersone = d.leggiInputPersone(); 
		for (int k = 0; k < listaDiPersone.size(); k++) {
			codiciPersone.add(Creazione.creaCodice(listaDiPersone.get(k)));
			System.out.println(Creazione.creaCodice(listaDiPersone.get(k)));
		}
		
		
		ArrayList<CodiceFiscale> codiciLetti = new ArrayList<CodiceFiscale>();
		codiciLetti = d.leggiCodiciFiscali();
		int contatoreGiusti = 0; 
		int contatoreSbagliati = 0;
		
		for (int i = 0; i < codiciLetti.size(); i++) {
			if (Controllore.controllaFormato(codiciLetti.get(i))) {
				// scrivi su nuovo xml
				contatoreGiusti ++; // conta i codici fiscali validi, nel caso dovesse servire
			} else {
				contatoreSbagliati++;
			}
			
		}
		System.out.println("I codici giusti sono: " +contatoreGiusti);
		System.out.println("I codici sbagliati sono: " +contatoreSbagliati);
		
		}
}