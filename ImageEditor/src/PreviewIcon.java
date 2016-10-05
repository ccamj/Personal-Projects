package image_editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PreviewIcon extends JPanel{
	BufferedImage img;
	public PreviewIcon(BufferedImage image){
		img = image;
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		repaint();
	}
	void updateImage(BufferedImage image){
		img = image;
		repaint();
	}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(img, 0, 0, 75, 75, null);
	}
}
