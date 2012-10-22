package Features;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.*;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Max;

import Utilities.Query;

public class Rating extends Feature {
	ArrayList<Double> ratinglist = new ArrayList<Double>();
	boolean validvalue = false;
	
	public Rating(int appid){
		super(appid, "rating");
		this.getRatingList();
		this.setAverage();
		this.setStdev();
		this.setMax();
	}
	
	public double[] convertPrimitiveAndRemoveInvalid(ArrayList<Double> list){
		double[] values = new double[list.size()];
		for(int i = 0; i < list.size();i++){
			if(list.get(i) != -1000 || validvalue){ // whether it is a valid value
			values[i] = (double)list.get(i);
			}
		}
		return values;
	}
	
	public void getRatingList(){
		Query q = new Query();
		String query = "SELECT DISTINCT currentDate, currentVersionRating FROM appInfo WHERE appID = "+ this.appid + " AND currentDate < '2012-05-02' ORDER BY currentDate;";
		//System.out.println(query);
		this.ratinglist = q.getRatingList(query);
		q.closeRsStmt();
		q.closeConn();
	}
	
	public void setAverage(){
		Mean av = new Mean();
	    this.average = av.evaluate(this.convertPrimitiveAndRemoveInvalid(this.ratinglist));
 	}
	
	public void setStdev(){
		StandardDeviation sd = new StandardDeviation();		
		this.stdev = sd.evaluate(this.convertPrimitiveAndRemoveInvalid(this.ratinglist));
		//System.out.println(this.stdev);
 	}
	
	public double getMax(){
		return this.max;
	}
	
	public void setMax(){
		Max m = new Max();
		this.max = m.evaluate(this.convertPrimitiveAndRemoveInvalid(this.ratinglist));
	}
	
	public String toString(){
		return this.getAverage() +","+this.getStdev() +","+this.getMax();
	}
	
	
	public static void main(String[] args){
		Rating r = new Rating(420636551);
		System.out.println(r.getAverage() +","+ r.getStdev()+","+ r.getMax());
	}
	
}
