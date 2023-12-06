package nosql.neo4j;

import org.neo4j.driver.EagerResult;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class EmprestimoModel {

    public static void create(EmprestimoBean emprestimo, Conexao con) {
        con.getExecutableQuery("""
                CREATE(e:Emprestimo {codemprestimo: $codemprestimo,
                                          codlivro: $codlivro,
                                        codcliente: $codcliente,
                                   data_emprestimo: $data_emprestimo,
                                              dias: $dias})
                """).withParameters(Map.of( "codemprestimo",emprestimo.getCodEmprestimo()+0,
                                            "codlivro",         emprestimo.getCodLivro(),
                                            "codcliente",       emprestimo.getCodCliente(),
                                            "data_emprestimo",  emprestimo.getDataEmprestimo(),
                                            "dias",             emprestimo.getDias())).execute();
    }

    public static List<Map<String,Object>> listAll(Conexao con) {
        return con.query("""
            MATCH (emprestimo:Emprestimo) return emprestimo;
            """);
    }


    public static int update(EmprestimoBean novoEmprestimo, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(e:Emprestimo)
                WHERE e.codemprestimo = $codemprestimo
                SET e.codcliente = $codcliente,
                    e.codlivro = $codlivro,
                    e.dias =  $dias,
                    e.data_emprestimo = $data_emprestimo
                RETURN e
                """).withParameters(Map.of( "codemprestimo", novoEmprestimo.getCodEmprestimo()+0,
                                            "codcliente",       novoEmprestimo.getCodCliente(),
                                            "codlivro",         novoEmprestimo.getCodLivro(),
                                            "dias",             novoEmprestimo.getDias(),
                                            "data_emprestimo",  novoEmprestimo.getDataEmprestimo())).execute();
        return result.records().size();
    }
    

    public static int delete(int codemprestimo, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH (e:Emprestimo) 
                WHERE e.codemprestimo = $codemprestimo
                DETACH DELETE e
                RETURN e
                """).withParameters(Map.of("codemprestimo",codemprestimo)).execute();
        return result.records().size();

    }

    public static int devolucao1(int codemprestimo, String data_devolucao_real, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(e:Emprestimo)
                WHERE e.codemprestimo = $codemprestimo
                SET e.data_devolucao_real = $data_devolucao_real
                RETURN e
                """).withParameters(Map.of( "codemprestimo", codemprestimo+0,
                "data_devolucao_real",       data_devolucao_real)).execute();
        return result.records().size();
    }
    public static int devolucao2(int codlivro, int codcliente, String data_devolucao_real, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(e:Emprestimo)
                WHERE e.codlivro = $codlivro 
                and e.codcliente = $codcliente
                SET e.data_devolucao_real = $data_devolucao_real
                RETURN e
                """).withParameters(Map.of( "codlivro", codlivro+0,
                "codcliente", codcliente+0,
                "data_devolucao_real",       data_devolucao_real)).execute();
        return result.records().size();
    }

}

    

