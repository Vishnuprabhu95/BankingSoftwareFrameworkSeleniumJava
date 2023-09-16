package com.inerBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig() {
		File src = new File("./Configurations/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);			
		} catch (Exception e) {
			
		}
	}
	
	public String getApplicationURL() {
		return pro.getProperty("baseURL");		
	}

	public String getUsername() {
		return pro.getProperty("userName");		
	}
	
	public String getPassword() {
		return pro.getProperty("password");		
	}
	
	public String getchromepath() {
		return pro.getProperty("chromepath");		
	}
	
	public String getgeckopath() {
		return pro.getProperty("geckopath");		
	}
}
