package unibs.fp.it.codicefiscale;

import java.util.Iterator;


public class Creazione {
	public static String creaCodice(Persona persona) {
		String codiceSenzaControllo = "";
		String codiceCompleto = "";
		
		
		String codiceCognome = creaCodiceCognome(persona.getCognome());
		String codiceNome = creaCodiceNome(persona.getNome());
		String data = persona.getData(); //la data viene letta come un'unica stringa
		String codiceAnno = data.substring(2, 4); //l'anno è una sottostringa di data, che considera solo le ultime 2 cifre dell'anno
		String mese = data.substring(5, 7); //sottostringa di data
		String giorno = data.substring(8); //sottostringa di data (fino all'ultimo carattere)
		String codiceMese = creaCodiceMese(mese);
		String codiceGiorno = creaCodiceGiorno(giorno, persona.getSesso());
		String codiceComune = creaCodiceComune(persona.getComune());

		codiceSenzaControllo = codiceCognome + codiceNome + codiceAnno + codiceMese + codiceGiorno + codiceComune;
		codiceCompleto = codiceSenzaControllo + creaCodiceControllo(codiceSenzaControllo);
		return codiceCompleto;
	}
	
	
///////////////////////////////////////////////////////////////////////////////
	//crea il codice iniziale di 3 caratteri dal cognome
	public static String creaCodiceCognome(String cognomePersona) {
		String codiceCognomePersona = "";
	
		codiceCognomePersona = estraiTreConsonanti(cognomePersona, "cognome");
		
		//se vengono trovate 3 consonanti viene ritornato il codice composto da 3 consonanti
		if (codiceCognomePersona.length() == 3) return codiceCognomePersona;
		else {
			//altrimenti chiamo il metodo che completa la stringa ottenuta fin qua
			return seConsonantiNonBastano(codiceCognomePersona, cognomePersona);
			}
	}

	
////////////////////////////////////////////////////////////////////////////
	public static String creaCodiceNome(String nomePersona) {
		int i = 0;
		String codiceNomePersona = "";
		
		codiceNomePersona = estraiTreConsonanti(nomePersona, "nome");
		
		//se vengono trovate 3 consonanti viene ritornato il codice composto da 3 consonanti
		if (codiceNomePersona.length() == 3) return codiceNomePersona;
		else {
			return seConsonantiNonBastano(codiceNomePersona, nomePersona);
			}
	}

	
/////////////////////////////////////////////////////////////////////////////
	//in base al mese di nascita preso come stringa (int non accetta 08, 09) ritorna la lettera corrispondente
	public static String creaCodiceMese(String mesePersona) {
		String meseCodice = "";
		switch (mesePersona) {
		case "01":
			meseCodice = "A";
			break;
		case "02":
			meseCodice = "B";
			break;
		case "03":
			meseCodice = "C";
			break;
		case "04":
			meseCodice = "D";
			break;
		case "05":
			meseCodice = "E";
			break;
		case "06":
			meseCodice = "H";
			break;
		case "07":
			meseCodice = "L";
			break;
		case "08":
			meseCodice = "M";
			break;
		case "09":
			meseCodice = "P";
			break;
		case "10":
			meseCodice = "R";
			break;
		case "11":
			meseCodice = "S";
			break;
		case "12":
			meseCodice = "T";
			break;

		default:
			break;
		}
		
		return meseCodice;
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////
	//prendo in ingresso il giorno di nascita e il sesso; torno il codice del giorno (se F aggiungo +40)
	public static String creaCodiceGiorno(String giornoPersona, String sessoPersona) {

		//se il sesso è F
		if (sessoPersona.equals("F")) {
			//converto la stringa del giorno in int
			int giornoPersonaInt = Integer.parseInt(giornoPersona);
			giornoPersonaInt += 40;
			//riconverto da int a string 
			giornoPersona = Integer.toString(giornoPersonaInt);
		}

		return giornoPersona;
	}
	
	
////////////////////////////////////////////////////////////////////////
	public static String creaCodiceComune(String comunePersona) {
		String codiceComunePersona = Dati.cercaXml(comunePersona, "comuni.xml");
		return codiceComunePersona;
	}
	
	
/////////////////////////////////////////////////////////////////////////
	public static String creaCodiceControllo(String codiceSenzaControllo) {
		String carattereControllo = "";
		String filePariDispari = "";
		int somma = 0;
		
		/*
		 * controlla se il carattere di cui controllare il codice corrisponente è un numero
		 * (questa parte poteva essere omessa e calcoalta con l'xml corrispondente come viene
		 * fatto per le lettere (vedi sotto), solo che la ricerca con i numeri dentro l'xml
		 * interrompeva la ricerca prima del dovuto e ritornava valori non esatti)
		 */
		for (int i = 0; i < codiceSenzaControllo.length(); i++) {
			if (codiceSenzaControllo.substring(i, i+1).equals("0") ||
				codiceSenzaControllo.substring(i, i+1).equals("1") ||
				codiceSenzaControllo.substring(i, i+1).equals("2") ||
				codiceSenzaControllo.substring(i, i+1).equals("3") ||
				codiceSenzaControllo.substring(i, i+1).equals("4") ||
				codiceSenzaControllo.substring(i, i+1).equals("5") ||
				codiceSenzaControllo.substring(i, i+1).equals("6") ||
				codiceSenzaControllo.substring(i, i+1).equals("7") ||
				codiceSenzaControllo.substring(i, i+1).equals("8") ||
				codiceSenzaControllo.substring(i, i+1).equals("9")) {
				
				//il numero (ancora in stringa) viene convertito in int, per poter usare lo switch nel metodo invocato 
				int carInt = Integer.parseInt(codiceSenzaControllo.substring(i, i+1));
				
				if ((i+1)%2 == 0) {
					carattereControllo = switchNumeriPosizioni(carInt, "pari");
				
				} else {
					carattereControllo = switchNumeriPosizioni(carInt, "dispari");
				}
			//altrimenti, se il carattere NON è un numero, il valore corrispondente viene cercato in un xml
			} else {
				if ((i+1) % 2 == 0) {
					filePariDispari = "letterePari.xml";
				} else {
					filePariDispari = "lettereDispari.xml";
				}
				
				//viene invocato il metodo di ricerca in un xml, passando il carattere (passato come sottostringa di 1 carattere)
				carattereControllo = Dati.cercaXml(codiceSenzaControllo.substring(i, i+1), filePariDispari);
			}

			//man mano che si determina il valore corrispondente alla posizione/carattere la somma viene aumentata
			somma += Integer.parseInt(carattereControllo);
			
			//esco dal for
			} 
	
		//si calcola il resto per poter determinare l'ultimo carattere (di controllo)
		int restoDiv = somma % 26;
		//si converte in stringa il resto appena calcolato
		String restoDivString = Integer.toString(restoDiv);
		//il resto viene passato al metodo di ricerca in xml, assieme alla stringa del nome dell'xml in cui cercare
		carattereControllo = Dati.cercaXml(restoDivString, "carattereFinale.xml");
		
		return carattereControllo;
	}
	
	//FINE DEI METODI PER CREARE LE SINGOLE SOTTOSTRINGHE (CODICI) CONTENUTI NEL CODICE FISCALE COMPLETO
/////////////////////////////////////////////////////////////////////////


	/*
	 *ricevendo in ingresso il numero presente in una posizione del codice fiscale, e
	 *sapendo che si trovi in una posizione pari o dispari, ritorna il valore corrispondente
	 */	
	public static String switchNumeriPosizioni(int numero, String pariOrDispari) {
		String valoreCorrispondente = "";
		switch (numero) {
		case 0:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "0";
			} else valoreCorrispondente = "1";
			break;
		case 1:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "1";
			} else valoreCorrispondente = "0";
			break;
		case 2:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "2";
			} else valoreCorrispondente = "5";
			break;
		case 3:	
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "3";
			} else valoreCorrispondente = "7";
			break;
		case 4:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "4";
			} else valoreCorrispondente = "9";
			break;
		case 5:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "5";
			} else valoreCorrispondente = "13";
			break;
		case 6:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "6";
			} else valoreCorrispondente = "15";
			break;
		case 7:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "7";
			} else valoreCorrispondente = "17";
			break;
		case 8:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "8";
			} else valoreCorrispondente = "19";
			break;
		case 9:
			if (pariOrDispari.equals("pari")) {
				valoreCorrispondente = "9";
			} else valoreCorrispondente = "21";
			break;
		default:
			break;
		}
		return valoreCorrispondente;
	}


	//questo metodo viene invocato quando la lunghezza del codice del nominativo (cognome o nome) non è sufficiente (<3)
	public static String seConsonantiNonBastano(String codiceDaCompletare, String nominativo) {
		
		//il for scorre il nominativo
		for (int j = 0; j < nominativo.length(); j++) {
				
			//se trova vocali le aggiunge alla stringa del codice del nominativo
			if (isConsonant(nominativo.charAt(j)) == false) {
				codiceDaCompletare += nominativo.charAt(j);
			}
			
			//quando la lunghezza è = 3, ritorna il codice (non serve scorrere ulteriormente)
			if (codiceDaCompletare.length() == 3) return codiceDaCompletare.toString();
		}

		//se, anche dopo aver controllato le vocali, restano spazi vuoti (dei 3 totali) si riempiono con questo metodo
		return riempiSpaziX(codiceDaCompletare).toString();
	}
	
	
	//se anche le vocali sono insufficienti si riempiono gli spazi rimasti con delle 'X'
	public static String riempiSpaziX(String codiceConSpaziDaRiempire) {
		
		//aggiungo una X finché la lunghezza del codice nominativo è sufficiente
		do {
			codiceConSpaziDaRiempire += "X";
		} while (codiceConSpaziDaRiempire.length() < 3);
		return codiceConSpaziDaRiempire;
	}
	
	
	/*
	 * estrae 3 consonanti:
	 * 		- se entra un cognome -> consonante 1, 2, 3
	 * 		- se entra un nome 	  -> se ci sono 3 consonanti o meno -> consonante 1, 2, 3
	 * 		- se entra un nome    -> se ci sono 4 consonanti o più  -> consonante 1, 3, 4
	 * 
	 * la stringa nomeOrCognome assume valore "nome" o "cognome", passato nel metodo creaCodiceNome() o creaCodiceCognome()
	 */
	public static String estraiTreConsonanti(String datoPersona, String nomeOrCognome) {
		String codiceTreConsonanti = "";
		
		if ((nomeOrCognome.equals("nome") && (contaConsonanti(datoPersona) <= 3) || nomeOrCognome.equals("cognome"))) {
		//scorre il cognome (o nome) della persona preso in ingresso
			for (int j = 0; j < datoPersona.length() ; j++) {
				//se scorrendo trovo una consonante la aggiungo
				if (isConsonant(datoPersona.charAt(j))) {
					codiceTreConsonanti += datoPersona.charAt(j);
				}
			
				//se si arriva già a 3 caratteri estratti non serve controllare anche le altre lettere
				if (codiceTreConsonanti.length() == 3) return codiceTreConsonanti;
				}
			}
		//quindi se entra un nome con 4 o più consonanti
		else {
			String codiceConsonanti = "";
			//creo un codice composto solo dalle consonanti
			for (int j = 0; j < datoPersona.length() ; j++) {
				if (isConsonant(datoPersona.charAt(j))) {
					codiceConsonanti += datoPersona.charAt(j);
				}
			}
			//il codice da ritornare è la somma della sottostringa (0, 1), primo carattere, + (2, 4), terzo e quarto carattere 
			codiceTreConsonanti = codiceConsonanti.substring(0, 1) + codiceConsonanti.substring(2, 4); 
			}
		return codiceTreConsonanti;
	}
	
	
	//conta le consonanti contenute in una parola (usato per il metodo creaCodiceNome)
		public static int contaConsonanti(String parola) {
			int numConsonanti = 0;
			//si scorre la parola per controllare ogni lettera
			for (int i = 0; i < parola.length(); i++) {
				if (isConsonant(parola.charAt(i))) numConsonanti++;		
			}
			return numConsonanti;
		}
		
		
	//controlla che un carattere dato in ingresso sia una consonante
		public static boolean isConsonant(char carattere) {
			if (carattere == 'a' || carattere == 'e' || carattere == 'i' || carattere == 'o' || carattere == 'u' || carattere == 'A' || carattere == 'E' || carattere == 'I' || carattere == 'O' || carattere == 'U') return false;
				else return true;
		}
}