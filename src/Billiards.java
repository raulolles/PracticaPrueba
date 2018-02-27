import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Billiards extends JFrame {

	public static int Width = 800;
	public static int Height = 600;

	private JButton b_start, b_stop;

	private Board board;

	// TODO update with number of group label. See practice statement.
	// HECHO. Grupo n∫3 -> 3+3=6
	private final int N_BALL = 6;
	private Ball[] balls;

	public Billiards() {

		board = new Board();
		board.setForeground(new Color(0, 128, 0));
		board.setBackground(new Color(0, 128, 0));

		initBalls();

		b_start = new JButton("Empezar");
		b_start.addActionListener(new StartListener());
		b_stop = new JButton("Parar");
		b_stop.addActionListener(new StopListener());

		JPanel p_Botton = new JPanel();
		p_Botton.setLayout(new FlowLayout());
		p_Botton.add(b_start);
		p_Botton.add(b_stop);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(board, BorderLayout.CENTER);
		getContentPane().add(p_Botton, BorderLayout.PAGE_END);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Width, Height);
		setLocationRelativeTo(null);
		setTitle("Pr√°ctica programaci√≥n concurrente objetos m√≥viles independientes");
		setResizable(false);
		setVisible(true);
	}

	private void initBalls() {
		// TODO init balls
		// HECHO --PARA REVISAR --
		
		balls = new Ball[N_BALL];
		for (int i=0; i< N_BALL; i++){
			balls[i] = new Ball();
		}
	}

	private class StartListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Code is executed when start button is pushed
			// øEs necesario que StartListener sea synchronized?
			

		}
	}

	private class StopListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Code is executed when stop button is pushed

		}
	}

	// Fuera del inicial
	// Hilos
	private class Hilo implements Runnable{
		private Ball miBola;
		
		public Hilo (Ball bola){
			miBola = bola;
		}
		
		@Override
		public void run(){
			boolean continua = true;
			try{
				while (continua) {
					miBola.move();
					board.setBalls(balls);
					board.repaint();
					Thread.sleep(500);
				}
			} catch (InterruptedException e){
				continua = false;
				System.out.println("ERROR");
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		new Billiards();
	}
}