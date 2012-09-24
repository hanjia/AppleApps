package Utilities;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;

public class JDBCConnection {
	Connection conn;
	
	public JDBCConnection() {
	}

	public Connection getConn() {
		return this.conn;
	}


	public void getConnection(String url, String username, String password){
		try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }	        
        Connection conn = null;                  
        
        try {	     
            conn = (Connection) DriverManager.getConnection(url,username,password);	      
            //stmt = (Statement) conn.createStatement();
            //rs = stmt.executeQuery(query);            
            //Database Connection
            
            }
        
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        this.conn=conn;
	}
	
	public void stopConnection(){
		 try {
             if(this.conn != null) {
                 this.conn.close();
             }
		 }
         catch(Exception ex) {
                 ex.printStackTrace();
          }    
	}
}
