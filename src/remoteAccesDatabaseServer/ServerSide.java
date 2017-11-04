package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.xml.internal.ws.api.Component;

@SuppressWarnings("serial")
public class ServerSide extends JFrame{
	
	//Declaring Components
	private JButton registerUser;
	private JButton shutdown;
	private JButton lockAll;
	private JButton registry;
	private JTextArea log;
	private JTextArea logTextArea;
	private JLabel action;
	private JPanel right;
	private JScrollPane logField;
	
	ServerSide(){
		
		//JFrame Details
		setVisible(true);
		setSize(1300, 1300);
		setTitle("Server Side");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		//Container "c"
		Container c = new Container();
		c.setLayout(null);
		
		//Border Black
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		//Log Text Area
		log = new JTextArea("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		log.setBackground(getBackground());
		log.setBorder(black);
		log.setVisible(true);
		c.add(log);
		
		
		//Action Label
		action = new JLabel("Action" );
		action.setHorizontalAlignment(SwingConstants.CENTER);
		action.setBorder(black);
		action.setVisible(true);
		c.add(action);
		
		
		//Right Panel
		right = new JPanel();
		right.setBorder(black);
		right.setVisible(true);
		c.add(right);
		
		
		//Log Text Area
		logTextArea = new JTextArea();
		logTextArea.setBorder(black);
		logTextArea.setBackground(getBackground());
		logTextArea.setEditable(false);
		
		
		//JScrollPane logField
		logField = new JScrollPane(logTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logField.setVisible(true);
		c.add(logField);
		
		
		//Register User Button
		registerUser = new JButton("Register User");
		registerUser.setVisible(true);
		c.add(registerUser);

		
		//Shutdown Button
		shutdown = new JButton("Shutdown");
		shutdown.setVisible(true);
		c.add(shutdown);
		
		
		//Lock All Users Button
		lockAll = new JButton("Lock All Users");
		lockAll.setVisible(true);
		c.add(lockAll);
		
		//Registry Button
		registry=new JButton("User Registry");
		registry.setVisible(true);
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
					//Log
				log.setLocation(0, 0);
				log.setSize(getWidth()/2, (int)(getHeight()*0.10));
					//Action
				action.setLocation((getWidth()/2), 0);
				action.setSize(getWidth()/2, (int)(getHeight()*0.10));
					//Left
				logField.setSize(getWidth()/2, (int)(getHeight()*0.90));
				logField.setLocation(0, (int)(getHeight()*0.10));
					//Right
				right.setSize(getWidth()/2, (int)(getHeight()*0.90));
				right.setLocation((getWidth()/2), (int)(getHeight()*0.10));
					//Register User
				registerUser.setSize(200,75);
				registerUser.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.15));
					//Shutdown
				shutdown.setSize(200,75);
				shutdown.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.35));
					//Lock All
				lockAll.setSize(200,75);
				lockAll.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.55));
					//Registry
				registry.setSize(200,75);
				registry.setLocation((getWidth()/2) + (int)(getWidth()*0.1),(int)(getHeight()*.75));
				
			}
		});
		
		
		//Set Layer Components on container
		c.setComponentZOrder(right, 4);
		c.setComponentZOrder(registerUser,0);
		c.setComponentZOrder(shutdown,1);
		c.setComponentZOrder(lockAll,2);
		c.setComponentZOrder(registry,3);
		
		
		
		add(c);
		/**
		 * Shrinks JFrame to smallest possible size
		 * This is to fix a bug that a component won't
		 * show up until the frame is resized
		 */
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		
	}
}
