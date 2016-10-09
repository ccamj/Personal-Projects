import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class Builder extends JFrame {
	public Builder(){
		super("Makefile Builder");
		
		ArrayList<NewSource> sources = new ArrayList<NewSource>();
		JPanel all = new JPanel();
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		JPanel sourceHeaderPanel = new JPanel();
		JPanel addPnl = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton addCpp = new JButton("Add");
		
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
		
		all.add(container);
		add(new JScrollPane(all));
		
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
						out.write("-o program -lsfml-graphics -lsfml-window -lsfml-system\n\n");
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
							out.write("-Wall -Werror -ansi -pedantic\n\n");
						}
						out.write("clean:\n\t");
						out.write("rm -rf program *.o *.~");
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
		app.setSize(600,500);
		app.setLocationRelativeTo(null);
	}
}