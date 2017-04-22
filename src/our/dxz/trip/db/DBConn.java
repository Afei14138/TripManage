package our.dxz.trip.db; //将该类保存到com.tools包中

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import our.dxz.trip.config.DB;
import our.dxz.trip.utils.Mog;

public class DBConn 
{
	
	private static final String TAG = DBConn.class.getSimpleName() + " ";
	
	protected PreparedStatement pstm;// 预编译sql
	public Connection conn; // 声明Connection对象的实例
	public Statement stmt; // 声明Statement对象的实例
	public ResultSet rs; // 声明ResultSet对象的实例
	
	private static String dbClassName;// 定义保存数据库驱动的变量
	private static String dbUrl;
	private static String dbUser;
	private static String dbPwd;

	public DBConn() 
	{
		try 
		{
			
			dbClassName = DB.dbClassName; // 获取数据库驱动
			dbUrl = DB.dbUrl; // 获取URL
			dbUser = DB.dbUser; // 获取登录用户
			dbPwd = DB.dbPwd; // 获取密码
			
		} catch (Exception e) 
		{
			e.printStackTrace(); // 输出异常信息
		}
	}

	public static Connection getConnection() 
	{
		Connection conn = null;
		try 
		{
			Class.forName(dbClassName).newInstance();
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPwd);
		} catch (Exception ee) 
		{
			ee.printStackTrace();
		}
		if (conn == null) 
		{
			System.err
					.println("警告: DBConnectionManager.getConnection() 获得数据库链接失败.\r\n\r\n链接类型:"
							+ dbClassName
							+ "\r\n链接位置:"
							+ dbUrl
							+ "\r\n用户/密码"
							+ dbUser + "/" + dbPwd);
		}
		return conn;
	}
	
	protected void closeAll() 
	{
		Mog.d(TAG, "closeAll()");
		
		if (rs != null) 
		{
			try 
			{
				rs.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		if (pstm != null) 
		{
			try 
			{
				pstm.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		if (stmt != null) 
		{
			try 
			{
				stmt.close();
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		try 
		{
			if (conn != null && conn.isClosed() == false) 
			{
				conn.close();
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public int executeSQL(String sql, Object[] param) throws Exception 
	{
		Mog.d(TAG, "executeSQL "+"sql:"+sql);
		
		int rows = 0;  
		try 
		{
    		conn=getConnection();
			if (param != null && param.length > 0) 
			{
				pstm =  conn.prepareStatement(sql);
				for (int i = 0; i < param.length; i++) 
				{
					pstm.setString(i + 1, param[i].toString());
				}
				
				Mog.d(TAG, "executeSQL() param != null "+"pstm:"+pstm.toString());
				
				rows = pstm.executeUpdate();
				
			} else
			{	
				stmt = conn.createStatement();
				Mog.d(TAG, "executeSQL() param = null "+"stmt:"+stmt.toString());
				rows = stmt.executeUpdate(sql);
				
			}
		} finally 
		{
			this.closeAll(); 
		}
	
		return rows; 
	}
	
	
	protected ResultSet executeQuery(String sql, Object[] param) throws Exception 
	{
		Mog.d(TAG, "executeQuery "+"sql:"+sql);
		
		try
		{
			conn=getConnection();
			if (param != null && param.length > 0) 
			{
				pstm =  conn.prepareStatement(sql);
				for (int i = 0; i < param.length; i++) 
				{
					pstm.setString(i + 1, param[i].toString());
				}
				rs = pstm.executeQuery();
				
				Mog.d(TAG, "executeQuery() param != null "+"pstm:"+pstm.toString());
				
			} else 
			{
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				Mog.d(TAG, "executeQuery() param = null "+"stmt:"+stmt.toString());
			}
		} finally 
		{
			this.closeAll();
		}
		
		return rs; 
	}

}
