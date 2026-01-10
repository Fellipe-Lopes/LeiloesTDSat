/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto) throws SQLException{
        conn = new conectaDAO().connectDB();

        String sql = "INSERT INTO produtos(nome, valor, status) VALUES(?,?,?)";

        try{
            prep = conn.prepareStatement(sql);
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3,produto.getStatus());

            prep.executeUpdate();
           
            JOptionPane.showMessageDialog(null, "Item cadastrado com sucesso");
        }catch(SQLException e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "Ocorreu um erro e o item não foi cadastrado");
        }finally{
            conn.close();
        }
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() throws SQLException{
        conn = new conectaDAO().connectDB();
        String sql = "SELECT * FROM produtos";
        
        try{
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
            
                ProdutosDTO produtos = new ProdutosDTO();
                
                produtos.setId(resultset.getInt("id"));
                produtos.setNome(resultset.getString("nome"));
                produtos.setValor(resultset.getInt("valor"));
                produtos.setStatus(resultset.getString("status"));
                
                listagem.add(produtos);
            }
        }catch(SQLException e){
            System.out.println(e);
            
            JOptionPane.showMessageDialog(null, "Erro ao listar produtos");
        }finally{
            conn.close();
        }
        return listagem;
    }
    
    
    
        
}

