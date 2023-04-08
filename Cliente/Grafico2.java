
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

public class Grafico2 extends JFrame {

	private JPanel contentPane;
	/*
	 * Ejecuta la aplicacion
	 */

	private boolean color1 = false;
	private boolean color2 = false;
	private int escenario;
	private static int eleccion = 10;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Grafico2 ventana = new Grafico2();
					ventana.setVisible(true);
					ventana.setTitle("Elegir ubicación");
					ventana.setResizable(false);
					ventana.setLocationRelativeTo(null);
					ventana.setSize(1100, 800);

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

	public Grafico2(int escenario) { // No hace nada el escenario. Solo es para tener dos construcores distintos.
		this.escenario = escenario;
	}

	public Grafico2() {
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

		Grafico2 objeto = new Grafico2(1);

		JButton bi = new JButton("Grada 1");
		bi.setBounds(870, 130, 160, 50);
		contentPane.add(bi);

		JButton bo = new JButton("Grada 2");
		bo.setBounds(870, 190, 160, 50);
		contentPane.add(bo);

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

		g.setColor(Color.GRAY);
		// ESCENARIO
		g.fillRect(145, 210, 170, 410);
		g.setColor(Color.LIGHT_GRAY);
		g.drawRect(325, 210, 120, 410);
		g.drawRect(450, 210, 350, 410);

		// GRADA 1
		if (color1 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(326, 211, 119, 409);
		// GRADA 2
		if (color2 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(451, 211, 349, 409);

		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 20));

		g.drawString("ESCENARIO", 170, 420);
		g.drawString("GRADA 1", 338, 420);
		g.drawString("GRADA 2", 580, 420);

	}

}
