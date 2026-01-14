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
    
    public void cadastrarProduto (ProdutosDTO produto){
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
        }
    }
    
    public void vender(int id) {
    String sql = "UPDATE produtos SET status = ? WHERE id = ?";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql)) {

        prep.setString(1, "Vendido");
        prep.setInt(2, id);

        prep.executeUpdate();

        JOptionPane.showMessageDialog(null, "Item vendido com sucesso");

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            null,
            "Ocorreu um erro e o item não foi vendido\n" + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
}
    public ArrayList<ProdutosDTO> listarProdutos() {
    String sql = "SELECT * FROM produtos";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql);
         ResultSet resultset = prep.executeQuery()) {

        while (resultset.next()) {

            ProdutosDTO produtos = new ProdutosDTO();

            produtos.setId(resultset.getInt("id"));
            produtos.setNome(resultset.getString("nome"));
            produtos.setValor(resultset.getInt("valor"));
            produtos.setStatus(resultset.getString("status"));

            listagem.add(produtos);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            null,
            "Erro ao listar produtos\n" + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
    }
    return listagem;
}  
    
    
     public ArrayList<ProdutosDTO> listarProdutosVendidos() {
    String sql = "SELECT * FROM produtos WHERE status = 'vendido'";

    try (Connection conn = new conectaDAO().connectDB();
         PreparedStatement prep = conn.prepareStatement(sql);
         ResultSet resultset = prep.executeQuery()) {

        while (resultset.next()) {

            ProdutosDTO produtos = new ProdutosDTO();

            produtos.setId(resultset.getInt("id"));
            produtos.setNome(resultset.getString("nome"));
            produtos.setValor(resultset.getInt("valor"));
            produtos.setStatus(resultset.getString("status"));

            listagem.add(produtos);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            null,
            "Erro ao listar produtos\n" + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
    }
    return listagem;
    }      
}

