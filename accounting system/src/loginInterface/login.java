package loginInterface;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class login extends JFrame {
	JPanel bosspanel = new JPanel();
	JPanel loginpanel = new JPanel();
	JPanel optionpanel = new JPanel();
	JPanel foodlistpanel = new JPanel();

	private String password = "duclub";

	public login() {
		setTitle("Accountimg system");
		setSize(700, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setContentPane(bosspanel);
		bosspanel.setVisible(true);
		bosspanel.setLayout(new CardLayout(0, 0));
		loginPage();
	}

	private void loginPage() {
		JLabel usernameLabel = new JLabel("Enter username");
		JLabel passwordLabel = new JLabel("Enter password");
		JTextField usernameTextField = new JTextField();
		JPasswordField passwordTextField = new JPasswordField();

		usernameLabel.setBounds(30, 130, 120, 30);
		usernameTextField.setBounds(220, 130, 250, 30);
		passwordLabel.setBounds(30, 180, 120, 30);
		passwordTextField.setBounds(220, 180, 250, 30);
		loginpanel.add(usernameLabel);
		loginpanel.add(passwordLabel);
		loginpanel.add(usernameTextField);
		loginpanel.add(passwordTextField);

		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (usernameTextField.getText().equals("Accountant")) {
					if (new String(passwordTextField.getPassword()).equals(password)) {
						loginpanel.setVisible(false);
						optionpanel.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(null, "Password mismatch");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Username mismatch");
				}
			}
		});
		
		loginButton.setBounds(300, 300, 70, 30);
		loginpanel.add(loginButton);
		loginpanel.setVisible(true);
		loginpanel.setLayout(null);
		bosspanel.add(loginpanel);
		
		JButton GenerateReportButton=new JButton("Generate report");
		GenerateReportButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				optionpanel.setVisible(false);
				//Baki tuk pore lekha hobe
			}
		});

		GenerateReportButton.setBounds(180, 130, 200, 30);
		optionpanel.add(GenerateReportButton);
		
		JButton transactionButton=new JButton("TransactionButton");
		transactionButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				optionpanel.setVisible(false);
				foodlistpanel.setVisible(true);
			}
		});
		
		transactionButton.setBounds(180, 200, 200, 30);
		optionpanel.add(transactionButton);
		loginpanel.setVisible(true);
		optionpanel.setLayout(null);
		bosspanel.add(optionpanel);

	}

}
