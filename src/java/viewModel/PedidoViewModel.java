/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModel;

/**
 *
 * @author Gabriel
 */
public class PedidoViewModel {
    
    public String Descricao;
    public int Quantidade;
    public int ProdutoId;

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public int getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(int Quantidade) {
        this.Quantidade = Quantidade;
    }

    public int getProdutoId() {
        return ProdutoId;
    }

    public void setProdutoId(int ProdutoId) {
        this.ProdutoId = ProdutoId;
    }
}
