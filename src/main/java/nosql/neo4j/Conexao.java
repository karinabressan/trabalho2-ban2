package nosql.neo4j;

import org.neo4j.driver.*;

import java.sql.Connection;
import java.util.*;
import java.util.stream.Collectors;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rebeca
 */
public class Conexao {
    private Connection con;
    private QueryConfig queryConfig;

    private final Driver driver;

    private static String NEO4J_URL="neo4j+s://53af1b6f.databases.neo4j.io";
    private static final String NEO4J_USER = "neo4j";

    
    public Conexao() {
        String neo4jPassword = System.getenv("NEO4J_PASSWORD");
        this.driver = GraphDatabase.driver(
                NEO4J_URL,
                AuthTokens.basic(NEO4J_USER, neo4jPassword));
        var builder = QueryConfig.builder();
        this.queryConfig = builder.build();
        Runtime.getRuntime().addShutdownHook(new Thread(driver::close));
    }

    public ExecutableQuery getExecutableQuery(String query){
        return this.driver.executableQuery(query).withConfig(this.queryConfig);
    }

//    public Map<String, Object> graph(int limit) {
//        var result = query(
//                """
//                  MATCH (m:Movie)<-[:ACTED_IN]-(a:Person)
//                  RETURN m.title as movie, collect(a.name) as cast
//                  LIMIT $limit""",
//                Map.of("limit", limit));
//
//        var nodes = new ArrayList<>();
//        var rels = new ArrayList<Map<String, Object>>();
//        var i = 0;
//        for (var row : result) {
//            nodes.add(Map.of("title", row.get("movie"), "label", "movie"));
//            var target = i;
//            i++;
//            for (Object name : (Collection<?>) row.get("cast")) {
//                var actor = Map.of("title", name, "label", "actor");
//                var source = nodes.indexOf(actor);
//                if (source == -1) {
//                    nodes.add(actor);
//                    source = i++;
//                }
//                rels.add(Map.of("source", source, "target", target));
//            }
//        }
//        return Map.of("nodes", nodes, "links", rels);
//    }

    public List<Map<String, Object>> query(String query) {
        if (query == null || query.trim().isEmpty()) return Collections.emptyList();
        return driver.executableQuery(query)
                .withConfig(queryConfig)
                .execute(Collectors.mapping(r -> r.asMap(Conexao::convert), Collectors.toList()));
    }

    private static Object convert(Value value) {
        return switch (value.type().name()) {
            case "PATH" -> value.asList(Conexao::convert);
            case "NODE", "RELATIONSHIP" -> value.asMap();
            default -> value.asObject();
        };
    }

}
