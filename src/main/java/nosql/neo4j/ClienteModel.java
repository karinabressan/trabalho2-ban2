package nosql.neo4j;

import org.neo4j.driver.EagerResult;
import java.util.List;
import java.util.Map;


public class ClienteModel {


    public static void create(ClienteBean cliente, Conexao con) {
        con.getExecutableQuery("""
                CREATE(:Cliente {id: $id, nome: $nome, email: $email, telefone: $telefone, endereco: $endereco})
                """).withParameters(Map.of("id",cliente.getId()+0, "nome", cliente.getNome(), "email",
                cliente.getEmail(), "telefone", cliente.getTelefone(), "endereco", cliente.getEndereco() )).execute();
    }

    public static List<Map<String,Object>> listAll(Conexao con) {
        return con.query("""
                MATCH (cliente:Cliente) return cliente;
                """);
    }


    public static int delete(int id, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH (c:Cliente) 
                WHERE c.id = $id
                DELETE c
                RETURN c
                """).withParameters(Map.of("id",id)).execute();
        return result.records().size();

    }


    public static int update(ClienteBean novoCliente, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(c:Cliente)
                WHERE c.id = $id
                SET c.nome = $nome,
                    c.telefone = $telefone,
                    c.email = $email,
                    c.endereco = $endereco
                RETURN c
                """).withParameters(Map.of("id", novoCliente.getId()+0, "nome", novoCliente.getNome(),
                "telefone", novoCliente.getTelefone(), "email", novoCliente.getEmail(), "endereco",
                novoCliente.getEndereco())).execute();
        return result.records().size();
    }
}
