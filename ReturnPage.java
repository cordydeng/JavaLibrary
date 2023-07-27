import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ReturnPage extends JFrame{
	
	private String columnName[]= {"�ѦW","�ɾ\���","�ٮѤ��","�ɾ\���A"};
	private String BorrowDate;
	private String BookName;
	
	private JPanel contentPane;
	private JButton ReturnBook;
	private JButton Return;
	
	private JTable BookList;
	private DefaultTableModel TableModel;
	private JScrollPane TableScrollPane;
	
	private Connection cn;
	private Statement st;
	private ResultSet rs;
	
	public ReturnPage() {
		
		contentPane=new JPanel();
		setTitle("Cordy's Library �ٮѤ���");
		setBounds(100,100,500,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setLayout(null);
		
		BookList=new JTable();
		BookList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		BookList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if((BookList.getValueAt(BookList.getSelectedRow(),3).toString().equals("�w�k��"))) {
					ReturnBook.setEnabled(false);
				}
				else {
					ReturnBook.setEnabled(true);
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
		TableScrollPane.setBounds(40,40,410,300);
		TableScrollPane.setViewportView(BookList);
		contentPane.add(TableScrollPane);
		
		ReturnBook=new JButton("�ٮ�");
		ReturnBook.setBounds(185,375,100,20);
		ReturnBook.setEnabled(false);
		ReturnBook.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//�p�G�S���ѥi�H��??
				BorrowDate=(BookList.getValueAt(BookList.getSelectedRow(),1).toString());
				BookName=(BookList.getValueAt(BookList.getSelectedRow(),0).toString());
				Return(BorrowDate,BookName);
				Refresh();
				JOptionPane.showMessageDialog(null,"�ٮѦ��\","Cordy's Library �ٮѤ���",1);
			}
		});
		contentPane.add(ReturnBook);
		
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
		
		Refresh();
		
		setVisible(true);
		
	}
	
	void Refresh() {
		try {
			cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
			st=cn.createStatement();
			rs=st.executeQuery("SELECT * FROM record WHERE Borrower = '"+HomePage.username+"'");
			TableModel.setRowCount(0);
			while(rs.next()) {
				String book[]=new String[4];
				book[0]=rs.getString(1).toString();
				book[1]=rs.getString(3).toString();
				if(rs.getString(4)==null) {
					book[2]="";
					book[3]="�|���k��";
				}
				else {
					book[2]=rs.getString(4).toString();
					book[3]="�w�k��";
				}
				TableModel.addRow(book);
			}
			rs.close();
			st.close();
			cn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void Return(String borrowDate,String bookname) {
		try {
			cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
			st=cn.createStatement();
			st.execute("UPDATE record SET ReturnDate = now() WHERE BorrowDate = '"+borrowDate+"'");
			st.execute("UPDATE booklist SET Status = 1 WHERE BookName = '"+bookname+"'");
			rs.close();
			st.close();
			cn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
