package image_editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Layer extends JPanel{
	BufferedImage image;
	Graphics2D gfx;
	boolean isSelected = false;
	PreviewIcon imgPreview;
	//Color color = Color.BLACK;
	//BasicStroke stroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	public Layer(int width, int height, BufferedImage image){
		this.image = image;
		gfx = image.createGraphics();
		
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPreview = new PreviewIcon(image);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            }
		});
		JPanel layerbox = new JPanel();
		layerbox.add(imgPreview);
		layerbox.add(delete);
		add(layerbox);
		
		addMouseListener(new MouseAdapter() {
	        @Override
	        public void mousePressed(MouseEvent e) {
	        	isSelected = true;
	        }
		});
		
		
	}
	void setGraphics(){
		gfx.setColor(Color.BLACK);
		gfx.setStroke(new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		gfx.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
	void updateImage(BufferedImage image){
		imgPreview.updateImage(image);
	}
	BufferedImage getImage(){
		return image;
	}
	boolean isSelected(){
		return isSelected;
	}
	void setSelected(boolean sel){
		isSelected = sel;
	}
}
