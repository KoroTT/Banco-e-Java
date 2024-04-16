package br.com.dbjava.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.Connection;

import br.com.bdjava.produtos.Produtos;
import br.com.dbjava.connection.ConnectionFactory;
import br.com.dbjava.produtosdao.ProdutosDAO;

public class Test extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane scroll;
	private DefaultTableModel model;
	
	public Test()  throws ClassNotFoundException, SQLException {
		setSize(500, 500);
		
		model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("Produto");
		
		table = new JTable(model);
		scroll = new JScrollPane(table);
		getContentPane().add(scroll);
		
		Produtos produto = new Produtos();
		
		produto.setDescrição("Cocaina 10g");
		produto.setPrecoVenda(50.30);
		produto.setCusto(10.46);
		produto.setDataCadastro(new Date());
		produto.setDataAlteracao(new Date());
		
		Connection conn = ConnectionFactory.createConnectioToMySQL();
		
		ProdutosDAO produtoDao = new ProdutosDAO(conn);
		produtoDao.insetNewProduto(produto);
		
		ArrayList<Produtos> listaProdutos = produtoDao.selectAll();
		
		for (Produtos p : listaProdutos) {
			model.addRow(new String[] {""+p.getId(), p.getDescrição()});
		}
		
		conn.close();
		
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		new Test().setVisible(true);
	}
	
}
