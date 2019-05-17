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
		ArrayList <String> codiciPersone = new ArrayList<String>();
		
		Dati d = new Dati();
		
		listaDiPersone = d.leggiInputPersone(); 
		for (int k = 0; k < listaDiPersone.size(); k++) {
			codiciPersone.add(Creazione.creaCodice(listaDiPersone.get(k)));
			//System.out.println(Creazione.creaCodice(listaDiPersone.get(k)));
		}
		
		//System.out.println(listaDiPersone);
		ArrayList<CodiceFiscale> codiciLetti = new ArrayList<CodiceFiscale>();
		codiciLetti = d.leggiCodiciFiscali();
		int contatoreGiusti = 0; 
		int contatoreSbagliati = 0;
		
		ArrayList <String> codiciLettiString = new ArrayList<String>();
		for (int i = 0; i < codiciLetti.size(); i++) {
			codiciLettiString.add(codiciLetti.get(i).toString());
		}
		
		for (int i = 0; i < codiciLetti.size(); i++) {
			if (Controllore.controllaFormato(codiciLettiString.get(i))) {
				// scrivi su nuovo xml
				contatoreGiusti ++; // conta i codici fiscali validi, nel caso dovesse servire
			} else {
				contatoreSbagliati++;
			}
			
		}
		
		int contatorePresente = 0;
		int contatoreAssente = 0;
		int contatoreSpaiati=0;
		/*
			for (int i = 0; i < listaDiPersone.size(); i++) {
				for (int k=0; k < codiciLetti.size(); k++) {
				if (codiciLetti.get(k).toString().equals(codiciPersone.get(i))) {
					contatorePresente++;
				}
				
				
			    }
			}
		*/
		
		
		
		
		
		for (int i = 0; i < codiciPersone.size(); i++) {
			if (codiciLettiString.contains(codiciPersone.get(i))) {
				System.out.println(codiciPersone.get(i)+" CONTENUTO");
				contatorePresente++;
		    }/*else if(codiciPersone.contains(codiciLettiString.get(i)) && Controllore.controllaFormato(codiciPersone.get(i))) {
			contatoreSpaiati++;
		    }*/else  {
				System.out.println(codiciPersone.get(i)+" NON C'é");
				contatoreAssente++;
			}
		}
		
		
		
		
		System.out.println("I codici giusti sono: " + contatoreGiusti);
		System.out.println("I codici sbagliati sono: " + contatoreSbagliati);
		System.out.println("I codici Spaiati sono:" + contatoreSpaiati);
		System.out.println("I codici presenti sono "+ contatorePresente+" I codici non presenti sono :"+contatoreAssente);
		
		}
	//////////////////////////////////////////////////////
	//codice Flavio
	/*import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Classe Main, gestisce i file di input e produce il file di output
 *
 * @author Flavio
 
public class Main {
    public static final String INPUT_PERSONE = "inputPersone.xml";
    public static final String INPUT_CODICI_FISCALI = "codiciFiscaliAncheFalzi.xml";
    private static final String RICHIESTA_OUTPUT = "Inserire il nome del file di output -> ";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String output;
        LinkedList<String> codici = getCodici(); //lista dei codici presenti nel file codiciFiscali.xml
        LinkedList<CodiceFiscale> codiciVeri = new LinkedList<>(); //codici estratti dal file che rispettano una sintassi corretta
        LinkedList<CodiceFiscale> codiciFalsi = new LinkedList<>(); //codici estratti dal file che non rispettano una sintassi corretta
        LinkedList<Persona> persone = getPersone(); //lista delle persone presenti nel file inputPersone.xml

        //questo ciclo divide i codici corretti da quelli errati in 2 liste diverse
        for (String codice : codici){
            if(CodiceFiscale.isCodiceFiscale(codice))
                codiciVeri.add(new CodiceFiscale(codice));
            else {
                codiciFalsi.add(new CodiceFiscale(codice));
            }
        }
        //questo ciclo inizializza il parametro codiceFiscale delle persone con il codice presente nel file codiciFiscali.xml
        for(Persona persona : persone){
            CodiceFiscale codice = new CodiceFiscale(persona);
            if(codiciVeri.contains(codice)){
                persona.initCodiceFiscale();
                codiciVeri.remove(codice);
            }
        }
        System.out.print(RICHIESTA_OUTPUT);
        output = in.next();
        write(output, persone, codiciVeri, codiciFalsi); //scrivo il file di output
    }

    /**
     *
     * @param fileName nome del file di output
     * @param persone lista delle persone da scrivere in formato xml
     * @param codiciSpaiati lista dei codici dalla sintassi corretta ma senza corrispondenza nel file inputPersone.xml
     * @param codiciFalsi lista dei codici dalla sintassi errata
     
    private static void write(String fileName, LinkedList<Persona> persone, LinkedList<CodiceFiscale> codiciSpaiati, LinkedList<CodiceFiscale> codiciFalsi) {
        System.out.println("Sto scrivendo il file...");
        XMLOutputFactory output = XMLOutputFactory.newInstance();
        XMLStreamWriter writer;

        try {
            writer = output.createXMLStreamWriter(new FileWriter(fileName));
            writer.writeStartDocument("utf-8","1.0");
            writer.writeStartElement("output"); //apro il tag output
            writer.writeStartElement("persone"); //apro il tag persone
            writer.writeAttribute("numero", persone.size()+""); //scrivo nel tag persone quanti sottoelementi contiene
           //in questo ciclo scrivo nel file tutte le persone col rispettivo codice fiscale
            for(Persona persona: persone) {
                writer.writeStartElement("persona"); //apro il tag persona
                writer.writeAttribute("id", persona.getId()+""); //scrivo nel tag persona l'id
                writer.writeStartElement("nome"); //apro il tag nome
                writer.writeCharacters(persona.getNome()); //scrivo il nome della persona
                writer.writeEndElement(); // chiudo nome
                writer.writeStartElement("cognome"); //apro il tag cognome
                writer.writeCharacters(persona.getCognome()); //scrivo il cognome della persona
                writer.writeEndElement(); //chiudo cognome
                writer.writeStartElement("sesso"); //apro il tag sesso
                writer.writeCharacters(persona.getSesso()+""); //scrivo il sesso della persona
                writer.writeEndElement(); //chiudo sesso
                writer.writeStartElement("comune_nascita"); //apro il tag comune_nascita
                writer.writeCharacters(persona.getLuogoNascita()); //scrivo il luogo di nascita della persona
                writer.writeEndElement(); //chiudo comune_nascita
                writer.writeStartElement("data_nascita"); //apro il tag data_nascita
                writer.writeCharacters(persona.getDataNascita()); //scrivo la data di nascita della persona
                writer.writeEndElement(); //chiudo data_nascita
                writer.writeStartElement("codice_fiscale"); //apro il tag codice_fiscale
                if(!persona.hasCodiceFiscale()) //controllo se la persona ha il codice fiscale (se non lo ha vuol dire che non era presente nel file codiciFiscali.xml
                    writer.writeCharacters("ASSENTE"); //se non lo ha scrivo assente
                else
                    writer.writeCharacters(persona.getCodiceFiscale().toString()); // se lo ha scrivo il codice fiscale
                writer.writeEndElement(); //chiudo  codice_fiscale
                writer.writeEndElement(); //chiudo persona
            }
            writer.writeEndElement(); //chiudo persone
            writer.writeStartElement("codici"); //apro il tag codici
            writer.writeStartElement("invalidi"); //apro il tag invalidi
            writer.writeAttribute("numero",codiciFalsi.size()+""); //scrivo in invalidi quanti sottoelementi contiene
           //questo ciclo scrive tutti i codici con la sintassi errata
            for(CodiceFiscale codice : codiciFalsi){
                writer.writeStartElement("codice"); //apro il tag codice
                writer.writeCharacters(codice.toString()); //scrivo il codice errato
                writer.writeEndElement(); //chiudo codice
            }
            writer.writeEndElement(); //chiudo invalidi
            writer.writeStartElement("spaiati"); //apro il tag spaiati
            writer.writeAttribute("numero",codiciSpaiati.size()+""); //scrivo in spaiati quanti sottoelementi contiene
            //questo ciclo scrive tutti i codici con la sintassi corretta ma senza corrispondenza nel file inputoPersone
            for(CodiceFiscale codice : codiciSpaiati){
                writer.writeStartElement("codice"); //apro il tag codice
                writer.writeCharacters(codice.toString()); //scrivo il codice spaiato
                writer.writeEndElement(); //chiudo codice
            }
            writer.writeEndElement(); //chiudo spaiati
            writer.writeEndElement(); //chiudo codici
            writer.writeEndElement(); //chiudo output
            writer.writeEndDocument(); //termino il documento
            writer.flush(); //svuoto la cache di writer
            writer.close(); //chiudo writer
            System.out.println("scritto");
        }
        catch (Exception e) {
            System.out.print("Errore");
            e.printStackTrace();
        }
    }

    /**
     * Metodo che legge il file inputPersone.xml e inserisce i dati in una lista di Persone
     * @return  una lista di tipo Persona contente tutte le persone contenute in inputPersone.xml
     
    private static LinkedList<Persona> getPersone() {
        LinkedList<Persona> persone = new LinkedList<>();
        int id = 0;
        String nome = null;
        String cognome = null;
        String luogoNascita = null;
        String dataNascita = null;
        char sesso = 0;
        boolean  n=false,  c=false,  l=false,  d=false,  s=false; //flag di salvataggio (se un é attivo so che il prossimo dato che leggeró andrá nella variabile corrispondente
        boolean n1=false, c1=false, l1=false, d1=false, s1=false; //flag di dato letto (quando sono tutti attivi so che posso aggiungere una nuova persona alla lista)
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(INPUT_PERSONE, new FileInputStream(INPUT_PERSONE));
            while (xmlr.hasNext()) {
                switch (xmlr.getEventType()) {

                    //nel caso inizi un elemento contollo quale elemento é,
                    //se mi interessa attivo il flag di salvataggio corrispondente
                    case XMLStreamConstants.START_ELEMENT:
                        if (xmlr.getLocalName().equals("persona")) {
                            id = Integer.valueOf(xmlr.getAttributeValue(null, "id"));
                        }
                        if (xmlr.getLocalName().equals("nome")) {
                            n = true;
                        }
                        if (xmlr.getLocalName().equals("cognome")) {
                            c = true;
                        }
                        if (xmlr.getLocalName().equals("sesso")) {
                            s = true;
                        }
                        if (xmlr.getLocalName().equals("comune_nascita")) {
                            l = true;
                        }
                        if (xmlr.getLocalName().equals("data_nascita")) {
                            d = true;
                        }
                        break;

                    //nel caso stia leggendo un dato controllo quale flag di salvattaggio é attivo,
                    // inserisco il dato nella variabile corrispondente
                    // disattivo il flag di salvataggio e attivo il flag di dato letto
                    case XMLStreamConstants.CHARACTERS:
                        if (n) {
                            nome = xmlr.getText();
                            n1 = true;
                            n = false;
                        }
                        if (c) {
                            cognome = xmlr.getText();
                            c1 = true;
                            c = false;
                        }
                        if (s) {
                            sesso = xmlr.getText().charAt(0);
                            s1 = true;
                            s = false;
                        }
                        if (l) {
                            luogoNascita = xmlr.getText();
                            l1 = true;
                            l = false;
                        }
                        if (d) {
                            dataNascita = xmlr.getText();
                            d1 = true;
                            d = false;
                        }
                        break;
                    default:
                        break;
                }
                //se tutti i flag di dato letto sono attivi li disattivo e aggiungo una nuova persona alla lista di perone
                if (c1 && n1 && s1 && l1 && d1 ) {
                    s1 = false;
                    n1 = false;
                    l1 = false;
                    d1 = false;
                    c1 = false;
                    persone.add(new Persona(nome, cognome, dataNascita, luogoNascita, sesso, id));
                }
                xmlr.next(); // leggo la prossima riga
            }
        } catch (Exception e) {
            System.err.println("Errore");
            System.err.println(e.getMessage());
        }
        return persone;
    }

    /**
     * metodo che legge il file codiciFiscali.xml e inserisce i dati in una lista di Codici Fiscalei
     * @return una lsita di tipo CodiceFiscale contente tutti codici fiscali contenuti in codiciFiscali.xml
     
    private static LinkedList<String> getCodici() {
        LinkedList<String> codici = new LinkedList<>();
        String codice = null;
        boolean c = false, c1 = false; //flag di salvataggio e flag di codice letto
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(INPUT_CODICI_FISCALI, new FileInputStream(INPUT_CODICI_FISCALI));
            while (xmlr.hasNext()) {
                switch (xmlr.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT: //inizia un elemento
                        //se é un codice fiscale attivo il flag di salvataggio
                        if (xmlr.getLocalName().equals("codice")) {
                            c = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        //se il flag di lettura salvo il codice nella variabile, attivo il flag di dato salvato e disattivo quello di salvataggio
                        if (c) {
                            codice = xmlr.getText();
                            c1 = true;
                            c = false;
                        }
                        break;
                    default:
                        break;
                }
                //se il flag di dato salvato é attivo aggiungo il codice alla lista
                if (c1) {
                    c1 = false;
                    codici.add(codice);
                }
                xmlr.next();
            }
        } catch (Exception e) {
            System.err.println("Errore");
            System.err.println(e.getMessage());
        }
        return codici;
    }
}*/
}