
import java.util.Date;


public class Relatorio3Bean {
    private int codlivro; 
    private String titulo; 
    private String autor;
    private String editora;
    private String genero;
    private Date dataemprestimo;
    private int dias; 
    private Date datadevolucao;
    private int codcliente; 
    private String cliente;
    
    public Relatorio3Bean(int codlivro, String titulo, String autor, String editora, String genero, Date dataemprestimo, int dias, Date datadevolucao, int codcliente, String cliente){
        this.codcliente = codcliente;
        this.cliente = cliente; 
        this.codlivro=codlivro;
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
    public int getCodLivro() {
        return codlivro;
    }

    public void setcodlivro(int codlivro) {
        this.codlivro = codlivro;
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
        return "Código Livro: "+codlivro+" | Livro: "+titulo+" | Autor: "+autor+" | Editora: "
                +editora+" | Genero: "+genero+" | Data do emprestimo: "+dataemprestimo+
                " | Dias com o livro: "+dias+" | Data devolução: "+datadevolucao+
                " | Código cliente: "+codcliente+" | Cliente: "+cliente;
    }
}
