package br.com.dbjava.test;

import java.util.Date;

import br.com.bdjava.produtos.Produtos;
import br.com.dbjava.produtosdao.ProdutosDAO;

public class Test {

	public static void main(String[] args) {
		Produtos produto = new Produtos();
		
		produto.setDescrição("Café Pilão 500gr");
		produto.setPrecoVenda(4.35);
		produto.setCusto(2.59);
		produto.setDataCadastro(new Date());
		produto.setDataAlteracao(new Date());
		
		ProdutosDAO produtoDao = new ProdutosDAO();
		produtoDao.insetNewProduto(produto);
	}
	
}
