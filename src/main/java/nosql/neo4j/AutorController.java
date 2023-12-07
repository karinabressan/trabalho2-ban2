package nosql.neo4j;

import org.neo4j.driver.exceptions.ClientException;

import java.sql.SQLException;
import java.util.*;

public class AutorController {

    public void createAutor(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codautor","Autor", con);
        System.out.println("Insira os seguintes dados para adicionar um novo autor sugestão(" + sugestao + ")");
        System.out.print("Codigo do Autor: ");
        int codautor = input.nextInt();
        System.out.print("autor: ");
        input = new Scanner(System.in);
        String nomeautor = input.nextLine();
        AutorBean eb = new AutorBean(codautor, nomeautor);
        AutorModel.create(eb, con);
        System.out.println("Autor adicionado com sucesso!!");
    }

    void listarAutor(Conexao con) throws SQLException {
        List<Map<String, Object>> all = AutorModel.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo do autor; Nome do autor");
        while (it.hasNext()) {
            var next = it.next();
            Map<String, Object> autor = (Map)next.get("autor");
            String nomeAutor = (String) autor.get("nomeautor");
            Long codigoAutor = (Long) autor.get("codautor");

            System.out.println(codigoAutor+";"+nomeAutor);
        }
    }
    
    public void updateAutor(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do autor que deseja atualizar: ");
        int codautor = input.nextInt();

        System.out.println("Insira os novos dados para atualizar o Autor: ");
        System.out.print("Novo nome autor: ");
        input = new Scanner(System.in);
        String novoNome = input.nextLine();

        AutorBean novoAutor = new AutorBean(codautor, novoNome);

        int amountUpdated = AutorModel.update(novoAutor, conexao);
        if (amountUpdated > 0) {
            System.out.println("Autor atualizado com sucesso!");
        } else {
            System.out.println("Nenhum autor encontrado com o código especificado.");
        }

    }
    
    public void deleteAutor(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do autor que deseja excluir: ");
        int codAutor = input.nextInt();

        try {
            int amountDeleted = AutorModel.delete(codAutor, conexao);
            if (amountDeleted > 0) {
                System.out.println("Autor excluido com sucesso!");
            } else {
                System.out.println("Nenhum autor encontrado com o código especificado.");
            }
        } catch (ClientException e) {
            if (e.code().equals("Neo.ClientError.Schema.ConstraintValidationFailed")) {
                System.out.println("Este autor esta sendo utilizado e não pode ser excluido");
            } else {
                throw e;
            }
        }
    }
    
    

}
