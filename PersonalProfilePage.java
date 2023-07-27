import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class PersonalProfilePage extends JFrame{
	
	private String UserName;
	private String AccountNumber;
	private String Password;
	//private String Identity;
	
	private JPanel contentPane;
	private JLabel UserNameLabel;
	private JTextField UserNameTextField;
	private JLabel AccountNumberLabel;
	private JTextField AccountNumberTextField;
	private JLabel PasswordLabel;
	private JPasswordField PasswordTextField;
	private JCheckBox showPassword;
	private JLabel SecondPasswordLabel;
	private JPasswordField SecondPasswordTextField;
	private JCheckBox showSecondPassword;
	private JButton Revise;
	private JButton Return;
	
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
	public PersonalProfilePage() {
		
		contentPane=new JPanel();
		setTitle("Cordy's Library �ӤH��T����");
		setBounds(100,100,400,350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setLayout(null);
		
		Return=new JButton("�^����");
		Return.setBounds(10,10,100,20);
		Return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage();
				dispose();
			}
		});
		contentPane.add(Return);
		
		UserNameLabel=new JLabel("�Τ�W");
		UserNameLabel.setBounds(50,75,50,20);
		contentPane.add(UserNameLabel);
		
		UserNameTextField=new JTextField();
		UserNameTextField.setBounds(120,75, 100, 20);
		UserNameTextField.setColumns(20);
		UserNameTextField.setText(HomePage.username);
		contentPane.add(UserNameTextField);
		
		AccountNumberLabel=new JLabel(" �b��");
		AccountNumberLabel.setBounds(50,125,50,20);
		contentPane.add(AccountNumberLabel);
		
		AccountNumberTextField=new JTextField();
		AccountNumberTextField.setBounds(120,125,100,20);
		AccountNumberTextField.setColumns(20);
		AccountNumberTextField.setText(HomePage.accountnumber);
		contentPane.add(AccountNumberTextField);
		
		PasswordLabel=new JLabel(" �K�X");
		PasswordLabel.setBounds(50,175,50,20);
		contentPane.add(PasswordLabel);
		
		PasswordTextField=new JPasswordField();
		PasswordTextField.setBounds(120,175,100,20);
		PasswordTextField.setColumns(20);
		PasswordTextField.setEchoChar('*');
		PasswordTextField.setText(HomePage.password);
		contentPane.add(PasswordTextField);
		
		showPassword=new JCheckBox("��ܱK�X");
		showPassword.setBounds(250,175,75,20);
		showPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showPassword.isSelected()) {
					PasswordTextField.setEchoChar((char)0);
				}
				else {
					PasswordTextField.setEchoChar('*');
				}
			}
		});
		contentPane.add(showPassword);
		
		SecondPasswordLabel=new JLabel("�T�{�K�X");
		SecondPasswordLabel.setBounds(50,225,50,20);
		contentPane.add(SecondPasswordLabel);
		
		SecondPasswordTextField=new JPasswordField();
		SecondPasswordTextField.setBounds(120,225,100,20);
		SecondPasswordTextField.setEchoChar('*');
		SecondPasswordTextField.setColumns(20);
		SecondPasswordTextField.setText(HomePage.password);
		contentPane.add(SecondPasswordTextField);
		
		showSecondPassword=new JCheckBox("��ܱK�X");
		showSecondPassword.setBounds(250,225,75,20);
		showSecondPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showSecondPassword.isSelected()) {
					SecondPasswordTextField.setEchoChar((char)0);
				}
				else {
					SecondPasswordTextField.setEchoChar('*');
				}
			}
		});
		contentPane.add(showSecondPassword);
		
		Revise=new JButton("��s�ӤH��T");
		Revise.setBounds(100,275,125,20);
		Revise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!(UserNameTextField.getText().equals(""))) {
					if(!(AccountNumberTextField.getText().equals(""))) {
						if(!(String.valueOf(PasswordTextField.getPassword())).equals("")) {
							if(!(String.valueOf(SecondPasswordTextField.getPassword())).equals("")) {
								Password=String.valueOf(PasswordTextField.getPassword());
								if((Password.equals(String.valueOf(SecondPasswordTextField.getPassword())))){
										UserName=UserNameTextField.getText();
										AccountNumber=AccountNumberTextField.getText();
										Update(UserName,AccountNumber,Password);
										JOptionPane.showMessageDialog(null,"�ӤH��ƭק粒��\n�Э��s�n�J","Cordy's Library �ӤH��T����",2);
										new HomePage();
										dispose();
									}
								else {
									JOptionPane.showMessageDialog(null,"�K�X���@�P","Cordy's Library �ӤH��T����",2);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"�T�{�K�X���ର��","Cordy's Library �ӤH��T����",2);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"�K�X��줣�ର��","Cordy's Library �ӤH��T����",2);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"�b����줣�ର��","Cordy's Library �ӤH��T����",2);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"�ϥΪ̦W�٤��ର��","Cordy's Library �ӤH��T����",2);
				}
			}
		});
		contentPane.add(Revise);
		
		setVisible(true);
	}
	
	//UPDATE account SET UserName = '22' , AccountNumber='22' , Password='22' , Identity='F' WHERE AccountNumber='2'
	void Update(String username,String accountNumber,String password){
		try {
			cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
			st=cn.createStatement();
			st.execute("UPDATE account SET UserName='"+UserName+"' , AccountNumber='"+AccountNumber+"' , Password='"+Password+"' WHERE AccountNumber='"+HomePage.accountnumber+"'");
			st.execute("UPDATE record SET Borrower='"+UserName+"' WHERE Borrower= '"+HomePage.username+"'");
			st.close();
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
