package image_editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.*;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	int x,y,old_x,old_y;
	BufferedImage image;
	Graphics2D g2;
	int layer_count = 1;
	//ArrayList<Layer> layer;
	Layer[] imgLayer;
	public Canvas(int width, int height, LayerWindow layers){
		//setLayout(new LayoutManger(new GridBagConstraints()));
		imgLayer = layers.layer;
		
		image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		
		//layer = new ArrayList<Layer>();
		//layer.add(new Layer(width, height, image,0));
		
		addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	old_x = e.getX();
	        	old_y = e.getY();
	        	
	        	
        		//g2.drawLine(old_x, old_y, old_x, old_y);
	        	//layer.get(0).setGraphics();
	        	//layer.get(0).gfx.drawLine(old_x, old_y, old_x, old_y);
	        	imgLayer[0].setGraphics();
	        	imgLayer[0].gfx.drawLine(old_x, old_y, old_x, old_y);
        		
        		repaint();
	        }
		});
		
		addMouseMotionListener(new MouseMotionAdapter(){
	        @Override
	        public void mouseDragged(MouseEvent e){
	        	x = e.getX();
	        	y = e.getY();
	        		
	        	//image.setRGB(x, y, 0xFFFF0000);
	        	/*
	        	g2.setColor(Color.BLACK);
	        	g2.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	        	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        	g2.drawLine(x, y, old_x, old_y);
	        	*/
	        	//layer.get(0).setGraphics();
	        	//layer.get(0).gfx.drawLine(x, y, old_x, old_y);
	        	imgLayer[0].setGraphics();
	        	imgLayer[0].gfx.drawLine(x, y, old_x, old_y);
	        		
	        		
	        		
	        		repaint();
	        		old_x = x;
	        		old_y = y;
	        	//}
	        }
		});
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		//layer.get(0).gfx = layer.get(0).image.createGraphics();
		imgLayer[0].gfx = imgLayer[0].image.createGraphics();
		//g2.setStroke(new BasicStroke(20));
		g.setColor(Color.RED);
		g.drawImage(imgLayer[0].image, 0, 0, null);
	}
}

