package unibs.fp.it.codicefiscale;

public class Persona {
	private String nome=null;
	private String cognome;
	private int data;
	private String comune;
	private String sesso;
	
	public Persona(String _nome, String _cognome, int _data, String _comune, String _sesso) {
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

	public int getData() {
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



	public void setData(int data) {
		this.data = data;
	}



	public void setComune(String comune) {
		this.comune = comune;
	}



	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
}
