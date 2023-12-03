package nosql.neo4j;

import org.neo4j.driver.EagerResult;
import org.neo4j.driver.Result;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GeneroModel {

    public static void create(GeneroBean genero, Conexao con) {
        con.getExecutableQuery("""
                CREATE(g:Genero {codgenero: $codgenero, nomegenero: $nomegenero})
                """).withParameters(Map.of("codgenero",genero.getCodGenero()+0, "nomegenero", genero.getNomeGenero())).execute();
    }

    public static List<Map<String,Object>> listAll(Conexao con) {
       return con.query("""
                MATCH (genero:Genero) return genero;
                """);
    }

    public static int delete(int codigoGenero, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH (g:Genero) 
                WHERE g.codgenero = $codgenero
                DELETE g
                RETURN g
                """).withParameters(Map.of("codgenero",codigoGenero)).execute();
        return result.records().size();

    }

    public static int update(GeneroBean novoGenero, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(g:Genero)
                WHERE g.codgenero = $codgenero
                SET g.nomegenero = $nomegenero
                RETURN g
                """).withParameters(Map.of("codgenero", novoGenero.getCodGenero()+0, "nomegenero", novoGenero.getNomeGenero())).execute();
        return result.records().size();
    }
}
