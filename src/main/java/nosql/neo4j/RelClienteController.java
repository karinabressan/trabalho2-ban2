package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class RelClienteController {

    public void relatoriocliente(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Selecione um cliente para listar:");
        int codigo  = input.nextInt();
        List<Map<String, Object>> all = RelClienteModel.listAll(codigo, con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo do cliente; Nome Cliente ; Livro ; Autor ; Editora ; Genero ; Data Emprestimo ; Dias com o Livro ; Data Devolução");
        while (it.hasNext()) {
            var next = it.next();
            Map<String, Object> RelCliente = (Map)next.get("RelCliente");
            Long id = (Long) RelCliente.get("id");
            String nome = (String) RelCliente.get("nome");
            String titulo = (String) RelCliente.get("titulo");
            String nomeautor = (String) RelCliente.get("nomeautor");
            String nomeeditora = (String) RelCliente.get("nomeeditora");
            String nomegenero = (String) RelCliente.get("nomegenero");
            String data_emprestimo = (String) RelCliente.get("data_emprestimo");
            String dias = (String) RelCliente.get("dias");
            String data_devolucao_real = (String) RelCliente.get("data_devolucao_real");

            System.out.println(id+";"+nome+";"+titulo+";"+nomeautor+";"+nomeeditora+";"+nomegenero+";"+data_emprestimo+";"+dias+";"+data_devolucao_real);
        }
    }
}
