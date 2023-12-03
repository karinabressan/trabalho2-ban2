package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class AutorController {

    public void createAutor(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codautor","autor", conexao);
        System.out.println("Insira os seguintes dados para adicionar um novo autor sugestão(" + sugestao + ")");
        System.out.print("Codigo do Autor: ");
        int codautor = input.nextInt();
        System.out.print("autor: ");
        String nomeautor = input.next();
        AutorBean eb = new AutorBean(codautor, nomeautor);
//        AutorModel.create(eb, conexao);
        System.out.println("Autor adicionado com sucesso!!");
    }
    
    public void updateAutor(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do autor que deseja atualizar: ");
        int codautor = input.nextInt();

//        if (!AutorModel.exists(codautor, conexao)) {
//            System.out.println("Autor não encontrado no banco de dados.");
//            return;
//        }

        System.out.println("Insira os novos dados para atualizar o Autor: ");
        System.out.print("Novo nome do autor: ");
        String novoNome = input.next();


        AutorBean novoAutor = new AutorBean(codautor, novoNome);

//        AutorModel.update(novoAutor, conexao);
        System.out.println("Autor atualizado com sucesso!!");
}
    
    public void deleteAutor(Conexao conexao) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do autor que deseja excluir: ");
        int codautor = input.nextInt();

//        if (!AutorModel.exists(codautor, conexao)) {
//            System.out.println("Autor não encontrado no banco de dados.");
//            return;
//        }

//        AutorModel.delete(codautor, conexao);
}
    
    
    void listarAutor(Conexao conexao) throws SQLException {
        HashSet all = AutorModel.listAll(conexao);
//        Iterator<AutorBean> it = all.iterator();
//        while(it.hasNext()) {
//            System.out.println(it.next().toString());
//        }
    }
}
