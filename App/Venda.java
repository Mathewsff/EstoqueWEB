/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;

/**
 *
 * @author Nowll
 */
public class Venda {
    
    int id, quantidade_venda, id_cliente, id_produto, id_funcionario;
    String data;
    float valor_venda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade_venda() {
        return quantidade_venda;
    }

    public void setQuantidade_venda(int quantidade_venda) {
        this.quantidade_venda = quantidade_venda;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_clente) {
        this.id_cliente = id_clente;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }
    
    
}
