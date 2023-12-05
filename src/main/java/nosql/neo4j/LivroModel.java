package nosql.neo4j;

import org.neo4j.driver.EagerResult;

import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class LivroModel {

    public static void createrel(LivroBean livro, Conexao con) {
        con.getExecutableQuery("""
                MATCH (livro:Livros {codlivro: $codlivro})
                MATCH (editora:Editora {codeditora: $codeditora})
                MATCH (genero:Genero {codgenero: $codgenero})
                MATCH (autor:Autor {codautor: $codautor})
                CREATE (livro)-[:PUBLICADO_POR]->(editora)
                CREATE (livro)-[:CONTEM_GENERO]->(genero)
                CREATE (livro)-[:ESCRITO_POR]->(autor)
                """).withParameters(Map.of("codlivro",livro.getCodLivro()+0,
                "codautor", livro.getCodAutor(),
                "codgenero", livro.getCodGenero(),
                "codeditora", livro.getCodEditora())).execute();

    }
    public static void create(LivroBean livro, Conexao con) {
        con.getExecutableQuery("""
                CREATE(:Livros {codlivro: $codlivro,
                                titulo: $titulo,
                                codautor: $codautor,
                                codgenero: $codgenero,
                                codeditora: $codeditora,
                                quantidade: $quantidade})
                """).withParameters(Map.of("codlivro",livro.getCodLivro()+0,
                                            "titulo", livro.getTitulo(),
                                            "codautor", livro.getCodAutor(),
                                            "codgenero", livro.getCodGenero(),
                                            "codeditora", livro.getCodEditora(),
                                            "quantidade", livro.getQuantidade())).execute();
    }


    public static List<Map<String,Object>> listAll(Conexao con) {
        return con.query("""
                MATCH (livro:Livros) return livro;
                """);
    }


    public static int update(LivroBean novoLivro, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH(l:Livros)
                WHERE l.codlivro = $codlivro
                SET l.titulo = $titulo,
                    l.codautor = $codautor,
                    l.codeditora = $codeditora,
                    l.codgenero = $codgenero,
                    l.quantidade = $quantidade
                RETURN l
                """).withParameters(Map.of("codlivro", novoLivro.getCodLivro()+0,
                                            "titulo", novoLivro.getTitulo(),
                                            "codautor", novoLivro.getCodAutor(),
                                            "codeditora", novoLivro.getCodEditora(),
                                            "codgenero", novoLivro.getCodGenero(),
                                            "quantidade", novoLivro.getQuantidade())).execute();
        return result.records().size();
    }
    public static void deleterel(int codlivro, Conexao con) {
        con.getExecutableQuery("""
                MATCH (livro:Livros {codlivro: $codlivro})-[rel]->() DELETE rel
                """).withParameters(Map.of("codlivro",codlivro)).execute();

    }

    public static int delete(int codlivro, Conexao con) {
        EagerResult result = con.getExecutableQuery("""
                MATCH (l:Livros) 
                WHERE l.codlivro = $codlivro
                DETACH DELETE l
                RETURN l
                """).withParameters(Map.of("codlivro",codlivro)).execute();
        return result.records().size();
    }

}

    

