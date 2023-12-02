import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class RelGeneroController {
    public void relatoriogenero(Connection con) throws SQLException{
        Scanner input = new Scanner(System.in);
            System.out.println("Selecione um genero para listar:");
            HashSet allgenero = GeneroModel.listAll(con);
            Iterator<GeneroBean> itgenero = allgenero.iterator();
            while(itgenero.hasNext()) {
                System.out.println(itgenero.next().toString());
            }
            int codgen = Integer.parseInt(input.nextLine());   
            HashSet all = RelGeneroModel.relatoriogenero(codgen, con);
            Iterator<RelGeneroBean> it = all.iterator();
            while(it.hasNext()) {
                System.out.println(it.next().toString());
            } 
       
    }
}
