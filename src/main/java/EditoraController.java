
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

public class EditoraController {

    public void createEditora(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codeditora","editora", con);
        System.out.println("Insira os seguintes dados para adicionar uma nova Editora: sugestão("+sugestao+") ");
        System.out.print("Codigo da Editora: ");
        int codeditora = input.nextInt();
        System.out.print("editora: ");
        String nomeeditora = input.next();
        EditoraBean eb = new EditoraBean(codeditora, nomeeditora);
        EditoraModel.create(eb, con);
        System.out.println("Editora adicionada com sucesso!!");
    }
    
    public void updateEditora(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código da editora que deseja atualizar: ");
        int codeditora = input.nextInt();

        // Verifica se a editora existe no banco de dados antes de prosseguir
        if (!EditoraModel.exists(codeditora, con)) {
            System.out.println("Editora não encontrada no banco de dados.");
            return;
        }

        System.out.println("Insira os novos dados para atualizar a Editora: ");
        System.out.print("Novo nome editora: ");
        String novoNome = input.next();


        EditoraBean novaEditora = new EditoraBean(codeditora, novoNome);

        EditoraModel.update(novaEditora, con);
        System.out.println("Editora atualizada com sucesso!!");
}
    
    public void deleteEditora(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código da editora que deseja excluir: ");
        int codeditora = input.nextInt();

        // Verifica se a editora existe no banco de dados antes de prosseguir
        if (!EditoraModel.exists(codeditora, con)) {
            System.out.println("Editora não encontrada no banco de dados.");
            return;
        }

        EditoraModel.delete(codeditora, con);
}
    
    
    void listarEditora(Connection con) throws SQLException {
        HashSet all = EditoraModel.listAll(con);
        Iterator<EditoraBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}
