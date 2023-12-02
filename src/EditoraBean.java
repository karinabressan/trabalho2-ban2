
public class EditoraBean {
    private int codeditora;
    private String nomeeditora;
   
   
   public EditoraBean(int codeditora, String nomeeditora) {
       this.codeditora  = codeditora;
       this.nomeeditora = nomeeditora;
   }    
   

    public int getCodEditora() {
        return codeditora;
    }


    public void setCodEditora(int codeditora) {
        this.codeditora = codeditora;
    }   
   

    public String getNomeEditora() {
        return nomeeditora;
    }

    public void setNomeEditora(String nomeeditora) {
        this.nomeeditora = nomeeditora;
    }
    
    public String toString(){
        return "codigo editora: "+codeditora+" | nome editora: "+nomeeditora;
    }
}
