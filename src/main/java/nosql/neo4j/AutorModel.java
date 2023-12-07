package nosql.neo4j;

import org.neo4j.driver.EagerResult;

import java.util.List;
import java.util.Map;

public class AutorModel {

    public static void create(AutorBean autor, Conexao con) {
        con.getExecutableQuery("""
                CREATE(g:Autor {codautor: $codautor, nomeautor: $nomeautor})
                """).withParameters(Map.of("codautor",autor.getCodAutor()+0, "nomeautor", autor.getNomeAutor())).execute();
    }

    public static List<Map<String,Object>> listAll(Conexao con) {
        return con.query("""
                MATCH (autor:Autor) return autor;
                """);
    }


    public static int delete(int codigoAutor, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH (a:Autor) 
                WHERE a.codautor = $codautor
                DELETE a
                RETURN a
                """).withParameters(Map.of("codautor",codigoAutor)).execute();
        return result.records().size();

    }

    public static int update(AutorBean novoAutor, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(a:Autor)
                WHERE a.codautor = $codautor
                SET a.nomeautor = $nomeautor
                RETURN a
                """).withParameters(Map.of("codautor", novoAutor.getCodAutor()+0, "nomeautor", novoAutor.getNomeAutor())).execute();
        return result.records().size();
    }
}
