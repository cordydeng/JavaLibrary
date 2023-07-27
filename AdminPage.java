import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminPage extends JFrame{
	
	private String columnName[]= {"�ѦW","ISBN�X","�@��","�X����","�ɾ\���A"};
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
		setTitle("Cordy's Library �޲z������");
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		BookNameLabel=new JLabel("�ѦW");
		BookNameLabel.setBounds(50,75,50,20);
		contentPane.add(BookNameLabel);
		
		BookNameTextField=new JTextField();
		BookNameTextField.setBounds(110,75,125,20);
		BookNameTextField.setColumns(30);
		contentPane.add(BookNameTextField);
		
		ISBNLabel=new JLabel("ISBN�X");
		ISBNLabel.setBounds(50,150,50,20);
		contentPane.add(ISBNLabel);
		
		ISBNTextField=new JTextField();
		ISBNTextField.setBounds(110,150,125,20);
		ISBNTextField.setColumns(100);
		contentPane.add(ISBNTextField);
		
		AuthorLabel=new JLabel("�@��");
		AuthorLabel.setBounds(50,225,50,20);
		contentPane.add(AuthorLabel);
		
		AuthorTextField=new JTextField();
		AuthorTextField.setBounds(110,225,125,20);
		AuthorTextField.setColumns(30);
		contentPane.add(AuthorTextField);
		
		PublisherLabel=new JLabel("�X����");
		PublisherLabel.setBounds(50,300,50,20);
		contentPane.add(PublisherLabel);
		
		PublisherTextField=new JTextField();
		PublisherTextField.setBounds(110,300,125,20);
		PublisherTextField.setColumns(30);
		contentPane.add(PublisherTextField);
		
		AddBook=new JButton("�s�W���y");
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
									cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
									st=cn.createStatement();
									ISBN=ISBNTextField.getText();
									sql="SELECT * FROM booklist WHERE ISBN = '"+ ISBN+"'";
									rs=st.executeQuery(sql);
									if(rs.next()) {
										JOptionPane.showMessageDialog(null,"ISBN�X����","�s�W���y����",2);
									}
									else {
										BookName=BookNameTextField.getText();
										Author=AuthorTextField.getText();
										Publisher=PublisherTextField.getText();
										sql="INSERT INTO booklist (BookName, ISBN, Author, Publisher, Status) VALUES ('"+BookName+"' , "+"'"+ISBN+"' , "+"'"+Author+"' , "+"' "+Publisher+"', '1 ' )";
										st.execute(sql);
										JOptionPane.showMessageDialog(null,"�s�W����","�s�W���y���\",1);
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
								JOptionPane.showMessageDialog(null,"�X������줣��d��","�s�W���y����",2);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"�@����줣��d��","�s�W���y����",2);
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"ISBN�X��줣��ť�","�s�W���y����",2);
					}
				}
				else {
					JOptionPane.showMessageDialog(null,"�ѦW��줣��ť�","�s�W���y����",2);
				}
			}
		});
		contentPane.add(AddBook);
		
		DeleteBook=new JButton("�R�����y");
		DeleteBook.setBounds(130,350,90,20);
		DeleteBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(BookNameTextField.getText().equals("")|| ISBNTextField.getText().equals("")|| AuthorTextField.getText().equals("")|| PublisherTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"��줣��d��","�R�����y����",2);
				}
				else {
					try {
						cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
						st=cn.createStatement();
						ISBN=ISBNTextField.getText();
						sql="SELECT * FROM booklist WHERE ISBN = '"+ ISBN+"'";
						rs=st.executeQuery(sql);
						if(rs.next()) {
							sql="DELETE FROM booklist WHERE ISBN = '"+ ISBN+"'";
							st.execute(sql);
							JOptionPane.showMessageDialog(null,"�R������","�R�����y���\",1);
							Reset();
						}
						else {
							JOptionPane.showMessageDialog(null,"�S�����R�������y","�R�����y����",2);
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
		
		ReviseBook=new JButton("�ק���y");
		ReviseBook.setBounds(230,350,90,20);
		ReviseBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(BookNameTextField.getText().equals("")|| ISBNTextField.getText().equals("")|| AuthorTextField.getText().equals("")|| PublisherTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"��줣��d��","�ק���y����",2);
				}
				else {
					try {
						cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
						st=cn.createStatement();
						sql="SELECT * FROM booklist WHERE ISBN = '"+ ISBN+"'";
						rs=st.executeQuery(sql);
						if(rs.next()) {
							String isbn=ISBNTextField.getText();
							if(!(isbn.equals(ISBN))) {
								JOptionPane.showMessageDialog(null,"ISBN�X����ק�","�ק���y����",2);
							}
							else {
								BookName=BookNameTextField.getText();
								Author=AuthorTextField.getText();
								Publisher=PublisherTextField.getText();
								sql="UPDATE booklist SET BookName = '"+BookName+"', Author = '"+Author+"', Publisher = '"+Publisher+"' WHERE ISBN = '"+ISBN+"'";
								st.execute(sql);
								JOptionPane.showMessageDialog(null,"�ק粒��","�ק���y���\",1);
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"�S�����ק諸���y","�ק���y����",2);
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
		
		LogOut=new JButton("�n�X");
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
			        if((BookList.getValueAt(BookList.getSelectedRow(),4)).toString().equals("�~�ɤ�")) {
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
		/*try {
			cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
			st=cn.createStatement();
			rs=st.executeQuery("SELECT * FROM booklist");
			while(rs.next()) {
				String book[]=new String[5];
				for(int i=1;i<=columnName.length;i++) {
					if(rs.getString(i).equals("1")) {
						book[i-1]="�i�ɾ\";
					}
					else if(rs.getString(i).equals("0")) {
						book[i-1]="�~�ɤ�";
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
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		setVisible(true);	
	}
	
	void Reset() {
		TableModel.setRowCount(0);
		try {
			cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
			st=cn.createStatement();
			rs=st.executeQuery("SELECT * FROM booklist");
			while(rs.next()) {
				String book[]=new String[5];
				for(int i=1;i<=columnName.length;i++) {
					if(rs.getString(i).equals("1")) {
						book[i-1]="�i�ɾ\";
					}
					else if(rs.getString(i).equals("0")) {
						book[i-1]="�~�ɤ�";
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
