package nosql.neo4j;

//import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

/*
public class RelClienteModel {
    
    public static HashSet relatorioCliente(int codCliente, Conexao con){
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String neo4j  = "select c.id, c.nome , l.titulo, a.nomeautor, e.nomeeditora, "
                + "g.nomegenero, em.data_emprestimo, em.dias , "
                + "em.data_devolucao_real "
                + "from cliente c join emprestimo em "
                + "on c.id = em.codcliente join livros l on "
                + "em.codlivro = l.codlivro join autor a on "
                + "l.codautor = a.codautor join editora e on "
                + "l.codeditora = e.codeditora join genero g on "
                + "l.codgenero = g.codgenero "
                + "where c.id = " + codCliente + " "
                + " order by c.id  ";
                
                ResultSet result = st.executeQuery(neo4j);
        while(result.next()) {
                list.add(new RelClienteBean(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getDate(7),
                        result.getInt(8),
                        result.getDate(9)));
            }
        return list;
        
        
    }
    
}*/
