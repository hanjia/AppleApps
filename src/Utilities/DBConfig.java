package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig {
	private Properties prop;
	
	public DBConfig() {
		this.prop = loadProperties();
	}
	private Properties loadProperties() {
		InputStream input = null;
		Properties prop = new Properties();
		try {
			input = this.getClass().getClassLoader()
					.getResourceAsStream("dbaccount.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	public String getUsername() {
		return prop.getProperty("username");
	}	
	public String getPassword() {
		return prop.getProperty("password");
	}
	public String getUrl() {
		return prop.getProperty("url");
	}
}
