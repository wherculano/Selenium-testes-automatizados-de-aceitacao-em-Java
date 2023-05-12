package br.com.alura.leilao.login;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.gecko.driver","src/main/java/br/com/alura/leilao/drivers/geckodriver");
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new FirefoxDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        this.browser.findElement(By.id("username")).sendKeys("fulano");
        this.browser.findElement(By.id("password")).sendKeys("pass");
        this.browser.findElement(By.id("login-form")).submit();

        try {
            // aguarda meio segundo apenas para dar tempo de efetuar o login
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertFalse(this.browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assertions.assertEquals("fulano", this.browser.findElement(By.id("usuario-logado")).getText());
    }

    @Test
    public void naoDeveriaEfutuarLoginComDadosInvalidos(){
        this.browser.findElement(By.id("username")).sendKeys("userQualquer");
        this.browser.findElement(By.id("password")).sendKeys("senhaInvalida");
        this.browser.findElement(By.id("login-form")).submit();

        try {
            // aguarda meio segundo apenas para dar tempo de efetuar o login
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertTrue(this.browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        Assertions.assertTrue(this.browser.getPageSource().contains("Usuário e senha inválidos"));
        Assertions.assertThrows(NoSuchElementException.class, () -> this.browser.findElement(By.id("usuario-logado")));

    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
        Assertions.assertTrue(this.browser.getCurrentUrl().equals("http://localhost:8080/login"));
        Assertions.assertFalse(this.browser.getPageSource().contains("Dados do Leilão"));
    }
}
