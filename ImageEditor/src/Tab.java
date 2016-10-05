package image_editor;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
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
		JPanel panel = new JPanel(new GridBagLayout());
		ToolBar tool = new ToolBar();
		
		LayerWindow layers = new LayerWindow(width, height, img);
		
		
		setLayout(new BorderLayout());
		add(tool, BorderLayout.WEST);
		
		
		canvas = new Canvas(width, height, layers);
		canvas.setBackground(Color.WHITE);
		canvas.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		canvas.setSize(width, height);
		canvas.setPreferredSize(new Dimension(width,height));
		
		panel.add(canvas, gbc);
		add(new JScrollPane(panel),  BorderLayout.CENTER);
		
		
		add(layers, BorderLayout.EAST);
		
	}
}
