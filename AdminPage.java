import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame{
	
	private String columnName[]= {"書名","ISBN碼","作者","出版社","借閱狀態"};
	private String BookName;
	private String ISBN;
	private String Author;
	private String Publisher;
	private String sql;
	
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
	private JPanel contentPane;
	private JLabel BookNameLabel;
	private JTextField BookNameTextField;
	private JLabel ISBNLabel;
	private JTextField ISBNTextField;
	private JLabel AuthorLabel;
	private JTextField AuthorTextField;
	private JLabel PublisherLabel;
	private JTextField PublisherTextField;
	private JButton AddBook;
	private JButton DeleteBook;
	private JButton ReviseBook;
	private JButton LogOut;
	
	private JTable BookList;
	private DefaultTableModel TableModel;
	private JScrollPane TableScrollPane;
	
	public AdminPage() {
		contentPane = new JPanel();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,600);
		setTitle("Cordy's Library 管理員介面");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		BookNameLabel=new JLabel("書名");
		BookNameLabel.setBounds(50,75,50,20);
		contentPane.add(BookNameLabel);
		
		BookNameTextField=new JTextField();
		BookNameTextField.setBounds(110,75,125,20);
		BookNameTextField.setColumns(30);
		contentPane.add(BookNameTextField);
		
		ISBNLabel=new JLabel("ISBN碼");
		ISBNLabel.setBounds(50,150,50,20);
		contentPane.add(ISBNLabel);
		
		ISBNTextField=new JTextField();
		ISBNTextField.setBounds(110,150,125,20);
		ISBNTextField.setColumns(100);
		contentPane.add(ISBNTextField);
		
		AuthorLabel=new JLabel("作者");
		AuthorLabel.setBounds(50,225,50,20);
		contentPane.add(AuthorLabel);
		
		AuthorTextField=new JTextField();
		AuthorTextField.setBounds(110,225,125,20);
		AuthorTextField.setColumns(30);
		contentPane.add(AuthorTextField);
		
		PublisherLabel=new JLabel("出版社");
		PublisherLabel.setBounds(50,300,50,20);
		contentPane.add(PublisherLabel);
		
		PublisherTextField=new JTextField();
		PublisherTextField.setBounds(110,300,125,20);
		PublisherTextField.setColumns(30);
		contentPane.add(PublisherTextField);
		
		AddBook=new JButton("新增書籍");
		AddBook.setBounds(30,350,90,20);
		AddBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!(BookNameTextField.getText().equals(""))) {
					if(!(ISBNTextField.getText().equals(""))) {
						if(!(AuthorTextField.getText().equals(""))) {
							if(!(PublisherTextField.getText().equals(""))) {
								try {
									cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
									st=cn.createStatement();
									ISBN=ISBNTextField.getText();
									sql="SELECT * FROM booklist WHERE ISBN = '"+ ISBN+"'";
									rs=st.executeQuery(sql);
									if(rs.next()) {
										JOptionPane.showMessageDialog(null,"ISBN碼重複","新增書籍失敗",2);
									}
									else {
										BookName=BookNameTextField.getText();
										Author=AuthorTextField.getText();
										Publisher=PublisherTextField.getText();
										sql="INSERT INTO booklist (BookName, ISBN, Author, Publisher, Status) VALUES ('"+BookName+"' , "+"'"+ISBN+"' , "+"'"+Author+"' , "+"' "+Publisher+"', '1 ' )";
										st.execute(sql);
										JOptionPane.showMessageDialog(null,"新增完成","新增書籍成功",1);
										Reset();
									}
									rs.close();
									st.close();
									cn.close();
									ISBNTextField.setText("");
									BookNameTextField.setText("");
									AuthorTextField.setText("");
									PublisherTextField.setText("");
								}
								catch(SQLException e1) {
									e1.printStackTrace();
								}
							}
							else {
								JOptionPane.showMessageDialog(null,"出版社欄位不能留空","新增書籍失敗",2);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"作者欄位不能留空","新增書籍失敗",2);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"ISBN碼欄位不能空白","新增書籍失敗",2);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"書名欄位不能空白","新增書籍失敗",2);
				}
			}
		});
		contentPane.add(AddBook);
		
		DeleteBook=new JButton("刪除書籍");
		DeleteBook.setBounds(130,350,90,20);
		DeleteBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(BookNameTextField.getText().equals("")|| ISBNTextField.getText().equals("")|| AuthorTextField.getText().equals("")|| PublisherTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"欄位不能留空","刪除書籍失敗",2);
				}
				else {
					try {
						cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
						st=cn.createStatement();
						ISBN=ISBNTextField.getText();
						sql="SELECT * FROM booklist WHERE ISBN = '"+ ISBN+"'";
						rs=st.executeQuery(sql);
						if(rs.next()) {
							sql="DELETE FROM booklist WHERE ISBN = '"+ ISBN+"'";
							st.execute(sql);
							JOptionPane.showMessageDialog(null,"刪除完成","刪除書籍成功",1);
							Reset();
						}
						else {
							JOptionPane.showMessageDialog(null,"沒有欲刪除的書籍","刪除書籍失敗",2);
						}
						st.close();
						cn.close();
						ISBNTextField.setText("");
						BookNameTextField.setText("");
						AuthorTextField.setText("");
						PublisherTextField.setText("");
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(DeleteBook);
		
		ReviseBook=new JButton("修改書籍");
		ReviseBook.setBounds(230,350,90,20);
		ReviseBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(BookNameTextField.getText().equals("")|| ISBNTextField.getText().equals("")|| AuthorTextField.getText().equals("")|| PublisherTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"欄位不能留空","修改書籍失敗",2);
				}
				else {
					try {
						cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
						st=cn.createStatement();
						sql="SELECT * FROM booklist WHERE ISBN = '"+ ISBN+"'";
						rs=st.executeQuery(sql);
						if(rs.next()) {
							String isbn=ISBNTextField.getText();
							if(!(isbn.equals(ISBN))) {
								JOptionPane.showMessageDialog(null,"ISBN碼不能修改","修改書籍失敗",2);
							}
							else {
								BookName=BookNameTextField.getText();
								Author=AuthorTextField.getText();
								Publisher=PublisherTextField.getText();
								sql="UPDATE booklist SET BookName = '"+BookName+"', Author = '"+Author+"', Publisher = '"+Publisher+"' WHERE ISBN = '"+ISBN+"'";
								st.execute(sql);
								JOptionPane.showMessageDialog(null,"修改完成","修改書籍成功",1);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"沒有欲修改的書籍","修改書籍失敗",2);
						}
						st.close();
						cn.close();
						ISBNTextField.setText("");
						BookNameTextField.setText("");
						AuthorTextField.setText("");
						PublisherTextField.setText("");
						Reset();
					}
					catch(SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		contentPane.add(ReviseBook);
		
		LogOut=new JButton("登出");
		LogOut.setBounds(10,540,75,20);
		LogOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new HomePage();
				dispose();
			}
		});
		contentPane.add(LogOut);
		
		BookList=new JTable();
		BookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		BookList.addMouseListener(new MouseAdapter() {
			 @Override
			    public void mouseClicked(MouseEvent event) {
			        BookNameTextField.setText((BookList.getValueAt(BookList.getSelectedRow(),0)).toString());
			        ISBNTextField.setText((BookList.getValueAt(BookList.getSelectedRow(),1)).toString());
			        AuthorTextField.setText((BookList.getValueAt(BookList.getSelectedRow(),2)).toString());
			        PublisherTextField.setText((BookList.getValueAt(BookList.getSelectedRow(),3)).toString());
			        if((BookList.getValueAt(BookList.getSelectedRow(),4)).toString().equals("外借中")) {
			        	DeleteBook.setEnabled(false);
			        	ReviseBook.setEnabled(false);
			        }
			        else {
			        	DeleteBook.setEnabled(true);
			        	ReviseBook.setEnabled(true);
			        }
			        ISBN=ISBNTextField.getText();
			    }
		});
		
		TableModel=new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
                 return false; 
              }
		};
		TableModel.setColumnIdentifiers(columnName);
		BookList.setModel(TableModel);
		
		TableScrollPane=new JScrollPane(BookList);
		TableScrollPane.setBounds(350,10,400,500);
		TableScrollPane.setViewportView(BookList);
		contentPane.add(TableScrollPane);
		
		Reset();
		
		setVisible(true);	
	}
	
	void Reset() {
		TableModel.setRowCount(0);
		try {
			cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
			st=cn.createStatement();
			rs=st.executeQuery("SELECT * FROM booklist");
			while(rs.next()) {
				String book[]=new String[5];
				for(int i=1;i<=columnName.length;i++) {
					if(rs.getString(i).equals("1")) {
						book[i-1]="可借閱";
					}
					else if(rs.getString(i).equals("0")) {
						book[i-1]="外借中";
					}
					else {
						book[i-1]=rs.getString(i);
					}
				}
				TableModel.addRow(book);
			}
			rs.close();
			st.close();
			cn.close();
			ISBNTextField.setText("");
			BookNameTextField.setText("");
			AuthorTextField.setText("");
			PublisherTextField.setText("");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
