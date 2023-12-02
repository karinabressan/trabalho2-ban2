import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class RelGeneroModel {
    
    public static HashSet relatoriogenero(int codgenero, Connection con) throws SQLException{
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
        return list;
        
        
    }
    
}
