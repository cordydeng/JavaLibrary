import java.sql.*;

public class AccountDB {
	public static void main(String args[]) {
		try
        {
        	Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ce)
        {
     	   System.out.println("JDBC沒有驅動程式" + ce.getMessage());
     	   return ;
        }
        try
        {
     	   Connection cn=DriverManager.getConnection ("輸入你的資料庫路徑及帳號密碼");
     	   System.out.println("資料庫連接成功");
           cn.close();
           System.out.println("釋放與資料庫的連線");
        }
        catch(SQLException e)
        {
     	   System.out.println("資料庫連接失敗\n" + e.getMessage());
        }
	}
}
