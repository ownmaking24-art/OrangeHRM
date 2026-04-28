package Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginBase {
	WebDriver driver;
	BaseUtility bu;
	By username_= By.name("username");
	By pass_= By.name("password");
	By login_btn= By.xpath("//button[@type='submit']");
	public LoginBase(WebDriver driver) {
		this.driver = driver;
		bu= new BaseUtility(driver);
		
	}
public void login(String username, String pass ) 
{
	bu.getUrl("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	bu.doSendKeys(username_, username);
	bu.doSendKeys(pass_, pass);
	bu.doClick(login_btn);
}
}
