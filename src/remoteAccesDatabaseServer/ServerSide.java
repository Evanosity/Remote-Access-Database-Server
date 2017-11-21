package remoteAccesDatabaseServer;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class ServerSide{
	
	private static JFrame serverSide;
	private static JFrame registryF;
	private static JFrame registerUserF;
	
	//Declaring Components (Main)
	private static JButton registerUser;
	private static JButton shutdown;
	private static JButton lockAll;
	private static JButton registry;
	private static JTextArea log;
	private static JTextArea logTextArea;
	private static JLabel action;
	private static JPanel right;
	private static JScrollPane logField;
	private boolean usersLocked = false;
	//(Registry + Register User)
	public static JButton exit;
	//(Registry)
	private static JLabel nameLabel;
	private static JLabel companyLabel;
	private static JLabel account;
	//(Register User)
	private static JButton enter;
	private static JTextField name;
	private static JTextField company;
	private static JTextField adminPass;
	private static JLabel companyError;
	private static JLabel adminPassError;
	private static JLabel nameTitle;
	private static JLabel companyTitle;
	private static JLabel adminPassTitle;
	//Image Icon
	private static ImageIcon frameIcon = new ImageIcon("src\\remoteAccesDatabaseServer\\ServerIcon.png");
	
	
	
	public ServerSide(){
		serverSide = new JFrame();
		
		//JFrame Details
		serverSide.setVisible(true);
		serverSide.setSize(1372, 774);
		serverSide.setTitle("Server Side");
		serverSide.setDefaultCloseOperation(2); //2=EXIT_ON_CLOSE
		serverSide.setIconImage(frameIcon.getImage());
		serverSide.setResizable(false);
		
		
		//Container "c"
		Container c = new Container();
		c.setLayout(null);
		
		//Border Black
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		//Log Text Area
		log = new JTextArea("Server Access Log - click here for full log" + "\n" + "Latest Entries on Log" );
		log.setBackground(serverSide.getBackground());
		log.setSize(686, 40);
		log.setLocation(0, 0);
		log.setBorder(black);
		log.setVisible(true);
		c.add(log);
		
		
		//Action Label
		action = new JLabel("Action" );
		action.setHorizontalAlignment(SwingConstants.CENTER);
		action.setSize(686, 40);
		action.setLocation(686, 0);
		action.setBorder(black);
		action.setVisible(true);
		c.add(action);
		
		
		//Right Panel
		right = new JPanel();
		right.setSize(686, 734);
		right.setLocation(686, 40);
		right.setBorder(black);
		right.setVisible(true);
		c.add(right);
		
		
		//Log Text Area
		logTextArea = new JTextArea();
		logTextArea.setSize(686, 734);
		logTextArea.setLocation(0, 40);
		logTextArea.setBorder(black);
		logTextArea.setBackground(serverSide.getBackground());
		logTextArea.setEditable(false);
		
		
		//JScrollPane logField
		logField = new JScrollPane(logTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		logField.setSize(686, 734);
		logField.setLocation(0, 40);
		logField.setVisible(true);
		c.add(logField);
		
		
		//Register User Button
		registerUser = new JButton("Register User");
		registerUser.setSize(200,75);
		registerUser.setLocation(750, 100);
		registerUser.setVisible(true);
		c.add(registerUser);
		registerUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registerUser();
				//System.out.println("Width: " + serverSide.getWidth() + "Height: " + serverSide.getHeight());
			}
			
		});

		
		//Shutdown Button
		shutdown = new JButton("Shutdown");
		shutdown.setSize(200,75);
		shutdown.setLocation(750, 250);
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
		lockAll.setSize(200,75);
		lockAll.setLocation(750, 400);
		lockAll.setVisible(true);
		lockAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(usersLocked) {
					usersLocked = false;
					lockAll.setText("Lock All Users");
				}else {
					usersLocked = true;
					lockAll.setText("Unclock All Users");
				}
			}
			
		});
		c.add(lockAll);
		
		//Registry Button
		registry=new JButton("User Registry");
		registry.setSize(200,75);
		registry.setLocation(750, 550);
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
		
		
		
		//Set Layer Components on container
		c.setComponentZOrder(right, 4);
		c.setComponentZOrder(registerUser,0);
		c.setComponentZOrder(shutdown,1);
		c.setComponentZOrder(lockAll,2);
		c.setComponentZOrder(registry,3);
		
		
		
		serverSide.getContentPane().add(c);
		/**
		 * Shrinks JFrame to smallest possible size
		 * This is to fix a bug that a component won't
		 * show up until the frame is resized
		 */
		
		
		serverSide.pack();
		serverSide.setSize(1372, 774);
		serverSide.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
	}
	
	public void registryFrame(){
		JPanel left = new JPanel();
		JPanel middle = new JPanel();
		JPanel right = new JPanel();
		
		//New Frame Specs
		registryF = new JFrame("Registry Frame");
		registryF.setSize(525, 630);
		registryF.setLocation(346, 103);
		registryF.setResizable(false);
		registryF.setVisible(true);
		registryF.setIconImage(frameIcon.getImage());
		
		//Remove when you transfer
		//registryF.setDefaultCloseOperation(registryF.EXIT_ON_CLOSE);
		
		//Border
		Border black = BorderFactory.createLineBorder(Color.BLACK, 1);
		
		//New Container
		Container regC = new Container();
		regC.setLayout(null);
		registryF.getContentPane().add(regC);
		
		left.setBorder(black);
		left.setSize(175,516);
		left.setLocation(0,25);
		regC.add(left);
		
		middle.setBorder(black);
		middle.setSize(175,516);
		middle.setLocation(175,25);
		regC.add(middle);
		
		right.setBorder(black);
		right.setSize(169,516);
		right.setLocation(350,25);
		regC.add(right);
				
		
		nameLabel = new JLabel("Name");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setSize(175,25);
		nameLabel.setLocation(0,0);
		nameLabel.setBorder(black);
		regC.add(nameLabel);
		
		companyLabel = new JLabel("Company");
		companyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		companyLabel.setSize(175,25);
		companyLabel.setLocation(175, 0);
		companyLabel.setBorder(black);
		regC.add(companyLabel);
		
		account = new JLabel("Account Creation Date");
		account.setHorizontalAlignment(SwingConstants.CENTER);
		account.setSize(169,25);
		account.setLocation(350, 0);
		account.setBorder(black);
		regC.add(account);
		
		
		//Exit button
		exit = new JButton("Exit");
		exit.setSize(100, 50);
		exit.setLocation(262, 540);
		exit.setVisible(true);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				registryF.dispose();
			}
			
		});
		regC.add(exit);
		
		
	}
	
	
	public void registerUser() {
		registerUserF = new JFrame("Register User");
		registerUserF.setSize(576, 335);
		registerUserF.setLocation(709, 125);
		//registryF.setResizable(false);
		registerUserF.setVisible(true);
		registerUserF.setIconImage(frameIcon.getImage());
		
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
