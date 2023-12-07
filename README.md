trabalhoban2
## Biblioteca em banco de dados em modelo em grafo

Alunas: Karina e Maiara
------

1. Criar uma instância de banco de dados no Neo4J em: https://neo4j.com/cloud/platform/aura-graph-database/?ref=nav-get-started-cta
2. No Neo4j, faça a importação do banco de dados e das tabelas através dos backups que estão 
na pasta: trabalho2-ban2/backups/neo4j/.
O backup do banco (data-importer-2023-12-07.zip) pode ser importado na opção Import > ... > Open model (with data). Na seção "Files", utilizar a opção Browse para selecionar os arquivos .csv correspondentes as tabelas.
3. No Windows, configurar uma variável de ambiente com o nome "NEO4J_PASSWORD", com o conteúdo da senha
que será gerada para a instância do seu banco no Neo4J. No arquivo Conexao.java (em:\src\main\java\nosql\neo4j), alterar o conteúdo da variável URL para a URL da sua instância do banco (NEO4J_URI).
4. Clonar o repositório do projeto e executar o programa principal (em: src\main\java\nosql\neo4j).

------

