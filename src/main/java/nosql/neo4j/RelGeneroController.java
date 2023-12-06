package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.*;



public class RelGeneroController {
    public void relatoriogenero(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Selecione um genero para listar:");
        int codigo = input.nextInt();
        List<Map<String, Object>> all = RelGeneroModel.listAll(codigo, con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Titulo do livro; Nome do autor; Nome da editora; Autor; Editora");
        while (it.hasNext()) {
            Map<String, Object> reportLine = it.next();
            String titulo = (String) reportLine.get("l.titulo");
            String nomeautor = (String) reportLine.get("a.nomeautor");
            String nomeeditora = (String) reportLine.get("e.nomeeditora");

            System.out.println(titulo + ";" + nomeautor + ";" + nomeeditora + ";");


        }
    }
}
