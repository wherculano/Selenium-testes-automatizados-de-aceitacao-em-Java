package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPage {
    private WebDriver browser;
    private static final String URL_LOGIN = "http://localhost:8080/login";

    public LoginPage() {
        System.setProperty("webdriver.gecko.driver", "src/main/java/br/com/alura/leilao/drivers/geckodriver");
        this.browser = new FirefoxDriver();
        this.browser.navigate().to(URL_LOGIN);
    }

    public void afterEach() {
        this.browser.quit();
    }

    public void fechar() {
        this.browser.quit();
    }

    public void preencheFormularioPaginaDeLogin(String user, String password) {
        this.browser.findElement(By.id("username")).sendKeys(user);
        this.browser.findElement(By.id("password")).sendKeys(password);
    }

    public void efetuaLogin() {
        this.browser.findElement(By.id("login-form")).submit();
        try {
            // aguarda meio segundo apenas para dar tempo de efetuar o login
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isPaginaDeLogin() {
        return this.browser.getCurrentUrl().equals(URL_LOGIN);
    }

    public String getNomeUsuerioLogado() {
        try {
            return this.browser.findElement(By.id("usuario-logado")).getText();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void irParaPaginaDeLances() {
        this.browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto) {
        return this.browser.getPageSource().contains(texto);
    }

    public boolean isPaginaDeLoginComDadosInvalidos() {
        return this.browser.getCurrentUrl().equals(URL_LOGIN + "?error");
    }
}
