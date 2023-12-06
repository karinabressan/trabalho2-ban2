package nosql.neo4j;

public class RelGeneroBean {
    private String titulo;
    private String autor;
    private String editora; 
    
    public RelGeneroBean(String titulo, String autor, String editora){
        this.titulo = titulo; 
        this.autor = autor;
        this.editora = editora;
        
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
    
    public String toString(){
        return "Livro: "+titulo+" | Autor: "+autor+" | Editora: "+editora+" | ";
    }
}
