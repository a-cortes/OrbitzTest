package orbitz.stepdefinition;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OrbitzTest {
	WebDriver driver;
	WebDriverWait wait;
	WebElement element;

	@Before
	public void setUp() {
		System.out.println("Start");
		System.setProperty("webdriver.chrome.driver",
				"C:\\\\Users\\\\acortes\\\\Desktop\\\\code\\\\Tools\\\\chromedriver.exe");// Create a new instance of
																							// the Firefox driver
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 60);
	}

	@After
	public void cleanup() {
		// driver.quit();
	}
	
	/*
	 * 
	 * 
	 * FLIGHT ONLY METHODS
	 * 
	 * 
	 * 
	 * 
	 */

	@Given("^User selecciona vuelo$")
	public void user_selecciona_vuelo() throws Throwable {
		driver.get("https://www.orbitz.com/");
		driver.findElement(By.xpath("//*[@id=\"tab-flight-tab\"]")).click();
	}

	@Given("^Selecciona \"([^\"]*)\" y \"([^\"]*)\" de vuelo$")
	public void selecciona_origen_y_destino_de_vuelo(String origen, String destino) throws Throwable {
		// Origen
		driver.findElement(By.xpath("//*[@id=\"flight-origin\"]")).sendKeys(origen);
		
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"aria-option-0\"]")));
		element.click();

		// Destino
		driver.findElement(By.xpath("//*[@id=\"flight-destination\"]")).sendKeys(destino);
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"aria-option-0\"]")));
		element.click();
	}

	@Given("^Selecciona departing y returning de vuelo$")
	public void selecciona_departing_y_returning_de_vuelo() throws Throwable {
		// Departing
		driver.findElement(By.id("flight-departing")).click();
		driver.findElement(By.xpath("//*[@id=\"flight-departing-wrapper\"]/div/div/button[2]")).click();
		driver.findElement(
				By.xpath("//*[@id=\"flight-departing-wrapper\"]/div/div/div[2]/table/tbody/tr[1]/td[7]/button"))
				.click();

		// Returning
		driver.findElement(By.id("flight-returning")).click();
		// driver.findElement(By.xpath("//*[@id=\"package-returning-wrapper\"]/div/div/button[2]")).click();
		driver.findElement(
				By.xpath("//*[@id=\"flight-returning-wrapper\"]/div/div/div[2]/table/tbody/tr[2]/td[2]/button"))
				.click();

	}

	@When("^Presiono Search vuelo$")
	public void presiono_Search_vuelo() throws Throwable {
		driver.findElement(By.id("search-button")).click();

		element = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"flight-wizard-search-button\"]")));

	}

	@Given("^Reservo primer vuelo ida sin paquete$")
	public void reservo_primer_vuelo_ida_sin_paquete() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sortBar\"]/div/fieldset/label/select")));
		List<WebElement> selects = driver.findElements(By.tagName("button"));
		for (WebElement select : selects) {
			if (select.getAttribute("data-test-id") != null
					&& select.getAttribute("data-test-id").equals("select-button")) {
				select.click();
				break;
			}
		}

	}

	@Given("^Reservo primer vuelo vuelta sin paquete$")
	public void reservo_primer_vuelo_vuelta_sin_paquete() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sortBar\"]/div/fieldset/label/select")));
		List<WebElement> selects = driver.findElements(By.tagName("button"));
		for (WebElement select : selects) {
			if (select.getAttribute("data-test-id") != null
					&& select.getAttribute("data-test-id").equals("select-button")) {
				select.click();
				break;
			}
		}
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"forcedChoiceNoThanks\"]")));
		
		// Perform the click operation that opens new window
				element.click();
				Thread.sleep(5000);

				// Switch to new window opened
				String parentWindow = driver.getWindowHandle();
				Set<String> handles = driver.getWindowHandles();
				for (String windowHandle : handles) {

					if (!windowHandle.equals(parentWindow)) {

						driver.switchTo().window(windowHandle);

						// <!--Perform your operation here for new window-->
						// driver.close(); //closing child window
						// driver.switchTo().window(parentWindow); //cntrl to parent window
					}
				}

				// Perform the actions on new window

	}
	@When("^Continuar reserva de vuelo$")
	public void continuar_reserva_de_vuelo() throws Throwable {
		element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bookButton\"]")));
		element.click();

	}
	@Then("^Llenar datos de viajero en vuelo$")
	public void llenar_datos_de_viajero_en_vuelo() throws Throwable {

		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"firstname[0]\"]")));
		element.sendKeys("Adrian");

		element = driver.findElement(By.xpath("//*[@id=\"lastname[0]\"]"));
		element.sendKeys("Cortes");

		Select dropdown = dropdown = new Select(driver.findElement(By
				.name("tripPreferencesRequest.airTripPreferencesRequest.travelerPreferences[0].phoneCountryCode")));
		dropdown.selectByValue("52");

		driver.findElement(
				By.name("tripPreferencesRequest.airTripPreferencesRequest.travelerPreferences[0].phoneNumber"))
				.sendKeys("4432980707");

		// dropdown = new Select(driver.findElement(By.id("date_of_birth_month[0]")));
		// dropdown.selectByValue("12");
		//
		// dropdown = new Select(driver.findElement(By.id("date_of_birth_day[0]")));
		// dropdown.selectByValue("06");
		//
		// dropdown = new Select(driver.findElement(By.id("date_of_birth_year[0]")));
		// dropdown.selectByValue("1988");

		// Datos de contacto
		// driver.findElement(By.xpath("//*[@id=\"preferences\"]/form/fieldset/fieldset[2]/div[1]/label/input")).sendKeys("George");
		driver.findElement(By.xpath("//*[@id=\"no_insurance\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"creditCardInput\"]")).sendKeys("000000000000000000");

		dropdown = new Select(driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/fieldset/label[1]/select")));
		dropdown.selectByValue("1");

		dropdown = new Select(driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/fieldset/label[2]/select")));
		dropdown.selectByValue("2020");

		driver.findElement(By.xpath("//*[@id=\"new_cc_security_code\"]")).sendKeys("111");

		driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[1]/input"))
				.sendKeys("Street NN 123");

		driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[3]/input"))
				.sendKeys("Atlanta");

		dropdown = new Select(driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[4]/select")));
		dropdown.selectByValue("GA");

		driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[5]/input"))
				.sendKeys("55555");

		driver.findElement(By.xpath("//*[@id=\"email\"]/div[1]/fieldset/label/input")).sendKeys("aaaaa@aaaaa.com");

		driver.findElement(By.id("complete-booking")).click();

	}

	/*
	 * 
	 * 
	 * 
	 * 
	 * PACKAGE METHODS
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	@Given("^User selecciona packages$")
	public void user_selecciona_packages() throws Throwable {
		driver.get("https://www.orbitz.com/");
		driver.findElement(By.xpath("//*[@id=\"tab-package-tab\"]/div")).click();
	}

	@Given("^Selecciona origen y destino$")
	public void selecciona_origen_y_destino() throws Throwable {

		// Origen
		driver.findElement(By.xpath("//*[@id=\"package-origin\"]")).sendKeys("Morelia");

		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"aria-option-0\"]")));
		element.click();

		// Destino
		driver.findElement(By.xpath("//*[@id=\"package-destination\"]")).sendKeys("Mexico");
		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"aria-option-0\"]")));
		element.click();
	}

	@Given("^Selecciona departing y returning$")
	public void selecciona_departing_y_returning() throws Throwable {
		// Departing
		driver.findElement(By.id("package-departing")).click();
		driver.findElement(By.xpath("//*[@id=\"package-departing-wrapper\"]/div/div/button[2]")).click();
		driver.findElement(
				By.xpath("//*[@id=\"package-departing-wrapper\"]/div/div/div[2]/table/tbody/tr[1]/td[7]/button"))
				.click();

		// Returning
		driver.findElement(By.id("package-returning")).click();
		// driver.findElement(By.xpath("//*[@id=\"package-returning-wrapper\"]/div/div/button[2]")).click();
		driver.findElement(
				By.xpath("//*[@id=\"package-returning-wrapper\"]/div/div/div[2]/table/tbody/tr[2]/td[2]/button"))
				.click();
	}

	@Given("^Numero de adultos$")
	public void Numero_de_adultos() throws Throwable {
		Select dropdown = new Select(driver.findElement(By.id("package-1-adults")));
		dropdown.selectByValue("1");
	}

	@When("^Presiono Search$")
	public void presiono_Search() throws Throwable {
		driver.findElement(By.id("search-button")).click();

		element = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[@id=\"sortContainer\"]/div/div[1]/div[2]/div/fieldset/ul/li[3]/button")));

	}

	@Given("^Reservo primer hotel$")
	public void selecciono_primer_hotel() throws Throwable {
		WebElement link = driver.findElement(By.xpath("//*[@id=\"22640\"]/div[2]/div/a"));
		System.out.println(link.getText());

		// Perform the click operation that opens new window
		link.click();
		Thread.sleep(5000);

		// Switch to new window opened
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {

			if (!windowHandle.equals(parentWindow)) {

				driver.switchTo().window(windowHandle);

				// <!--Perform your operation here for new window-->
				// driver.close(); //closing child window
				// driver.switchTo().window(parentWindow); //cntrl to parent window
			}
		}

		// Perform the actions on new window

		

		Thread.sleep(1000);
		// Click on room type
		element = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"rooms-and-rates\"]/article[2]/div/div/div[3]/div[2]/a")));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();

	}

	@Given("^Reservo primer vuelo ida$")
	public void reservo_primer_vuelo_ida() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sortBar\"]/div/fieldset/label/select")));
		List<WebElement> selects = driver.findElements(By.tagName("button"));
		for (WebElement select : selects) {
			if (select.getAttribute("data-test-id") != null
					&& select.getAttribute("data-test-id").equals("select-button")) {
				select.click();
				break;
			}
		}

	}

	@Given("^Reservo primer vuelo vuelta$")
	public void reservo_primer_vuelo_vuelta() throws Throwable {
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"sortBar\"]/div/fieldset/label/select")));
		List<WebElement> selects = driver.findElements(By.tagName("button"));
		for (WebElement select : selects) {
			if (select.getAttribute("data-test-id") != null
					&& select.getAttribute("data-test-id").equals("select-button")) {
				select.click();
				break;
			}
		}
	}

	@When("^Continuar reserva$")
	public void continuar_reserva() throws Throwable {
		element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"FlightUDPBookNowButton1\"]/button")));
		element.click();

	}

	@Then("^Llenar datos de viajero$")
	public void llenar_datos_de_viajero() throws Throwable {

		element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"firstname[0]\"]")));
		element.sendKeys("Adrian");

		element = driver.findElement(By.xpath("//*[@id=\"lastname[0]\"]"));
		element.sendKeys("Cortes");

		Select dropdown = dropdown = new Select(driver.findElement(By
				.name("tripPreferencesRequests[0].airTripPreferencesRequest.travelerPreferences[0].phoneCountryCode")));
		dropdown.selectByValue("52");

		driver.findElement(
				By.name("tripPreferencesRequests[0].airTripPreferencesRequest.travelerPreferences[0].phoneNumber"))
				.sendKeys("4432980707");

		// dropdown = new Select(driver.findElement(By.id("date_of_birth_month[0]")));
		// dropdown.selectByValue("12");
		//
		// dropdown = new Select(driver.findElement(By.id("date_of_birth_day[0]")));
		// dropdown.selectByValue("06");
		//
		// dropdown = new Select(driver.findElement(By.id("date_of_birth_year[0]")));
		// dropdown.selectByValue("1988");

		// Datos de contacto
		// driver.findElement(By.xpath("//*[@id=\"preferences\"]/form/fieldset/fieldset[2]/div[1]/label/input")).sendKeys("George");
		driver.findElement(By.xpath("//*[@id=\"no_insurance_package\"]")).click();

		driver.findElement(By.xpath("//*[@id=\"creditCardInput\"]")).sendKeys("000000000000000000");

		dropdown = new Select(driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/fieldset/label[1]/select")));
		dropdown.selectByValue("1");

		dropdown = new Select(driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/fieldset/label[2]/select")));
		dropdown.selectByValue("2020");

		driver.findElement(By.xpath("//*[@id=\"new_cc_security_code\"]")).sendKeys("111");

		driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[1]/input"))
				.sendKeys("Street NN 123");

		driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[3]/input"))
				.sendKeys("Atlanta");

		dropdown = new Select(driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[4]/select")));
		dropdown.selectByValue("GA");

		driver.findElement(By.xpath(
				"//*[@id=\"payment-type-creditcard\"]/div[2]/form/fieldset/div[1]/div/div[3]/div/fieldset/div[3]/label[5]/input"))
				.sendKeys("55555");

		driver.findElement(By.xpath("//*[@id=\"email\"]/div[1]/fieldset/label/input")).sendKeys("aaaaa@aaaaa.com");

		driver.findElement(By.id("complete-booking")).click();

	}

}
