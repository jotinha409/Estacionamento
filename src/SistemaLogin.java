import java.util.HashMap;
import java.util.Map;

public class SistemaLogin {
    private Map<String, String> usuariosCadastrados = new HashMap<>();

    public boolean cadastrarUsuario(String nomeUsuario, String senha) {
        if (usuariosCadastrados.containsKey(nomeUsuario)) {
            return false;
        }
        usuariosCadastrados.put(nomeUsuario, senha);
        return true;
    }

    public boolean autenticar(String nomeUsuario, String senha) {
        return senha.equals(usuariosCadastrados.get(nomeUsuario));
    }
}
