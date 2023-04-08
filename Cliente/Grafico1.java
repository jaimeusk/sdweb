
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

public class Grafico1 extends JFrame {

	private JPanel contentPane;
	/*
	 * Ejecuta la aplicacion
	 */

	private boolean color1 = false;
	private boolean color2 = false;
	private boolean color3 = false;
	private int escenario;
	private static int eleccion = 10;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Grafico1 ventana = new Grafico1();
					ventana.setVisible(true);
					ventana.setTitle("Elegir ubicactión");
					ventana.setResizable(false);
					ventana.setLocationRelativeTo(null);
					ventana.setSize(1100, 800);
					;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/*
	 * Crear el frame
	 */

	public int devuelve() {
		System.out.println(eleccion);
		return eleccion;

	}

	public Grafico1(int escenario) { // No hace nada el escenario. Solo es para tener dos construcores distintos.
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

	public void paint(Graphics h) {
		Graphics g = h;
		super.paint(g);

		Grafico1 objeto = new Grafico1(1);

		JButton bi = new JButton("Grada 1");
		bi.setBounds(870, 130, 160, 50);
		contentPane.add(bi);

		JButton bo = new JButton("Grada 2");
		bo.setBounds(870, 190, 160, 50);
		contentPane.add(bo);

		JButton bu = new JButton("Grada 3");
		bu.setBounds(870, 250, 160, 50);
		contentPane.add(bu);

		bi.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				color1 = true;
				repaint();

			}

			public void mouseExited(MouseEvent e) {
				color1 = false;
				repaint();
			}

			public void mousePressed(MouseEvent e) { // Hay que añadir estos metodos, aunque no se haga nada, pq la
														// clase no es abstraca y hay q definirlos
			}

			public void mouseClicked(MouseEvent e) {
				eleccion = 1;
				objeto.devuelve();
				System.exit(0);

			}

			public void mouseReleased(MouseEvent e) {
			}

		});

		bo.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				color2 = true;
				repaint();

			}

			public void mouseExited(MouseEvent e) {
				color2 = false;
				repaint();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				eleccion = 2;
				objeto.devuelve();
				System.exit(0);

			}

			public void mouseReleased(MouseEvent e) {
			}

		});

		bu.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				color3 = true;
				repaint();

			}

			public void mouseExited(MouseEvent e) {
				color3 = false;
				repaint();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				eleccion = 3;
				objeto.devuelve();
				System.exit(0);

			}

			public void mouseReleased(MouseEvent e) {
			}

		});

		// ESCEANRIO
		g.setColor(Color.GRAY);

		g.fillRect(415, 390, 305, 67);
		g.fillRect(715, 195, 100, 458);
		// BORDES GRADA
		g.setColor(Color.darkGray);
		g.drawRect(153, 195, 255, 455);
		g.drawRect(415, 195, 295, 190);
		g.drawRect(415, 461, 295, 190);

		if (color1 == false)
			g.setColor(Color.lightGray);
		else
			g.setColor(Color.darkGray);
		// GRADA 1
		g.fillRect(152, 196, 256, 454);

		if (color2 == false)
			g.setColor(Color.lightGray);
		else
			g.setColor(Color.darkGray);
		// GRADA 2
		g.fillRect(416, 196, 294, 189);

		if (color3 == false)
			g.setColor(Color.lightGray);
		else
			g.setColor(Color.darkGray);
		// GRADA 3
		g.fillRect(416, 462, 294, 189);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("GRADA 1", 235, 435);
		g.drawString("GRADA 2", 520, 300);
		g.drawString("GRADA 3", 520, 560);
		g.drawString("ESCENARIO", 620, 430);

	}

}
