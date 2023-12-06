package nosql.neo4j;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Value;

import java.util.*;



public class RelClienteModel {


    public static List<Map<String, Object>> listAll(int id, Conexao con) {
        int codcliente = id;

        try (Session session = con.getDriver().session()) {
            Result result = session.run("""
                    MATCH (c:Cliente)-[:EMPRESTA]->(em:Emprestimo)-[:RESERVA_LIVRO]->(l:Livros)
                    MATCH (l)-[:ESCRITO_POR]->(a:Autor)
                    MATCH (l)-[:PUBLICADO_POR]->(e:Editora)
                    MATCH (l)-[:CONTEM_GENERO]->(g:Genero)
                    WHERE c.id = $id1
                    RETURN c.id, c.nome, l.titulo, a.nomeautor, e.nomeeditora, g.nomegenero, em.data_emprestimo, em.dias, em.data_devolucao_real
                    ORDER BY c.id;
                    """, Map.of("id1", codcliente));

            List<Map<String, Object>> resultList = new ArrayList<>();

            while (result.hasNext()) {
                Record record = result.next();
                Map<String, Object> resultMap = new HashMap<>();

                resultMap.put("id", record.get("c.id").asInt());
                resultMap.put("nome", record.get("c.nome").asString());
                resultMap.put("titulo", record.get("l.titulo").asString());
                resultMap.put("nomeautor", record.get("a.nomeautor").asString());
                resultMap.put("nomeeditora", record.get("e.nomeeditora").asString());
                resultMap.put("nomegenero", record.get("g.nomegenero").asString());
                resultMap.put("data_emprestimo", record.get("em.data_emprestimo").asString());
                resultMap.put("dias", record.get("em.dias").asInt());
                resultMap.put("data_devolucao_real", record.get("em.data_devolucao_real").asString());

                resultList.add(resultMap);
            }

            return resultList;
        }
    }
}