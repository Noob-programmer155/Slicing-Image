package Main_Frame;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

public class File_proc extends JFileChooser{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private final Image lkh = new BufferedImage(20,20,BufferedImage.TYPE_INT_ARGB);
	private final WeakHashMap <File,ImageIcon> kjhp = new WeakHashMap <File,ImageIcon>();
	
	File_proc(String s){
		this.title = s;	
		file_log();
	}
	private void file_log() {
		Toolkit poi = Toolkit.getDefaultToolkit();
		setAcceptAllFileFilterUsed(false);
		setMultiSelectionEnabled(false);
		setDialogTitle(title);
		setSize(600, 400);
		/*
		 * lda.setPreferredSize(lda.getSize()); lda.setMinimumSize(lda.getSize());
		 * lda.setMaximumSize(lda.getSize());
		 */
		setLocation(poi.getScreenSize().width/2 - 300, poi.getScreenSize().height/2 - 200);
		setFileFilter(new Filterss());
		setFileSelectionMode(JFileChooser.FILES_ONLY);
		setFileView(new viewers());
		setApproveButtonToolTipText("Buka Gambar");
	}
	private class viewers extends FileView{
		private final ExecutorService ht = Executors.newSingleThreadScheduledExecutor();
		
		public String getDescription(File f) {
			return null;
		}
		public Icon getIcon(File h) {
			Sub_Data lke = new  Sub_Data();
			ImageIcon kgf = kjhp.get(h);
			if(h != null) {
			if (lke.getEkstensi(h).contentEquals(lke.giff) || lke.getEkstensi(h).contentEquals(lke.jpeg) || 
					lke.getEkstensi(h).contentEquals(lke.jpg) || lke.getEkstensi(h).contentEquals(lke.png) || lke.getEkstensi(h).contentEquals(lke.tiff)) {
					if(kgf == null) {
					kgf = new ImageIcon(lkh);
					kjhp.put(h, kgf);
					ht.submit(new getimagesd(h,kgf));
					SwingUtilities.invokeLater(new Runnable() {public void run() {repaint();}});
					System.gc();
					}
				}
			}
			repaint();
			return kgf;
		}
		public String getName(File f) {
			return null;
		}
		public String getTypeDescription(File g) {
			String bg = null;
			Sub_Data nsa = new Sub_Data();
			if(nsa.getEkstensi(g).contentEquals(nsa.giff)) {
				bg = new String("GIFF Image");
			}
			else if(nsa.getEkstensi(g).contentEquals(nsa.jpeg) || nsa.getEkstensi(g).contentEquals(nsa.jpg)) {
				bg = new String("JPG Image");
			}
			else if(nsa.getEkstensi(g).contentEquals(nsa.png)) {
				bg = new String("PNG Image");
			}
			else if(nsa.getEkstensi(g).contentEquals(nsa.tiff)) {
				bg = new String("TIFF Image");
			}
			else {}
			
			return bg;
		}
		public Boolean isTraversable(File g) {
			return null;
		}
	}
	private class getimagesd implements Runnable{
		private final File file;
		private final ImageIcon icon;
		getimagesd (File f, ImageIcon m){
			this.file = f;
			this.icon = m;
		}
		@Override
		public void run() {
			ImageIcon lwq = new ImageIcon(file.getAbsolutePath());
			Image jfd = lwq.getImage().getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH);
			icon.setImage(jfd);
			System.gc();
		}
	}
}

class Filterss extends FileFilter{
	@Override
	public boolean accept(File ar) {
		Sub_Data lk = new Sub_Data();
		String hg = lk.getEkstensi(ar);
		if(ar.isDirectory()) {
			return true;
		}
		if(hg != null) {
		if(hg.contentEquals(lk.giff) || hg.contentEquals(lk.jpeg) || hg.contentEquals(lk.jpg) || hg.contentEquals(lk.png) ||
				hg.contentEquals(lk.tiff)) {
			return true;
		}
		else {return false;}
		}
		return false;
	}
	@Override
	public String getDescription() {
		return ".jpg, .png, .tiff, .giff";
	}
}


