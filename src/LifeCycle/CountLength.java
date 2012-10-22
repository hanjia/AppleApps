package LifeCycle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Utilities.CalculateDateDiff;

public class CountLength {

	public static void main(String[] args) throws IOException{
		String fileName = "/Users/johnidealist/Documents/Research Projects/App/allcycle/finishedall.csv";
		File posFile = new File(fileName);
		FileReader posinStream = new FileReader(posFile);
		BufferedReader in = new BufferedReader(posinStream);
		String line = "";
		HashMap<String,String> categoryset = new HashMap<String,String>();
		
		while ((line = in.readLine()) != null) {
			String[] splits = line.split(",");
			//System.out.println(splits[0]);
			String date1 = splits[3];
			String date2 = splits[4];
			String date3 = splits[5];
			String date4 = splits[6];
			//System.out.println(date1);
			String id = splits[0];
			String cate = splits[1].toUpperCase();
			if(!date1.equals("null") && !date4.equals("null")){
			CalculateDateDiff cdd = new CalculateDateDiff(date1, date4);
			long diff1 = cdd.getDiff();
//			CalculateDateDiff cdd1 = new CalculateDateDiff(date2, date3);
//			long diff2 = cdd1.getDiff();
//			CalculateDateDiff cdd2 = new CalculateDateDiff(date3, date4);
//			long diff3 = cdd2.getDiff();
			String record =  id + "\t" + cate + "\t" +diff1;
			System.out.println(record);
			
			categoryset.put(record,cate);
			}
						
		}
//		String[] categorylist = { /**"ALL",**/ "BUSINESS", "GAME", "ENTERTAINMENT",
//		"SPORTS", "NEWS", "Navigation", "Utilities", "Travel",
//		"SocialNetwork", "Reference", "Productivity", "Music",
//		"Photography", "Medical", "Lifestyle", "HealthCareAndFitness",
//		"Finance", "Books", "Education" };
//
//		for(int j = 0 ; j < categorylist.length; j ++){
//		String category = categorylist[j].toUpperCase();
//		ArrayList<String> recordlist= new ArrayList<String>(categoryset.keySet());
//		String filename = "/Users/johnidealist/Desktop/Unfinished-"+category+".csv";
//		BufferedWriter overalloutput = new BufferedWriter(new FileWriter(
//				filename, false));
//		for(int i = 0 ; i < recordlist.size(); i++){
//			String record = recordlist.get(i);
//			String cate = categoryset.get(record);
//			if(cate.equals(category)){
//				System.out.println(record);
//				overalloutput.write(record + "\n");
//			}
//		}
//		
//		overalloutput.close();
//		}
		
		
		in.close();
		posinStream.close();
	}
	
	
}
