import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SlaveMaster extends JFrame {
	  /**
	  * Sets up the GUI to select if  the user wants to be the slave or the master.
	  * @author Nick Higgins
	  */
	public SlaveMaster(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JPanel central = new JPanel();
		
		JButton submit = new JButton("ok");
		submit.setBounds(140, 140, 50, 30);
		//ActionListener click = new ActionListener();
		//submit.addActionListener(arg0);
		JRadioButton slaveButton = new JRadioButton("Slave");
		slaveButton.setBounds(0, 0, 200, 30);
		JRadioButton masterButton = new JRadioButton("Master");
		masterButton.setBounds(0, 30, 200, 30);
		ButtonGroup group = new ButtonGroup();
		
		group.add(slaveButton);
		group.add(masterButton);
		this.add(slaveButton);
		this.add(masterButton);
		this.add(submit);
		this.setSize(200, 200);
		this.setResizable(false);
		this.setVisible(true);
		this.add(central);
		
	}
}
	