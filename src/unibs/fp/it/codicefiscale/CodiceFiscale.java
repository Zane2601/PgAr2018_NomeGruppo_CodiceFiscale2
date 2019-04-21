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

}
