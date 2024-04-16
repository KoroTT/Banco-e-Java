package br.com.dbjava.produtosdao;

import java.sql.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.bdjava.produtos.Produtos;
import br.com.dbjava.connection.ConnectionFactory;

public class ProdutosDAO {
	public void insetNewProduto(Produtos produto) {
		String slq = "INSERT INTO produtos(descricao, preco_venda, data_cadastro, data_alteracao, custo) VALUES (?, ?, ?, ?, ?)";
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = ConnectionFactory.createConnectioToMySQL();
			pstm = (PreparedStatement)con.prepareStatement(slq);
			
			pstm.setString(1, produto.getDescrição());
			pstm.setDouble(2, produto.getPrecoVenda());
			pstm.setDate(3, new Date(produto.getDataCadastro().getTime()));
			pstm.setDate(4, new Date(produto.getDataAlteracao().getTime()));
			pstm.setDouble(5, produto.getCusto());
			pstm.execute();
			System.out.println("Produto Cadastrado");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}if(con!=null) {
					con.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
