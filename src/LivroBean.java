
public class LivroBean {
   private int codlivro;
   private String titulo;
   private int codautor;
   private int codeditora;
   private int codgenero;
   private int quantidade;
   
   public LivroBean(int codlivro, String titulo, int codautor, int codeditora, int codgenero, int quantidade) {
       this.codlivro = codlivro;
       this.titulo= titulo;
       this.codautor = codautor;
       this.codeditora = codeditora;
       this.codgenero = codgenero;
       this.quantidade = quantidade; 
   }
    public int getCodLivro() {
        return codlivro;
    }

    public void setCodLivro(int codlivro) {
        this.codlivro = codlivro;
    }
    
    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCodAutor() {
        return codautor;
    }

    public void setCodAutor(int codautor) {
        this.codautor = codautor;
    }
    
    public int getCodEditora() {
        return codeditora;
    }


    public void setCodEditora(int codeditora) {
        this.codeditora = codeditora;
    }

    public int getCodGenero() {
        return codgenero;
    }

    public void setCodGenero(int codgenero) {
        this.codgenero = codgenero;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
   
    public String toString(){
        return "codigo: "+codlivro+" | titulo: "+titulo+" | Cod Autor: "+codautor+" | cod editora : "
                +codeditora+" | cod genero: "+codgenero+" | Quantidade: "+quantidade;
    }
}