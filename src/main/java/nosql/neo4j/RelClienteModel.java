package nosql.neo4j;

import java.util.*;
import java.util.stream.Collectors;


public class RelClienteModel {


    public static List<Map<String, Object>> listAll(int codigoCliente, Conexao conexao) {
        List<Map<String, Object>> result = conexao.getExecutableQuery("""
                MATCH (c:Cliente)-[:EMPRESTA]->(em:Emprestimo)-[:RESERVA_LIVRO]->(l:Livros)
                MATCH (l)-[:ESCRITO_POR]->(a:Autor)
                MATCH (l)-[:PUBLICADO_POR]->(e:Editora)
                MATCH (l)-[:CONTEM_GENERO]->(g:Genero)
                WHERE c.id = $id1
                RETURN c.id, c.nome, l.titulo, a.nomeautor, e.nomeeditora, g.nomegenero, em.data_emprestimo, em.dias, em.data_devolucao_real
                ORDER BY c.id;
                """).withParameters(Map.of("id1", codigoCliente)).execute(Collectors.mapping(record -> record.asMap(),Collectors.toList()));
        return result;
    }

}