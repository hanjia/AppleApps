package Features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CombineLCwithFeature {
	public int appid;
	public String type;
	public int length;
	public Feature feature;
	
	public CombineLCwithFeature(Feature feature,int id, int length, String type){
		this.feature = feature;
		this.setAppid(id);
		this.setLength(length);
		this.setType(type);
	}
	
	public int getAppid() {
		return appid;
	}


	public void setAppid(int appid) {
		this.appid = appid;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getLength() {
		return length;
	}


	public void setLength(int length) {
		this.length = length;
	}


	public Feature getFeature() {
		return feature;
	}


	public void setFeature(Feature feature) {
		this.feature = feature;
	}
	
	public String toString(){
		return this.getAppid() +","+ this.getType() +"," + this.getLength() + ","+this.getFeature().toString();
	}


	public static void main(String[] args) throws IOException{
//		String fileroot = "";
		
//		if (args.length != 3 || Integer.valueOf(args[1]) < 1) {
//		System.out.println("Usage: <path> <start> <end>. start >= 1");
//		System.exit(2);
//	}
//		fileroot = args[0];
//		int start = Integer.valueOf(args[1]);
//		int end = Integer.valueOf(args[2]);
		
		
		String fileroot = "/Users/johnidealist/Documents/Research Projects/App/EntireLifeCycle/All/LC length/Unfinished";
//		int start = 1;
//		int end = 3;
		
		 File directory = new File(fileroot);
		 String[] children = directory.list();
		 if (children != null) { //To traverse all files
	            for  (int i = 0; i < children.length; i++) {
	            	 String openfile = children[i];
	            	 //System.out.println(openfile);
	            	if (openfile.endsWith(".csv") && openfile.startsWith("Unfinished")) {
//	            		BufferedWriter output = new BufferedWriter(new FileWriter(fileroot + "/Rating-" + openfile, false));	
	            		BufferedWriter output = new BufferedWriter(new FileWriter(fileroot + "/Price-" + openfile, false));	
	            		System.out.println(openfile);
	            		FileReader posinStream = new FileReader(fileroot +"/"+openfile);
	            		BufferedReader in = new BufferedReader(posinStream);
	            		String line = "";
	            		while ((line = in.readLine()) != null) {
	            			String[] section = line.split(",");
	            			int id = Integer.valueOf(section[0]);
	            			int length = Integer.valueOf(section[2]);
	            			String type = section[1];
//	            			Rating r = new Rating(id);
	            			Price p = new Price(id);
//	            			CombineLCwithFeature clwf = new CombineLCwithFeature(r,id, length,type);
	            			CombineLCwithFeature clwf = new CombineLCwithFeature(p,id, length,type);
	            			System.out.println(clwf.toString());
	            			output.write(clwf.toString() + "\n");
	            		}
	            		output.close();
	            		posinStream.close();
	            		in.close();
	            		}
	            }
		 }
	}
}
