package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;


    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
    }


    @Test
    public void deveriaCadastrarLeilao() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioPaginaDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        CadastroLeilaoPage paginaDeCadastro = this.paginaDeLeiloes.carregarFormulario();

        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);

        try {
            // aguarda meio segundo apenas para dar tempo de carregar a pagina
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertTrue(this.paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));

    }
}
