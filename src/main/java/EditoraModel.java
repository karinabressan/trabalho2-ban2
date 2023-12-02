import java.sql.*;
import java.util.HashSet;

public class EditoraModel {

    public static void create(EditoraBean a, Connection con) throws SQLException {
        try (PreparedStatement st = con.prepareStatement("INSERT INTO editora (codeditora, nomeeditora) VALUES (?,?)")) {
            st.setInt(1, a.getCodEditora());
            st.setString(2, a.getNomeEditora());
            st.execute();
        }
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
        st = con.createStatement();
        String sql = "SELECT codeditora, nomeeditora FROM editora order by codeditora";
        ResultSet result = st.executeQuery(sql);
        while (result.next()) {
            list.add(new EditoraBean(result.getInt(1), result.getString(2)));
        }
        return list;
    }

    public static void delete(int codeditora, Connection con) throws SQLException {
        String query = "DELETE FROM editora WHERE codeditora = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codeditora);

            int rowsDeleted = statement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Editora excluída com sucesso!");
            } else {
                System.out.println("Nenhuma editora encontrada com o código especificado.");
            }
        }
    }

    public static boolean exists(int codeditora, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM editora WHERE codeditora = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codeditora);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }

        return false;
    }

    public static void update(EditoraBean novaEditora, Connection con) throws SQLException {
        String query = "UPDATE editora SET nomeeditora = ? WHERE codeditora = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, novaEditora.getNomeEditora());
            statement.setInt(2, novaEditora.getCodEditora());

            statement.executeUpdate();
        }
    }
}
