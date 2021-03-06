package Utilities;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import DAO.App;
import DAO.Rank;
import Features.Price;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Query {
	PreparedStatement stmt;
	ResultSet rs;
	JDBCConnection jconn;

	public Query() {
		DBConfig dbconfig = new DBConfig();
		this.jconn = new JDBCConnection();
		// System.out.println(dbconfig.getUrl()+","+dbconfig.getUsername()+","+dbconfig.getUsername());
		jconn.getConnection(dbconfig.getUrl(), dbconfig.getUsername(),
				dbconfig.getPassword());

	}

	public ArrayList<String> getAppList(String query) {
		ArrayList<String> applist = new ArrayList<String>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);
			while (this.rs.next()) {
				applist.add(this.rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return applist;
	}

	public Rank getOneApp(String query) {
		Rank r = new Rank();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				r.peak = this.rs.getInt(1);
				r.category = this.rs.getString(2);
				r.type = this.rs.getString(3);
				r.free = this.rs.getInt(4);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}

	public ArrayList<Rank> getOneAppRank(String query) {
		ArrayList<Rank> ranklist = new ArrayList<Rank>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				Rank r = new Rank();
				r.date = this.rs.getDate(1);
				r.rank = this.rs.getInt(2);
				ranklist.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ranklist;
	}

	public ArrayList<Rank> getAppDetailRank(String query) {
		ArrayList<Rank> ranklist = new ArrayList<Rank>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);
			while (this.rs.next()) {
				Rank r = new Rank();
				r.date = this.rs.getDate(1);
				r.rank = this.rs.getInt(2);
				r.peak = this.rs.getInt(3);
				r.category = this.rs.getString(4);
				r.type = this.rs.getString(5);
				r.free = this.rs.getInt(6);
				ranklist.add(r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ranklist.size());

		return ranklist;
	}

	public ArrayList<Integer> getLongApps(String query) {
		ArrayList<Integer> ranklist = new ArrayList<Integer>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);
			while (this.rs.next()) {
				int id = this.rs.getInt(1);
				ranklist.add(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(ranklist.size());

		return ranklist;
	}

	public App getAppInfo(String query) {
		App app = new App();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				app.setId(this.rs.getInt(1));
				app.setName(this.rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return app;
	}

	public Rank getPeak(String query) {
		Rank r = new Rank();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				r.peak = this.rs.getInt(1);
				r.date = this.rs.getDate(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return r;
	}

	public ArrayList<java.sql.Date> getTwoDate(String query) {
		ArrayList<java.sql.Date> date = new ArrayList<java.sql.Date>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				date.add(this.rs.getDate(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public ArrayList<Double> getRatingList(String query) {
		ArrayList<Double> ratinglist = new ArrayList<Double>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				double rating = Double.valueOf(this.rs.getString(2));
				// System.out.println(this.rs.getString(1));
				ratinglist.add(rating);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ratinglist;

	}

	public ArrayList<Double> getPriceList(String query) {
		ArrayList<Double> pricelist = new ArrayList<Double>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				double price = Double.valueOf(this.rs.getString(2));
				// System.out.println(this.rs.getString(1));
				pricelist.add(price);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pricelist;

	}

	public ArrayList<App> getReleaseDate(String query) {
		ArrayList<App> releaselist = new ArrayList<App>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				App a = new App();
				a.id = this.rs.getInt(1);
				a.releaseDate = this.rs.getDate(2);
				releaselist.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return releaselist;
	}

	public ArrayList<java.sql.Date> getReleaseDateList(String query) {
		ArrayList<java.sql.Date> releaseDateList = new ArrayList<java.sql.Date>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				java.sql.Date d = this.rs.getDate("releaseDate");
				releaseDateList.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return releaseDateList;
	}
	
	public int getReleaseFreq(String query){
		int releaseFreq = 0;
		
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				releaseFreq = this.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return releaseFreq;
	}

	public ArrayList<String> getCategory(String query) {
		ArrayList<String> category = new ArrayList<String>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				category.add(this.rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}
	
	public HashMap<Integer, String> getRequirement(String query){
		HashMap<Integer, String> map= new HashMap<Integer,String>();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				map.put(this.rs.getInt(1),this.rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	public void updateRequirement(String query){
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public App getApp(String query) {
		App app = new App();
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			while (this.rs.next()) {
				app.setId(this.rs.getInt(1));// appID, category, currentDate,
												// rank, peak
				String cate = this.rs.getString(2);
				if (cate.equals("ALL")) {
					app.categorynotall = "ALL";
				} else {
					app.categorynotall = cate;
				}

				Rank r = new Rank();
				r.date = this.rs.getDate(3);
				r.rank = this.rs.getInt(4);
				r.peak = this.rs.getInt(5);
				app.rankalllist.add(r);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return app;
	}
	
	public int getReqChangeFreq(String query){
		int freq=0;
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.rs = this.stmt.executeQuery(query);

			if (this.rs.next()) {
				freq=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return freq;
	}

	public void setMissingValue(String query) {
		try {
			this.stmt = (PreparedStatement) this.jconn.getConn()
					.prepareStatement(query);
			this.stmt.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeRsStmt() {
		try {
			if (this.rs != null) {
				this.rs.close();
			}
			if (this.stmt != null) {
				this.stmt.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void closeConn() {
		try {
			this.jconn.stopConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
