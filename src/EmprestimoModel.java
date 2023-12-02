
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;



public class EmprestimoModel {

    public static void create(EmprestimoBean a, Connection con) throws SQLException {
        
        PreparedStatement st;
            st = con.prepareStatement("INSERT INTO emprestimo (codemprestimo, codlivro, codcliente, data_emprestimo, dias) VALUES (?,?,?,?,?)");
            st.setInt(1,a.getCodEmprestimo());
            st.setInt(2, a.getCodLivro());
            st.setInt(3, a.getCodCliente());
            
            // Converte java.util.Date para java.sql.Date
            java.util.Date dataEmprestimo = a.getDataEmprestimo();
            Date sqlDataEmprestimo = new Date(dataEmprestimo.getTime());
            st.setDate(4, sqlDataEmprestimo);
            st.setInt(5, a.getDias());

            st.execute();
            st.close();  
    }

    static HashSet listAll(Connection con) throws SQLException {
        Statement st;
        HashSet list = new HashSet();
            st = con.createStatement();
            String sql = "SELECT codemprestimo, codlivro, codcliente, data_emprestimo, dias, data_devolucao_real FROM emprestimo order by codemprestimo";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                list.add(new EmprestimoBean(result.getInt(1),
                        result.getInt(2),
                        result.getInt(3), 
                        result.getDate(4),
                        result.getInt(5),
                        result.getDate(6)));
            }
        return list;
    }
    
    public static boolean exists(int codemprestimo, Connection con) throws SQLException {
        String query = "SELECT COUNT(*) FROM emprestimo WHERE codemprestimo = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codemprestimo);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }
        
        return false;
    }
        
    public static void update(EmprestimoBean novoEmprestimo, Connection con) throws SQLException {
        String query = "UPDATE emprestimo SET codcliente = ?, codlivro = ?, dias = ?, data_emprestimo = ? WHERE codemprestimo = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, novoEmprestimo.getCodCliente());
            statement.setInt(2, novoEmprestimo.getCodLivro());
            statement.setInt(3, novoEmprestimo.getDias());
            
             // Converte java.util.Date para java.sql.Date
            java.util.Date dataEmprestimo = novoEmprestimo.getDataEmprestimo();
            Date sqlDataEmprestimo = new Date(dataEmprestimo.getTime());
            statement.setDate(4, sqlDataEmprestimo);
            
            
            statement.setInt(5, novoEmprestimo.getCodEmprestimo());
            statement.executeUpdate();
        }
    }
    
    public static void delete(int codemprestimo, Connection con) throws SQLException {
        String query = "DELETE FROM emprestimo WHERE codemprestimo = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, codemprestimo);
            
            int rowsDeleted = statement.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Emprestimo excluído com sucesso!");
            } else {
                System.out.println("Nenhum emprestimo encontrado com o código especificado.");
            }
        }
    }
    public static void devolucao1(int codemprestimo, Date datadevo, Connection con) throws SQLException {
        String query = "UPDATE emprestimo SET data_devolucao_real = ?  WHERE codemprestimo = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            java.util.Date dataDevReal = datadevo;
            Date sqlDataDevReal = new Date(dataDevReal.getTime());
            statement.setDate(1, sqlDataDevReal);            
            statement.setInt(2, codemprestimo);
            
            int rowsUpdate = statement.executeUpdate();
            
            if (rowsUpdate > 0) {
                System.out.println("Emprestimo devolvido com sucesso!");
            } else {
                System.out.println("Nenhum emprestimo encontrado com o código especificado.");
            }
        }
    }
    
    public static void devolucao2(int codlivro, int codcliente, Date datadevo, Connection con) throws SQLException {
        String query = "UPDATE emprestimo SET data_devolucao_real = ?  WHERE codlivro = ? and codcliente = ?";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
            java.util.Date dataDevReal = datadevo;
            Date sqlDataDevReal = new Date(dataDevReal.getTime());
            statement.setDate(1, sqlDataDevReal);            
            statement.setInt(2, codlivro);
            statement.setInt(3, codcliente);
            
            int rowsUpdate = statement.executeUpdate();
            
            if (rowsUpdate > 0) {
                System.out.println("Emprestimo devolvido com sucesso!");
            } else {
                System.out.println("Nenhum emprestimo encontrado com o código especificado.");
            }
        }
    }
}

    

