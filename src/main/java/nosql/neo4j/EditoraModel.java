package nosql.neo4j;

import org.neo4j.driver.EagerResult;

import java.util.List;
import java.util.Map;

public class EditoraModel {


    public static void create(EditoraBean editora, Conexao con) {
        con.getExecutableQuery("""
                CREATE(:Editora {codeditora: $codeditora, nomeeditora: $nomeeditora})
                """).withParameters(Map.of("codeditora",editora.getCodEditora()+0, "nomeeditora", editora.getNomeEditora())).execute();
    }


    public static List<Map<String,Object>> listAll(Conexao con) {
        return con.query("""
                MATCH (editora:Editora) return editora;
                """);
    }


    public static int delete(int codeditora, Conexao con) {

        EagerResult result = con.getExecutableQuery("""
                MATCH (g:Editora) 
                WHERE g.codeditora = $codeditora
                DELETE g
                RETURN g
                """).withParameters(Map.of("codeditora",codeditora)).execute();
        return result.records().size();
    }

    public static int update(EditoraBean novaEditora, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(g:Editora)
                WHERE g.codeditora = $codeditora
                SET g.nomeeditora = $nomeeditora
                RETURN g
                """).withParameters(Map.of("codeditora", novaEditora.getCodEditora()+0, "nomeeditora", novaEditora.getNomeEditora())).execute();
        return result.records().size();
    }
}
