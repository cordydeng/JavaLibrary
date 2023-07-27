import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BorrowBookPage extends JFrame{
	
	private String columnName[]= {"書名","ISBN碼","作者","出版社","借閱狀態"};
	private String BookName;
	private String ISBN;
	private String Author;
	private String Publisher;
	private String sql;
	
	private JPanel contentPane;
	private JRadioButton SearchbyBookName;
	private JTextField BookNameTextField;
	private JRadioButton SearchbyISBN;
	private JTextField ISBNTextField;
	private JRadioButton SearchbyAuthor;
	private JTextField AuthorTextField;
	private JRadioButton SearchbyPublisher;
	private JTextField PublisherTextField;
	private JButton Search;
	private JButton ShowAllBook;
	private JButton BorrowBook;
	private JButton Return;
	
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
	private JTable BookList;
	private DefaultTableModel TableModel;
	private JScrollPane TableScrollPane;
	
	public BorrowBookPage() {
		
		contentPane=new JPanel();
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,600);
		setTitle("Cordy's Library 借書介面");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		SearchbyBookName=new JRadioButton("搜尋書名");
		SearchbyBookName.setBounds(50,75,75,20);
		contentPane.add(SearchbyBookName);
		
		BookNameTextField=new JTextField();
		BookNameTextField.setBounds(150,75,100,20);
		contentPane.add(BookNameTextField);
		
		SearchbyISBN=new JRadioButton("搜尋ISBN碼");
		SearchbyISBN.setBounds(50,125,100,20);
		contentPane.add(SearchbyISBN);
		
		ISBNTextField=new JTextField();
		ISBNTextField.setBounds(150,125,100,20);
		contentPane.add(ISBNTextField);
		
		SearchbyAuthor=new JRadioButton("搜尋作者");
		SearchbyAuthor.setBounds(50,175,75,20);
		contentPane.add(SearchbyAuthor);
		
		AuthorTextField=new JTextField();
		AuthorTextField.setBounds(150,175,100,20);
		contentPane.add(AuthorTextField);
		
		SearchbyPublisher=new JRadioButton("搜尋出版社");
		SearchbyPublisher.setBounds(50,225,100,20);
		contentPane.add(SearchbyPublisher);
		
		PublisherTextField=new JTextField();
		PublisherTextField.setBounds(150,225,100,20);
		contentPane.add(PublisherTextField);

		ButtonGroup group = new ButtonGroup();
		group.add(SearchbyBookName);
		group.add(SearchbyISBN);
		group.add(SearchbyAuthor);
		group.add(SearchbyPublisher);
		
		Search=new JButton("搜尋");
		Search.setBounds(110,275,75,20);
		Search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String searchItem="";
				String searchWord="";
				if(SearchbyBookName.isSelected()) {
					searchItem="BookName";
					searchWord=BookNameTextField.getText();
				}
				if(SearchbyISBN.isSelected()) {
					searchItem="ISBN";
					searchWord=ISBNTextField.getText();
				}
				if(SearchbyAuthor.isSelected()) {
					searchItem="Author";
					searchWord=AuthorTextField.getText();
				}
				if(SearchbyPublisher.isSelected()) {
					searchItem="Publisher";
					searchWord=PublisherTextField.getText();
				}
				if(searchItem.equals("")||searchWord.equals("")) {
					JOptionPane.showMessageDialog(null,"搜尋失敗","錯誤訊息",2);
				}
				else {
					Search(searchItem,searchWord);
				}
			}
		});
		contentPane.add(Search);
		
		ShowAllBook=new JButton("全部書籍");
		ShowAllBook.setBounds(520,530,100,20);
		ShowAllBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Refresh();
			}
		});
		contentPane.add(ShowAllBook);
		
		BorrowBook=new JButton("我要借書");
		BorrowBook.setBounds(95,400,100,20);
		BorrowBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Borrow();
				JOptionPane.showMessageDialog(null,"借閱成功","借閱成功通知",1);
			}
		});
		contentPane.add(BorrowBook);
		
		Return=new JButton("上一頁");
		Return.setBounds(50,500,75,20);
		Return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new UserPage();
				dispose();
			}
		});
		contentPane.add(Return);
		
		BookList=new JTable();
		BookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		BookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if((BookList.getValueAt(BookList.getSelectedRow(),4).toString().equals("外借中"))) {
					BorrowBook.setEnabled(false);
				}
				else {
					BorrowBook.setEnabled(true);
				}
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
		Refresh();
		
		setVisible(true);
	}
	
	void Search(String item,String word) {
		try {
			cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
			st=cn.createStatement();
			sql="SELECT * FROM booklist WHERE INSTR ( "+item+", '"+word+"')";
			rs=st.executeQuery(sql);
			TableModel.setRowCount(0);
			if(rs.next()) {
				do {
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
				}while(rs.next());
				rs.close();
				st.close();
				cn.close();
				BookNameTextField.setText("");
				ISBNTextField.setText("");
				AuthorTextField.setText("");
				PublisherTextField.setText("");
			}
			else {
				JOptionPane.showMessageDialog(null,"查無此書","搜尋失敗",2);
				Refresh();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void Refresh() {
		try {
			cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
			st=cn.createStatement();
			rs=st.executeQuery("SELECT * FROM booklist");
			TableModel.setRowCount(0);
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
			BookNameTextField.setText("");
			ISBNTextField.setText("");
			AuthorTextField.setText("");
			PublisherTextField.setText("");
			rs.close();
			st.close();
			cn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void Borrow() {
		try {
			cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
			st=cn.createStatement();
			sql="INSERT INTO record ( BookName, Borrower, BorrowDate ) VALUES ( '"+BookList.getValueAt(BookList.getSelectedRow(),0).toString()+"', '"+HomePage.username+"' , now() )";
			st.execute(sql);
			sql="UPDATE booklist SET Status = '0' WHERE BookName = '"+BookList.getValueAt(BookList.getSelectedRow(),0).toString()+"'";
			st.execute(sql);
			//System.out.println(sql);
			Refresh();
			rs.close();
			st.close();
			cn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}

