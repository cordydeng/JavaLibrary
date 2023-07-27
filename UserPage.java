import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserPage extends JFrame{
	
	private JPanel contentPane;
	private JButton BorrowBook;
	private JButton ReturnBook;
	private JButton PersonalProfile;
	private JButton LogOut;
	
	
	public UserPage() {
		
		contentPane=new JPanel();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,550,100);
		setTitle("Cordy's Library 使用者介面");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		BorrowBook=new JButton("借書");
		BorrowBook.setBounds(10,20,100,20);
		BorrowBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BorrowBookPage bbp=new BorrowBookPage();
				dispose();
			}
		});
		contentPane.add(BorrowBook);
		
		ReturnBook=new JButton("還書及借閱紀錄");
		ReturnBook.setBounds(130,20,150,20);
		ReturnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new ReturnPage();
				dispose();
			}
		});
		contentPane.add(ReturnBook);
		
		PersonalProfile=new JButton("個人資料");
		PersonalProfile.setBounds(300,20,100,20);
		PersonalProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PersonalProfilePage();
				dispose();
			}
		});
		contentPane.add(PersonalProfile);
		
		LogOut=new JButton("登出");
		LogOut.setBounds(420,20,100,20);
		LogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HomePage();
				dispose();
			}
		});
		contentPane.add(LogOut);
		
		setVisible(true);
	}
}
