import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class Relatorio3Model {
    
    public static HashSet relatoriolivro( Connection con) throws SQLException{
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql  = "select l.codlivro, l.titulo, a.nomeautor, e.nomeeditora, " +
                        "	g.nomegenero, em.data_emprestimo, em.dias ," +
                        "	em.data_devolucao_real, c.id, c.nome " +
                        "	from emprestimo em join livros l on " +
                        "	em.codlivro = l.codlivro join autor a on " +
                        "	l.codautor = a.codautor join editora e on " +
                        "	l.codeditora = e.codeditora join genero g on " +
                        "	l.codgenero = g.codgenero join cliente c on " +
                        "	c.id = em.codcliente " +
                        "where " +
                        "	em.data_emprestimo <= current_date and " +
                        "	em.data_devolucao_real is null " +
                        "       order by l.codlivro ";
                
                ResultSet result = st.executeQuery(sql);
        while(result.next()) {
                list.add(new Relatorio3Bean(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getDate(6),
                        result.getInt(7),
                        result.getDate(8),
                        result.getInt(9),
                        result.getString(10)));
            }
        return list;
        
        
    }
    
}
