package image_editor;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu extends JFrame {
	private final JMenuBar menuBar;
	private final JMenu menu_file;
	private final JMenuItem file_new;
	private final JMenuItem file_open;
	private final JMenuItem file_save;
	private JTabbedPane tabs;
	JFrame thisframe = this;
	public MainMenu(){
		
		super("Image Editor");
		
		//setLayout(new GridLayout());
		tabs = new JTabbedPane();
		
		// Menu Bar & Components
		menuBar = new JMenuBar();
		menu_file = new JMenu("File");
		
		//File
		file_new = new JMenuItem("New");
		file_open = new JMenuItem("Open");
		file_save = new JMenuItem("Save");
		
		//File > New
		file_new.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	newProject();
            }

        });
		
		file_open.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	openImage();
            }

        });
		
		//Add Menu Components
		menu_file.add(file_new);
		menu_file.add(file_open);
		menu_file.add(file_save);
		menuBar.add(menu_file);
		
		//add(menuBar);
		setJMenuBar(menuBar);
		add(tabs);
		//add(canvasArea, SwingConstants.CENTER);
		setVisible(true);
	}
	
	private void newProject(){
		JFrame newFrame = new JFrame();
    	newFrame.setTitle("New");
    	newFrame.setVisible(true);
    	newFrame.setSize(400,150);
    	newFrame.setLocationRelativeTo(null);
    	
    	JPanel mainPanel = new JPanel();
    	mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    	JPanel namePanel = new JPanel();
    	JPanel widthPanel = new JPanel();
    	JPanel heightPanel = new JPanel();
    	JPanel okCancelPanel = new JPanel();
    	
    	JLabel nameLabel = new JLabel("Name: ");
    	JLabel widthLabel = new JLabel("Width: ");
    	JLabel heightLabel = new JLabel("Height: ");
    	JLabel wPixelLabel = new JLabel("px");
    	JLabel hPixelLabel = new JLabel("px");
    	namePanel.add(nameLabel);
    	widthPanel.add(widthLabel);
    	heightPanel.add(heightLabel);
    	
    	JTextField nameBox = new JTextField(15);
    	nameBox.setText("Untitled 1");
    	JTextField widthBox = new JTextField(5);
    	JTextField heightBox = new JTextField(5);
    	namePanel.add(nameBox);
    	widthPanel.add(widthBox);
    	heightPanel.add(heightBox);
    	widthPanel.add(wPixelLabel);
    	heightPanel.add(hPixelLabel);
    	
    	JButton newOK = new JButton("OK");
    	JButton newCancel = new JButton("Cancel");
    	okCancelPanel.add(newOK);
    	okCancelPanel.add(newCancel);
    	
    	newOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int width = Integer.parseInt(widthBox.getText());
            	int height = Integer.parseInt(heightBox.getText());
            	Tab newTab = new Tab(width, height, nameBox.getText());
            	tabs.addTab(nameBox.getText(), newTab);
            	newTab.repaint();
            	newFrame.dispatchEvent(new WindowEvent(newFrame, WindowEvent.WINDOW_CLOSING));
            }

        });
    	
    	mainPanel.add(namePanel);
    	mainPanel.add(widthPanel);
    	mainPanel.add(heightPanel);
    	mainPanel.add(okCancelPanel);
    	newFrame.add(mainPanel);
    	widthBox.requestFocusInWindow();
	}
	
	private void openImage(){
		JFileChooser chooser = new JFileChooser();
		File file;
		BufferedImage img;
		int result = chooser.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
			try
			{
				img=ImageIO.read(file);
				int width = img.getWidth();
				int height = img.getHeight();
				Tab newTab = new Tab(width, height, img);
				tabs.addTab(file.getName(), newTab);
				newTab.repaint();
				
			} catch(IOException e1) {}
		}
		
	}
}
