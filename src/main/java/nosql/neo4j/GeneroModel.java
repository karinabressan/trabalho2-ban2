package nosql.neo4j;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class GeneroModel {

    public static void create(GeneroBean a, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO genero (codgenero, nomegenero) VALUES (?,?)");
        st.setInt(1, a.getCodGenero());
        st.setString(2, a.getNomeGenero());
        st.execute();
        st.close();
    }

    public static List<Map<String,Object>> listAll(Conexao con) throws SQLException {
       return con.query("""
                MATCH (g:Genero) return g;
                """);
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
