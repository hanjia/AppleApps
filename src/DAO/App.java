package DAO;

import java.util.ArrayList;
import java.util.Date;

import Utilities.Query;



public class App {
	public String id;
	public String provider;
	public String name;
	public ArrayList<Rank> rankalllist;
	public ArrayList<Rank> rankcatlist;
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public ArrayList<Rank> getRankcatlist() {
		return rankcatlist;
	}
	public void setRankcatlist(ArrayList<Rank> rankcatlist) {
		this.rankcatlist = rankcatlist;
	}
	public App(String appid){
		this.id = appid;
		
	}
	

}
