package image_editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ToolBar extends JPanel {
	Color color1 = Color.BLACK, color2 = Color.WHITE, color3;
	int strokeSize = 10;
	JButton primColor, secColor;
	BasicStroke stroke = new BasicStroke(10, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	
	public ToolBar(){
		// set toolbar size
		setPreferredSize(new Dimension(150,600));
		
		int brush = 0, pencil = 1;
		
		ImageIcon pencilIcon = loadImage("pencil.png");
		JToggleButton pencilButton = new JToggleButton(pencilIcon);
		pencilButton.setPreferredSize(new Dimension(30,30));
		
		ImageIcon brushIcon = loadImage("brush.png");
		JToggleButton brushButton = new JToggleButton(brushIcon);
		brushButton.setPreferredSize(new Dimension(30,30));
		
		
		JPanel colorRow = new JPanel();
		colorRow.setPreferredSize(new Dimension(148,55));
		
		primColor = new JButton();
		secColor = new JButton();
		primColor.setPreferredSize(new Dimension(50,50));
		secColor.setPreferredSize(new Dimension(50,50));
		
		primColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	color3 = color1;
            	color1 = JColorChooser.showDialog(null, "Primary Color", color1);
            	if(color1 != null)
            		primColor.setBackground(color1);
            	else
            		color1 = color3;
            }
		});
		
		secColor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	color3 = color2;
            	color2 = JColorChooser.showDialog(null, "Seconday Color", color2);
            	if(color2 != null)
            		secColor.setBackground(color2);
            	else
            		color2 = color3;
            }
		});
		
		primColor.setBackground(color1);
		secColor.setBackground(color2);
		
		ImageIcon swapIcon = loadImage("swap.png");
		JButton swapButton = new JButton(swapIcon);
		swapButton.setPreferredSize(new Dimension(25,20));
		swapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	swapColor();
            }
		});
		
		colorRow.add(primColor);
		colorRow.add(swapButton);
		colorRow.add(secColor);
		
		ButtonGroup btnGroup = new ButtonGroup();
		btnGroup.add(pencilButton);
		btnGroup.add(brushButton);
		
		add(pencilButton);
		add(brushButton);
		add(colorRow);
		
		
	}
	void swapColor(){
		color3 = color2;
		color2 = color1;
		color1 = color3;
		primColor.setBackground(color1);
		secColor.setBackground(color2);
	}
	ImageIcon loadImage(String path){
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(path));
		return icon;
	}
}
