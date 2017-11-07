package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import com.sun.xml.internal.ws.api.Component;

@SuppressWarnings("serial")
public class ServerSide extends JFrame{
	
	//Declaring Components (Main)
	private JButton registerUser;
	private JButton shutdown;
	private JButton lockAll;
	private JButton registry;
	private JTextArea log;
	private JTextArea logTextArea;
	private JLabel action;
	private JPanel right;
	private JScrollPane logField;
	//(Registry + Register User)
	public JButton exit;
	//(Registry)
	//(Register User)
	private JButton enter;
	private JTextField name;
	private JTextField company;
	private JTextField adminPass;
	private JLabel companyError;
	private JLabel adminPassError;
	
	
	
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
		registerUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registerUser();
			}
			
		});

		
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
		registry.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registryFrame();
			}
			
		});
		
		
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
	
	void registryFrame(){
		//New Frame Specs
		JFrame registryF = new JFrame("Registry Frame");
		registryF.setSize(330, 500);
		registryF.setLocation(346, 103);
		registryF.setResizable(false);
		registryF.setVisible(true);
		
		//New Container
		Container regC = new Container();
		regC.setLayout(null);
		registryF.add(regC);
		
		//Exit button
		exit = new JButton("Exit");
		exit.setSize(100, 50);
		exit.setLocation(0, 0);
		exit.setVisible(true);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registryF.dispose();
			}
			
		});
		regC.add(exit);
		
		
	}
	
	
	void registerUser() {
		JFrame registerUserF = new JFrame("Register User");
		registerUserF.setSize(500, 311);
		registerUserF.setLocation(709, 125);
		//registryF.setResizable(false);
		registerUserF.setVisible(true);
		
		Container registerUserC = new Container();
		registerUserC.setLayout(null);
		registerUserF.add(registerUserC);
		
		exit = new JButton("Exit");
		exit.setSize(100, 50);
		exit.setLocation(0, 0);
		exit.setVisible(true);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registerUserF.dispose();
			}
			
		});
		registerUserC.add(exit);
		
		enter = new JButton("Enter");
		enter.setLocation(0,60);
		enter.setSize(100,50);
		enter.setVisible(true);
		registerUserC.add(enter);
		
		
		name = new JTextField();
		name.setLocation(0,60);
		name.setSize(200,50);
		name.setVisible(true);
		registerUserC.add(name);
		
		
		company = new JTextField();
		company.setLocation(0,180);
		company.setSize(200,50);
		company.setVisible(true);
		registerUserC.add(company);
		
		
		adminPass = new JTextField();
		adminPass.setLocation(0, 300);
		adminPass.setSize(200,50);
		adminPass.setVisible(true);
		registerUserC.add(adminPass);
		
		
		companyError = new JLabel("This Company Does not exist");
		companyError.setLocation(0,120);
		companyError.setSize(200,50);
		companyError.setVisible(true);
		registerUserC.add(companyError);
		
		
		adminPassError = new JLabel("This is not the right Admin password");
		adminPassError.setLocation(0,240);
		adminPassError.setSize(200,50);
		adminPassError.setVisible(true);
		registerUserC.add(adminPassError);
	}
}
