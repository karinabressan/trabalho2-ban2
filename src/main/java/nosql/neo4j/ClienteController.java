package nosql.neo4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;


public class ClienteController {
    public void createCliente(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("id","Cliente", con);
        System.out.println("Insira os seguintes dados para a criar um novo Cliente: sugestão "+sugestao+") ");
        System.out.print("codigo cliente: ");
        int id = input.nextInt();
        System.out.print("nome: ");
        input = new Scanner(System.in);
        String nome = input.next();
        System.out.print("e-mail: ");
        input = new Scanner(System.in);
        String email = input.next();
        System.out.print("telefone: ");
        input = new Scanner(System.in);
        String telefone = input.next();
        input = new Scanner(System.in);
        System.out.print("endereço: ");
        input = new Scanner(System.in);
        String endereco = input.next();

        
        ClienteBean mb = new ClienteBean(id, nome, email, telefone, endereco);
        ClienteModel.create(mb, con);
        System.out.println("Cliente criado com sucesso!!");     
    }

    void listarCliente(Conexao con) throws SQLException {
        List<Map<String, Object>> all = ClienteModel.listAll(con);
        Iterator<Map<String, Object>> it = all.iterator();
        System.out.println("Id; Nome ; telefone  ; endereço  ; email ");
        while (it.hasNext()) {
            var next = it.next();
            Map<String, Object> cliente = (Map)next.get("cliente");
            String nome = (String) cliente.get("nome");
            Long id = (Long) cliente.get("id");
            String telefone = (String) cliente.get("telefone");
            String endereco = (String) cliente.get("endereco");
            String email = (String) cliente.get("email");

            System.out.println(id+";"+nome+";"+telefone+";"+endereco+";"+email);
        }
    }

    public void updateCliente(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do cliente que deseja atualizar: ");
        int id = input.nextInt();



        System.out.println("Insira os novos dados para atualizar o Cliente: ");
        System.out.print("Nome Cliente: ");
        String novoNome = input.next();
        System.out.print("E-mail: ");
        String novoEmail = input.next();
        System.out.print("Telefone: ");
        String novoTelefone = input.next();
        System.out.print("Endereço: ");
        String novoEndereco = input.next();

        ClienteBean NovoCliente = new ClienteBean(id, novoNome, novoEmail, novoTelefone, novoEndereco);

        ClienteModel.update(NovoCliente, con);
        System.out.println("Cliente atualizado com sucesso!!");
}
    
    public void deleteCliente(Conexao con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do Cliente que deseja excluir: ");
        int id = input.nextInt();



        ClienteModel.delete(id, con);
    }

}