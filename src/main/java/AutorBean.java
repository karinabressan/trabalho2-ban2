
public class AutorBean {
    private int codautor;
    private String nomeautor;
   
   
   public AutorBean(int codautor, String nomeautor) {
       this.codautor  = codautor;
       this.nomeautor = nomeautor;
   }    
   
    public int getCodAutor() {
        return codautor;
    }

    public void setCodAutor(int codautor) {
        this.codautor = codautor;
    }   
   
    public String getNomeAutor() {
        return nomeautor;
    }

    public void setNomeAutor(String nomeautor) {
        this.nomeautor = nomeautor;
    }
    
    public String toString(){
        return "cod autor: "+codautor+" | nome autor : "+nomeautor;
    }
}
