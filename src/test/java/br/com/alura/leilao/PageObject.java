package br.com.alura.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageObject {
    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.gecko.driver", "src/main/java/br/com/alura/leilao/drivers/geckodriver");
        if(browser == null) {
            this.browser = new FirefoxDriver();
        }else{
            this.browser = browser;
        }
    }

    public void fechar() {
        this.browser.quit();
    }
}
