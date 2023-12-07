package nosql.neo4j;

import java.util.*;
import java.util.stream.Collectors;

public class RelGeneroModel {
    
    public static List<Map<String, Object>> listAll(int codigoGenero, Conexao conexao) {
        List<Map<String, Object>> result = conexao.getExecutableQuery("""
                MATCH(g:Genero)<-[:CONTEM_GENERO]-(l:Livros)
                MATCH(l)-[:ESCRITO_POR]->(a:Autor)
                MATCH(l)-[:PUBLICADO_POR]->(e:Editora)
                WHERE g.codgenero = $id1
                RETURN l.titulo, a.nomeautor, e.nomeeditora;
                """).withParameters(Map.of("id1", codigoGenero)).execute(Collectors.mapping(record -> record.asMap(),Collectors.toList()));
        return result;

    }
    
}
