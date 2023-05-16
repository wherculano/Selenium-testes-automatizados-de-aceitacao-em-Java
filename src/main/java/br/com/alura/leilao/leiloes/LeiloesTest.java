package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

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
    }
}
