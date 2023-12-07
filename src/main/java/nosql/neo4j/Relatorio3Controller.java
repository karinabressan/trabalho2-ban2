package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Relatorio3Controller {
    public void relatoriolivro(Conexao con) throws SQLException{

        List<Map<String, Object>> all = Relatorio3Model.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Cliente ; livro; Autor; Editora; Genero ; Editora ; Data Emprestimo ; Dias ; ");
        while (it.hasNext()) {
            Map<String, Object> reportLine = it.next();
            // l.titulo, a.nomeautor, e.nomeeditora, g.nomegenero, em.data_emprestimo, em.dias, em.data_devolucao_real, c.nome
            String nome = (String) reportLine.get("c.nome");
            String titulo = (String) reportLine.get("l.titulo");
            String nomeautor = (String) reportLine.get("a.nomeautor");
            String nomeeditora = (String) reportLine.get("e.nomeeditora");
            String nomegenero = (String) reportLine.get("g.nomegenero");
            String data_emprestimo = (String) reportLine.get("em.data_emprestimo");
            Long dias = (Long) reportLine.get("em.dias");


            System.out.println(nome + ";" + titulo + ";" + nomeautor + ";" + nomeeditora + ";" + nomegenero + ";" + data_emprestimo + ";" + dias + ";");
        }
    }
}
