package image_editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class LayerWindow extends JPanel {
	BufferedImage layerImage;
	Layer[] layer;
	int count =0;
	public LayerWindow(int width, int height, BufferedImage image){
		layerImage = image;
		setLayout(new BorderLayout());
		//ArrayList<Layer> layer = new ArrayList<Layer>();
		
		
		ButtonGroup layerGroup = new ButtonGroup();
		JPanel layerScroll = new JPanel(new GridLayout(0, 1));
		JPanel layerControls = new JPanel();
		JPanel layerWindow = new JPanel(new GridLayout(0, 1));
		layer = new Layer[30];
		
		
		JButton addLayer = loadImage("add.png", "Add new Layer", 30, 30);
		JButton layerUp = loadImage("up.png", "Rise Layer 1 Step", 30, 30);
		JButton layerDown = loadImage("down.png", "Lower Layer 1 Step", 30, 30);
		JButton deleteLayer = loadImage("delete.png", "Delete Layer", 30, 30);
		
		//create first layer by default
		layer[count] = new Layer(width, height, layerImage, count);
		layerGroup.add(layer[count].layerButton);
		layer[count].layerButton.setSelected(true);
		layer[count].setVisible(true);
		layerScroll.add(layer[count]);
		count++;
		
		for(int i=1;i<30;i++){
			layer[i] = new Layer(width, height, new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB), i);
        	layerGroup.add(layer[i].layerButton);
        	layerScroll.add(layer[i]);
		}
		
		//layerScroll.add(scroll);
		
		layerControls.add(addLayer);
		layerControls.add(layerUp);
		layerControls.add(layerDown);
		layerControls.add(deleteLayer);
		
		JScrollPane scroll = new JScrollPane(layerScroll);
		scroll.setPreferredSize(new Dimension(150,580));
		layerWindow.add(scroll, BorderLayout.NORTH);
		layerWindow.add(layerControls, BorderLayout.SOUTH);
		
		add(layerWindow);
		
		addLayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(count< 30){
            		layer[count].setVisible(true);
            		layerGroup.clearSelection();
            		layer[count].layerButton.setSelected(true);
            		count++;
            	}
            }
		});
		layerUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
		});
		layerDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
		});
		deleteLayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
		});
		/*for(int i=0; i<layer.size()-1; i++){
			layer.get(i).addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            }
			});
		}*/
	}
	
	JButton loadImage(String path, String toolTip, int width, int height){
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
		JButton button = new JButton(icon);
		button.setToolTipText(toolTip);
		button.setPreferredSize(new Dimension(width,height));
		return button;
	}
}