package Main_Frame;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Background_view extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage hg;
	private BufferedImage gh;
	private Integer anim;
	private int img = 1;
	private int img1 = 2;
	private Float Alpha = 0f;
	private long iu = -1;
	protected Timer fg;
	private Timer kj;
	Background_view(int secondanimation) throws IOException {
		this.anim = secondanimation;
		hg = ImageIO.read(Background_view.class.getResourceAsStream("/Main_Frame/ImageBg/"+img+".png"));
		gh = ImageIO.read(Background_view.class.getResourceAsStream("/Main_Frame/ImageBg/"+img1+".png"));
				
				kj = new Timer(40, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent ar) {
						if(iu < 0) {
							iu = System.currentTimeMillis();
						}
						else {
							long hf = System.currentTimeMillis();
							long dur = hf - iu;
							if(dur >= anim) {
								iu = -1;
								if(Alpha == 1f) {
									Alpha = 0f;
								}
								else {
									Alpha = 1f;
								}
								hgf();
							}
							else {
								Alpha= 1f - ((float)dur / (float)anim);
							}
							repaint();
						}
					}});
				hgf();
		}
	
	private void hgf() {
		try {
			Thread.sleep(anim-2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(img == 21) {
			img = 1;
		}
		if(img1 == 21) {
			img1 = 1;
		}
		try {
				hg = ImageIO.read(Background_view.class.getResourceAsStream("/Main_Frame/ImageBg/"+img+".png"));
				gh = ImageIO.read(Background_view.class.getResourceAsStream("/Main_Frame/ImageBg/"+img1+".png"));
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		kj.start();
		img++;
		img1++;
	}
	
	@Override
	protected void paintComponent(Graphics s) {
		super.paintComponent(s);
		Graphics2D poi = (Graphics2D) s.create();
		poi.setComposite(AlphaComposite.SrcOver.derive(Alpha));
		int x = (getWidth() - hg.getWidth())/2;
		int y = (getHeight() - hg.getHeight())/2;
		poi.drawImage(hg, x, y, this);
		
		poi.setComposite(AlphaComposite.SrcOver.derive(1f - Alpha));
		x = (getWidth() - gh.getWidth())/2;
		y = (getHeight() - gh.getHeight())/2;
		poi.drawImage(gh, x, y, this);
		poi.dispose();
	}
}
