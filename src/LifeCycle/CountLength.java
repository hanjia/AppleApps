package LifeCycle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Utilities.CalculateDateDiff;

public class CountLength {

	public static void main(String[] args) throws IOException{
		String fileName = "/Users/johnidealist/Desktop/App/allcycle/unfinishedall.csv";
		File posFile = new File(fileName);
		FileReader posinStream = new FileReader(posFile);
		BufferedReader in = new BufferedReader(posinStream);
		String line = "";
		while ((line = in.readLine()) != null) {
			String[] splits = line.split(",");
			//System.out.println(splits[0]);
			String date1 = splits[3];
			String date2 = splits[4];
			String date3 = splits[5];
			String date4 = splits[6];
			//System.out.println(date1);
			String id = splits[0];
			String cate = splits[1];
			if(!date1.equals("null") &&!date2.equals("null") &&!date3.equals("null") && !date4.equals("null")){
			CalculateDateDiff cdd = new CalculateDateDiff(date1, date2);
			long diff1 = cdd.getDiff();
			CalculateDateDiff cdd1 = new CalculateDateDiff(date2, date3);
			long diff2 = cdd1.getDiff();
			CalculateDateDiff cdd2 = new CalculateDateDiff(date3, date4);
			long diff3 = cdd2.getDiff();
			String record =  id + "\t" + cate + "\t" +diff1 + "\t" + diff2 + "\t" + diff3;
			System.out.println(record);
			}
		}
		
		in.close();
		posinStream.close();
	}
	
	
}
