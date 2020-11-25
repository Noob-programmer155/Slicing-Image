package Main_Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class Sub_Menu extends JPanel implements ActionListener, CaretListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lky;
	private Component copm;
	private Integer cgs;
	private Integer rgf;
	private JTextField mq;
	private JTextField me;
	private Sub_Data bee;
	private JPanel tr;
	private JLabel jge;
	private File hg;
	private JLabel jgw;
	
	Sub_Menu(Component kq, Sub_Data ly) throws IOException{
		this.copm = kq;
		this.bee = ly;
		setLayout(new FlowLayout(FlowLayout.LEFT,2,20));
		setBackground(new Color(255,255,255));
		
		JPanel yc = new JPanel();
		yc.setLayout(new BoxLayout(yc, BoxLayout.PAGE_AXIS));
		yc.setBackground(new Color(0,150,136));
		
		JPanel jg = bee.createButton("Pilih Gambar","Pilih gambar yang ingin di pecah", new Color(33,150,243), Color.WHITE, this);    //biru
		JPanel fc = bee.createLabelfor("<html><p align=center><Font face=\"Times New Roman\" size=5>Masukkan banyak gambar dengan form "
				+ "<br><strong><Font color=yellow>\" banyak gambar kesamping , banyak gambar kebawah \"</Font></strong></Font></p>","", null, Color.WHITE, bee.CENTER);
		tr = bee.createTextField("Masukkan angka untuk jumlah pecahan gambar(harus di enter)","text1", new Font("Times New Roman", Font.PLAIN, 15), yc.getBackground(), Color.WHITE,this);
		JPanel ui = bee.createLabelfor("<html><<Font face=\"Times New Roman\" size=5>Masukkan besar pecahan gambar <Font color=yellow><strong>\"p x l\"</Font></strong></Font>","", null, Color.WHITE, bee.CENTER);
		yc.add(jg); yc.add(fc); yc.add(tr); yc.add(ui);
		
		JPanel cdf = new JPanel();
		cdf.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
		cdf.setBackground(yc.getBackground());
		JLabel t = new JLabel("P: ");
		t.setBackground(yc.getBackground());
		t.setForeground(Color.WHITE);
		JLabel yt = new JLabel("L: ");
		yt.setBackground(yc.getBackground());
		yt.setForeground(Color.WHITE);
		mq = new JTextField();
		mq.setToolTipText("Masukkan nilai panjang");
		mq.setBorder(new TextBubbleBorder(Color.BLACK, 1, 50, 10, false));
		mq.setColumns(5);
		mq.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		mq.setBackground(yc.getBackground());
		mq.setForeground(Color.WHITE);
		me = new JTextField();
		me.setToolTipText("Masukkan nilai lebar");
		me.setBorder(new TextBubbleBorder(Color.BLACK, 1, 50, 10, false));
		me.setColumns(5);
		me.setOpaque(false);
		me.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		me.setBackground(yc.getBackground());
		me.setForeground(Color.WHITE);
		cdf.add(t); cdf.add(mq); cdf.add(yt); cdf.add(me);
		yc.add(cdf);
		
		JPanel pc = bee.createButton("Simpan","menyimpan hasil pecahan gambar", new Color(33,150,243), Color.WHITE, this);
		yc.add(pc);
		
		JPanel gd = new JPanel();
		gd.setLayout(new BorderLayout(5,5));
		jge = new JLabel();
		gd.add(jge, BorderLayout.CENTER);
		jgw = new JLabel();
		gd.add(jgw, BorderLayout.SOUTH);
		
		add(yc);
		add(gd);
	}
	@Override
	public void actionPerformed(ActionEvent a) {
		if (a.getActionCommand().contentEquals("text1")) {
			lky = ((JTextField)tr.getComponent(0)).getText();
			String uyt[] = lky.split(",");
			rgf = Integer.parseInt(uyt[uyt.length-1].trim()); 
			cgs = Integer.parseInt(uyt[0].trim());
			repaint();
		}
		else if (a.getActionCommand().contentEquals("Pilih Gambar")) {
			File_proc pot = new File_proc("pilihan gambar");
			int y = pot.showDialog(copm, "Buka");
			if(y == JFileChooser.APPROVE_OPTION) {
				bee.setFiles(pot.getSelectedFile());
				try {
					Icon nb = viewing();
					jge.setIcon(nb);
				} catch (IOException e) {
					e.printStackTrace();
				}
				pot.setSelectedFile(null);
			}
			repaint();
			}
		else if (a.getActionCommand().contentEquals("Simpan")) {
			if(rgf != null && cgs != null) {
				String x = mq.getText();
				String q = me.getText();
				Integer gv=0;
				Integer tc=0;
				if((x.contentEquals("") || q.contentEquals("")) && (gv <= 0 || tc <= 0)) {
					JOptionPane.showMessageDialog(copm, "Harap diisi formatnya dengan benar!", "Peringatan!!!", JOptionPane.ERROR_MESSAGE);
				}
				else {
					gv = Integer.parseInt(x);
					tc = Integer.parseInt(q);
					try {
						bee.imageslice(bee.getFiles(), rgf, cgs, gv, tc);
						JOptionPane.showMessageDialog(copm, "Gambar berhasil dibagi", "Berhasil", JOptionPane.INFORMATION_MESSAGE, new ImageIcon(Sub_Menu.class.getResource("/Main_Frame/success.png")));
					} catch (IOException e) {
						JOptionPane.showMessageDialog(copm, "Gambar tidak berhasil dibagi", "Gagal", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(copm, "Banyak gambar harap diisi dengan benar dan harus dienter bila telah selesai", "Peringatan!!!", JOptionPane.ERROR_MESSAGE);
			}
			repaint();
			}
	}
	@Override
	public void caretUpdate(CaretEvent ar) {
		if(ar.getSource() == JTextField.class) {
			me.repaint();
			mq.repaint();
		}
	}
	private Icon viewing() throws IOException {
		hg = bee.getFiles();
		Icon df = null;
		if(hg != null) {
			BufferedImage jh = ImageIO.read(hg);
			jgw.setText("<html><p align=center>"+hg.getName()+"<br>"+jh.getWidth()+" X "+jh.getHeight()+"</p>");
			int v = jh.getWidth();
			int b = jh.getHeight();
			if(b >= v) {
				Image jh1 = jh.getScaledInstance(100, this.getHeight()/2, BufferedImage.SCALE_SMOOTH); 
				df = new ImageIcon(jh1);
			}
			else {
				Image jh2 = jh.getScaledInstance(100, this.getHeight()/4, BufferedImage.SCALE_SMOOTH); 
				df = new ImageIcon(jh2);
			}
		}
		return df;
	}
}
