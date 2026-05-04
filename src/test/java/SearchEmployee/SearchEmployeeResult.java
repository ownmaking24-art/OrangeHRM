package SearchEmployee;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Base.BaseClass;
import Base.BaseUtility;
import Base.LoginBase;

public class SearchEmployeeResult extends BaseClass {
	LoginBase loginBase;
	BaseUtility baseUtility;
	@BeforeMethod
	public void Initialize() {
		loginBase= new LoginBase(driver);
		baseUtility = new BaseUtility(driver);
		loginBase.login("Admin", "admin123");
		baseUtility.doClick(By.xpath("//span[text()='Admin']"));
		baseUtility.doClick(By.xpath("(//nav[@aria-label='Topbar Menu']//li)[1]"));
		baseUtility.doClick(By.xpath("(//nav[@aria-label='Topbar Menu']//li)[1]//ul"));
	}
	@Test (priority = 1)
	public void searchByUsername() 
	{
		baseUtility.doSendKeys(By.xpath("(//div[@class='oxd-form-row']//input)[1]"), "john38127");
		baseUtility.doClick(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']/div")).size()>0);
		
	}
	@Test  (priority = 2)
	public void searchByRole() 
	{
		baseUtility.doClick(By.xpath("(//i[contains(@class,'arrow')])[1]"));
		baseUtility.doClick(By.xpath("//div[@role='option']//span[text()='Admin']"));
		baseUtility.doClick(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']/div")).size()>0);
	}
	@Test (priority = 3)
	public void searchByName() 
	{
		baseUtility.doSendKeys(By.xpath("//input[@placeholder='Type for hints...']"), "John");
		baseUtility.doClick(By.xpath("(//div[@role='listbox']//span[text()='John  Terry'])[1]"));
		baseUtility.doClick(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']/div")).size()>0);
	}
	@Test  (priority = 4)
	public void searchByStatus() 
	{
		baseUtility.doClick(By.xpath("(//i[contains(@class,'arrow')])[2]"));
		baseUtility.doClick(By.xpath("//div[@role='listbox']//span[text()='Enabled']"));
		baseUtility.doClick(By.xpath("//button[@type='submit']"));
		Assert.assertTrue(baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']/div")).size()>0);
		
	}
	/*
	 * The has a bug that cannot search with all the options filled.
	 * @Test
	public void searchByAll() 
	{
		baseUtility.doSendKeys(By.xpath("(//div[@class='oxd-form-row']//input)[1]"), "Admin");
		baseUtility.doClick(By.xpath("(//i[contains(@class,'arrow')])[1]"));
		baseUtility.doClick(By.xpath("//div[@role='option']//span[text()='Admin']"));
		baseUtility.doSendKeys(By.xpath("//input[@placeholder='Type for hints...']"), "John  TestAuto");
		baseUtility.doClick(By.xpath("(//div[@role='listbox']//span[text()='John  TestAuto'])[1]"));
		baseUtility.doClick(By.xpath("(//i[contains(@class,'arrow')])[2]"));
		baseUtility.doClick(By.xpath("//div[@role='listbox']//span[text()='Enabled']"));
		baseUtility.doClick(By.xpath("//button[@type='submit']"));
		if (baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']/div")).size()>0) 
		{	
			Assert.assertTrue(true);
		}
		else 
		{
			Assert.fail();
		}
	}*/
	@Test (priority = 5)
	public void addEmployee() 
	{
		baseUtility.doClick(By.xpath("//div[@class='orangehrm-header-container']//button[@type='button']"));
		baseUtility.doClick(By.xpath("(//i[contains(@class,'arrow')])[1]"));
		baseUtility.doClick(By.xpath("(//div[@role='option'])[3]"));
		baseUtility.doSendKeys(By.xpath("//input[@placeholder='Type for hints...']"), "test");
		baseUtility.doClick(By.xpath("(//span[text()='Testy  Autom'])[1]"));
		baseUtility.doClick(By.xpath("(//i[contains(@class,'arrow')])[2]"));
		baseUtility.doClick(By.xpath("(//div[@role='option'])[3]"));
		baseUtility.doSendKeys(By.xpath("(//input[@autocomplete='off'])[1]"), "test123");
		baseUtility.doSendKeys(By.xpath("(//input[@autocomplete='off'])[2]"), "asdf@12");
		baseUtility.doSendKeys(By.xpath("(//input[@autocomplete='off'])[3]"), "asdf@12");
		baseUtility.doClick(By.xpath("//button[@type='submit']"));
		baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']//div[text()='test123']"));
		baseUtility.doClick(By.xpath("//div[@class='oxd-table-body']//div[text()='test123']/following::div//button[@type='button']"));
		baseUtility.doClick(By.xpath("(//div[@class='orangehrm-modal-footer']//button)[2]"));
		Assert.assertTrue(baseUtility.doFindElements(By.xpath("//div[@class='oxd-table-body']//div[text()='test123']")).size()==0);
	}
}
