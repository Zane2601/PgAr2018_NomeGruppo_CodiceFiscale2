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
	
	public void stampa(ArrayList liste) {
		for (int i = 0; i < liste.size(); i++) {
			System.out.println(liste.indexOf(i));
		}
	}
}
