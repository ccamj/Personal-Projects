import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Builder extends JFrame {
	public Builder(){
		super("Makefile Builder");
		
		ArrayList<NewSource> sources = new ArrayList<NewSource>();
		JPanel all = new JPanel();
		all.setLayout(new BoxLayout(all, BoxLayout.PAGE_AXIS));
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel sourceHeaderPanel = new JPanel();
		JPanel addPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton addCpp = new JButton("Add");
		
		JPanel flagcont = new JPanel(new FlowLayout());
		JPanel flags = new JPanel(new GridLayout(0, 1));
		JTextField execBox = new JTextField(15);
		execBox.setText("program");
		JTextField cflagBox = new JTextField(20);
		cflagBox.setText("-Wall -Werror -ansi -pedantic");
		JTextField lflagBox = new JTextField(20);
		lflagBox.setText("-lsfml-graphics -lsfml-window -lsfml-system");
		JLabel execLbl = new JLabel("Executable: ");
		JLabel cflagLbl = new JLabel("Compiling Flags: ");
		JLabel lflagLbl = new JLabel("Library Flags: ");
		JPanel boostPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel boostLbl = new JLabel("Boost Unit Test");
		JCheckBox boostCheckBox = new JCheckBox();
		
//		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("mfileicon.png"));
//		ImageIcon icon = new ImageIcon( );
//		setIconImage(icon.getImage());
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("mfileicon.png"));
		setIconImage(icon.getImage());
		/*
		try {
		    setIconImage(ImageIO.read(new File("res/mfileicon.png")));
		}
		catch (IOException exc) {
		    exc.printStackTrace();
		}*/
		
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
	    fileMenu.setMnemonic(KeyEvent.VK_F);
	    menu.add(fileMenu);
	    
	    JMenuItem saveItem = new JMenuItem("Save Makefile", KeyEvent.VK_S);
	    fileMenu.add(saveItem);
	    
	    
	    
	    setJMenuBar(menu);
		
		
		sources.add(new NewSource());
		sourceHeaderPanel.add(sources.get(sources.size()-1));
		container.add(sourceHeaderPanel);
		addPnl.add(addCpp);
		container.add(addPnl);
		
		
		flags.add(execLbl);
		flags.add(execBox);
		flags.add(cflagLbl);
		flags.add(cflagBox);
		flags.add(lflagLbl);
		flags.add(lflagBox);
		
		boostPnl.add(boostCheckBox);
		boostPnl.add(boostLbl);
		flags.add(boostPnl);
		flagcont.add(flags);
		all.add(flagcont,new GridBagConstraints());
		all.add(new JScrollPane(container));
		
		add(all);
		
		
		addCpp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	sources.add(new NewSource());
            	sourceHeaderPanel.add(sources.get(sources.size()-1));
            	revalidate();
            	repaint();
            }
		});
		
		saveItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        	JFileChooser saveFile = new JFileChooser();
	        	saveFile.setSelectedFile(new File("Makefile"));
                int returnVal = saveFile.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                	File file = saveFile.getSelectedFile();
                	
                	for(int i=0;i<=sources.size()-1;i++){
                		if(sources.get(i).getSource().contains("."))
                			continue;
                		return;
                	}
                	try {
						BufferedWriter out = new BufferedWriter(new FileWriter(file));
						out.write("all: proj\n\n");
						out.write("proj: ");
						ArrayList<String> source_list = new ArrayList<String>();
						for(int i=0;i<=sources.size()-1;i++){
							int index = 0;
							for(;sources.get(i).getSource().charAt(index) != '.';index++){
								
							}
							if(sources.get(i).getSource().contains(".")){
								source_list.add(sources.get(i).getSource().substring(0, index));
								out.write(source_list.get(i) + ".o ");
							}
						}
						out.write("\n\t g++ ");
						for(int i=0;i<=sources.size()-1;i++){
							if(sources.get(i).getSource().contains("."))
								out.write(source_list.get(i) + ".o ");
						}
						out.write("-o ");
						out.write(execBox.getText());
						out.write(" ");
						out.write(lflagBox.getText());
						if(boostCheckBox.isSelected()){
							out.write(" -lboost_unit_test_framework");
						}
						out.write("\n\n");
						for(int outer=0;outer<=sources.size()-1;outer++){
							if(sources.get(outer).getSource().contains(".")){
								out.write(source_list.get(outer) + ".o: ");
								out.write(sources.get(outer).getSource() + " ");
							}
							for(int inner=0;inner<=sources.get(outer).headers.size()-1;inner++){
								if(sources.get(outer).headers.get(inner).getHeader().contains("."))
									out.write(sources.get(outer).headers.get(inner).getHeader() + " ");
							}
							out.write("\n\tg++ -c "+ sources.get(outer).getSource() + " ");
							out.write(cflagBox.getText());
							out.write("\n\n");
						}
						out.write("clean:\n\t");
						out.write("rm -rf ");
						out.write(execBox.getText());
						out.write(" *.o *.~");
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
	        }
	    });
		
		setVisible(true);
	}
	public static void main(String[] args){
		Builder app = new Builder();

		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(600,600);
		app.setLocationRelativeTo(null);
	}
}