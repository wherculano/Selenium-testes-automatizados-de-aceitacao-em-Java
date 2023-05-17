package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage paginaDeLeiloes;
    private CadastroLeilaoPage paginaDeCadastro;

    @BeforeEach
    public void beforeEach() {
        LoginPage paginaDeLogin = new LoginPage();
        paginaDeLogin.preencheFormularioPaginaDeLogin("fulano", "pass");
        this.paginaDeLeiloes = paginaDeLogin.efetuaLogin();
        this.paginaDeCadastro = this.paginaDeLeiloes.carregarFormulario();
    }

    @AfterEach
    public void afterEach() {
        this.paginaDeLeiloes.fechar();
    }


    @Test
    public void deveriaCadastrarLeilao() {
        String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String nome = "Leilao do dia " + hoje;
        String valor = "500.00";

        this.paginaDeLeiloes = this.paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
        esperaMeioSegundo();
        Assertions.assertTrue(this.paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));

    }

    @Test
    public void deveriaValidarCadastroDeLeilao() {
        this.paginaDeLeiloes = this.paginaDeCadastro.cadastrarLeilao("", "", "");
        esperaMeioSegundo();
        Assertions.assertFalse(this.paginaDeCadastro.isPaginaAtual());
        Assertions.assertTrue(this.paginaDeLeiloes.isPaginaAtual());
        Assertions.assertTrue(this.paginaDeCadastro.isMensagensDeValidacaoVisiveis());
    }

    private static void esperaMeioSegundo() {
        try {
            // aguarda meio segundo apenas para dar tempo de carregar a pagina
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
