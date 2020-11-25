package Main_Frame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.AbstractBorder;

public class Sub_Data {
	protected final int LEFT = SwingConstants.LEFT;
	protected final int CENTER = SwingConstants.CENTER;
	protected final int RIGHT = SwingConstants.RIGHT;
	protected final String jpg = "jpg";
	protected final String jpeg = "jpeg";
	protected final String png = "png";
	protected final String tiff = "tiff";
	protected final String giff = "giff";
	public ImageIcon image;
	public File files;
	private int yu;
		
	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public JPanel createButton(String s, String Tool, Color Bg, Color Fg, ActionListener lg) {
		JPanel button = new JPanel();
		button.getInsets(new Insets(0,2,0,0));
		button.setLayout(new GridLayout(1,3));
		button.setBackground(new Color(0,150,136));
		
		JButton k = new JButton(s);
		k.setActionCommand(s);
		k.setToolTipText(Tool);
		k.setBackground(Bg);
		k.addActionListener(lg);
		k.setForeground(Fg);
		button.add(new JLabel());
		button.add(k);
		button.add(new JLabel());
		
		return button;
	}
	
	public JPanel createLabelfor(String text, String Tool, Color Bg, Color Fg, int Alignment) {
		JPanel label = new JPanel();
		label.getInsets(new Insets(0,2,0,0));
		label.setLayout(new FlowLayout());
		label.setBackground(null);
		
		JLabel l = new JLabel(text);
		l.setToolTipText(Tool);
		l.setBackground(Bg);
		l.setForeground(Fg);
		l.setHorizontalAlignment(Alignment);
		label.add(l);
		
		return label;
	}
	
	public JPanel createLabelfor(String text, String Tool, Font font, Color Bg, Color Fg, int Alignment) {
		JPanel label = new JPanel();
		label.getInsets(new Insets(0,2,0,0));
		label.setLayout(new FlowLayout());
		label.setBackground(null);
		
		JLabel p = new JLabel(text);
		p.setToolTipText(Tool);
		p.setFont(font);
		p.setBackground(Bg);
		p.setForeground(Fg);
		p.setHorizontalAlignment(Alignment);
		label.add(p);
		
		return label;
	}
	
	public JPanel createTextField(String Tool, String action, Font font, Color Bg, Color Fg, ActionListener kj) {
		JPanel text = new JPanel();
		text.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
		text.setBackground(Bg);
		
		JTextField p = new JTextField();
		p.setToolTipText(Tool);
		p.setFocusCycleRoot(false);
		p.setActionCommand(action);
		p.setBorder(new TextBubbleBorder(Color.BLACK, 1, 50, 10, false));
		p.setColumns(15);
		p.setFont(font);
		p.setBackground(Bg);
		p.setForeground(Fg);
		p.addActionListener(kj);
		text.add(p);
		
		return text;
	}
	
	public String getEkstensi(File g) {
		String jd = g.getName();
		String[] lo = jd.split("\\.");
		return lo[lo.length-1];
	}
	
//	public Icon setBackgroundImage(Component g,File h) throws IOException {
//		SwingUtilities.invokeLater(new Runnable() {
//			@Override
//			public void run() {
//				Image ytr;
//				try {
//					ytr = ImageIO.read(h).getScaledInstance(g.getWidth(), g.getHeight(), BufferedImage.SCALE_SMOOTH);
//					image = new ImageIcon(ytr);
//					g.repaint();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}});
//		return this.image;
//	}
//	
//	public Icon getBackgroundImage(Component split) {
//		if(image == null) {
//			JOptionPane.showMessageDialog(split, "\"Tidak ada gambar yang diterapkan\"", "Peringatan", JOptionPane.ERROR_MESSAGE);
//		}
//		else {}
//		return image;
//	}
	
	public void imageslice(File jh, int row, int column, int width1, int height1) throws IOException {
		String ht = jh.getName();
		String[] lo = ht.split("\\.");
		if(lo[lo.length-1] == "png" || lo[lo.length-1] == "gif") {
			yu = BufferedImage.TYPE_INT_ARGB;
		}
		else {
			yu = BufferedImage.TYPE_INT_RGB;
		}
		String uh = jh.getParent()+"\\Image Slicing";
		File gft = new File(uh);
		gft.mkdir();
		BufferedImage pok = ImageIO.read(jh);
		int widthimage = pok.getWidth()/column;
		int heightimage = pok.getHeight()/row;
		int po = 0;
		for(int y = 0; y < row; y++) {
			for(int x = 0; x < column ;x++) {
				BufferedImage sd = pok.getSubimage(x*widthimage, y*heightimage, widthimage, heightimage);
				Image poi = sd.getScaledInstance(width1, height1, BufferedImage.SCALE_AREA_AVERAGING);
				
				BufferedImage fds = new BufferedImage(width1, height1, yu); 
				fds.getGraphics().drawImage(poi, 0, 0, null);
				ImageIO.write(fds, lo[lo.length-1], new File(uh +"\\"+ lo[0] +"("+po+")."+ lo[lo.length-1]));
				po++;
			}
		}
	}
}

class TextBubbleBorder extends AbstractBorder {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color color;
    private int thickness = 4;
    private int radii = 8;
    private int pointerSize = 7;
    private Insets insets = null;
    private BasicStroke stroke = null;
    private int strokePad;
    private int pointerPad = 4;
    private boolean left = true;
    RenderingHints hints;

