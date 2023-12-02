package nosql.neo4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class date {
        public static Date datateclado() {
        Date data1 = new Date();
        String dataString = new String();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Digite uma data no formato dd/MM/yyyy: ");
        
        if (scanner.hasNextLine()) { // Verifica se há uma próxima linha
            dataString = scanner.nextLine(); // Lê a linha se houver uma próxima
            System.out.println("Você digitou: " + dataString);
        } else {
            System.out.println("Nenhuma linha foi digitada.");
        }

        try {
            Date data = formato.parse(dataString);
          //  System.out.println("Data lida: " + formato.format(data));
            scanner.close();
            return data;
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.");
        } finally {
            scanner.close();
        }
        scanner.close();
        return data1;
    }
        
    public static int sugerecodigo(String campo, String tabela, Connection con) throws SQLException {
        int codigo = 0;
        String query = "SELECT MAX("+campo+") FROM "+tabela+" ";
        
        try (PreparedStatement statement = con.prepareStatement(query)) {
    
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    codigo = resultSet.getInt(1);
                    return codigo + 1;
                }
            }
        }
            
        return codigo;
    }
    
}
