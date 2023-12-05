package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class LivroController {
    
    public void createLivro(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codlivro","Livros", con);
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
        LivroModel.createrel(ab, con);
        System.out.println("Livro adicionado com sucesso!!");
    }

        
    public void updateLivro(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do livro que deseja atualizar: ");
        int codlivro = input.nextInt();

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

        LivroModel.deleterel(codlivro, con);
        LivroModel.update(novoLivro, con);
        LivroModel.createrel(novoLivro, con);
        System.out.println("Livro atualizado com sucesso!!");
}

    public void deleteLivro(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do livro que deseja excluir: ");
        int codlivro = input.nextInt();


        //LivroModel.deleterel(codlivro, con);
        LivroModel.delete(codlivro, con);
}
    

    void listarLivro(Conexao con) throws SQLException {
        List<Map<String, Object>> all = LivroModel.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo do livro; titulo; codigo autor ;" +
                "codigo genero ;codigo editora ;quantidade");
        while (it.hasNext()) {
            var next = it.next();
            Map<String, Object> livro = (Map)next.get("livro");
            String titulo = (String) livro.get("titulo");
            Long codlivro = (Long) livro.get("codlivro");
            Long codautor = (Long) livro.get("codautor");
            Long codgenero = (Long) livro.get("codgenero");
            Long codeditora = (Long) livro.get("codeditora");
            Long quantidade = (Long) livro.get("quantidade");


            System.out.println(codlivro+";"+titulo+";"+codautor+";"+codgenero+";"+codeditora+";"+quantidade);
        }
    }
}
