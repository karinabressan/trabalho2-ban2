import java.util.Date;

public class EmprestimoBean {
    private int codemprestimo;
    private int codcliente;
    private int codlivro;
    private Date dataemprestimo;
    private int dias;
    private Date datadevreal;

    // Construtor
    public EmprestimoBean(int codemprestimo, int codcliente, int codlivro, Date dataemprestimo, int dias, Date datadevreal) {
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

    public Date getDataEmprestimo() {
        return dataemprestimo;
    }

    public void setDataEmprestimo(Date dataemprestimo) {
        this.dataemprestimo = dataemprestimo;
    }

    public Date getDataDevReal() {
        return datadevreal;
    }

    public void setDataDevReal(Date datadevreal) {
        this.datadevreal = datadevreal;
    }
    
    public String toString(){
        return "codigo emprestimo: "+codemprestimo+" | codigo cliente: "+codcliente+" | Cod Livro: "
                +codlivro+" | data emprestimo: "+dataemprestimo+" | dias com o livro: "+dias+
                " | Data devolução: "+datadevreal;
    }
}
