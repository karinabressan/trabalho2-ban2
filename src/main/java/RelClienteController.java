import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class RelClienteController {
    public void relatoriocliente(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);
            System.out.println("Selecione um cliente para listar:");
            HashSet allcliente = ClienteModel.listAll(con);
            Iterator<ClienteBean> itcliente = allcliente.iterator();
            while(itcliente.hasNext()) {
                System.out.println(itcliente.next().toString());
            }
            int codcliente = Integer.parseInt(input.nextLine());   
            HashSet all = RelClienteModel.relatoriocliente(codcliente, con);
            Iterator<RelClienteBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            } 
       
    }
}
