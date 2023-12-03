package nosql.neo4j;

import org.neo4j.driver.exceptions.ClientException;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GeneroController {

    public void createGenero(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codgenero", "Genero", con);
        System.out.println("Insira os seguintes dados para adicionar um novo Genero: sugestão(" + sugestao + ") ");
        System.out.print("Codigo da Genero: ");
        int codigoGenero = input.nextInt();
        System.out.print("genero: ");
        input = new Scanner(System.in);
        String nomeGenero = input.nextLine();
        GeneroBean gb = new GeneroBean(codigoGenero, nomeGenero);
        GeneroModel.create(gb, con);
        System.out.println("Genero adicionada com sucesso!!");
    }

    void listarGenero(Conexao con) throws SQLException {
        List<Map<String, Object>> all = GeneroModel.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo do genero; Nome do genero");
        while (it.hasNext()) {
            var next = it.next();
            Map<String, Object> genero = (Map)next.get("genero");
            String nomeGenero = (String) genero.get("nomegenero");
            Long codigoGenero = (Long) genero.get("codgenero");

            System.out.println(codigoGenero+";"+nomeGenero);
        }
    }

    public void updateGenero(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do genero que deseja atualizar: ");
        int codgenero = input.nextInt();

        System.out.println("Insira os novos dados para atualizar o Genero: ");
        System.out.print("Novo nome genero: ");
        input = new Scanner(System.in);
        String novoNome = input.nextLine();


        GeneroBean novoGenero = new GeneroBean(codgenero, novoNome);

        int amountUpdated = GeneroModel.update(novoGenero, conexao);
        if (amountUpdated > 0) {
            System.out.println("Genero atualizado com sucesso!");
        } else {
            System.out.println("Nenhum genero encontrado com o código especificado.");
        }
    }

    public void deleteGenero(Conexao conexao) throws ClientException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do Genero que deseja excluir: ");
        int codigoGenero = input.nextInt();

        try {
            int amountDeleted = GeneroModel.delete(codigoGenero, conexao);
            if (amountDeleted > 0) {
                System.out.println("Genero excluido com sucesso!");
            } else {
                System.out.println("Nenhum genero encontrado com o código especificado.");
            }
        } catch (ClientException e) {
            if (e.code().equals("Neo.ClientError.Schema.ConstraintValidationFailed")) {
                System.out.println("Este genero esta sendo utilizado e não pode ser excluido");
            } else {
                throw e;
            }
        }

    }
}
