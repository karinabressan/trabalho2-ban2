package nosql.neo4j;

import org.neo4j.driver.exceptions.ClientException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EditoraController {

    public void createEditora(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codeditora","Editora", con);
        System.out.println("Insira os seguintes dados para adicionar uma nova Editora: sugestão("+sugestao+") ");
        System.out.print("Codigo da Editora: ");
        int codeditora = input.nextInt();
        System.out.print("editora: ");
        String nomeeditora = input.next();
        EditoraBean eb = new EditoraBean(codeditora, nomeeditora);
        EditoraModel.create(eb, con);
        System.out.println("Editora adicionada com sucesso!!");
    }
    
    public void updateEditora(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código da editora que deseja atualizar: ");
        int codeditora = input.nextInt();


        System.out.println("Insira os novos dados para atualizar a Editora: ");
        System.out.print("Novo nome editora: ");
        String novoNome = input.next();


        EditoraBean novaEditora = new EditoraBean(codeditora, novoNome);

        EditoraModel.update(novaEditora, conexao);
        System.out.println("Editora atualizada com sucesso!!");
}
    
    public void deleteEditora(Conexao conexao) throws ClientException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código da editora que deseja excluir: ");
        int codeditora = input.nextInt();

        try {
            int amountDeleted = EditoraModel.delete(codeditora, conexao);
            if (amountDeleted > 0) {
                System.out.println("Editora excluido com sucesso!");
            } else {
                System.out.println("Nenhuma editora encontrado com o código especificado.");
            }
        } catch (ClientException e) {
            if (e.code().equals("Neo.ClientError.Schema.ConstraintValidationFailed")) {
                System.out.println("Esta editora esta sendo utilizada e não pode ser excluida");
            } else {
                throw e;
            }
        }
}
    
    
    void listarEditora(Conexao con) throws SQLException {
        List<Map<String, Object>> all = EditoraModel.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo editora; Nome da editora");
        while(it.hasNext()) {
            var next = it.next();
            Map<String, Object> editora = (Map)next.get("editora");
            String nomeeditora = (String) editora.get("nomeeditora");
            Long codeditora = (Long) editora.get("codeditora");

            System.out.println(codeditora+";"+nomeeditora);
        }
    }
}
