package VerifyLogin;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtility{
	WebDriver driver;
	public ScreenshotUtility(WebDriver driver) {
		this.driver= driver;
	}
	public void takeSS(String Filename) throws IOException 
	{
		String dateFormat= new SimpleDateFormat("yyyymmddhhmmss a").format(new Date());
		File screenshot= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File directory= new File(System.getProperty("user.dir")+File.separator+"Screenshot");
		if (!directory.exists())
		{
			directory.mkdir();
		}
		FileHandler.copy(screenshot, new File(directory+File.separator+Filename+"_"+dateFormat+".png"));
		
	}
	public boolean checkDirectory() 
	{
		File directory= new File(System.getProperty("user.dir")+File.separator+"Screenshot");
		File[] filelist= directory.listFiles();
		if (filelist.length==0) 
		{
			return true;
		}
		else 
		{
			return false;
		}
		
	}

}
