package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Container;
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
		//
		setSize(1300, 1300);
		//Maximize JFrame to be full screen
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//
		setResizable(false);
		
		Container c = new Container();
		c.setLayout(null);
		
		//Log title
		JLabel log = new JLabel("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		log.setHorizontalAlignment(SwingConstants.CENTER);
		log.setLocation(0, 0);
		//Label Set Size 	(h,w)
		log.setSize(648, 100);
		//Set Border
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		log.setBorder(black);
		//Add Label to Container C
		c.add(log);
		
		
		//Log title
		JLabel action = new JLabel("Action" );
		action.setHorizontalAlignment(SwingConstants.CENTER);
		//Label Set Bounds	 (x,y,h,w)
		action.setLocation(689, 0);
		//
		action.setSize(648, 100);
		//Set Border
		action.setBorder(black);
		//Add Label to Container C
		c.add(action);
		
		JPanel left = new JPanel();
		//JPanel left Set Size
		left.setSize(648,644);
		//JPanel Left Set Location
		left.setLocation(0,99);
		//JPanel Left set Border to Border Black
		left.setBorder(black);
		//add Left to container c
		c.add(left);
		
		
		JPanel right = new JPanel();
		//JPanel right Set Size
		right.setSize(648,644);
		//JPanel right Set Location
		right.setLocation(689,99);
		//JPanel right set Border to Border Black
		right.setBorder(black);
		//add right to container c
		c.add(right);
		
		
		getContentPane().add(c);
	}
}
