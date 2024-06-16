import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {

    WebDriver driver;

    @BeforeEach //przed każdym testem zrób ponizszą metode
    //OTWIERANIE PRZEGLADARKI I CHROME DRIVER
    public void driverSetup(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @AfterEach
    //ZAMYKANIE PRZEGLADARKI
    public void driverClose(){

        //METODY ZAMYKANIA
        //CLOSE: driver.close(); - ZAMYKA PRZEGLĄDARKĘ, DRIVER OTWARTY
        //QUIT -  ZAMYKA PRZEGLĄDARKĘ, ZAMYKA DRIVERa
        driver.quit();
    }

    //pierwszy test
    @Test
    public void firstTest(){
        //metoda kierująca na konkretny adres strony
        driver.get("https://skleptest.pl/"); 
    }
    //drugi test
    @Test
    public void secondTest(){
        //metoda wskazująca element po nazwie ID -> tutaj będzie to TAG 'input'
        driver.get("https://skleptest.pl/"); 
        WebElement searchInput = driver.findElement(By.id("search-field-top-bar"));
         //search to predefiniowana wartość 'place holder' i trzeba ją wyczyścić:
         searchInput.clear();
         //wpisz do paska wyszukiwania
         searchInput.sendKeys("dress");
         WebElement searchButton = driver.findElement(By.className("search-top-bar-submit")); //className bo jak wpiszemy class to komputer nie wiem o ktore klas chodzi jest to slowo klucz javy
         searchButton.click();
    }
     //trzeci test
     @Test
     public void thirdTest(){
        driver.get("https://skleptest.pl/my-account/");
        //znajdź pole 'username' formularza i guzik 'login'
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement userLoginButton = driver.findElement(By.name("login"));
        //wyczyść pole 'username' i wpisz 'null' oraz kliknij guzik login
        usernameInput.clear();
        usernameInput.sendKeys("null");
        userLoginButton.click();

         //znajdz element UL po xpath i nazwij go 'information'
        //getText(); -wyciagamy tekst ktory kryje sie pod xPath i nazwij go 'message'
        WebElement information = driver.findElement(By.xpath("//ul[@class=\"woocommerce-error\"]"));
        String message = information.getText();
        String komunikat = "Error: The password field is empty.";
        //ASERCJE -  Assertions.
        //asercja assertNotNull(information) sprawdzanie czy podany w nawiasie element nie jest nullem
        //assertTrue(true) - czy podany w asercji warunek jest prawdziwy
        //assertFalse(false) - czy podany w asercji warunek jest fałszywy
        //AssertEquals(expected, actual); -> (komunikat, message);
        Assertions.assertEquals(komunikat, message);
    }
    @Test
    public void forthTest(){
       driver.get("https://skleptest.pl/");
       //znajdź pole 'name' oraz pole 'email' formularza i guzik 'Subscribe'
       WebElement nameInput = driver.findElement(By.id("es_txt_name"));
       WebElement emailInput = driver.findElement(By.id("es_txt_email"));
       WebElement subscribeButton = driver.findElement(By.id("es_txt_button"));
       //wyczyść pole 'name'oraz'email' i wpisz 'ivona' i 'ivona@gmail.com' oraz kliknij guzik subscribe
       nameInput.clear();
       nameInput.sendKeys("ivona");
       emailInput.clear();
       emailInput.sendKeys("ivona@gmail.com");
       subscribeButton.click();

   }

   //wyszukaj listę elementow na stronie
   //tworzymy  listę (dziala podobnie jak tablica) List<WebElement> o nazwie listaElementow
   //wyszukamy po xPath -> find.Elements(By.xpath(""))
   //wydrukuj listę znalezionych elementow; gdy nie znajdzie żadnych elementow ZWRACA LISTĘ PUSTĄ a gdy nie znajdzie 1 elemetu, to wyskoczy ERROR
    @Test
    public void findMenthodsTest(){
        driver.get("https://skleptest.pl/");
        WebElement newsletterSubscribeButton = driver.findElement(By.name("es_txt_button"));
        List<WebElement> listaElementow = driver.findElements(By.xpath("//ul[@id=\"desktop-menu\"]/li"));
        System.out.println(listaElementow.size());
    }

}
