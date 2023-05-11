package br.com.alura.leilao.login;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.Assertions;

public class LoginTest {
    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        System.setProperty("webdriver.gecko.driver","src/main/java/br/com/alura/leilao/drivers/geckodriver");
        WebDriver browser = new FirefoxDriver();
        browser.navigate().to("http://localhost:8080/login");
        browser.findElement(By.id("username")).sendKeys("fulano");
        browser.findElement(By.id("password")).sendKeys("pass");
        browser.findElement(By.id("login-form")).submit();

        try {
            // aguarda meio segundo apenas para dar tempo de efetuar o login
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assertions.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
        browser.quit();
    }
}
