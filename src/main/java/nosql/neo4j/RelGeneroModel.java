package nosql.neo4j;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

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






    /*HashSet relatoriogenero(int codgenero, Connection con) throws SQLException{
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql  = "Select l.titulo, a.nomeautor, e.nomeeditora From genero g"
                + " join livros l on g.codgenero = l.codgenero join autor a "
                + "on l.codautor = a.codautor join editora e on l.codeditora = e.codeditora ";
                if (codgenero > 0){
                    sql += "Where g.codgenero = "+codgenero+" ";
                }
                
                ResultSet result = st.executeQuery(sql);
        while(result.next()) {
            list.add(new RelGeneroBean(result.getString(1),result.getString(2),result.getString(3)));
            }
        return list; */
        
        
    }
    
}
