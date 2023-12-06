package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;


public class RelClienteController {

    public void relatoriocliente(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Selecione um cliente para listar:");
        int codigo  = input.nextInt();
        List<Map<String, Object>> all = RelClienteModel.listAll(codigo, con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo do cliente; Nome Cliente; Livro; Autor; Editora; Genero; Data Emprestimo; Dias com o Livro; Data Devolução");
        while (it.hasNext()) {
            Map<String, Object> reportLine = it.next();
            Long id = (Long) reportLine.get("c.id");
            String nome = (String) reportLine.get("c.nome");
            String titulo = (String) reportLine.get("l.titulo");
            String nomeautor = (String) reportLine.get("a.nomeautor");
            String nomeeditora = (String) reportLine.get("e.nomeeditora");
            String nomegenero = (String) reportLine.get("g.nomegenero");
            ZonedDateTime data_emprestimo = (ZonedDateTime) reportLine.get("em.data_emprestimo");
            Long dias = (Long) reportLine.get("em.dias");
            String data_devolucao_real = (String) reportLine.get("em.data_devolucao_real");

            System.out.println(id+";"+nome+";"+titulo+";"+nomeautor+";"+nomeeditora+";"+nomegenero+";"+data_emprestimo+";"+dias+";"+data_devolucao_real);
        }
    }
}
