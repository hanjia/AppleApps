package LifeCycle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import DAO.App;
import DAO.Rank;
import Utilities.Query;

public class EntireCycle {
	public int appid;
	public boolean finished;
	public java.sql.Date catalogbirthdate;
	public java.sql.Date commercialbirthdate;
	public java.sql.Date peakdate;
	public java.sql.Date commercialdeathdate;
	public int peak;
	public ArrayList<Rank> ranklist = new ArrayList<Rank>();;
	public String category;
	public boolean useful;

	
	public int getAppid() {
		return appid;
	}

	public void setAppid(int appid) {
		this.appid = appid;
	}
	
	public ArrayList<Rank> getRanklist() {
		return ranklist;
	}

	public void setRanklist(ArrayList<Rank> ranklist) {
		this.ranklist = ranklist;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public java.sql.Date getCatalogbirthdate() {
		return catalogbirthdate;
	}

	public void setCatalogbirthdate(java.sql.Date catalogbirthdate) {
		this.catalogbirthdate = catalogbirthdate;
	}

	public java.sql.Date getCommercialbirthdate() {
		return commercialbirthdate;
	}

	public void setCommercialbirthdate(java.sql.Date commercialbirthdate) {
		this.commercialbirthdate = commercialbirthdate;
	}

	public java.sql.Date getPeakdate() {
		return peakdate;
	}

	public void setPeakdate(java.sql.Date peakdate) {
		this.peakdate = peakdate;
	}

	public java.sql.Date getCommercialdeathdate() {
		return commercialdeathdate;
	}

	public void setCommercialdeathdate(java.sql.Date commercialdeathdate) {
		this.commercialdeathdate = commercialdeathdate;
	}

	public int getPeak() {
		return peak;
	}

	public void setPeak(int peak) {
		this.peak = peak;
	}

	public EntireCycle(int appid, Date birthdate,boolean all){
		
		this.appid = appid;
		this.catalogbirthdate = birthdate;
		Query q = new Query();
		String query = "";
		if(all){
		this.category = "ALL";
		query = "SELECT appID, category, currentDate, rank, peak FROM appRank WHERE appID ="+ appid +" AND currentDate < '2012-05-02' AND category = 'ALL' ORDER BY currentDate ;";
		}
		else{
		query = "SELECT appID, category, currentDate, rank, peak FROM appRank WHERE appID ="+ appid +" AND currentDate < '2012-05-02' AND category <> 'ALL' ORDER BY currentDate ;";
		}
		App app = new App();
		app = q.getApp(query);
		q.closeRsStmt();
		q.closeConn();
		this.category = app.categorynotall;
		this.ranklist = app.rankalllist;
		//System.out.println(ranklist.size());
		
		try{
		Date min = ranklist.get(0).getDate();
		int last = ranklist.size() - 1;
		Date max = ranklist.get(last).getDate();
		int pk = 200;
		for(int i = 0; i < ranklist.size(); i++){
			Rank r = ranklist.get(i);
			//System.out.println(r.peak);
			if(r.peak < pk && r.peak > 0){
				pk = r.peak;
			}
		}
		this.commercialbirthdate = min;
		this.commercialdeathdate = max;
		this.peak = pk;
		
	    Calendar beforeoneweek = new GregorianCalendar(2012, 3, 18);
	    java.sql.Date threshold = new java.sql.Date(beforeoneweek.getTime().getTime());
	    
		if(this.commercialdeathdate.before(threshold)){
			this.finished = true;
		}
		else{
			this.finished = false;
		}		
		
		for(int j = 0; j < ranklist.size(); j++){
			Rank r = ranklist.get(j);
			if(r.peak == this.peak){
				this.peakdate = r.getDate();
			}
		}
		
		this.useful = true;
		}	
		catch(Exception e){
			this.useful = false;
			System.out.println("cannot find useful records:" + this.appid + " in "+ this.category);
		}
		
	}

	@Override
	public String toString() {
		return this.appid +","+this.category+"," + this.peak+ "," +this.catalogbirthdate + ","+this.commercialbirthdate+ "," +this.peakdate+","+this.commercialdeathdate+"\n";
	}	
	
	public String toStringRanklist(){
		String ranklist = String.valueOf(this.appid) ;
		String datelist = String.valueOf(this.appid) ;
		for(int i = 0; i < this.ranklist.size() ; i ++){
			ranklist += "," + this.ranklist.get(i).rank ;
			datelist += "," + this.ranklist.get(i).date;
		}
		
		return datelist + "\n" + ranklist + "\n";
	}
	
	
	
//	public static void main(String[] args) throws IOException{
//		String outputdirectory = "";
//		
//		if (args.length != 3 || Integer.valueOf(args[1]) < 1) {
//			System.out.println("Usage: <path> <start> <end>. start >= 1");
//			System.exit(2);
//		}
//		outputdirectory = args[0];
//		int start = Integer.valueOf(args[1]);
//		int end = Integer.valueOf(args[2]);
//		
////		int start = 30601;
////		int end = 30610;
////		outputdirectory = "/Users/johnidealist/Desktop/results"; 
//		
//		
//		String finishinfo = outputdirectory + "/finished.csv";
//		String finishrank = outputdirectory + "/finishedrank.csv";
//		String unfinishinfo = outputdirectory + "/unfinished.csv";
//		String unfinishrank = outputdirectory + "/unfinishedrank.csv";
//		String finishinfoall = outputdirectory + "/finishedall.csv";
//		String finishrankall = outputdirectory + "/finishedrankall.csv";
//		String unfinishinfoall = outputdirectory + "/unfinishedall.csv";
//		String unfinishrankall = outputdirectory + "/unfinishedrankall.csv";
//		
//		Query query = new Query();
//		String q = "select appID, releaseDate from appInfo group by appID having releaseDate > '2011-03-29' ORDER BY appID;";
//		ArrayList<App> applist = new ArrayList<App>();
//		applist = query.getReleaseDate(q);
//		query.closeRsStmt();
//		query.closeConn();
//		
//		
//		BufferedWriter finishoutputall = new BufferedWriter(new FileWriter(
//				finishinfoall, false));		
//		BufferedWriter finishrankoutputall = new BufferedWriter(new FileWriter(
//						finishrankall, false));		
//		BufferedWriter unfinishoutputall = new BufferedWriter(new FileWriter(
//				unfinishinfoall, false));		
//		BufferedWriter unfinishrankoutputall = new BufferedWriter(new FileWriter(
//						unfinishrankall, false));
//		BufferedWriter finishoutput = new BufferedWriter(new FileWriter(
//				finishinfo, false));
//		BufferedWriter finishrankoutput = new BufferedWriter(new FileWriter(
//				finishrank, false));
//		BufferedWriter unfinishoutput = new BufferedWriter(new FileWriter(
//				unfinishinfo, false));
//		BufferedWriter unfinishrankoutput = new BufferedWriter(new FileWriter(
//				unfinishrank, false));
//		
//		for(int i = start - 1; i < end; i ++){
//			App a = applist.get(i);
//			System.out.println(a.id);
//			EntireCycle eccat = new EntireCycle(a.id, a.releaseDate, false);
//			if(!eccat.finished && eccat.useful){
//				System.out.println("Unfinished in its own category: "+ eccat.appid);
//				unfinishoutput.write(eccat.toString());
//				unfinishrankoutput.write(eccat.toStringRanklist());
//			}
//			else if(eccat.finished && eccat.useful){
//				System.out.println("Finished in its own category: "+ eccat.appid);
//				finishoutput.write(eccat.toString());
//				finishrankoutput.write(eccat.toStringRanklist());
//			}
//			EntireCycle ecall = new EntireCycle(a.id, a.releaseDate, true);
//			if(!ecall.finished && ecall.useful){
//				System.out.println("Unfinished in its own category: "+ eccat.appid);
//				unfinishoutputall.write(ecall.toString());
//				unfinishrankoutputall.write(ecall.toStringRanklist());
//			}
//			else if (ecall.finished && ecall.useful){
//				System.out.println("Finished in ALL category: "+ eccat.appid);
//				finishoutputall.write(ecall.toString());
//				finishrankoutputall.write(ecall.toStringRanklist());
//			}
//		}
//		
//		unfinishoutputall.close();
//		unfinishrankoutputall.close();
//		finishoutput.close();
//		finishrankoutput.close();
//		finishoutputall.close();
//		finishrankoutputall.close();
//		unfinishoutput.close();
//		unfinishrankoutput.close();
//		
//	}
	
	public String toSPSSString(){
		return "";
	}
	
	public String toWEKAString(){
		return "";
	}
}
