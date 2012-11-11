package Features;

import java.sql.Date;
import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.moment.Mean;

import Utilities.Query;

public class Version extends Feature {
	public Integer releaseFreq;
	public Integer maxVersionLength;
	public Integer minVersionLength;
	public ArrayList<Date> releaseDateList;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Version v = new Version(420636551);
		System.out.println(v.maxVersionLength +","+ v.minVersionLength+","+ v.releaseFreq);
	}

	public Version(int appid) {
		super(appid, "version");
		this.getReleaseDateList();
		this.setMaxVersionLength();
		this.setMinVersionLength();
		this.setReleaseFreq();
	}

	public void getReleaseDateList() {
		Query q = new Query();
		String query = "SELECT DISTINCT releaseDate FROM appInfo WHERE appID = "
				+ this.appid + " ORDER BY releaseDate;";
		// System.out.println(query);
		this.releaseDateList = q.getReleaseDateList(query);
		q.closeRsStmt();
		q.closeConn();
	}
	
	public void setReleaseFreq(){
		Query q = new Query();
		String query = "SELECT count(DISTINCT releaseDate) FROM appInfo WHERE appID = "
				+ this.appid + ";";
//		 System.out.println(query);
		this.releaseFreq = q.getReleaseFreq(query);
		q.closeRsStmt();
		q.closeConn();
	}

	public void setMaxVersionLength() {
		if (this.releaseDateList.size() > 1) {
			Integer versionLength = 0;
			for (int i = 0; i < releaseDateList.size() - 1; i++) {
				Integer diff=(int) ((releaseDateList.get(i+1).getTime() - releaseDateList.get(i).getTime())/ (1000 * 60 * 60 * 24));
				if (diff > versionLength) {
					versionLength = diff;
				}
			}

			this.maxVersionLength = versionLength;
		} else {
			this.maxVersionLength = null;
		}
	}

	public void setMinVersionLength() {
		if (this.releaseDateList.size() > 1) {
			Integer versionLength = 0;
			versionLength=(int) ((releaseDateList.get(1).getTime()-releaseDateList.get(0).getTime())/ (1000 * 60 * 60 * 24));
			for (int i = 1; i < releaseDateList.size() - 1; i++) {
				Integer diff=(int) ((releaseDateList.get(i+1).getTime() - releaseDateList.get(i).getTime())/ (1000 * 60 * 60 * 24));
				if (diff < versionLength) {
					versionLength = diff;
				}
			}

			this.minVersionLength = versionLength;
		} else {
			this.minVersionLength = null;
		}
	}
}
