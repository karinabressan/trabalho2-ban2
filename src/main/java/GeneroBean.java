
public class GeneroBean {
    private int codgenero;
    private String nomegenero;
   
   
   public GeneroBean(int codgenero, String nomegenero) {
       this.codgenero  = codgenero;
       this.nomegenero = nomegenero;
   }    
   
    /**
     * @return the codgenero
     */
    public int getCodGenero() {
        return codgenero;
    }

    /**
     * @param codgenero the codgenero to set
     */
    public void setCodGenero(int codgenero) {
        this.codgenero = codgenero;
    }   
   
   
   /**
     * @return the nomegenero
     */
    public String getNomeGenero() {
        return nomegenero;
    }

    /**
     * @param nomegenero the nomegenero to set
     */
    public void setNomeGenero(String nomegenero) {
        this.nomegenero = nomegenero;
    }
    
    public String toString(){
        return "código genero: "+codgenero+" | nome editora: "+nomegenero;
    }
}
