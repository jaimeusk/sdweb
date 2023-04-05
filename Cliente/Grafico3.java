
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

public class Grafico3 extends JFrame {

	private JPanel contentPane;
	/*
	 * Ejecuta la aplicacion
	 */

	private boolean color1 = false;
	private boolean color2 = false;
	private boolean color3 = false;
	private boolean color4 = false;
	private boolean color5 = false;
	private int escenario;
	private static int eleccion = 10;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Grafico3 ventana = new Grafico3();
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

	public Grafico3(int escenario) { // No hace nada el escenario. Solo es para tener dos construcores distintos.
		this.escenario = escenario;
	}

	public Grafico3() {
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

		Grafico3 objeto = new Grafico3(1);

		JButton b1 = new JButton("FRONT STAGE");
		b1.setBounds(870, 130, 160, 50);
		contentPane.add(b1);

		JButton b2 = new JButton("PISTA");
		b2.setBounds(870, 190, 160, 50);
		contentPane.add(b2);

		JButton b3 = new JButton("GRADA 1");
		b3.setBounds(870, 250, 160, 50);
		contentPane.add(b3);

		JButton b4 = new JButton("GRADA 2");
		b4.setBounds(870, 310, 160, 50);
		contentPane.add(b4);

		JButton b5 = new JButton("GRADA 3");
		b5.setBounds(870, 370, 160, 50);
		contentPane.add(b5);

		b1.addMouseListener(new MouseListener() {
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

		b2.addMouseListener(new MouseListener() {
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

		b3.addMouseListener(new MouseListener() {
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

		b4.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				color4 = true;
				repaint();

			}

			public void mouseExited(MouseEvent e) {
				color4 = false;
				repaint();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				eleccion = 4;
				objeto.devuelve();
				System.exit(0);

			}

			public void mouseReleased(MouseEvent e) {
			}

		});

		b5.addMouseListener(new MouseListener() {
			public void mouseEntered(MouseEvent e) {
				color5 = true;
				repaint();

			}

			public void mouseExited(MouseEvent e) {
				color5 = false;
				repaint();
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				eleccion = 5;
				objeto.devuelve();
				System.exit(0);

			}

			public void mouseReleased(MouseEvent e) {
			}

		});

		// ESCENARIO
		g.setColor(Color.GRAY);
		g.fillArc(100, 220, 400, 400, 90, 180);

		g.setColor(Color.DARK_GRAY);
		// FRONT STAGE
		g.drawRect(310, 220, 85, 400);
		// PISTA
		g.drawRect(400, 220, 250, 400);
		// GRADA 1
		g.drawRect(664, 120, 150, 600);
		// GRADA 2
		g.drawRect(350, 120, 300, 90);
		// GRADA 3
		g.drawRect(351, 630, 300, 90);

		g.setColor(Color.LIGHT_GRAY);
		// FONT STAGE
		if (color1 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(311, 221, 84, 400);
		// PISTA
		if (color2 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(401, 221, 249, 400);
		// GRADA 1
		if (color3 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(665, 121, 149, 599);
		// GRADA 2
		if (color4 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(351, 121, 299, 89);
		// GRADA 3
		if (color5 == false)
			g.setColor(Color.LIGHT_GRAY);
		else
			g.setColor(Color.DARK_GRAY);
		g.fillRect(352, 631, 299, 89);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Escenario", 150, 420);
		g.drawString("100", 325, 420);
		g.drawString("450", 510, 420);
		g.drawString("350", 710, 420);
		g.drawString("150", 480, 175);
		g.drawString("150", 480, 675);

	}

}