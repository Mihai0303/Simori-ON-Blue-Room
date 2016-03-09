package Simori;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	public static Boolean slaveMaster = false;
	public final static int port = 20160;
	
	 private final static int PORT = 20160;
	 final static String HOST = "127.0.0.1";
	  
	public static void master(){
		try{	
			ServerSocket ss = new ServerSocket(PORT);
			while(true){
				Socket s = ss.accept();
				Layer[] layers = ChangeLayer.Layers;
				OutputStream out = s.getOutputStream();
				ObjectOutputStream writer = new ObjectOutputStream(out);
				writer.writeObject(layers);
			}}
		catch(IOException e){}
	}
	
	public static void slave() throws IOException{
		try{
			Socket s = new Socket(HOST, PORT);
			try{	
				InputStream in = s.getInputStream();
				OutputStream out = s.getOutputStream();
				ObjectInputStream reader = new ObjectInputStream(in);
				Layer[] layers = (Layer[]) reader.readObject();
				System.out.println(layers);
				ChangeLayer.Layers = layers;
			}catch(Exception e){}
			s.close();
		}catch(IOException e){}
	}
	
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
				slave();
				System.exit(0);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else if(masterButton.isSelected()){
			master();
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
	