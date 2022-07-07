import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexTest {

    private final String CPF_REGEX = "([\\d]{3}\\.[\\d]{3}\\.[\\d]{3}-[\\d]{2})|(\\d{11})|([\\d]{9}-[\\d]{2})";
    private final String CNPJ_REGEX = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}-\\d{2}$|^\\d{14}$";
    private final String NOME_REGEX = "^([A-Z][a-zÀ-ú]+)(\\s[A-Z][a-zÀ-ú]+|\\sd[aeo]s?)+$";

    @ParameterizedTest
    @ValueSource(strings = {"111.111.111-11", "11111111111", "111111111-11"})
    public void testCPFValido(String cpf) {
        Pattern pattern = Pattern.compile(CPF_REGEX);
        Matcher matcher = pattern.matcher(cpf);
        assertTrue(matcher.matches());
    }

    @Test
    public void testCPFInvalido() {
        var CPF = "111.111.111-1";
        Pattern pattern = Pattern.compile(CPF_REGEX);
        Matcher matcher = pattern.matcher(CPF);
        assertFalse(matcher.matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10.111.111/0001-11",  "11111111111111"})
    public void validaCNPJValido(String cnpj) {
        Pattern pattern = Pattern.compile(CNPJ_REGEX);
        Matcher matcher = pattern.matcher(cnpj);
        assertTrue(matcher.matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {"10.111.1110001-11",  "11.1111111111-11"})
    public void validaCNPJINvalido(String cnpj) {
        Pattern pattern = Pattern.compile(CNPJ_REGEX);
        Matcher matcher = pattern.matcher(cnpj);
        assertFalse(matcher.matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Guilherme da Silva", "Ze da Silva", "Vovô da Maça"})
    public void validaNomesValidos(String nome) {
        Pattern pattern = Pattern.compile(NOME_REGEX);
        Matcher matcher = pattern.matcher(nome);
        assertTrue(matcher.matches());
    }

    @ParameterizedTest
    @ValueSource(strings = {" Guilherme da Silva", "João da S"})
    public void validaNomesInvalidos(String nome) {
        Pattern pattern = Pattern.compile(NOME_REGEX);
        Matcher matcher = pattern.matcher(nome);
        assertFalse(matcher.matches());
    }

    //TODO: Implementar as regex de datas, telefone e e-mail conforme enunciado no class

}
