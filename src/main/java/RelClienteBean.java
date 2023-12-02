import java.util.Date;


public class RelClienteBean {
    private int codcliente; 
    private String cliente;
    private String titulo; 
    private String autor;
    private String editora;
    private String genero;
    private Date dataemprestimo;
    private int dias; 
    private Date datadevolucao; 
    
    public RelClienteBean(int codcliente, String cliente, String titulo, String autor, String editora, String genero, Date dataemprestimo, int dias, Date datadevolucao){
        this.codcliente = codcliente;
        this.cliente = cliente; 
        this.titulo = titulo; 
        this.autor = autor;
        this.editora = editora;
        this.dataemprestimo = dataemprestimo;
        this.dias = dias;
        this.datadevolucao = datadevolucao; 
    }
    
    public int getCodCliente() {
        return codcliente;
    }

    public void setcodcliente(int codcliente) {
        this.codcliente = codcliente;
    }
    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
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
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public Date getDataEmprestimo() {
        return dataemprestimo;
    }

    public void setDataEmprestimo(Date dataemprestimo) {
        this.dataemprestimo = dataemprestimo;
    }

    public Date getDataDevReal() {
        return datadevolucao;
    }

    public void setDataDevReal(Date datadevolucao) {
        this.datadevolucao = datadevolucao;
    }
    public String toString(){
        return "Código cliente: "+codcliente+" | Cliente: "+cliente+" | Livro: "+titulo+" | Autor: "
                +autor+" | Editora: "+editora+" | Genero: "+genero+" | Data do emprestimo: "
                +dataemprestimo+" | Dias com o livro: "+dias+" | Data devolução: "+datadevolucao;
    }
}
