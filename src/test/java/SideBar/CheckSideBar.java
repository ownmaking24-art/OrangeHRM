package SideBar;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.BaseClass;
import Base.BaseUtility;
import Base.LoginBase;
/*
 * Here we used beforemethod because driver is getting initialized in beforemethod in Baseclass.
 * so, if we create objects in costructor then Baseclass has't executed beforemethod so, it will throw NullPointer Exception.
 * Run -> Constructor -> create object -> executed methods.
 * In this program first CheckSideBar constructor runs -> @BeforeMethod of Baseclass because it is a parent class - > @beforeMethod of this class.
 */
public class CheckSideBar extends BaseClass{
	LoginBase loginBase;
	SidebarUtility sidebarUtility;
	BaseUtility baseUtility;
	@BeforeMethod
	public void Initialize() {
		sidebarUtility = new SidebarUtility(driver);
		loginBase= new LoginBase(driver);
		baseUtility = new BaseUtility(driver);
		loginBase.login("Admin", "admin123");
	}
	@Test(priority = 2)
	public void checkAllOptions()
	{
		List <WebElement> options=baseUtility.doFindElements(By.xpath("//ul[@class='oxd-main-menu']//li"));
		for (int i= 0;i<options.size();i++) 
		{
			baseUtility.doClick(baseUtility.doFindElements(By.xpath("//ul[@class='oxd-main-menu']//li")).get(i));
			if(i==9) 
			{
				baseUtility.doClick(By.xpath("//button[text()=' Cancel ']"));
			}
		}
	
	}
	@Test (priority = 0)
	public void checkArrow() 
	{
	
		int count=0;
		baseUtility.doClick(By.xpath("//button[contains(@class,'oxd-main-menu-button')]"));
		if (baseUtility.doFindElements(By.xpath("//input[contains(@class,'toggled')]")).size()>0) count++;
		baseUtility.doClick(By.xpath("//button[contains(@class,'oxd-main-menu-button')]"));
		if (baseUtility.doFindElements(By.xpath("//input[contains(@class,'toggled')]")).size()==0) count++;
		else Assert.fail();
		Assert.assertTrue(count==2);
		
		
	}

}
