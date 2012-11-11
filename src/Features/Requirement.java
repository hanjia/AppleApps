package Features;

import java.util.ArrayList;
import java.util.HashMap;

import Utilities.Query;

public class Requirement extends Feature {
	public int reqChangeFreq;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Requirement r = new Requirement(420636551);
		System.out.println(r.getReqChangeFreq());
	}

	public Requirement(int appid) {
		super(appid, "requirement");
		this.setReqChangeFreq();
	}

	public void setReqChangeFreq() {
		int freq = 0;
		Query q = new Query();
		String query = "SELECT count(distinct name) from appInfo, requirements WHERE reqID=requirements.ID and appID = "
				+ this.appid + ";";
//		System.out.println(query);
		freq=q.getReqChangeFreq(query);
		this.reqChangeFreq=freq;
	}
	
	public int getReqChangeFreq(){
		return this.reqChangeFreq;
	}

	// Some apps have -- "ixMac.MarketingName" -- as a hardware requirement. It
	// was due to a bug of Apple App Store.
	// Read more:
	// http://www.macrumors.com/2011/04/14/what-is-ix-mac-marketingname/
	// This function removes this string from the requirement.
	public static String removePlaceholder(String str) {
		String placeholder = ", and ix.Mac.MarketingName";
		String cleanedStr = "";
		if (str.indexOf(placeholder) > 0) {
			int indexOfP = str.indexOf(placeholder);
			String temp = str.substring(0, indexOfP);
			String post = str.substring(indexOfP + 27);
			int indexOfComma = temp.lastIndexOf(",");
			String pre = temp.substring(0, indexOfComma);
			String middle = temp.substring(indexOfComma);
			middle = ", and " + middle.replace(", ", "") + ".";
			cleanedStr = pre + middle + post;
		} else {
			return str;
		}
		return cleanedStr;
	}
	
	//Clean up the Requirements table so that the same requirements share the same string
	public static void cleanData() {
		Query q = new Query();
		String query = "SELECT * from requirements;";
		HashMap<Integer, String> map = q.getRequirement(query);
		ArrayList<Integer> keys = new ArrayList<Integer>(map.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String temp = map.get(keys.get(i));
//			System.out.println("before:"+temp);
			temp = removePlaceholder(temp);
			temp = temp.replace(".R", ". R");
			temp=temp.replace("later.", "later");
			temp = temp.trim();
//			System.out.println("after:  "+temp);
			query = "update Requirements set name=\"" + temp + "\" where ID="
					+ keys.get(i);
//			System.out.println(query);
			 q.updateRequirement(query);
		}
	}

}
