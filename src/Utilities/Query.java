package Utilities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import DAO.Rank;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Query {
	PreparedStatement stmt;
	ResultSet rs;
	JDBCConnection jconn;
	
	
	public Query(){
		DBConfig dbconfig = new DBConfig();
    	this.jconn = new JDBCConnection();
    	//System.out.println(dbconfig.getUrl()+","+dbconfig.getUsername()+","+dbconfig.getUsername());
    	jconn.getConnection(dbconfig.getUrl(),dbconfig.getUsername(),dbconfig.getPassword());
    	
	}	
	
    public ArrayList<String> getAppList(String query){
    	ArrayList<String> applist = new ArrayList<String>();
    	try{
            this.stmt = (PreparedStatement) this.jconn.getConn().prepareStatement(query);
            this.rs = this.stmt.executeQuery(query); 
            while(this.rs.next()){
            	applist.add(this.rs.getString(1));
            }
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
	
    	return applist;
    }
    
    public ArrayList<Rank> getOneAppRank(String query){
    	ArrayList<Rank> ranklist = new ArrayList<Rank>();
    	try{
            this.stmt = (PreparedStatement) this.jconn.getConn().prepareStatement(query);
            this.rs = this.stmt.executeQuery(query);
            
            while(this.rs.next()){
            	Rank r = new Rank();
            	r.date = this.rs.getDate(1);
            	r.rank = this.rs.getInt(2);
            	ranklist.add(r);
            }
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}	
    	return ranklist;
    }
    
    public ArrayList<Rank> getPreNextRank(String query){
        ArrayList<Rank> ranklist = new ArrayList<Rank>();
    	try{
            this.stmt = (PreparedStatement)  this.jconn.getConn().prepareStatement(query);
            this.rs = this.stmt.executeQuery(query);
            while(this.rs.next()){
            	Rank r = new Rank();
            	r.date = this.rs.getDate(1);
            	r.rank = this.rs.getInt(2);
            	r.peak = this.rs.getInt(3);
            	r.category = this.rs.getString(4);
            	r.type = this.rs.getString(5);
            	r.free = this.rs.getInt(6);
            	ranklist.add(r);
            }
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	System.out.println(ranklist.size());

    	return ranklist;
    }
    
    public void setMissingValue(String query){
    	try{
            this.stmt = (PreparedStatement) this.jconn.getConn().prepareStatement(query);
            this.stmt.executeUpdate(query);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void closeRsStmt(){
        try {
            if(this.rs != null) {
                this.rs.close();
            }
            if(this.stmt != null) {
                this.stmt.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void closeConn(){
        try {
        	this.jconn.stopConnection();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
