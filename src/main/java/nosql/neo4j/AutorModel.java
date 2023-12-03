package nosql.neo4j;

import java.sql.*;
import java.util.HashSet;

public class AutorModel {

    public static void create(AutorBean a, Connection con) throws SQLException {
        PreparedStatement st;
        st = con.prepareStatement("INSERT INTO autor (codautor, nomeautor) VALUES (?,?)");
        st.setInt(1, a.getCodAutor());
        st.setString(2, a.getNomeAutor());
        st.execute();
        st.close();
    }

    public static HashSet listAll(Conexao conexao)  {
        return null;
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
