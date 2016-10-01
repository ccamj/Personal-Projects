package image_editor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tab extends JPanel {
	int width, height;
	Canvas canvas;
	private BufferedImage img;
	private int index;
	public Tab(int width, int height, String name){
        setOpaque(true);
		this.width = width;
		this.height = height;
		
		img = new BufferedImage(10,10,BufferedImage.TYPE_INT_ARGB);
		
		init();
	}
	public Tab(int width, int height, BufferedImage image){
		setOpaque(true);
		this.width = width;
		this.height = height;
		img = image;
		init();
        
	}
	private void init(){
		GridBagConstraints gbc = new GridBagConstraints();
		
		//setBackground(Color.GREEN);
		canvas = new Canvas(width, height);
		canvas.setBackground(Color.WHITE);
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		canvas.setSize(width, height);
		canvas.setPreferredSize(new Dimension(width,height));
        add(canvas,gbc);
		
	}
}
