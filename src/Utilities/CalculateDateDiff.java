package Utilities;

import java.sql.Date;


public class CalculateDateDiff {

public Date a;
public Date b;
public long daydiff;

public CalculateDateDiff(String databefore, String dataafter){
	this.a = Date.valueOf(databefore);
	this.b = Date.valueOf(dataafter);
	this.daydiff =  (this.b.getTime() - this.a.getTime())/ (1000 * 60 * 60 * 24);
}

public CalculateDateDiff(long datebefore, long dateafter){
	
}

public CalculateDateDiff(Date datebefore, Date dateafter){
	this.a = datebefore;
	this.b = dateafter;
	this.daydiff =  (this.b.getTime() - this.a.getTime())/ (1000 * 60 * 60 * 24);
}

public long getDiff() {
	return daydiff;
}

public void setDiff(long diff) {
	this.daydiff = diff;
}

}
