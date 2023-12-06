package nosql.neo4j;

import java.util.Date;

public class EmprestimoBean {
    private int codemprestimo;
    private int codcliente;
    private int codlivro;
    private String dataemprestimo;
    private int dias;
    private String datadevreal;

    // Construtor
    public EmprestimoBean(int codemprestimo, int codcliente, int codlivro, String dataemprestimo, int dias, String datadevreal) {
        this.codemprestimo = codemprestimo;
        this.codcliente = codcliente;
        this.codlivro = codlivro;
        this.dataemprestimo = dataemprestimo;
        this.dias = dias;
        this.datadevreal = datadevreal;
    }
    

    // Getters e Setters
    public int getCodEmprestimo() {
        return codemprestimo;
    }

    public void setCodEmprestimo(int codemprestimo) {
        this.codemprestimo = codemprestimo;
    }
    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }
    public int getCodCliente() {
        return codcliente;
    }

    public void setCodCliente(int codcliente) {
        this.codcliente = codcliente;
    }

    public int getCodLivro() {
        return codlivro;
    }

    public void setCodLivro(int codlivro) {
        this.codlivro = codlivro;
    }

    public String getDataEmprestimo() {
        return dataemprestimo;
    }

    public void setDataEmprestimo(String dataemprestimo) {
        this.dataemprestimo = dataemprestimo;
    }

    public String getDataDevReal() {
        return datadevreal;
    }

    public void setDataDevReal(String datadevreal) {
        this.datadevreal = datadevreal;
    }
    
    public String toString(){
        return "codigo emprestimo: "+codemprestimo+" | codigo cliente: "+codcliente+" | Cod Livro: "
                +codlivro+" | data emprestimo: "+dataemprestimo+" | dias com o livro: "+dias+
                " | Data devolução: "+datadevreal;
    }
}
