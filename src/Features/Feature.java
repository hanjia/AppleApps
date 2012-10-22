package Features;

public abstract class Feature {
	public int appid;
	public String featurename;
	public double average;
	public double stdev;
	public double max;
	
	
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public double getStdev() {
		return stdev;
	}
	public void setStdev(double stdev) {
		this.stdev = stdev;
	}
	
	public Feature(int appid, String featurename){
		this.appid = appid;
		this.featurename = featurename;
	}
}
