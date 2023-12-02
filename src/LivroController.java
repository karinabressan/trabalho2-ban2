
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
import java.sql.SQLException;

public class LivroController {
    
    public void createLivro(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codlivro","livros", con);
        System.out.println("Insira os seguintes dados para adicionar um novo Livro: sugestao:"+sugestao+") ");
        System.out.print("Codigo do Livro: ");
        int codlivro = Integer.parseInt(input.nextLine());
        System.out.print("titulo: ");
        String titulo = input.nextLine();
        System.out.print("código autor: ");
        int codautor = Integer.parseInt(input.nextLine());
        System.out.print("código editora: ");
        int codeditora = Integer.parseInt(input.nextLine());
        System.out.print("código genero: ");
        int codgenero = Integer.parseInt(input.nextLine());
        System.out.print("Quantidade: ");
        int quantidade = Integer.parseInt(input.nextLine());
        LivroBean ab = new LivroBean(codlivro, titulo, codautor, codeditora, codgenero, quantidade);
        LivroModel.create(ab, con);
        System.out.println("Livro adicionado com sucesso!!");
    }

        
    public void updateLivro(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do livro que deseja atualizar: ");
        int codlivro = input.nextInt();

        // Verifica se o livro existe no banco de dados antes de prosseguir
        if (!LivroModel.exists(codlivro, con)) {
            System.out.println("Livro não encontrado no banco de dados.");
            return;
        }

        System.out.println("Insira os novos dados para atualizar o Livro: ");
        System.out.print("Novo título: ");
        String novoTitulo = input.next();
        System.out.print("Novo código do autor: ");
        int novoCodAutor = input.nextInt();
        System.out.print("Novo código da editora: ");
        int novoCodEditora = input.nextInt();
        System.out.print("Novo código do gênero: ");
        int novoCodGenero = input.nextInt();
        System.out.print("Quantidade: ");
        int novoQuantidade = input.nextInt();

        LivroBean novoLivro = new LivroBean(codlivro, novoTitulo,novoCodAutor, novoCodEditora, novoCodGenero, novoQuantidade);

        LivroModel.update(novoLivro, con);
        System.out.println("Livro atualizado com sucesso!!");
}

    public void deleteLivro(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do livro que deseja excluir: ");
        int codlivro = input.nextInt();

        // Verifica se o livro existe no banco de dados antes de prosseguir
        if (!LivroModel.exists(codlivro, con)) {
            System.out.println("Livro não encontrado no banco de dados.");
            return;
        }

        LivroModel.delete(codlivro, con);
}
    
    void listarLivro(Connection con) throws SQLException {
        HashSet all = LivroModel.listAll(con);
        Iterator<LivroBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
}
