import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


class HomePage extends JFrame{
	
	private String AccountNumber;
	private String Password;
	public static String username;
	public static String accountnumber;
	public static String password;
	public static String identity;
	
	private JPanel contentPane;
	private JLabel HomePageTitle;
	private JLabel AccountNumberLabel;
	private JTextField AccountNumberField;
	private JLabel PasswordLabel;
	private JPasswordField PasswordField;
	private JCheckBox showPassword;
	private JLabel showPasswordLabel;
	private JButton LogIn;
	private JButton Regist;
	private JButton Exit;
	private Connection cn;
	private Statement stmt;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	
	public HomePage() {
		contentPane = new JPanel();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,500,400);
		setTitle("Cordy's Library");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		HomePageTitle=new JLabel("�w��Ө� Cordy's Library");
		HomePageTitle.setBounds(170, 20, 200, 20);
		contentPane.add(HomePageTitle);
		
		AccountNumberLabel=new JLabel("�b��");
		AccountNumberLabel.setBounds(150, 100, 25, 20);
		contentPane.add(AccountNumberLabel);
		
		AccountNumberField=new JTextField();
		AccountNumberField.setBounds(200, 100, 100, 20);
		AccountNumberField.setColumns(20);
		contentPane.add(AccountNumberField);
		
		PasswordLabel=new JLabel("�K�X");
		PasswordLabel.setBounds(150, 140, 25, 20);
		contentPane.add(PasswordLabel);
		
		PasswordField=new JPasswordField();
		PasswordField.setBounds(200, 140, 100, 20);
		PasswordField.setColumns(20);
		PasswordField.setEchoChar('*');
		contentPane.add(PasswordField);
		
		showPassword=new JCheckBox();
		showPassword.setBounds(310, 140, 20, 20);
		showPassword.addActionListener(new ActionListener() 
			{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(showPassword.isSelected()) {
					PasswordField.setEchoChar((char)0);
				}
				else {
					PasswordField.setEchoChar('*');
				}
			}
			
		});
		contentPane.add(showPassword);
		
		showPasswordLabel=new JLabel("��ܱK�X");
		showPasswordLabel.setBounds(330, 140, 50, 20);
		contentPane.add(showPasswordLabel);
		
		LogIn=new JButton("�n�J");
		LogIn.setBounds(175,180,60,20);
		LogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if(!(AccountNumberField.getText().equals(""))) {
						AccountNumber=AccountNumberField.getText();
						Password=String.valueOf(PasswordField.getPassword());
						if(!(Password.equals(""))) {
							cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
							stmt=cn.createStatement();
							String sql="SELECT * FROM account WHERE AccountNumber = '"+ AccountNumber+"'";
							//System.out.println(sql);
							rs=stmt.executeQuery(sql);
							if(rs.next()) {
								if(rs.getString(3).equals(Password)){
									JOptionPane.showMessageDialog(null,"�n�J���\","Cordy's Library",1);
									if(rs.getString(4).equals("A")) {
										new AdminPage();
										dispose();
									}
									else {
										username=rs.getString(1);
										accountnumber=rs.getString(2);
										password=rs.getString(3);
										identity=rs.getString(4);
										new UserPage();
										dispose();
									}
								}
								else {
									System.out.println(rs.getString(3));
									JOptionPane.showMessageDialog(null,"�K�X���~","Cordy's Library",2);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"�b�����s�b","Cordy's Library",2);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"�п�J�K�X","�n�J����",2);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"�п�J�b��","�n�J����",2);
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		contentPane.add(LogIn);
		
		Regist=new JButton("���U");
		Regist.setBounds(250, 180, 60, 20);
		Regist.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegistPage();
				dispose();
			}
		});
		contentPane.add(Regist);
		
		Exit=new JButton("�h�X");
		Exit.setBounds(10,325,75,20);
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		contentPane.add(Exit);
		
		setVisible(true);
	}
}