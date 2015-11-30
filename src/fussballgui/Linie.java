package fussballgui;

/**
 * Diese Klasse stellt eine zu zeichnende Linie dar.<br>
 * Sie nimmt als Werte die echten Laengen entgegen und rechnet dies vollautomatisch auf die Displaygroesse herunter.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Linie {
	
	double frameb, framel;
	double x, y, l;
	double tempx, tempy, templ;
	
	public Linie(double x, double y, double l) {
		this.tempx = x;
		this.tempy = y;
		this.templ = l;
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
		this.l = (templ/77)*framel;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getL() {
		return l;
	}
}