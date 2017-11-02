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
import javax.swing.border.Border;
import javax.swing.SwingConstants;

public class ServerSide extends JFrame{
	ServerSide(){
		//Set JFrame to be Visible
		setVisible(true);
		//Maximize JFrame to be full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//
		//setResizable(false);
		
		Container c = new Container();
		c.setLayout(null);
		
		//Log title
		JLabel log = new JLabel("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		log.setHorizontalAlignment(SwingConstants.CENTER);
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
		
		JPanel left = new JPanel();
		//JPanel Left set Border to Border Black
		left.setBorder(black);
		//add Left to container c
		c.add(left);
		
		
		JPanel right = new JPanel();
		//JPanel right set Border to Border Black
		right.setBorder(black);
		//add right to container c
		c.add(right);
		
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
				left.setSize(getWidth()/2, (int)(getHeight()*0.90));
				//JPanel Left Set Location
				left.setLocation(0, (int)(getHeight()*0.10));
					//Right
				//JPanel right Set Size
				right.setSize(getWidth()/2, (int)(getHeight()*0.90));
				//JPanel right Set Location
				right.setLocation((getWidth()/2), (int)(getHeight()*0.10));
			}
		});
		
		
		add(c);
		pack();
		//frame set size
		setSize(1300, 1300);
	}
}
