import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NewSource extends JPanel{
	JTextField cppArea;
	ArrayList<NewHeader> headers;
	public NewSource(){
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
		headers = new ArrayList<NewHeader>();
		setLayout(new GridLayout(0, 1));
		cppArea = new JTextField(10);
		
		JPanel addPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		JButton addHpp = new JButton("+");
		JLabel sourceLbl = new JLabel("source:");
		sourceLbl.setAlignmentX(Component.LEFT_ALIGNMENT);
		cppArea.setAlignmentX(Component.LEFT_ALIGNMENT);
		JLabel headerLbl = new JLabel("headers:");
		
		addPanel.add(addHpp);
		
		
		add(sourceLbl);
		add(cppArea);
		add(headerLbl);
		
		headers.add(new NewHeader());
    	add(headers.get(headers.size()-1));
		
		add(addPanel);
		
		addHpp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	headers.add(new NewHeader());
            	add(headers.get(headers.size()-1));
            	remove(addPanel);
            	add(addPanel);
            	revalidate();
            	repaint();
            }
		});
		
	}
	public String getSource(){
		return cppArea.getText();
	}
}
