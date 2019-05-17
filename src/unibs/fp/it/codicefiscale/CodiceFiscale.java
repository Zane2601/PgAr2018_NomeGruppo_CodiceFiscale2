package unibs.fp.it.codicefiscale;

public class CodiceFiscale {
private String codice = null;
	
	public CodiceFiscale (String _codice) {
		this.codice = _codice;
	}
	
	public String getCodice() {
		return codice;
	}
	
	public void setCodice(String codice) {
		this.codice=codice;
	}
	
	public String toString () {
		return codice;
	}
	
	////////////////////////////////////////////////////////////
	// codice flavio
	/*//non controlla che i dati inseriti siano giusti

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.util.HashMap;

/**
 * Classe per la gestione di codici fiscali
 
public class CodiceFiscale implements Comparable<CodiceFiscale>{
    private static final String XMLCODICIFISCALI = "comuni.xml";
    private static HashMap<String,String> comuni;
    private String codiceFiscale;

    /**
     * costruttore che richiede tutti i dati di una persona e inizializza la variabile codiceFiscale
     * @param nome
     * @param cognome
     * @param data
     * @param luogo
     * @param sesso
     
    public CodiceFiscale(String nome, String cognome, String data, String luogo,char sesso){
        if(comuni == null) //controllo se la hashmap di comuni é giá stata inizializzata e in caso la inizializzo
            initComuni();
        codiceFiscale = calcolaCodice(cognome,nome,data,luogo,sesso);
    }

    /**
     * Costruttore che richiede il codice fiscale
     * @param codice
     
    public CodiceFiscale(String codice){
        if(comuni == null) //controllo se la hashmap di comuni é giá stata inizializzata e in caso la inizializzo
            initComuni();
        codiceFiscale = codice;
    }

    /**
     * costruttore che richiede come input un oggetto Persona e inizializza la variabile codice fiscale
     * @param persona
     
    public CodiceFiscale(Persona persona){
        this(persona.getNome(), persona.getCognome(), persona.getDataNascita(), persona.getLuogoNascita(), persona.getSesso());
    }

    /**
     *
     * @return la stringa codice fiscale
     
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * metodo che verifica se una stringa é un codice fiscale
     * @param codiceFiscale
     * @return vero se la stringa passata é un codice fiscale, falso se non é un codice fiscale
     
    public static boolean isCodiceFiscale(String codiceFiscale){
        if(comuni == null)
            initComuni();
        String cognome;
        String nome;
        String data;
        String comune;
        if(codiceFiscale.length() != 16)
            return false;
        nome = codiceFiscale.substring(3,6);
        cognome = codiceFiscale.substring(0,3);
        if(!checkCodice(cognome) || !checkCodice(nome)){
            return false;
        }
        data = codiceFiscale.substring(6,11);
        if(!checkData(data))
            return false;
        comune = codiceFiscale.substring(11,15);
        if(!comuni.containsValue(comune))
            return false;
        return codiceFiscale.charAt(15) == calcolaLetteraUnivoca(codiceFiscale.substring(0,15));
    }

    private static boolean checkData(String data) {
        if(!(Character.isDigit(data.charAt(0)) && Character.isDigit(data.charAt(1))))
            return false;
        //bisestile
        int anno = Integer.valueOf(data.charAt(0) + "" + data.charAt(1));
        int giorno = Integer.valueOf(data.substring(3,5))%40;

        if(data.charAt(2) == 'B' && giorno == 29 && anno % 4 == 0)
            return true;
        switch (data.charAt(2)){
            case 'A':
                return (giorno >= 1 && giorno <= 31);
            case 'B':
                return (giorno >= 1 && giorno <= 28);
            case 'C':
                return (giorno >= 1 && giorno <= 31);
            case 'D':
                return (giorno >= 1 && giorno <= 30);
            case 'E':
                return (giorno >= 1 && giorno <= 31);
            case 'H':
                return (giorno >= 1 && giorno <= 30);
            case 'L':
                return (giorno >= 1 && giorno <= 31);
            case 'M':
                return (giorno >= 1 && giorno <= 31);
            case 'P':
                return (giorno >= 1 && giorno <= 30);
            case 'R':
                return (giorno >= 1 && giorno <= 31);
            case 'S':
                return (giorno >= 1 && giorno <= 30);
            case 'T':
                return (giorno >= 1 && giorno <= 31);

            default:
                return false;
        }
    }

    private static boolean checkCodice(String nome){
        boolean vocal = false;
        for (int i = 0; i < 3; i++){
            char c = nome.charAt(i);
            if(Character.isAlphabetic(c)){
                if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
                    vocal = true;
                if(vocal && c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U' && c != 'X')
                    return false;
            }
            else return false;
        }
        return true;
    }

    /**
     * metodo che calcola il codice fiscale passando i parametri richiesti
     * @param cognome
     * @param nome
     * @param data
     * @param luogo
     * @param sesso
     * @return il codice fiscale
     
    public static String calcolaCodice(String cognome, String nome, String data, String luogo, char sesso) {
        String codice = calcolaCognome(cognome)+calcolaNome(nome)+calcolaData(data,sesso)+calcolaLuogo(luogo);
        return codice+calcolaLetteraUnivoca(codice);
    }

    static private String calcolaCognome(String cognome){
        String siglaCognome = "";
        char[] c_cognome = cognome.toCharArray();
        for(char c : c_cognome){ //INIZIO CON LE CONSONANTI
            if(c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U'){
                siglaCognome+=c;
            }
            if(siglaCognome.length() == 3)
                break;
        }
        if (siglaCognome.length() < 3){ //CONTROLLO ANCHE LE VOCALI
            for(char c : c_cognome){
                if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                    siglaCognome+=c;
                }
                if(siglaCognome.length() == 3)
                    break;
            }
        }
        while (siglaCognome.length()<3){
            siglaCognome+='X';
        }
        return siglaCognome;
    }

    private static String calcolaNome(String nome){
        //NOME
        char[]c_nome = nome.toCharArray();
        String appoggio = "";
        String codiceNome = "";
        for(char c : c_nome){ //INIZIO CON LE CONSONANTI
            if(c != 'A' && c != 'E' && c != 'I' && c != 'O' && c != 'U'){
                appoggio+=c;
            }

        }
        if(appoggio.length()<3){
            for(char c : nome.toCharArray()){
                if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'){
                    appoggio+=c;
                }
                if(appoggio.length() == 3)
                    break;
            }
        }
        while (appoggio.length()<3)
            appoggio+='X';
        if(appoggio.length()>3)
            codiceNome = "" + appoggio.charAt(0) + appoggio.charAt(2) +appoggio.charAt(3);
        else
            codiceNome = appoggio;
        return codiceNome;
    }

    private static String calcolaData(String data, char sesso){
        String codiceData= "";
        char[] c_data = data.toCharArray();
        codiceData+=""+c_data[2]+c_data[3];
        String meseS= (""+c_data[5]+c_data[6]);
        int mese = Integer.valueOf(meseS);

        switch (mese){
            case 1:
                codiceData+='A';
                break;
            case 2:
                codiceData+='B';
                break;
            case 3:
                codiceData+='C';
                break;
            case 4:
                codiceData+='D';
                break;
            case 5:
                codiceData+='E';
                break;
            case 6:
                codiceData+='H';
                break;
            case 7:
                codiceData+='L';
                break;
            case 8:
                codiceData+='M';
                break;
            case 9:
                codiceData+='P';
                break;
            case 10:
                codiceData+='R';
                break;
            case 11:
                codiceData+='S';
                break;
            case 12:
                codiceData+='T';
                break;
            default:
                break;
        }
        if(sesso == 'M'){
            codiceData+= ""+c_data[8]+c_data[9];
        }
        else{
            int giorno = 40 + Integer.valueOf(""+c_data[8]+c_data[9]);
            codiceData+= giorno;
        }
        return codiceData;
    }

    private static String calcolaLuogo(String luogo){
        if(comuni == null) //controllo se la hashmap di comuni é giá stata inizializzata e in caso la inizializzo
            initComuni();
        return comuni.get(luogo);
    }

    private static char calcolaLetteraUnivoca(String codice){
        final int[][] nums = {{0,1,2,3,4,5,6,7,8,9,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25}, // pari
                {1,0,5,7,9,13,15,17,19,21,1,0,5,7,9,13,15,17,19,21,2,4,18,20,11,3,6,8,12,14,16,10,22,25,24,23} // dispari
        };
        int len = 15;
        int sum = 0;
        for (int i = 0; i < 15; i++) {
            char c = codice.charAt(i);
            int ord = ord(c);
            sum += nums[(i + 1) % 2][ord];
        }
        int code = sum % 26;
        int out = 'a' + code;
        return (char) Character.toUpperCase(out);
    }

    //serve a calcolaLetteraUnivoca
    private static int ord(char ci) {
        char c = Character.toUpperCase(ci);
        if (c >= '0' && c <= '9')
            return (int) (c - '0');

        return (int) (c - 'A') + 10;
    }

    @Override
    public String toString() {
        return codiceFiscale;
    }

    private static void initComuni() {
        comuni = new HashMap<>();
        String nome=null, codice=null;
        boolean n = false, c = false, n1 = false, c1 = false;
        try {
            XMLInputFactory xmlif = XMLInputFactory.newInstance();
            XMLStreamReader xmlr = xmlif.createXMLStreamReader(XMLCODICIFISCALI, new FileInputStream(XMLCODICIFISCALI));
            while(xmlr.hasNext()) {
                switch(xmlr.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT:
                        if(xmlr.getLocalName().equals("nome")) {
                            n = true;
                        }
                        if(xmlr.getLocalName().equals("codice")) {
                            c = true;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        if(n){
                            nome = xmlr.getText();
                            n1 = true;
                            n = false;
                        }
                        if(c) {
                            codice = xmlr.getText();
                            c1 = true;
                            c = false;
                        }
                        break;
                    default:
                        break;
                }
                if (c1 && n1){
                    comuni.put(nome,codice);
                    n1 = false;
                    c1 = false;
                }
                xmlr.next();
            }
        }

        catch(Exception e){
            System.err.println("Error detected");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public int compareTo(CodiceFiscale o) {
        return codiceFiscale.compareTo(o.codiceFiscale);
    }

    @Override
    public boolean equals(Object obj) {
        CodiceFiscale cod = (CodiceFiscale) obj;
        return codiceFiscale.equals(cod.codiceFiscale);
    }
}
*/

}
