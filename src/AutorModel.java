import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorModel {
    
    public static void create(AutorBean a, Connection con) throws SQLException {
    PreparedStatement st;
        st = con.prepareStatement("INSERT INTO autor (codautor, nomeautor) VALUES (?,?)");
        st.setInt(1, a.getCodAutor());
        st.setString(2, a.getNomeAutor());
        st.execute();
        st.close();  
    }
        static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT codautor, nomeautor FROM autor order by codautor";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new AutorBean(result.getInt(1), result.getString(2)));
            }
        return list;
    }
        
    public static void delete(int codautor, Connection con) throws SQLException {
        String query = "DELETE FROM autor WHERE codautor = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codautor);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Autor excluído com sucesso!");
            } else {
                System.out.println("Nenhum autor encontrado com o código especificado.");
            }
        }
    }
        
    public static boolean exists(int codautor, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM autor WHERE codautor = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codautor);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        
        return false;
    }
    
    public static void update(AutorBean novoAutor, Connection con) throws SQLException {
        String query = "UPDATE autor SET nomeautor = ? WHERE codautor = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, novoAutor.getNomeAutor());
            statement.setInt(2, novoAutor.getCodAutor());
            
            statement.executeUpdate();
        }
    }
}
