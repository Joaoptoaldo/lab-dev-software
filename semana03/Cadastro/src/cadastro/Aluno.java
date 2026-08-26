/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cadastro;

/**
 *
 * @author laboratorio
 */
public class Aluno {
    private String nomeCompleto;
    private String dataNascimento;
    private String sexo;
    private String matricula;
    private String curso;
    private String cpf;
    private String enderecoCompleto;
    private String estado;
    private String telefone;

    public Aluno(String nomeCompleto, String dataNascimento, String sexo, String matricula, 
                   String curso, String cpf, String enderecoCompleto, String estado, String telefone) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.matricula = matricula;
        this.curso = curso;
        this.cpf = cpf;
        this.enderecoCompleto = enderecoCompleto;
        this.estado = estado;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nomeCompleto + ";" + 
               dataNascimento + ";" + 
               sexo + ";" + 
               matricula + ";" + 
               curso + ";" + 
               cpf + ";" + 
               enderecoCompleto + ";" + 
               estado + ";" + 
               telefone;
    }


    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getCurso() {
        return curso;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public String getEstado() {
        return estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNome() {
        return nomeCompleto;
    }
}
