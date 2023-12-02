
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutorController {

    public void createAutor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codautor","autor", con);
        System.out.println("Insira os seguintes dados para adicionar um novo autor sugestão(" + sugestao + ")");
        System.out.print("Codigo do Autor: ");
        int codautor = input.nextInt();
        System.out.print("autor: ");
        String nomeautor = input.next();
        AutorBean eb = new AutorBean(codautor, nomeautor);
        AutorModel.create(eb, con);
        System.out.println("Autor adicionado com sucesso!!");
    }
    
    public void updateAutor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do autor que deseja atualizar: ");
        int codautor = input.nextInt();

        if (!AutorModel.exists(codautor, con)) {
            System.out.println("Autor não encontrado no banco de dados.");
            return;
        }

        System.out.println("Insira os novos dados para atualizar o Autor: ");
        System.out.print("Novo nome do autor: ");
        String novoNome = input.next();


        AutorBean novoAutor = new AutorBean(codautor, novoNome);

        AutorModel.update(novoAutor, con);
        System.out.println("Autor atualizado com sucesso!!");
}
    
    public void deleteAutor(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do autor que deseja excluir: ");
        int codautor = input.nextInt();

        if (!AutorModel.exists(codautor, con)) {
            System.out.println("Autor não encontrado no banco de dados.");
            return;
        }

        AutorModel.delete(codautor, con);
}
    
    
    void listarAutor(Connection con) throws SQLException {
        HashSet all = AutorModel.listAll(con);
        Iterator<AutorBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}
