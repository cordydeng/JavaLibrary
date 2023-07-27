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
		
		HomePageTitle=new JLabel("歡迎來到 Cordy's Library");
		HomePageTitle.setBounds(170, 20, 200, 20);
		contentPane.add(HomePageTitle);
		
		AccountNumberLabel=new JLabel("帳號");
		AccountNumberLabel.setBounds(150, 100, 25, 20);
		contentPane.add(AccountNumberLabel);
		
		AccountNumberField=new JTextField();
		AccountNumberField.setBounds(200, 100, 100, 20);
		AccountNumberField.setColumns(20);
		contentPane.add(AccountNumberField);
		
		PasswordLabel=new JLabel("密碼");
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
		
		showPasswordLabel=new JLabel("顯示密碼");
		showPasswordLabel.setBounds(330, 140, 50, 20);
		contentPane.add(showPasswordLabel);
		
		LogIn=new JButton("登入");
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
							cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
							stmt=cn.createStatement();
							String sql="SELECT * FROM account WHERE AccountNumber = '"+ AccountNumber+"'";
							//System.out.println(sql);
							rs=stmt.executeQuery(sql);
							if(rs.next()) {
								if(rs.getString(3).equals(Password)){
									JOptionPane.showMessageDialog(null,"登入成功","Cordy's Library",1);
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
									JOptionPane.showMessageDialog(null,"密碼錯誤","Cordy's Library",2);
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"帳號不存在","Cordy's Library",2);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"請輸入密碼","登入失敗",2);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"請輸入帳號","登入失敗",2);
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		contentPane.add(LogIn);
		
		Regist=new JButton("註冊");
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
		
		Exit=new JButton("退出");
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
