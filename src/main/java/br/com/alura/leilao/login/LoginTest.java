package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage paginaDeLogin;


    @BeforeEach
    public void beforeEach() {
        this.paginaDeLogin = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLogin.fechar();
    }

    @Test
    public void deveriaEfetuarLoginComDadosValidos() {
        paginaDeLogin.preencheFormularioPaginaDeLogin("fulano", "pass");
        paginaDeLogin.efetuaLogin();

        Assertions.assertFalse(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertEquals("fulano", paginaDeLogin.getNomeUsuerioLogado());
    }

    @Test
    public void naoDeveriaEfutuarLoginComDadosInvalidos() {
        paginaDeLogin.preencheFormularioPaginaDeLogin("userQualquer", "senhaInvalida");
        paginaDeLogin.efetuaLogin();

        Assertions.assertTrue(paginaDeLogin.isPaginaDeLoginComDadosInvalidos());
        Assertions.assertNull(paginaDeLogin.getNomeUsuerioLogado());
        Assertions.assertTrue(paginaDeLogin.contemTexto("Usuário e senha inválidos."));
    }

    @Test
    public void naoDeveriaAcessarPaginaRestritaSemEstarLogado() {
        paginaDeLogin.irParaPaginaDeLances();
        Assertions.assertTrue(paginaDeLogin.isPaginaDeLogin());
        Assertions.assertFalse(paginaDeLogin.contemTexto("Dados do Leilão"));
    }
}
