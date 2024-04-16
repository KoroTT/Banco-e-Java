package br.com.dbjava.produtosdao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import br.com.bdjava.produtos.Produtos;
import br.com.dbjava.connection.ConnectionFactory;

public class ProdutosDAO {
	
	private String insertSQL = "INSERT INTO produtos(descricao, preco_venda, data_cadastro, data_alteracao, custo) VALUES (?, ?, ?, ?, ?)";
	private String selectSQL = "SELECT * FROM produtos";
	
	private PreparedStatement pstInsert, pstSelectAll;
	
	public ProdutosDAO(Connection conn) throws SQLException {
		pstInsert = conn.prepareStatement(insertSQL);
		pstSelectAll = conn.prepareStatement(selectSQL);
	}
	
	
	public ArrayList<Produtos> selectAll() throws SQLException {
		
		ResultSet resultSet = pstSelectAll.executeQuery();
		ArrayList<Produtos> listaLocal = new ArrayList<Produtos>();
		
		while (resultSet.next()) {
			
			Produtos p = new Produtos();
			p.setId(resultSet.getInt("id"));
			p.setDescrição(resultSet.getString("descricao"));
			
			listaLocal.add(p);
		}
		
		return listaLocal;
	}
	 
	public void insetNewProduto(Produtos produto) {
		Connection con = null;
		PreparedStatement pstm = null;
		
		try {
			con = ConnectionFactory.createConnectioToMySQL();
			
			pstInsert.setString(1, produto.getDescrição());
			pstInsert.setDouble(2, produto.getPrecoVenda());
			pstInsert.setDate(3, new Date(produto.getDataCadastro().getTime()));
			pstInsert.setDate(4, new Date(produto.getDataAlteracao().getTime()));
			pstInsert.setDouble(5, produto.getCusto());
			pstInsert.execute();
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
