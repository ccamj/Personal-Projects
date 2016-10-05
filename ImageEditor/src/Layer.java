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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class Layer extends JPanel{
	BufferedImage image;
	Graphics2D gfx;
	boolean isSelected = false;
	PreviewIcon imgPreview;
	int layerIndex;
	JToggleButton layerButton;
	//Color color = Color.BLACK;
	//BasicStroke stroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	public Layer(int width, int height, BufferedImage image, int index){
		setPreferredSize(new Dimension(100,70));
		this.image = image;
		gfx = image.createGraphics();
		layerIndex = index;
		
		layerButton = new JToggleButton("Layer "+index);
		layerButton.setPreferredSize(new Dimension(150,60));
		//JLabel layerName = new JLabel("Layer "+index);
		
		//setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgPreview = new PreviewIcon(image);
		

		JPanel layerbox = new JPanel();
		//layerbox.add(imgPreview);
		layerbox.add(layerButton);
		add(layerButton);
		
		setVisible(false);
	}
	void setIndex(int index){
		layerIndex = index;
	}
	int getIndex(){
		return layerIndex;
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
	//boolean isSelected(){
		//return isSelected;
	//}
	//void setSelected(boolean sel){
		//isSelected = sel;
	//}
}
