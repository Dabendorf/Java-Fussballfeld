package fussballgui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Dies ist die Hauptklasse des Fussballfeldes. Er nimmt die Groessen des Feldes entgegen und zeichnet sie auf einem Canvas.
 * 
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Fussballfeld {
	
	private JFrame frame1 = new JFrame("Fußballfeld");
	private JPanel spielpanel = new JPanel() {
		public void paint(Graphics gr) {
			super.paint(gr);
            zeichne(gr);
        }
	};
	private ArrayList<Rechteck> rechtecke = new ArrayList<Rechteck>();
	private ArrayList<Kreis> kreise = new ArrayList<Kreis>();
	private ArrayList<Linie> linien = new ArrayList<Linie>();
	private ArrayList<Winkel> winkel = new ArrayList<Winkel>();
	private static int breite, laenge;
	
	public Fussballfeld() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(1070,720));
		frame1.setMinimumSize(new Dimension(535,360));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridLayout(1,1));
		
		spielpanel.setOpaque(true);
		spielpanel.setVisible(true);
		frame1.add(spielpanel);
		spielpanel.setBackground(new Color(0x33B200));
		
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
		
		breite = frame1.getWidth();
		laenge = frame1.getHeight();
		generieren();
		spielpanel.repaint();
	}
	
	/**
	 * Diese Methode nimmt alle zu zeichnenden Elemente entgegen und fuegt sie in die dazugehoerige Liste ein.
	 */
	private void generieren() {
		Rechteck aussenlinie = new Rechteck(1,1,105,70);
		rechtecke.add(aussenlinie);
		Linie mittellinie = new Linie(53,1,70);
		linien.add(mittellinie);
		Kreis mittelkreis = new Kreis(53,36,18,18,false);
		kreise.add(mittelkreis);
		Kreis mittelpunkt = new Kreis(53,36,2,2,true);
		kreise.add(mittelpunkt);
		Kreis elfmeter1 = new Kreis(12,36,2,2,true);
		kreise.add(elfmeter1);
		Kreis elfmeter2 = new Kreis(95,36,2,2,true);
		kreise.add(elfmeter2);
		Rechteck strafraum1 = new Rechteck(1,16,16,40);
		rechtecke.add(strafraum1);
		Rechteck strafraum2 = new Rechteck(90,16,16,40);
		rechtecke.add(strafraum2);
		Rechteck torraum1 = new Rechteck(1,27,6,18);
		rechtecke.add(torraum1);
		Rechteck torraum2 = new Rechteck(100,27,6,18);
		rechtecke.add(torraum2);
		Winkel strafraumparabel1 = new Winkel(14,30,6,12,270,180);
		winkel.add(strafraumparabel1);
		Winkel strafraumparabel2 = new Winkel(87,30,6,12,90,180);
		winkel.add(strafraumparabel2);
		Winkel ecke1 = new Winkel(1,1,1,1,270,90);
		winkel.add(ecke1);
		Winkel ecke2 = new Winkel(1,70,1,1,0,90);
		winkel.add(ecke2);
		Winkel ecke3 = new Winkel(105,70,1,1,90,90);
		winkel.add(ecke3);
		Winkel ecke4 = new Winkel(105,1,1,1,180,90);
		winkel.add(ecke4);
	}
	
	/**
	 * Dies ist die Zeichenmethode fuer die graphische Ausgabe. Sie iteriert jede ArrayList an Elementen durch und zeichnet die Elemente.<br>
	 * Ausserdem wurden hier die Linienfarbe und die -Dicke festgelegt.
	 * @param gr Nimmt das Graphicselement entgegen.
	 */
	private void zeichne(Graphics gr) {
		Graphics2D g2d = (Graphics2D) gr;
		float dicke = 5;
		g2d.setStroke(new BasicStroke(dicke));
		
		g2d.setColor(Color.white);
		breite = frame1.getWidth();
		laenge = frame1.getHeight();
		
		for(Rechteck rect:rechtecke) {
			rect.berechne();
			g2d.drawRect((int)rect.getX(),(int)rect.getY(),(int)rect.getB(),(int)rect.getL());
		}
		for(Kreis kr:kreise) {
			kr.berechne();
			int x = (int)kr.getX();
			int y = (int)kr.getY();
			int b = (int)kr.getB();
			int l = (int)kr.getL();
			if(kr.isAusgemalt()) {
				g2d.fillOval(x-b/2,y-l/2,b,l);
			} else {
				g2d.drawOval(x-b/2,y-l/2,b,l);
			}
		}
		for(Linie lin:linien) {
			lin.berechne();
			g2d.drawLine((int)lin.getX(),(int)lin.getY(),(int)lin.getX(),(int)lin.getY()+(int)lin.getL());
		}
		for(Winkel wi:winkel) {
			wi.berechne();
			g2d.drawArc((int)wi.getX(),(int)wi.getY(),(int)wi.getB(),(int)wi.getL(),wi.getLeerwinkel(),wi.getWinkel());
		}
	}

	public static void main(String[] args) {
		new Fussballfeld();
	}

	public static int getBreite() {
		return breite;
	}

	public static int getLaenge() {
		return laenge;
	}
}