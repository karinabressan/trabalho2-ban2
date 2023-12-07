package nosql.neo4j;

import org.neo4j.driver.Record;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class date {

    public static String validData(){
        String dataString = new String();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formato2 = new SimpleDateFormat("yyyy-MM-dd");

        System.out.print(" Digite uma data no formato dd/MM/yyyy: ");
        if (scanner.hasNextLine()) { // Verifica se há uma próxima linha
            dataString = scanner.nextLine(); // Lê a linha se houver uma próxima
            //System.out.println("Você digitou: " + dataString);
        } else {
            System.out.println("Nenhuma linha foi digitada.");
        }
        try {
            Date data = formato.parse(dataString);
            String datapronta = formato2.format(data);
            scanner.close();
            return datapronta;
        } catch (ParseException e) {
            System.err.println("Formato de data inválido. Certifique-se de usar o formato dd/MM/yyyy.");
            scanner.close();
            return "-1";
        }
    }
    public static Date dataTeclado() {
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

    public static int sugerecodigo(String campo, String tabela, Conexao con) throws SQLException {
        Record max = con.getExecutableQuery("MATCH(x:"+tabela+") return max(x."+campo+")").execute().records().get(0);
        return max.get(0).asInt()+1;
    }

}