    TextBubbleBorder(
            Color color) {
        this(color, 4, 8, 7);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize) {
        this.thickness = thickness;
        this.radii = radii;
        this.pointerSize = pointerSize;
        this.color = color;

        stroke = new BasicStroke(thickness);
        strokePad = thickness / 2;

        hints = new RenderingHints(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = pointerSize + strokePad;
        int bottomPad = pad + pointerSize + strokePad;
        insets = new Insets(pad, pad+5, bottomPad, pad+5);
    }

    TextBubbleBorder(
            Color color, int thickness, int radii, int pointerSize, boolean left) {
        this(color, thickness, radii, pointerSize);
        this.left = left;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        return getBorderInsets(c);
    }

    @Override
    public void paintBorder(
            Component c,
            Graphics g,
            int x, int y,
            int width, int height) {

        Graphics2D g2 = (Graphics2D) g;

        int bottomLineY = height - thickness - pointerSize - strokePad;

        RoundRectangle2D.Double bubble = new RoundRectangle2D.Double(
                0 ,
                0 + strokePad+4.0,
                width - thickness,
                bottomLineY,
                radii,
                radii);

        Polygon pointer = new Polygon();

        if (left) {
            // left point
            pointer.addPoint(
                    strokePad + radii + pointerPad,
                    bottomLineY);
            // right point
            pointer.addPoint(
                    strokePad + radii + pointerPad + pointerSize,
                    bottomLineY);
            // bottom point
            pointer.addPoint(
                    strokePad + radii + pointerPad + (pointerSize / 2),
                    height - strokePad);
        } else {
			/*
			 * // left point pointer.addPoint( width - (strokePad + radii + pointerPad),
			 * bottomLineY); // right point pointer.addPoint( width - (strokePad + radii +
			 * pointerPad + pointerSize), bottomLineY); // bottom point pointer.addPoint(
			 * width - (strokePad + radii + pointerPad + (pointerSize / 2)), height -
			 * strokePad);
			 */
        }

        Area area = new Area(bubble);
        area.add(new Area(pointer));

        g2.setRenderingHints(hints);

        // Paint the BG color of the parent, everywhere outside the clip
        // of the text bubble.
        Component parent  = c.getParent();
        if (parent!=null) {
            Color bg = parent.getBackground();
            RoundRectangle2D.Double rect = new RoundRectangle2D.Double(0,0,width-thickness, bottomLineY,radii,radii);
            Area borderRegion = new Area(rect);
            borderRegion.subtract(area);
            g2.setClip(borderRegion);
            g2.setColor(bg);
            g2.fillRoundRect(0, 0, width-thickness, bottomLineY, radii, radii);
            g2.setClip(null);
        }

        g2.setColor(color);
        g2.setStroke(stroke);
        g2.draw(area);
    }
}

class button_menu extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Component lk;
	button_menu(Component h){
		this.lk = h;
		setBackground(Color.WHITE);
		setBorderPainted(false);
		
		JMenuItem pi = new JMenuItem("About");
		pi.setBackground(Color.WHITE);
		//pi.setFocusable(false);
		pi.setForeground(Color.CYAN);
		pi.addActionListener(new ActionListener() {
			private JDialog tr;
			private BufferedImage jhks;
			private float d = 0f;
			private Timer po;
			private Timer po1;
			@Override
			public void actionPerformed(ActionEvent ar) {
				try {
					jhks = ImageIO.read(Sub_Data.class.getResource("/Main_Frame/SliceLogo.png"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tr = new JDialog();
				tr.setBounds(lk.getX()+lk.getWidth()/2-200, lk.getY()+lk.getHeight()/2-150, 400, 300);
				tr.setUndecorated(true);
				tr.setOpacity(d);
				
				JPanel iu = new JPanel() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					protected void paintComponent(Graphics s) {
						super.paintComponent(s);
						Graphics2D bg = (Graphics2D) s.create();
						bg.drawImage(jhks, 0, 0, this);
					}
				};
				iu.setBounds(0,0,400,300);
				tr.setContentPane(iu);
				tr.setVisible(true);
				
				
				po1 = new Timer(30, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ar) {
						if(d > 0f) {	
							tr.setOpacity(d);
							d -= 0.01f;
							tr.repaint();
						}
						else {
							po1.stop();
							tr.repaint();
							tr.dispose();
							tr = null;
							System.gc();
							}
					}
				});
				po = new Timer(30, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ar) {
						if (d < 1.0f) {
							tr.setOpacity(d);
							d += 0.01f;
							tr.repaint();
						}
						else {
							d -= 0.01f;
							try {
								Thread.sleep(3000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							po.stop();
							po1.start();
						}
					}
				});
				po.start();
			}
		});
		add(pi);
		pi.addMouseListener(new MouseListener() {
			private Timer poi;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				pi.setBackground(Color.GREEN);
				pi.setForeground(Color.WHITE);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pi.setBackground(Color.WHITE);
				pi.setForeground(Color.CYAN);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				poi = new Timer(200,new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ar) {
						int r = (int) (Math.random()*250);
						int g = (int) (Math.random()*250);
						int b =	(int) (Math.random()*250);
						pi.setBackground(new Color(r,g,b));
						pi.setForeground(Color.WHITE);
					}});
				poi.start();
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				poi.stop();
				pi.setBackground(Color.WHITE);
				pi.setForeground(Color.CYAN);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}});
	}
}