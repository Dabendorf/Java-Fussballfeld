package fussballgui;

/**
 * Diese Klasse stellt einen zu zeichnenden Kreis dar.<br>
 * Sie nimmt als Werte die echten Laengen entgegen und rechnet dies vollautomatisch auf die Displaygroesse herunter.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Kreis {
	
	double frameb, framel;
	double x, y, b, l;
	double tempx, tempy, tempb, templ;
	boolean ausgemalt;
	
	public Kreis(double x, double y, double b, double l, boolean ausgemalt) {
		this.tempx = x;
		this.tempy = y;
		this.tempb = b;
		this.templ = l;
		this.ausgemalt = ausgemalt;
		berechne();
	}
	
	/**
	 * Diese Methode berechnet anhand der gegebenen Werte und der gegenwaertigen Groesse des Fensters, wie gross das Element sein soll.
	 */
	public void berechne() {
		frameb = Fussballfeld.getBreite();
		framel = Fussballfeld.getLaenge();
		
		this.x = (tempx/107)*frameb;
		this.y = (tempy/77)*framel;
		this.b = (tempb/107)*frameb;
		this.l = (templ/77)*framel;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getB() {
		return b;
	}

	public double getL() {
		return l;
	}

	public boolean isAusgemalt() {
		return ausgemalt;
	}
}