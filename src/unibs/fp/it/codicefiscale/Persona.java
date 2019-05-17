package unibs.fp.it.codicefiscale;

import java.util.ArrayList;

public class Persona {
	private String nome=null;
	private String cognome;
	private String data;
	private String comune;
	private String sesso;
	
	
	public Persona(String _nome, String _cognome, String _data, String _comune, String _sesso) {
		this.nome=_nome;
		this.cognome=_cognome;
		this.data=_data;
		this.comune=_comune;
		this.sesso=_sesso;
		
	}


	public Persona() {
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getData() {
		return data;
	}

	public String getComune() {
		return comune;
	}

	public String getSesso() {
		return sesso;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public void setData(String data) {
		this.data = data;
	}



	public void setComune(String comune) {
		this.comune = comune;
	}



	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	public ArrayList persone() {
		
		return persone();
	}
	public void stampa(ArrayList liste) {
		for (int i = 0; i < liste.size(); i++) {
			System.out.println(liste.indexOf(i));
		}
	}
	
	//////////////////////////////////////////////
	//codice Flavio
	/*public class Persona {
    private int id;
    private char sesso;
    private String nome;
    private String cognome;
    private String dataNascita;
    private String luogoNascita;
    private CodiceFiscale codiceFiscale;

    public Persona(String nome, String cognome, String dataNascita, String luogoNascita, char sesso, int id) {
        this(nome,cognome,dataNascita,luogoNascita,sesso, id, null);

    }
    public Persona(String nome, String cognome, String dataNascita, String luogoNascita, char sesso, int id ,CodiceFiscale codiceFiscale) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.luogoNascita = luogoNascita;
        this.sesso = sesso;
        this.codiceFiscale = codiceFiscale;
        this.id = id;
    }

    public CodiceFiscale getCodiceFiscale() {
        if(codiceFiscale == null)
            initCodiceFiscale();
        return codiceFiscale;
    }

    public void initCodiceFiscale() {
        codiceFiscale = new CodiceFiscale(nome,cognome,dataNascita,luogoNascita,sesso);
    }

    public boolean hasCodiceFiscale(){
        return codiceFiscale != null;
    }

    public char getSesso() {
        return sesso;
    }

    public int getId() {
        return id;
    }

    public String getCognome() {
        return cognome;
    }

    public String getDataNascita() {
        return dataNascita;
    }

    public String getLuogoNascita() {
        return luogoNascita;
    }

    public String getNome() {
        return nome;
    }

    public void setCodiceFiscale(CodiceFiscale codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setDataNascita(String dataNascita) {
        this.dataNascita = dataNascita;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLuogoNascita(String luogoNascita) {
        this.luogoNascita = luogoNascita;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSesso(char sesso) {
        this.sesso = sesso;
    }

    @Override
    public String toString() {
        String daStampare = nome + " " + cognome + " " + sesso +" " + luogoNascita +" " + dataNascita;
        if(codiceFiscale != null)
            daStampare += " " + codiceFiscale;
        return daStampare;
    }

}*/
}
