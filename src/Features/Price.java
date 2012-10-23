package Features;

import java.util.ArrayList;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Max;

import Utilities.Query;

public class Price extends Feature {
	
	ArrayList<Double> pricelist = new ArrayList<Double>();
	boolean validvalue = false;
	
	public Price(int appid){
		super(appid, "price");
		this.getPriceList();
		this.setAverage();
		this.setStdev();
		this.setMax();
	}
	
	public double[] convertToArray(ArrayList<Double> list){
		double[] values = new double[list.size()];
		for(int i = 0; i < list.size();i++){
			values[i] = (double)list.get(i);
		}
		return values;
	}
	
	public void getPriceList(){
		Query q = new Query();
		String query = "SELECT DISTINCT currentDate, price FROM appInfo WHERE appID = "+ this.appid + " AND currentDate < '2012-05-02' ORDER BY currentDate;";
		//System.out.println(query);
		this.pricelist = q.getPriceList(query);
		q.closeRsStmt();
		q.closeConn();
	}
	
	public void setAverage(){
		Mean av = new Mean();
	    this.average = av.evaluate(convertToArray(this.pricelist));
 	}
	
	public void setStdev(){
		StandardDeviation sd = new StandardDeviation();		
		this.stdev = sd.evaluate(convertToArray(this.pricelist));
		//System.out.println(this.stdev);
 	}
	
	public double getMax(){
		return this.max;
	}
	
	public void setMax(){
		Max m = new Max();
		this.max = m.evaluate(convertToArray(this.pricelist));
	}
	
	public String toString(){
		return this.getAverage() +","+this.getStdev() +","+this.getMax();
	}
	
	
	public static void main(String[] args){
		Price p = new Price(420636551);
		System.out.println(p.toString());
	}

}
