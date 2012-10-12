package LifeCycle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DAO.App;
import DAO.Rank;
import Utilities.Query;

public class LengthDist {

	static String outputdirectory = "";
	public BufferedWriter output;
	public int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public static void main(String[] args) throws IOException {
		// parameter passed by command line
		if (args.length != 3 || Integer.valueOf(args[1]) < 1) {
			System.out.println("Usage: <path> <start> <end>. start >= 1");
			System.exit(2);
		}
		outputdirectory = args[0];
		int start = Integer.valueOf(args[1]);
		int end = Integer.valueOf(args[2]);
//		outputdirectory = "/src/data";
		String[] categorylist = { "ALL", "BUSINESS", "GAME", "ENTERTAINMENT",
				"SPORTS", "NEWS", "Navigation", "Utilities", "Travel",
				"SocialNetwork", "Reference", "Productivity", "Music",
				"Photography", "Medical", "Lifestyle", "HealthCareAndFitness",
				"Finance", "Books", "Education" };
		String filename = outputdirectory + "/overall.csv";
		BufferedWriter overalloutput = new BufferedWriter(new FileWriter(
				filename, false));
		String finalresult = "";
		for (int i = start - 1; i <= end - 1/** categorylist.length **/
		; i++) {
			String line = categorylist[i] + ",";
			for (int j = 400; j > 0; j--) {
				String indfilename = outputdirectory + "/list-"
						+ categorylist[i] + "-" + j + ".csv";
				LengthDist el = new LengthDist(categorylist[i], j,
						indfilename);
				line += el.getCount() + ",";
			}
			finalresult += line + "\n";
		}
		overalloutput.write(finalresult);
		overalloutput.close();
	}

	public LengthDist(String category, int number, String filename)
			throws IOException {
		this.output = new BufferedWriter(new FileWriter(filename, false));
		String query = "";
		ArrayList<Integer> idlist = new ArrayList<Integer>();
		Query q = new Query();
		if(number < 400){
		query = "SELECT appID, count(*) FROM appRank WHERE category = '"
				+ category
				+ "' AND currentDate < '2012-05-02' GROUP BY appID HAVING count(*) >= "
				+ number + " and count(*) < " + (number + 1) + ";";
		
		}
		else if (number == 400) {
		query = "SELECT appID, count(*) FROM appRank WHERE category = '"
					+ category
					+ "' AND currentDate < '2012-05-02' GROUP BY appID HAVING count(*) >= "
					+ number+ ";";
		}
		else {
			System.out.println("Error in number");
		}
		idlist = q.getLongApps(query);
		q.closeRsStmt();
		String result = "";
		System.out.println(idlist.size());
		for (int id : idlist) {
			Rank r = new Rank();
			System.out.println(id);
			query = "select peak,category,type,free from appRank where appID = '"
					+ id + "'and category = '" + category + "';";
			r = q.getOneApp(query);
			q.closeRsStmt();
			query = "SELECT ID, name FROM app WHERE ID = " + id + ";";
			App app = new App();
			app = q.getAppInfo(query);
			String name = app.getName();
			result += id + "," + name.replaceAll(",", "") + "," + r.toString();
			System.out.println(id + "," + app.getName() + "," + r.toString());
		}
		this.setCount(idlist.size());
		q.closeConn();
		this.output.write(result);
		this.output.close();
	}
}
