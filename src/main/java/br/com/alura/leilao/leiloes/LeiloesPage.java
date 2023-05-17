package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage {
    private WebDriver browser;
    private static final String URL_LEILOES = "http://localhost:8080/leiloes";
    private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";


    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public void fechar() {
        this.browser.quit();
    }


    public CadastroLeilaoPage carregarFormulario() {
        this.browser.navigate().to(URL_CADASTRO_LEILAO);
        return new CadastroLeilaoPage(this.browser);
    }

    public boolean isLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
        WebElement ultimaLinhaDaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
        WebElement colunaNome = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunaDataAbertura = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunaValorInicial = ultimaLinhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

        return colunaNome.getText().equals(nome) &&
                colunaDataAbertura.getText().equals(dataAbertura) &&
                colunaValorInicial.getText().equals(valorInicial);
    }

    public boolean isPaginaAtual() {
        return this.browser.getCurrentUrl().equals(URL_LEILOES);
    }
}
