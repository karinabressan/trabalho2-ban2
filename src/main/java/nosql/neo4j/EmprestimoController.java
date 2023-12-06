package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

public class EmprestimoController {
    // Método para criar um novo empréstimo
    public void createEmprestimo(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codemprestimo","Emprestimo", con);
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
        String dataemprestimo = date.validData();
        EmprestimoBean mb = new EmprestimoBean(codemprestimo,codcliente,codlivro,dataemprestimo,dias,dataemprestimo );
        EmprestimoModel.create(mb, con);
        System.out.println("Empréstimo adicionado com sucesso!!");
    }

    // Método para atualizar um empréstimo existente
    public void updateEmprestimo(Conexao con) throws SQLException  {
        Scanner input = new Scanner(System.in);
        String datadevreal = new String();
        
        System.out.print("Digite o código do Emprestimo que deseja atualizar: ");
        int codemprestimo = Integer.parseInt(input.nextLine());

        
        System.out.println("Insira os novos dados para atualizar o Emprestimo: ");
        System.out.print("Código Livro ");
        int codlivro = Integer.parseInt(input.nextLine());
        System.out.print("Código Cliente ");
        int codcliente = Integer.parseInt(input.nextLine());
        System.out.print("Dias que ficará com o livro ");
        int dias = Integer.parseInt(input.nextLine());
        System.out.print("Data Emprestimo");
        String dataemprestimo = date.validData();

        
        
        EmprestimoBean novoEmprestimo = new EmprestimoBean(codemprestimo, codcliente,codlivro, dataemprestimo, dias, datadevreal);

        EmprestimoModel.update(novoEmprestimo, con);
        System.out.println("Emprestimo atualizado com sucesso!!");
    }

    // Método para excluir um empréstimo
    public void deleteEmprestimo(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do Emprestimo que deseja excluir: ");
        int id = Integer.parseInt(input.nextLine());

        EmprestimoModel.delete(id, con);
    }
    
    // Método para excluir um empréstimo
    public void devolucaoEmprestimo(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.println("Deseja fazer a devolução por: ");
        System.out.println("1- Código do Empréstimo ");
        System.out.println("2- Código do Livro + Cliente ");
        int opc = Integer.parseInt(input.nextLine());
        switch (opc){
            case 1: // Devolução por codigo do emprestimo
                System.out.println("Insira o código do emprestimo: ");
                int codemprestimo = Integer.parseInt(input.nextLine());


                System.out.println("Data devolução: (dd/mm/yyyy)");
                String datadev = date.validData();

                EmprestimoModel.devolucao1(codemprestimo, datadev, con);
                System.out.println("Devolução concluida ");
                break; 
            case 2: // Devolução por Livro e Cliente 
                System.out.println("Insira o código do Livro: ");
                int codlivro = Integer.parseInt(input.nextLine());


                System.out.println("Insira o código do Cliete: ");
                int codcliente = Integer.parseInt(input.nextLine());


                System.out.println("Data devolução: (dd/mm/yyyy)");
                String datadev2 = date.validData();
                EmprestimoModel.devolucao2(codlivro, codcliente,datadev2, con);
                System.out.println("Devolução concluida ");
                break;    
        }

    }


    void listarEmprestimo(Conexao con) throws SQLException {
        List<Map<String, Object>> all = EmprestimoModel.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Codigo do emprestimo; Código livro; Codigo cliente; Data Emprestimo ;" +
                "Dias com o livro ; Data devolução");
        while (it.hasNext()) {
            var next = it.next();
            Map<String, Object> emprestimo = (Map)next.get("emprestimo");
            Long codemprestimo = (Long) emprestimo.get("codemprestimo");
            Long codlivro = (Long) emprestimo.get("codlivro");
            Long codcliente = (Long) emprestimo.get("codcliente");
            String data_emprestimo = (String) emprestimo.get("data_emprestimo");
            Long dias = (Long) emprestimo.get("dias");
            String data_devolucao_real = (String) emprestimo.get("data_devolucao_real");

            System.out.println(codemprestimo+";"+codlivro+";"+codcliente+";"+data_emprestimo+";"+dias+";"+data_devolucao_real);
        }
    }
    
}
