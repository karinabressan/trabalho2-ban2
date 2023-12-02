
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;


public class ClienteController {
    public void createCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);
        int sugestao = date.sugerecodigo("codcliente","cliente", con);
        System.out.println("Insira os seguintes dados para a criar um novo Cliente: sugestão "+sugestao+") ");
        System.out.print("codigo cliente: ");
        int id = input.nextInt();
        System.out.print("nome: ");
        String nome = input.next();          
        System.out.print("e-mail: ");
        String email = input.next();  
        System.out.print("telefone: ");
        String telefone = input.next();  
        System.out.print("enreço: ");
        String endereco = input.next();  

        
        ClienteBean mb = new ClienteBean(id, nome, email, telefone, endereco);
        ClienteModel.create(mb, con);
        System.out.println("Cliente criado com sucesso!!");     
    }

    void listarCliente(Connection con) throws SQLException {
        HashSet all = ClienteModel.listAll(con);
        Iterator<ClienteBean> it = all.iterator();
        while(it.hasNext()) {
            System.out.println(it.next().toString());
        }
    }
    public void updateCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do cliente que deseja atualizar: ");
        int id = input.nextInt();

        // Verifica se o genero existe no banco de dados antes de prosseguir
        if (!ClienteModel.exists(id, con)) {
            System.out.println("Cliente não encontrado no banco de dados.");
            return;
        }

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
    
    public void deleteCliente(Connection con) throws SQLException {
        Scanner input = new Scanner(System.in);

        System.out.print("Digite o código do Cliente que deseja excluir: ");
        int id = input.nextInt();

        // Verifica se a genero existe no banco de dados antes de prosseguir
        if (!ClienteModel.exists(id, con)) {
            System.out.println("Cliente não encontrado no banco de dados.");
            return;
        }

        ClienteModel.delete(id, con);
    }

}