import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewHeader extends JPanel {
	JTextField hppArea;
	public NewHeader(){
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
		
		hppArea = new JTextField(10);
		hppArea.setAlignmentX(Component.RIGHT_ALIGNMENT);
		add(hppArea);
	}
	public String getHeader(){
		return hppArea.getText();
	}
}
