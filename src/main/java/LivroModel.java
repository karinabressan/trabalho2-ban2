
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class LivroModel {

    public static void create(LivroBean a, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO livros (codlivro, titulo, codautor, codeditora, codgenero, quantidade) VALUES (?,?,?,?,?,?)");
            st.setInt(1,a.getCodLivro());
            st.setString(2, a.getTitulo());
            st.setInt(3, a.getCodAutor());
            st.setInt(4, a.getCodEditora());
            st.setInt(5, a.getCodGenero());
            st.setInt(6, a.getQuantidade());
            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT codlivro, titulo, codautor, codeditora, codgenero, quantidade FROM livros order by codlivro";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new LivroBean(result.getInt(1),
                        result.getString(2),
                        result.getInt(3), 
                        result.getInt(4), 
                        result.getInt(5),
                        result.getInt(6)));
            }
        return list;
    }
    
    
    public static boolean exists(int codlivro, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM livros WHERE codlivro = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codlivro);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        
        return false;
    }
        
    public static void update(LivroBean novoLivro, Connection con) throws SQLException {
        String query = "UPDATE livros SET titulo = ?, codautor = ?, codeditora = ?, codgenero = ?, quantidade = ? WHERE codlivro = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, novoLivro.getTitulo());
            statement.setInt(2, novoLivro.getCodAutor());
            statement.setInt(3, novoLivro.getCodEditora());
            statement.setInt(4, novoLivro.getCodGenero());
            statement.setInt(5, novoLivro.getQuantidade());
            statement.setInt(6, novoLivro.getCodLivro());
            
            statement.executeUpdate();
        }
    }
    
    public static void delete(int codlivro, Connection con) throws SQLException {
        String query = "DELETE FROM livros WHERE codlivro = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codlivro);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Livro excluído com sucesso!");
            } else {
                System.out.println("Nenhum livro encontrado com o código especificado.");
            }
        }
    }
}

    

