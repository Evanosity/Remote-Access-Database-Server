package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.SwingConstants;

public class ServerSide extends JFrame{
	ServerSide(){
		//Set JFrame to be Visible
		setVisible(true);
		//frame set size
		setSize(1300, 1300);
		//
		setTitle("Server Side");
		
		Container c = new Container();
		c.setLayout(null);
		
		//Log title
		JTextArea log = new JTextArea("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		log.setBackground(getBackground());
		//Set Border
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		log.setBorder(black);
		//Add Label to Container C
		c.add(log);
		
		
		//Log title
		JLabel action = new JLabel("Action" );
		action.setHorizontalAlignment(SwingConstants.CENTER);
		//Set Border
		action.setBorder(black);
		//Add Label to Container C
		c.add(action);
		
		
		JPanel right = new JPanel();
		//JPanel right set Border to Border Black
		right.setBorder(black);
		//add right to container c
		c.add(right);
		
		//Declaring text area for scroll pane
		JTextArea logTextArea = new JTextArea();
		//
		logTextArea.setBorder(black);
		//Set Background to match Frame Background
		logTextArea.setBackground(getBackground());
		//
		logTextArea.setEditable(false);
		
		//Declare JScrollPane logField
		JScrollPane logField = new JScrollPane(logTextArea);
		//
		logField.setVisible(true);
		//
		c.add(logField);
		
		getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				Component c = (Component)e.getSource();
					//Log
				//Label set location
				log.setLocation(0, 0);
				//Label Set Size 	(w, h)
				log.setSize(getWidth()/2, (int)(getHeight()*0.10));
					//Action
				//Label Set Bounds	 (x,y,w,h)
				action.setLocation((getWidth()/2), 0);
				//Action set size
				action.setSize(getWidth()/2, (int)(getHeight()*0.10));
					//Left
				//JPanel left Set Size
				logField.setSize(getWidth()/2, (int)(getHeight()*0.90));
				//JPanel Left Set Location
				logField.setLocation(0, (int)(getHeight()*0.10));
					//Right
				//JPanel right Set Size
				right.setSize(getWidth()/2, (int)(getHeight()*0.90));
				//JPanel right Set Location
				right.setLocation((getWidth()/2), (int)(getHeight()*0.10));
			}
		});
		
		
		add(c);
		pack();
		//Maximize JFrame to be full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
}
