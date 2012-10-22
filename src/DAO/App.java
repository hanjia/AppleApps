package DAO;

import java.util.ArrayList;
import java.sql.Date;

import Utilities.Query;

public class App {  // Each App is an object of this class
	public int id;
	public String provider;
	public Date releaseDate;
	public String name;
	public int peak;
	public String categorynotall;
	public ArrayList<Rank> rankalllist = new ArrayList<Rank>();
	public boolean free;

	public int getPeak() {
		return peak;
	}
	public void setPeak(int peak) {
		this.peak = peak;
	}
	public boolean isFree() {
		return free;
	}
	public void setFree(boolean free) {
		this.free = free;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getCategorynotall() {
		return categorynotall;
	}
	public void setCategorynotall(String categorynotall) {
		this.categorynotall = categorynotall;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public ArrayList<Rank> getRankalllist() {
		return rankalllist;
	}
	public void setRankalllist(ArrayList<Rank> rankalllist) {
		this.rankalllist = rankalllist;
	}

	public App(int appid){
		this.id = appid;
		
	}
	public App(){
		
	}
	@Override
	public String toString() {
		return "App [id=" + id + ", provider=" + provider + ", releaseDate="
				+ releaseDate + ", name=" + name + ", peak=" + peak
				+ ", categorynotall=" + categorynotall + ", rankalllist="
				+ rankalllist + ", free=" + free + "]";
	}
	

}
