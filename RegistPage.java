import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class RegistPage extends JFrame{
	
	private String UserName;
	private String AccountNumber;
	private String password;
	private String Identity;
	
	private JRadioButton AdminRadioButton;
	private JRadioButton FacultyRadioButton;
	private JRadioButton StudentRadioButton;
	private JButton homePage;
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
	private JLabel AdminCodeLabel;
	private JPasswordField AdminCodeField;
	private JCheckBox showAdminCode;
	private JCheckBox checkBox;
	private JButton Regist;
	
	
	private Connection cn;
	private Statement stmt;
	
	public RegistPage() {
		contentPane = new JPanel();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,500,400);
		setTitle("Cordy's Library 註冊畫面");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		homePage=new JButton("回首頁");
		homePage.setBounds(10,10,75,20);
		homePage.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HomePage hp = new HomePage();
				dispose();
			}
		});
		contentPane.add(homePage);
		
		AdminRadioButton =new JRadioButton("管理員");
		AdminRadioButton.setBounds(70,50,70,20);
		AdminRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(AdminRadioButton.isSelected()) {
					AdminCodeField.setEditable(true);
				}
			}
		});
		contentPane.add(AdminRadioButton);
		
		FacultyRadioButton =new JRadioButton("教職員");
		FacultyRadioButton.setBounds(150,50,70,20);
		FacultyRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(FacultyRadioButton.isSelected()) {
					AdminCodeField.setText("");
					AdminCodeField.setEditable(false);
				}
			}
		});
		contentPane.add(FacultyRadioButton);
		
		StudentRadioButton =new JRadioButton("學生");
		StudentRadioButton.setBounds(230,50,70,20);
		StudentRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(StudentRadioButton.isSelected()) {
					AdminCodeField.setText("");
					AdminCodeField.setEditable(false);
					showAdminCode.setEnabled(true);
				}
			}
		});
		contentPane.add(StudentRadioButton);
		
		ButtonGroup group = new ButtonGroup();
		group.add(AdminRadioButton);
		group.add(FacultyRadioButton);
		group.add(StudentRadioButton);
		
		
		UserNameLabel=new JLabel("用戶名");
		UserNameLabel.setBounds(100,80,50,20);
		contentPane.add(UserNameLabel);
		
		UserNameTextField=new JTextField();
		UserNameTextField.setBounds(150, 80, 100, 20);
		UserNameTextField.setColumns(20);
		contentPane.add(UserNameTextField);
		
		AccountNumberLabel=new JLabel(" 帳號");
		AccountNumberLabel.setBounds(100,130,50,20);
		contentPane.add(AccountNumberLabel);
		
		AccountNumberTextField=new JTextField();
		AccountNumberTextField.setBounds(150, 130,100,20);
		AccountNumberTextField.setColumns(20);
		contentPane.add(AccountNumberTextField);
		
		PasswordLabel=new JLabel(" 密碼");
		PasswordLabel.setBounds(100,180,50,20);
		contentPane.add(PasswordLabel);
		
		PasswordTextField=new JPasswordField();
		PasswordTextField.setBounds(150,180,100,20);
		PasswordTextField.setColumns(20);
		PasswordTextField.setEchoChar('*');
		contentPane.add(PasswordTextField);
		
		showPassword=new JCheckBox("顯示密碼");
		showPassword.setBounds(250,180,75,20);
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
		
		SecondPasswordLabel=new JLabel("確認密碼");
		SecondPasswordLabel.setBounds(90,220, 50,20);
		contentPane.add(SecondPasswordLabel);
		
		SecondPasswordTextField=new JPasswordField();
		SecondPasswordTextField.setBounds(150,220,100,20);
		SecondPasswordTextField.setEchoChar('*');
		SecondPasswordTextField.setColumns(20);
		contentPane.add(SecondPasswordTextField);
		
		showSecondPassword=new JCheckBox("顯示密碼");
		showSecondPassword.setBounds(250,220,75,20);
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
		
		AdminCodeLabel=new JLabel("管理員金鑰");
		AdminCodeLabel.setBounds(100,250,75,20);
		contentPane.add(AdminCodeLabel);
		
		AdminCodeField=new JPasswordField();
		AdminCodeField.setBounds(180,250,50,20);
		AdminCodeField.setColumns(4);
		AdminCodeField.setEchoChar('*');
		AdminCodeField.setEditable(false);
		contentPane.add(AdminCodeField);
		
		showAdminCode=new JCheckBox("顯示金鑰");
		showAdminCode.setBounds(235,250,75,20);
		showAdminCode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showAdminCode.isSelected()) {
					AdminCodeField.setEchoChar((char)0);
				}
			}
		});
		contentPane.add(showAdminCode);
		
		checkBox=new JCheckBox("已詳閱帳戶創建之規則");
		checkBox.setBounds(100,280,150,20);
		contentPane.add(checkBox);
		
		Regist=new JButton("註冊");
		Regist.setBounds(160,310,75,20);
		Regist.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if((AdminRadioButton.isSelected())||(FacultyRadioButton.isSelected())||(StudentRadioButton.isSelected())) {
					if(!(UserNameTextField.getText().equals(""))) {
						if(!(AccountNumberTextField.getText().equals(""))) {
							String Password=String.valueOf(PasswordTextField.getPassword());
							if(!(Password.equals(""))){
								String SecondPassword=String.valueOf(SecondPasswordTextField.getPassword());
								if(!(SecondPassword.equals(""))) {
									if((Password.equals(SecondPassword))) {
										if(AdminRadioButton.isSelected()) {
											String AdminCode=String.valueOf(AdminCodeField.getPassword());
											if(!(AdminCode.equals(""))) {
												if(AdminCode.equals("0000")) {
													if(checkBox.isSelected()) {
														//創建帳戶
														try {
															cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
															stmt=cn.createStatement();
															UserName=UserNameTextField.getText();
															AccountNumber=AccountNumberTextField.getText();
															password=String.valueOf(PasswordTextField.getPassword());
															Identity="A";
															String sql="INSERT INTO account (UserName, AccountNumber, Password, Identity) VALUES ('"+UserName+"' , "+"'"+AccountNumber+"' , '"+"'"+password+" , "+"'"+Identity+"')";
															stmt.execute(sql);
															stmt.close();
															cn.close();
															JOptionPane.showMessageDialog(null,"註冊成功","提醒",1);
														} 
														catch (SQLException e1) {
															System.out.println("資料庫連接失敗\n" + e1.getMessage());
														}
													}
													else {
														JOptionPane.showMessageDialog(null,"請勾選我已詳閱規範","提醒",2);
													}
												}
												else {
													JOptionPane.showMessageDialog(null,"管理員金鑰錯誤","提醒",2);
													AdminCodeField.setText("");
												}
											}
											else {
												JOptionPane.showMessageDialog(null,"管理員金鑰不能留空","提醒",2);
											}
										}
										else {
											if(checkBox.isSelected()) {
												//創建帳戶
												try {
													cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
													stmt=cn.createStatement();
													UserName=UserNameTextField.getText();
													AccountNumber=AccountNumberTextField.getText();
													password=String.valueOf(PasswordTextField.getPassword());
													//Identity=FacultyRadioButton.isSelected()?FacultyRadioButton.getText():StudentRadioButton.getText();
													Identity=FacultyRadioButton.isSelected()?"F":"S";
													String sql="INSERT INTO account (UserName, AccountNumber, Password, Identity) VALUES ('"+UserName+"' , "+"'"+AccountNumber+"' , "+"'"+password+"' , "+"'"+Identity+"')";
													//System.out.print(sql);
													stmt.execute(sql);
													stmt.close();
													cn.close();
													JOptionPane.showMessageDialog(null,"註冊成功","提醒",1);
													new HomePage();
													dispose();
												} 
												catch (SQLException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												
											}
											else {
												JOptionPane.showMessageDialog(null,"請勾選我已詳閱規範","提醒",2);
											}
										}
									}
									else {
										JOptionPane.showMessageDialog(null,"密碼不一致","提醒",2);
									}
								}
								else {
									JOptionPane.showMessageDialog(null,"確認密碼不能留空","提醒",2);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"密碼不能留空","提醒",2);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"帳號不能留空","提醒",2);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"用戶名不能留空","提醒",2);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"請選身分別","提醒",2);
				}
			}
		});
		contentPane.add(Regist);
		
		setVisible(true);
	}
}