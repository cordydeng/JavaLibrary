import java.sql.*;

public class AccountDB {
	public static void main(String args[]) {
		try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ce)
        {
     	   System.out.println("JDBC�S���X�ʵ{��" + ce.getMessage());
     	   return ;
        }
        try
        {
     	   Connection cn=DriverManager.getConnection ("jdbc:mysql://localhost/library","javauser","advjava2022");
     	   System.out.println("��Ʈw�s�����\");
           cn.close();
           System.out.println("����P��Ʈw���s�u");
        }
        catch(SQLException e)
        {
     	   System.out.println("��Ʈw�s������\n" + e.getMessage());
        }
	}
}
