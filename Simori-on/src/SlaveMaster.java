import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class SlaveMaster extends JFrame implements ActionListener {
	  /**
	  * Sets up the GUI to select if  the user wants to be the slave or the master.
	  * @author Nick Higgins
	  */
	private JRadioButton slaveButton = new JRadioButton("Slave");
	JRadioButton masterButton = new JRadioButton("Master");
	public SlaveMaster(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JPanel central = new JPanel();
		
		JButton submit = new JButton("ok");
		submit.setBounds(140, 30, 50, 30);
		submit.addActionListener(this);
		slaveButton.setBounds(0, 0, 70, 30);
		
		masterButton.setBounds(0, 30, 70, 30);
		ButtonGroup group = new ButtonGroup();
		
		group.add(slaveButton);
		group.add(masterButton);
		this.add(slaveButton);
		this.add(masterButton);
		this.add(submit);
		this.setSize(200, 100);
		this.setResizable(false);
		this.setVisible(true);
		this.add(central);
		this.setLocationRelativeTo(null);
		
	}
	public void actionPerformed(ActionEvent e) {
		if(slaveButton.isSelected()){
			try {
				GUI.slave();
				 System.exit(0);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(masterButton.isSelected()){
			GUI.master();
			 System.exit(0);
		}
		else{
			JOptionPane.showMessageDialog(null, "Nothing selected");
		}
    }
	public static void main(String[] args){
		SlaveMaster sm = new SlaveMaster();
		
	}
}
	