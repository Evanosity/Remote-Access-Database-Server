package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
//import java.awt.event.FocusEvent;
//import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class ServerSide extends JFrame{
	
	private JButton registerUser;
	private JButton shutdown;
	private JButton lockAll;
	private JButton registry;
	private JTextArea log;
	private JLabel action;
	private JPanel right;
	private JTextArea logTextArea;
	private JScrollPane logField;
	
	ServerSide(){
		registerUser = new JButton("Register User");
		shutdown = new JButton("Shutdown");
		lockAll = new JButton("Lock All USers");
		registry=new JButton("User Registry");
		
		//Set JFrame to be Visible
		setVisible(true);
		//frame set size
		setSize(1300, 1300);
		//This sets the title of the JFrame
		setTitle("Server Side");
		
		
		//Adds and implements a container named "c"
		Container c = new Container();
		//Sets the layout to null
		c.setLayout(null);
		
		//Log title
		log = new JTextArea("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		//Set Log Size
		//Set Background of Component log
		log.setBackground(getBackground());
		//Set Border
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		log.setBorder(black);
		//
		log.setVisible(true);
		//Add Label to Container C
		c.add(log);
		
		
		//Log title
		action = new JLabel("Action" );
		//Set the text Alignment for the text horizontally to be in the center
		action.setHorizontalAlignment(SwingConstants.CENTER);
		//Set Border
		action.setBorder(black);
		//Set the component action to be visible
		action.setVisible(true);
		//Add Label to Container C
		c.add(action);
		
		
		right = new JPanel();
		//JPanel right set Border to Border Black
		right.setBorder(black);
		//Set component right to be visible
		right.setVisible(true);
		//add right to container c
		c.add(right);
		
		
		//Declaring text area for scroll pane
		logTextArea = new JTextArea();
		//Set border of component logTextArea to be the boreder "black"
		logTextArea.setBorder(black);
		//Set Background to match Frame Background
		logTextArea.setBackground(getBackground());
		//Set editable to false
		logTextArea.setEditable(false);
		
		
		//Declare JScrollPane logField
		logField = new JScrollPane(logTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//Set component log field to be visible
		logField.setVisible(true);
		//add log field to container c
		c.add(logField);
		
		
		//Sets button "registerUser" to be visible
		registerUser.setVisible(true);
		//Add button "registerUser" to container "c"
		right.add(registerUser);

		
		//Set button "shutdown" to be visible
		shutdown.setVisible(true);
		//Add button "shutdown" to container "c"
		c.add(shutdown);
		
		
		//Set button "lockAll" to be visible
		lockAll.setVisible(true);
		//Add button "lockAll" to container "c"
		c.add(lockAll);
		
		
		//Set button "registry" to be visible
		registry.setVisible(true);
		//Add button "registry" to container "c"
		c.add(registry);
		
		/**
		 * This component listener listens for when the JFrame is resized
		 * Once it detects the action of being resized it will set the size
		 * of all components according to a ratio using the screen size
		 * this insures no matter the screen size all components should be 
		 * located properly
		 */
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
					//Register User
				////Register User set size 
				registerUser.setSize(200,75);
				// //Register User set location 
				registerUser.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.15));
					//Shutdown
				//Shutdown set size 
				shutdown.setSize(200,75);
				//Shutdown set location 
				shutdown.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.35));
					//Lock All
				//Lock all set size 
				lockAll.setSize(200,75);
				//Lock all set location
				lockAll.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.55));
					//Registry
				//Registry set size 
				registry.setSize(200,75);
				//Registry set location
				registry.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.75));
				
			}
		});
		
		
		c.setComponentZOrder(right, 0);
		c.setComponentZOrder(registerUser, 1);
		c.setComponentZOrder(shutdown,1);
		c.setComponentZOrder(lockAll,1);
		c.setComponentZOrder(registry,1);
		
		
		//Adds container "c" to JFrame "ServerSide"
		getContentPane().add(c);
		/**
		 * Shrinks JFrame to smallest possible size
		 * This is to fix a bug that a component won't
		 * show up until the frame is resized
		 */
		pack();
		//Maximize JFrame to be full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
}
