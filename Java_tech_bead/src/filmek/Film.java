package filmek;

import java.io.Serializable;
import java.util.Date;

public class Film implements Serializable {
	private static final long serialVersionUID = 1L;
	private int azon;
	private String cim;
	private Date evszam;
	private String rendezo;
	private int hossz;

	public Film(int azon, String cim, Date evszam, String rendezo, int hossz) {
		this.azon = azon;
		this.cim = cim;
		this.evszam = evszam;
		this.rendezo = rendezo;
		this.hossz = hossz;
	}

	public int getazon() {
		return azon;
	}

	public String getcim() {
		return cim;
	}

	public Date getevszam() {
		return evszam;
	}

	public String getrendezo() {
		return rendezo;
	}

	public int gethossz() {
		return hossz;
	}

}
