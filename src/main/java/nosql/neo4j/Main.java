package nosql.neo4j;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Conexao conexao = new Conexao();

        int op = 0;
        do {
            op = menu();
            try {
                switch (op) {
                    case 1: //Livro
                        op = menu2();
                        switch (op){
                            case 1: new LivroController().createLivro(conexao); //incluir
                                break;
                            case 2: new LivroController().updateLivro(conexao);// alterar
                                break;
                            case 3: new LivroController().listarLivro(conexao);// visualizar
                                break;
                            case 4: new LivroController().deleteLivro(conexao);// deletar
                                break;
                        }
                        break;
                    case 2:// Editora
                        op = menu2();
                        switch (op){
                            case 1: new EditoraController().createEditora(conexao); //incluir
                                break;
                            case 2: new EditoraController().updateEditora(conexao);// alterar
                                break;
                            case 3: new EditoraController().listarEditora(conexao);// visualizar
                                break;
                            case 4: new EditoraController().deleteEditora(conexao);// deletar
                                break;
                        }
                        break;
                    case 3: // Genero
                        op = menu2();
                        switch (op) {
                            case 1:
                                new GeneroController().createGenero(conexao); //incluir 2
                                break;
                            case 2:
                                new GeneroController().updateGenero(conexao);// alterar 4
                                break;
                            case 3:
                                new GeneroController().listarGenero(conexao);// visualizar 1
                                break;
                            case 4:
                                new GeneroController().deleteGenero(conexao);// deletar 3
                                break;
                        }
                        break;
                    case 4: // Cliente
                        op = menu2();
                        switch (op){
                            case 1: new ClienteController().createCliente(conexao); //incluir
                                break;
                            case 2: new ClienteController().updateCliente(conexao);// alterar
                                break;
                            case 3: new ClienteController().listarCliente(conexao);// visualizar
                                break;
                            case 4: new ClienteController().deleteCliente(conexao);// deletar
                                break;
                        }
                        break;
                    case 5: //Autor
                        op = menu2();
                        switch (op){
                            case 1: new AutorController().createAutor(conexao); //incluir
                                break;
                            case 2: new AutorController().updateAutor(conexao);// alterar
                                break;
                            case 3: new AutorController().listarAutor(conexao);// visualizar
                                break;
                            case 4: new AutorController().deleteAutor(conexao);// deletar
                                break;
                        }
                   case 6: //Emprestimo
                        op = menuEmprestimo();
                        switch (op){
                            case 1: new EmprestimoController().createEmprestimo(conexao); //incluir
                                break;
                            case 2: new EmprestimoController().updateEmprestimo(conexao);// alterar
                                break;
                            case 3: new EmprestimoController().listarEmprestimo(conexao);// visualizar
                                break;
                            case 4: new EmprestimoController().deleteEmprestimo(conexao);// deletar
                                break;
                            case 5: new EmprestimoController().devolucaoEmprestimo(conexao);// devolução
                                break;
                        }
                    case 7://relatorios
                        op = menuRelatorio();
                        switch(op){
                           // case 1: new RelGeneroController().relatoriogenero(conexao);
                             //   break;
                            case 2: new RelClienteController().relatoriocliente(conexao);
                                break;
                           // case 3: new Relatorio3Controller().relatoriolivro(conexao);
                            //    break;
                        }
                }
            } catch (SQLException ex) {
                //ex.printStackTrace();
                System.out.println(ex.getMessage());
                continue;
            }
        } while (op >= 0 && op < 9);

    }

    private static int menu() {
        int nret = 99;
        System.out.println("");
        System.out.println("Informe o número da opção que desejas executar: ");
        System.out.println("1 - Livro");
        System.out.println("2 - Editora");
        System.out.println("3 - Genero");
        System.out.println("4 - Clientes");
        System.out.println("5 - Autor");
        System.out.println("6 - Empréstimos");
        System.out.println("7 - Relatórios");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        if (input.hasNextLine()) {
            nret = Integer.parseInt(input.nextLine());
        }
        return nret;
    }

    private static int menu2() {
        int nret = 99;
        System.out.println("");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Visualizar");
        System.out.println("4 - Excluir");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        if (input.hasNextLine()) {
            nret = Integer.parseInt(input.nextLine());
        }
        return nret;
    }

    private static int menuEmprestimo() {

        int nret = 0;
        System.out.println("");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 - Incluir");
        System.out.println("2 - Alterar");
        System.out.println("3 - Visualizar");
        System.out.println("4 - Excluir");
        System.out.println("5 - Devolução do emprestimo");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        if (input.hasNextLine()) {
            nret = Integer.parseInt(input.nextLine());
        }
        return nret;
    }

    private static int menuRelatorio() {

        int nret = 0;
        System.out.println("");
        System.out.println("Informe o número da opção que deseja executar: ");
        System.out.println("1 - Livros por genero");
        System.out.println("2 - Clientes por livro");
        System.out.println("3 - Livros fora da biblioteca");
        System.out.println("Digite qualquer outro valor para sair");
        System.out.print("Sua opção: ");
        Scanner input = new Scanner(System.in);
        if (input.hasNextLine()) {
            nret = Integer.parseInt(input.nextLine());
        }
        return nret;
    }
}
