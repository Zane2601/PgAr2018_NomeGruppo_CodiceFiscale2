package unibs.fp.it.codicefiscale;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;


public class Dati {
	XMLInputFactory xmlif = null;
	XMLStreamReader xmlr=null;
	
	
	private String codiciFiscali="codiciFiscali.xml";
	private String comuni = "comuni.xml";
	private String inputPersone ="inputPersone.xml";
	
	ArrayList<CodiceFiscale> codici = new ArrayList<CodiceFiscale>();
	
	
	public ArrayList<CodiceFiscale> leggiCodiciFiscali() {
		
		boolean flag = false;
		int j = 0;
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(codiciFiscali, new FileInputStream(codiciFiscali));
			
			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					break;
				case XMLStreamConstants.START_ELEMENT:
					if(xmlr.getLocalName().equals("codice")) {
						CodiceFiscale codiceOggetto = new CodiceFiscale(xmlr.getElementText());
						codici.add(codiceOggetto);
						flag = true;
					}
						
					break;
				case XMLStreamConstants.END_ELEMENT:
					break;
				case XMLStreamConstants.CHARACTERS:
					if (flag==true) {
						codici.add(new CodiceFiscale(xmlr.getText()));
					    flag = false;
					}
					break;
				}
				xmlr.next();
			}//fine while
			
		}//fine try 
		catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage()); 
			}
		return codici;
	}
	/*
	public String leggiComuni(Persona p) {
		
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(comuni, new FileInputStream(comuni)); 
			 while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione 
				 switch (xmlr.getEventType()) { // switch sul tipo di evento
				 case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento 
					 System.out.println("Start Read Doc " + comuni); 
					 break;
			 
				 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi 
					 System.out.println("Tag " + xmlr.getLocalName());
					// if)getLocalname.equals(codiuce)
					//	codici.add(codice)
					 for (int i = 0; i < xmlr.getAttributeCount(); i++)
						 System.out.printf(" => attributo %s->%s%n", xmlr.getAttributeLocalName(i), xmlr.getAttributeValue(i)); 
					 break;
			     case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso 
			    	 System.out.println("END-Tag " + xmlr.getLocalName()); 
			    	 break;
			     case XMLStreamConstants.COMMENT:
			         System.out.println("// commento " + xmlr.getText()); 
			         break; // commento: ne stampa il contenuto
			     case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo 
			    	 if (xmlr.getText().trim().length() > 0) // controlla se il testo non contiene solo spazi
			         System.out.println("-> " + xmlr.getText());
			    	 if(xmlr.getLocalName().equals("nome")) {
			    		 if(xmlr.getText().equals(p.getComune())){
			    			 if(xmlr.getLocalName().equals("codice")) return xmlr.getText();
			    		 }
			    	 }
			    	 break;
			 }
			    xmlr.next();
			 }
		} 
		catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage()); 
			}
	}
	*/
	
	public ArrayList<Persona> leggiInputPersone() {
		ArrayList<Persona> persone=new ArrayList<Persona>();
		int k = -1;
		try {
			xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(inputPersone, new FileInputStream(inputPersone)); 
			

			while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione 
				 switch (xmlr.getEventType()) { // switch sul tipo di evento
				 case XMLStreamConstants.START_DOCUMENT: // inizio del documento: stampa che inizia il documento 
					 
					 //System.out.println("Start Read Doc " + inputPersone); 
					 break;
			 
				 case XMLStreamConstants.START_ELEMENT: // inizio di un elemento: stampa il nome del tag e i suoi attributi 
					switch (xmlr.getLocalName()) {
					case "persona":
						 //System.out.println("Tag " + xmlr.getLocalName());
						 Persona p = new Persona();
						 persone.add(p);
						 k++;
						break;
						
					case "nome":
						persone.get(k).setNome(xmlr.getElementText());
						break;
						
					case "cognome":
						persone.get(k).setCognome(xmlr.getElementText());
						break;
					
					case "comune_nascita":
						persone.get(k).setComune(xmlr.getElementText());
						break;
					
					case "sesso":
						persone.get(k).setSesso(xmlr.getElementText());
						break;
					
					case "data_nascita":
						persone.get(k).setData(xmlr.getElementText());
						break;
					
					default:
						break; 
			    	
			    	 }
					 
										
					 break;
			     case XMLStreamConstants.END_ELEMENT: // fine di un elemento: stampa il nome del tag chiuso 
			    	 //System.out.println("END-Tag " + xmlr.getLocalName()); 
			    	 
			    	 break;
			     case XMLStreamConstants.COMMENT:
			        // System.out.println("// commento " + xmlr.getText()); 
			         break; // commento: ne stampa il contenuto
			     case XMLStreamConstants.CHARACTERS: // content all’interno di un elemento: stampa il testo 
			    	
			    	 break;
			    	 
			     }
				
			
			    xmlr.next();
			}
		}
		catch (Exception e) {
			System.out.println("Errore nell'inizializzazione del reader:");
			System.out.println(e.getMessage()); 
			}
			 
		return persone;
		}
	
	public void creaXMl() {
		
		 try {
			 XMLOutputFactory xmlof = null;
			 XMLStreamWriter xmlw = null;
	         xmlof = XMLOutputFactory.newInstance();
		     xmlw = xmlof.createXMLStreamWriter(new FileOutputStream("OUTPUT"), "utf-8"); 
		     xmlw.writeStartDocument("utf-8", "1.0");
		     //Qui va il richiamo alla lista che contiene i dati delle persone e i codici fiscali 
		     try { // blocco try per raccogliere eccezioni
		    	 xmlw.writeStartElement(""); // scrittura del tag radice <programmaArnaldo> 
		    	 xmlw.writeComment("INIZIO LISTA"); // scrittura di un commento
		    	 for (int i = 0; i < .length; i++) {    //bisogna mettere apposto
		    	        xmlw.writeStartElement("Persona"); // scrittura del tag autore... 
		    	        xmlw.writeAttribute("id", Integer.toString(i)); // ...con attributo id... 
		    	        xmlw.writeCharacters([i]); // ...e content dato  //bisogna mettere apposto
		    	        xmlw.writeEndElement(); // chiusura di </autore>
		    	 }
		    	 xmlw.writeEndElement(); // chiusura di </programmaArnaldo> 
		    	 xmlw.writeEndDocument(); // scrittura della fine del documento 
		    	 xmlw.flush(); // svuota il buffer e procede alla scrittura 
		    	 xmlw.close(); // chiusura del documento e delle risorse impiegate
		    	 } 
		     catch (Exception e) { // se c’è un errore viene eseguita questa parte 
		    		 System.out.println("Errore nella scrittura");
		    	 }
		     } 
		 catch (Exception e) {
		 System.out.println("Errore nell'inizializzazione del writer:"); 
		 System.out.println(e.getMessage());
		 }
	}
	
	public static String cercaXml(String daCercare, String nomeFile) {
        boolean match = false;   
        XMLInputFactory xmlif1 = null;
        XMLStreamReader xmlr1 = null;

        try {
            xmlif1 = XMLInputFactory.newInstance();
            xmlr1 = xmlif1.createXMLStreamReader(nomeFile, new FileInputStream(nomeFile)); 
            while (xmlr1.hasNext()) { 
                switch (xmlr1.getEventType()) { 
                
                case XMLStreamConstants.CHARACTERS: 
                    if (xmlr1.getText().trim().length() > 0) {
                        if (!xmlr1.getText().equals(daCercare)) {

                            if (match) {
                                return xmlr1.getText();      
                            }
                        }
                        else {
                            if (match) {
                                return xmlr1.getText();
                            }

                            match = true;
                        }
                    }
                    break;
                }
                xmlr1.next();
            }
        }
       
        catch (Exception e) {
            System.out.println("reader initialization error");
            System.out.println(e.getMessage()); 
        }
        
       return nomeFile;
    }
	}
	
			