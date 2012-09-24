package DAO;

import java.util.Date;

public class Rank {
public Date date;
public int rank;
public int peak;
public String category;
public String type;
public int free;


public int getPeak() {
	return peak;
}
public void setPeak(int peak) {
	this.peak = peak;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public int getFree() {
	return free;
}
public void setFree(int free) {
	this.free = free;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public int getRank() {
	return rank;
}
public void setRank(int rank) {
	this.rank = rank;
}


}
