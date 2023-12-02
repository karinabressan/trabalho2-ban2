import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GeneroModel {
    
    public static void create(GeneroBean a, Connection con) throws SQLException {
    PreparedStatement st;
        st = con.prepareStatement("INSERT INTO genero (codgenero, nomegenero) VALUES (?,?)");
        st.setInt(1, a.getCodGenero());
        st.setString(2, a.getNomeGenero());
        st.execute();
        st.close();  
    }
    public static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT codgenero, nomegenero FROM genero order by codgenero";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new GeneroBean(result.getInt(1), result.getString(2)));
            }
        return list;
    }
    public static void delete(int codgenero, Connection con) throws SQLException {
        String query = "DELETE FROM genero WHERE codgenero = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codgenero);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Genero excluido com sucesso!");
            } else {
                System.out.println("Nenhum genero encontrado com o código especificado.");
            }
        }
    }
        
    public static boolean exists(int codgenero, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM genero WHERE codgenero = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codgenero);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        
        return false;
    }
    
    public static void update(GeneroBean novoGenero, Connection con) throws SQLException {
        String query = "UPDATE genero SET nomegenero = ? WHERE codgenero = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, novoGenero.getNomeGenero());
            statement.setInt(2, novoGenero.getCodGenero());
            
            statement.executeUpdate();
        }
    }
}
