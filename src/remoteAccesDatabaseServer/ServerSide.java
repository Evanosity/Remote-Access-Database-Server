package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
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
public class ServerSide{
	
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
	private JLabel nameTitle;
	private JLabel companyTitle;
	private JLabel adminPassTitle;
	
	
	
	ServerSide(){
		JFrame serverSide = new JFrame();
		
		//JFrame Details
		serverSide.setVisible(true);
		serverSide.setSize(1300, 1300);
		serverSide.setTitle("Server Side");
		serverSide.setDefaultCloseOperation(serverSide.EXIT_ON_CLOSE);
		
		
		//Container "c"
		Container c = new Container();
		c.setLayout(null);
		
		//Border Black
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		//Log Text Area
		log = new JTextArea("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		log.setBackground(serverSide.getBackground());
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
		logTextArea.setBackground(serverSide.getBackground());
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
		shutdown.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				serverSide.dispose();
			}
			
		});
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
		serverSide.getContentPane().addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
					//Log
				log.setLocation(0, 0);
				log.setSize(serverSide.getWidth()/2, (int)(serverSide.getHeight()*0.10));
					//Action
				action.setLocation((serverSide.getWidth()/2), 0);
				action.setSize(serverSide.getWidth()/2, (int)(serverSide.getHeight()*0.10));
					//Left
				logField.setSize(serverSide.getWidth()/2, (int)(serverSide.getHeight()*0.90));
				logField.setLocation(0, (int)(serverSide.getHeight()*0.10));
					//Right
				right.setSize(serverSide.getWidth()/2, (int)(serverSide.getHeight()*0.90));
				right.setLocation((serverSide.getWidth()/2), (int)(serverSide.getHeight()*0.10));
					//Register User
				registerUser.setSize(200,75);
				registerUser.setLocation((serverSide.getWidth()/2) + (int)(serverSide.getWidth()*0.1),(int)(serverSide.getHeight()*.15));
					//Shutdown
				shutdown.setSize(200,75);
				shutdown.setLocation((serverSide.getWidth()/2) + (int)(serverSide.getWidth()*0.1),(int)(serverSide.getHeight()*.35));
					//Lock All
				lockAll.setSize(200,75);
				lockAll.setLocation((serverSide.getWidth()/2) + (int)(serverSide.getWidth()*0.1),(int)(serverSide.getHeight()*.55));
					//Registry
				registry.setSize(200,75);
				registry.setLocation((serverSide.getWidth()/2) + (int)(serverSide.getWidth()*0.1),(int)(serverSide.getHeight()*.75));
				
			}
		});
		
		
		//Set Layer Components on container
		c.setComponentZOrder(right, 4);
		c.setComponentZOrder(registerUser,0);
		c.setComponentZOrder(shutdown,1);
		c.setComponentZOrder(lockAll,2);
		c.setComponentZOrder(registry,3);
		
		
		
		serverSide.add(c);
		/**
		 * Shrinks JFrame to smallest possible size
		 * This is to fix a bug that a component won't
		 * show up until the frame is resized
		 */
		
		
		serverSide.pack();
		serverSide.setSize(1300, 1300);
		serverSide.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
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
		registerUserF.setSize(576, 335);
		registerUserF.setLocation(709, 125);
		//registryF.setResizable(false);
		registerUserF.setVisible(true);
		
		Container registerUserC = new Container();
		registerUserC.setLayout(null);
		registerUserF.getContentPane().add(registerUserC);
		
		exit = new JButton("Exit");
		exit.setSize(100, 50);
		exit.setLocation(424, 235);
		exit.setVisible(true);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registerUserF.dispose();
				//System.out.println("Height: " + registerUserF.getHeight() + "\n Width: " + registerUserF.getWidth() + "\n X pos: " + registerUserF.getX() + "\n Y pos: " + registerUserF.getY());
			}
			
		});
		registerUserC.add(exit);
		
		enter = new JButton("Enter");
		enter.setLocation(288,235);
		enter.setSize(100,50);
		enter.setVisible(true);
		enter.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(adminPassError.isVisible()) {
					adminPassError.setVisible(false);
					companyError.setVisible(false);
				}
				else{
					adminPassError.setVisible(true);
					companyError.setVisible(true);
				}
			}
			
		});
		registerUserC.add(enter);
		
		
		name = new JTextField();
		name.setLocation(10,29);
		name.setSize(204,41);
		name.setVisible(true);
		registerUserC.add(name);
		
		
		company = new JTextField();
		company.setLocation(10,125);
		company.setSize(204,41);
		company.setVisible(true);
		registerUserC.add(company);
		
		
		adminPass = new JTextField();
		adminPass.setLocation(10, 220);
		adminPass.setSize(204,41);
		adminPass.setVisible(true);
		registerUserC.add(adminPass);
		
		
		companyError = new JLabel("This Company Does not exist");
		companyError.setForeground(Color.RED);
		companyError.setLocation(10,166);
		companyError.setSize(204,27);
		companyError.setVisible(false);
		registerUserC.add(companyError);
		
		
		adminPassError = new JLabel("Incorrect Administrator Password"); 
		adminPassError.setForeground(Color.RED);
		adminPassError.setLocation(10,261);
		adminPassError.setSize(204,27);
		adminPassError.setVisible(false);
		registerUserC.add(adminPassError);
		
		nameTitle = new JLabel();
		nameTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		nameTitle.setHorizontalAlignment(SwingConstants.CENTER);
		nameTitle.setText("Name");
		nameTitle.setLocation(10,1);
		nameTitle.setSize(204,27);
		nameTitle.setVisible(true);
		registerUserC.add(nameTitle);
		
		companyTitle = new JLabel();
		companyTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		companyTitle.setHorizontalAlignment(SwingConstants.CENTER);
		companyTitle.setText("Company");
		companyTitle.setLocation(10,98);
		companyTitle.setSize(204,27);
		companyTitle.setVisible(true);
		registerUserC.add(companyTitle);
		
		
		adminPassTitle = new JLabel();
		adminPassTitle.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		adminPassTitle.setHorizontalAlignment(SwingConstants.CENTER);
		adminPassTitle.setText("Administrator Password");
		adminPassTitle.setLocation(10,193);
		adminPassTitle.setSize(204,27);
		adminPassTitle.setVisible(true);
		registerUserC.add(adminPassTitle);
		
		registerUserF.pack();
		registerUserF.setSize(576, 335);
	}
}
