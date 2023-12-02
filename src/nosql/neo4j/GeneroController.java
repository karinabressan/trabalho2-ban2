package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class GeneroController {
    
    public void createGenero(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codgenero","genero", con);
        System.out.println("Insira os seguintes dados para adicionar um novo Genero: sugestão("+sugestao+") ");
        System.out.print("Codigo da Genero: ");
        int codgenero = input.nextInt();
        System.out.print("genero: ");
        String nomegenero = input.next();
        GeneroBean gb = new GeneroBean(codgenero, nomegenero);
        GeneroModel.create(gb, con);
        System.out.println("Genero adicionada com sucesso!!");
    }
 
    void listarGenero(Connection con) throws SQLException {
        HashSet all = GeneroModel.listAll(con);
        Iterator<GeneroBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    public void updateGenero(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do genero que deseja atualizar: ");
        int codgenero = input.nextInt();

        // Verifica se o genero existe no banco de dados antes de prosseguir
        if (!GeneroModel.exists(codgenero, con)) {
            System.out.println("Genero não encontrado no banco de dados.");
            return;
        }

        System.out.println("Insira os novos dados para atualizar o Genero: ");
        System.out.print("Novo nome genero: ");
        String novoNome = input.next();


        GeneroBean novoGenero = new GeneroBean(codgenero, novoNome);

        GeneroModel.update(novoGenero, con);
        System.out.println("Genero atualizado com sucesso!!");
}
    
    public void deleteGenero(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do Genero que deseja excluir: ");
        int codgenero = input.nextInt();

        // Verifica se a genero existe no banco de dados antes de prosseguir
        if (!GeneroModel.exists(codgenero, con)) {
            System.out.println("Genero não encontrado no banco de dados.");
            return;
        }

        GeneroModel.delete(codgenero, con);
    }
}
