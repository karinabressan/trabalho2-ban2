package nosql.neo4j;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*MATCH (em:Emprestimo)-[:RESERVA_LIVRO]->(l:Livros)
MATCH (l)-[:ESCRITO_POR]->(a:Autor)
MATCH (l)-[:PUBLICADO_POR]->(e:Editora)
MATCH (l)-[:CONTEM_GENERO]->(g:Genero)
OPTIONAL MATCH (em)-[:EMPRESTA]->(c:Cliente)
WHERE em.data_emprestimo <= date() AND (em.data_devolucao_real IS NULL OR em.data_devolucao_real >= date())
RETURN l.codlivro, l.titulo, a.nomeautor, e.nomeeditora, g.nomegenero, em.data_emprestimo, em.dias, em.data_devolucao_real, c.id, c.nome
ORDER BY l.codlivro;*/
public class Relatorio3Model {



    public static List<Map<String, Object>> listAll(Conexao conexao) {
        List<Map<String, Object>> result = conexao.query("""
                MATCH (em:Emprestimo)-[:RESERVA_LIVRO]->(l:Livros)
                MATCH (l)-[:ESCRITO_POR]->(a:Autor)
                MATCH (l)-[:PUBLICADO_POR]->(e:Editora)
                MATCH (l)-[:CONTEM_GENERO]->(g:Genero)
                MATCH (em)-[:EMPRESTA]-(c:Cliente)
                WHERE em.data_devolucao_real ='NULL' 
                RETURN c.nome,  l.titulo, a.nomeautor, e.nomeeditora, g.nomegenero, em.data_emprestimo, em.dias
                ORDER BY l.codlivro;
                """);
        return result;
    }
    
}
