//Para poder ejectar el código, he necesitado ejecutar los siguientes comandos:
// sudo apt-get update
// sudo apt install openjdk-17-jdk
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Grafico1 extends JFrame{

    private JPanel contentPane;
    /*
      Ejecuta la aplicacion
     */

    private boolean color1 = false;
    private boolean color2 = false;
    private boolean color3 = false;
    private int escenario;
    private static int  eleccion = 10;

    public static void main (String[] args)   {
	EventQueue.invokeLater(new Runnable() {
		public void run() {
		    try {
			
			Grafico1 frame = new Grafico1();
			frame.setVisible(true);
			
		    } catch (Exception e) {
			e.printStackTrace();
		    }
		}
	    });
	

    }

    /*
      Crear el frame
     */

    public int devuelve() {
	System.out.println(eleccion);
	return eleccion;
	
    }
    public Grafico1(int escenario) { //No hace nada el escenario. Solo es para tener dos construcores distintos.
	this.escenario = escenario; 
    }
    
    public Grafico1() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 450, 300);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	setBounds(0, 0, 800, 600);
    }

    public void paint (Graphics h) {
	Graphics g = h;
	super.paint(g);
	/*
	g.setColor (Color.blue);
	g.drawLine(0, 70, 100, 70);
	g.drawRect(150, 70, 50, 70);
	g.drawRoundRect(250, 70, 50, 70, 6, 6);
	g.drawOval (350, 70, 50, 70);
	int [] vx1 = {500, 550, 450};
	int [] vy1 = {70, 120, 120};
	g.drawPolygon (vx1, vy1, 3);
	*/

	/*
	int [] x = {220, 220, 470, 470};
	int [] y = {170, 30, 30, 170};
	g.setColor(Color.darkGray);
	g.drawPolygon(x,y,x.length);
	Polygon poligono = new Polygon();
	poligono.addPoint(220,170);
	poligono.addPoint(220,30);
	poligono.addPoint(470,30);
	poligono.addPoint(470,170);
	g.setColor(Color.lightGray);
	g.fillPolygon(poligono);
	*/
	//fillRect(int x, int y, int largo, int ancho)


	Grafico1 objeto = new Grafico1(1);
	
	JLabel titulo = new JLabel("ESCENARIO");
	titulo.setBounds(210, 60, 200, 100);
	contentPane.add(titulo);
	
	JButton bi = new JButton("Fondo");
	bi.setBounds(650, 130, 110, 30);
	contentPane.add(bi);

	JButton bo = new JButton("Derecha");
	bo.setBounds(650, 170, 110, 30);
	contentPane.add(bo);

	JButton bu = new JButton("Izquierda");
	bu.setBounds(650, 210, 110, 30);
	contentPane.add(bu);

	bi.addMouseListener(new MouseListener(){
		public void mouseEntered(MouseEvent e) {
		    color1 = true;
		    repaint();
				  
		}
		public void mouseExited(MouseEvent e) {
		    color1 = false;
		    repaint();
		}
		public void mousePressed(MouseEvent e){ //Hay que añadir estos metodos, aunque no se haga nada, pq la clase no es abstraca y hay q definirlos
		}
		public void mouseClicked(MouseEvent e){
		    eleccion = 1;
		    objeto.devuelve();
		    System.exit(0);
		    
		    
		}
		public void mouseReleased(MouseEvent e){
		}
		
	    });
	
	bo.addMouseListener(new MouseListener(){
		public void mouseEntered(MouseEvent e) {
		    color2 = true;
		    repaint();
				  
		}
		public void mouseExited(MouseEvent e) {
		    color2 = false;
		    repaint();
		}
		public void mousePressed(MouseEvent e){
		}
		public void mouseClicked(MouseEvent e){
		    eleccion = 2;
		    objeto.devuelve();
		    System.exit(0);
		    
		}
		public void mouseReleased(MouseEvent e){
		}
		
	    });
	
	bu.addMouseListener(new MouseListener(){
		public void mouseEntered(MouseEvent e) {
		    color3 = true;
		    repaint();
				  
		}
		public void mouseExited(MouseEvent e) {
		    color3 = false;
		    repaint();
		}
		public void mousePressed(MouseEvent e){
		}
		public void mouseClicked(MouseEvent e){
		    eleccion = 3;
		    objeto.devuelve();
		    System.exit(0);
		    
		}
		public void mouseReleased(MouseEvent e){
		}
		
	    });
	
	g.setColor (Color.darkGray);
	g.drawRect(219, 169, 172, 272);
	g.drawRect(389, 169, 233, 111);
	g.drawRect(390, 330, 231, 111);
	
	if (color1 == false)
	    g.setColor (Color.lightGray);
	else
	    g.setColor (Color.darkGray);
	g.fillRect (220, 170, 170, 270);

	if (color2 == false)
	    g.setColor (Color.lightGray);
	else
	    g.setColor (Color.darkGray);
	g.fillRect(392, 170, 230, 110);

	if (color3 == false)
	    g.setColor (Color.lightGray);
	else
	    g.setColor (Color.darkGray);
	g.fillRect(391, 331, 230, 110);

	g.setColor(Color.blue);
	g.setFont(new Font("Arial", Font.BOLD, 20));
	g.drawString("200",290,310);
	g.drawString("150",490,230);
	g.drawString("150",490,390);


    }
    
}
