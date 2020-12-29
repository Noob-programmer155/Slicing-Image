package Main_Frame;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;

public class Main{
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 */
	public Main() throws IOException, URISyntaxException, InterruptedException {
		initialize();
	}

	/**
	 * @throws IOException 
	 * @throws URISyntaxException 
	 * @throws InterruptedException 
	 */
	private void initialize() throws IOException, URISyntaxException, InterruptedException {
		Toolkit gfd = Toolkit.getDefaultToolkit();
		Sub_Data pfg = new Sub_Data();
		frame = new JFrame("Slice Image");
		frame.setBounds(gfd.getScreenSize().width/2-400, gfd.getScreenSize().height/2-300, 800, 600);
		frame.setResizable(false);
		Image io = ImageIO.read(Main.class.getResource("/Main_Frame/Untitled.png"));
		frame.setIconImage(io);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JLayeredPane p = new JLayeredPane();
		p.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		Background_view gf = new Background_view(5000);
		p.setLayer(gf, 0);
		gf.setBounds(p.getBounds());
		p.add(gf);
		
		JPanel le = new JPanel();
		p.setLayer(le, 1);
		le.setLayout(null);
		le.setBounds(0,p.getHeight()/2-175 , 600, 350);
		le.setBackground(new Color(255,255,255,50));
		Sub_Menu oi = new Sub_Menu(frame, pfg);
		oi.setBorder(BorderFactory.createLineBorder(Color.CYAN, 2));
		oi.setBounds(-2,p.getHeight()/2 - 260, 590, 300);
		le.add(oi);
		
		p.add(le);
		
		frame.getContentPane().add(p);
		
		frame.setJMenuBar(new button_menu(frame));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		frame.repaint();
	}
}
