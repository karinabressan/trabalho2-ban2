
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClienteModel {

    static void create(ClienteBean m, Connection con) throws SQLException {
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO cliente (id, nome, email, telefone, endereco) " + "VALUES (?,?,?,?,?)");
            st.setInt(1, m.getId());
            st.setString(2, m.getNome());
            st.setString(3, m.getEmail());
            st.setString(4, m.getTelefone());
            st.setString(5, m.getEndereco());
            st.execute();
            st.close();
    }
    
    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT id, nome, email, telefone, endereco FROM cliente order by id";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new ClienteBean(result.getInt(1), result.getString(2),result.getString(3),result.getString(4),result.getString(5)));
            }
        return list;
    }    
    public static void delete(int id, Connection con) throws SQLException {
        String query = "DELETE FROM cliente WHERE id = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Cliente excluído com sucesso!");
            } else {
                System.out.println("Nenhum cliente encontrado com o código especificado.");
            }
        }
    }
        
    public static boolean exists(int id, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM cliente WHERE id = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        
        return false;
    }
    
    public static void update(ClienteBean NovoCliente, Connection con) throws SQLException {
        String query = "UPDATE cliente SET nome = ?, email = ?, telefone = ? , endereco = ? WHERE id = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, NovoCliente.getNome());
            statement.setString(2, NovoCliente.getEmail());
            statement.setString(3, NovoCliente.getTelefone());
            statement.setString(4, NovoCliente.getEndereco());
            statement.setInt(5, NovoCliente.getId());
            
            statement.executeUpdate();
        }
    }
}
