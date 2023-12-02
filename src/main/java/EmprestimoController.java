
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Date;
import java.util.Scanner;

public class EmprestimoController {
    // Método para criar um novo empréstimo
    public void createEmprestimo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codemprestimo","emprestimo", con);
        System.out.println("Novo Empréstimo: ");
        System.out.print("Codigo do Emprestimo (sugestão:"+sugestao+"): ");
        int codemprestimo = Integer.parseInt(input.nextLine());
        System.out.print("Codigo do Cliente: ");
        int codcliente = Integer.parseInt(input.nextLine());
        System.out.print("Codigo do Livro: ");
        int codlivro  = Integer.parseInt(input.nextLine());
        System.out.print("Quantos dias ficara com o livro: ");
        int dias = Integer.parseInt(input.nextLine());
        System.out.print("Data do emprestimo ");
        Date dataemprestimo = date.dataTeclado();
        EmprestimoBean mb = new EmprestimoBean(codemprestimo,codcliente,codlivro,dataemprestimo,dias,dataemprestimo );
        EmprestimoModel.create(mb, con);
        System.out.println("Empréstimo adicionado com sucesso!!");
    }

    // Método para atualizar um empréstimo existente
    public void updateEmprestimo(Connection con) throws SQLException  {
        Scanner input = new Scanner(System.in);
        Date datadevreal = new Date();
        
        System.out.print("Digite o código do Emprestimo que deseja atualizar: ");
        int codemprestimo = Integer.parseInt(input.nextLine());
        
        if (!EmprestimoModel.exists(codemprestimo, con)) {
            System.out.println("Emprestimo não encontrado no banco de dados.");
            return;
        }
        
        System.out.println("Insira os novos dados para atualizar o Emprestimo: ");
        System.out.print("Código Livro ");
        int codlivro = Integer.parseInt(input.nextLine());
        System.out.print("Código Cliente ");
        int codcliente = Integer.parseInt(input.nextLine());
        System.out.print("Dias que ficará com o livro ");
        int dias = Integer.parseInt(input.nextLine());
        System.out.print("Data Emprestimo");
        Date dataemprestimo = date.dataTeclado();

        
        
        EmprestimoBean novoEmprestimo = new EmprestimoBean(codemprestimo, codcliente,codlivro, dataemprestimo, dias, datadevreal);

        EmprestimoModel.update(novoEmprestimo, con);
        System.out.println("Emprestimo atualizado com sucesso!!");
    }

    // Método para excluir um empréstimo
    public void deleteEmprestimo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do Emprestimo que deseja excluir: ");
        int id = Integer.parseInt(input.nextLine());

        // Verifica se a genero existe no banco de dados antes de prosseguir
        if (!EmprestimoModel.exists(id, con)) {
            System.out.println("Emprestimo não encontrado no banco de dados.");
            return;
        }

        EmprestimoModel.delete(id, con);
    }
    
    // Método para excluir um empréstimo
    public void devolucaoEmprestimo(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.println("Deseja fazer a devolução por: ");
        System.out.println("1- Código do Empréstimo ");
        System.out.println("2- Código do Livro + Cliente ");
        int opc = Integer.parseInt(input.nextLine());
        switch (opc){
            case 1: // Devolução por codigo do emprestimo
                System.out.println("Insira o código do emprestimo: ");
                int codemprestimo = Integer.parseInt(input.nextLine());

                // Verifica se a genero existe no banco de dados antes de prosseguir
                if (!EmprestimoModel.exists(codemprestimo, con)) {
                    System.out.println("Emprestimo não encontrado no banco de dados.");
                    return;
                }
                System.out.println("Data devolução: (dd/mm/yyyy)");
                Date datadev = date.dataTeclado();
                Date dataDevReal = datadev;
                java.sql.Date sqlDataDevReal = new java.sql.Date(dataDevReal.getTime());
                EmprestimoModel.devolucao1(codemprestimo, sqlDataDevReal, con);
                break; 
            case 2: // Devolução por Livro e Cliente 
                System.out.println("Insira o código do Livro: ");
                int codlivro = Integer.parseInt(input.nextLine());

                // Verifica se a genero existe no banco de dados antes de prosseguir
                if (!LivroModel.exists(codlivro, con)) {
                    System.out.println("Livro não encontrado no banco de dados.");
                    return;
                }
                System.out.println("Insira o código do Cliete: ");
                int codcliente = Integer.parseInt(input.nextLine());

                // Verifica se a genero existe no banco de dados antes de prosseguir
                if (!ClienteModel.exists(codcliente, con)) {
                    System.out.println("Cliente não encontrado no banco de dados.");
                    return;
                }  
                System.out.println("Data devolução: (dd/mm/yyyy)");
                Date datadev2 = date.dataTeclado();
                Date dataDevReal2 = datadev2;
                java.sql.Date sqlDataDevReal2 = new java.sql.Date(dataDevReal2.getTime());
                EmprestimoModel.devolucao2(codlivro, codcliente,sqlDataDevReal2, con);
                break;    
        }

    }

    void listarEmprestimo(Connection con) throws SQLException {
                  
        HashSet all = EmprestimoModel.listAll(con);
        Iterator<EmprestimoBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
                    
    }
    
}
