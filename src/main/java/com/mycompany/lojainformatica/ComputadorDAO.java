/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lojainformatica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oaugu
 */
public class ComputadorDAO {
    
    static String url = "jdbc:mysql://localhost:3306/lojaInformatica";
    static String login = "root";
    static String senha = "root";
    
    
    public static boolean salvar(Computador novoComputador) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, login, senha);

            comandoSQL = conexao.prepareStatement("INSERT INTO computador(ds_marca,ds_hd,ds_processador) VALUES(?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
            comandoSQL.setString(1, novoComputador.getMarca());
            comandoSQL.setString(2, novoComputador.getHD());
            comandoSQL.setString(3, novoComputador.getProcessador());

            int linhasAfetadas = comandoSQL.executeUpdate();

            if(linhasAfetadas > 0){
                rs = comandoSQL.getGeneratedKeys(); 
                if(rs != null){
                    while(rs.next()){
                        int idGerado = rs.getInt(1);
                        novoComputador.setID(idGerado);
                    }
                }
                
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }
    
    public static ArrayList<Computador> listar(){
           ArrayList<Computador> lista = new ArrayList<>();
           Connection conexao = null;
           PreparedStatement comandoSQL = null;
           ResultSet rs = null;
           
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection(url, login, senha);
            
            comandoSQL = conexao.prepareStatement("SELECT * FROM computador");
            
             rs = comandoSQL.executeQuery();
            
            if(rs != null){
         
                while(rs.next()){
          
                int id = rs.getInt("pk_id");
                String marca = rs.getString("ds_marca");
               String hd = rs.getString("ds_hd");
                String processador = rs.getString("ds_processador");
                
                
                var computador = new Computador(id,hd,processador,marca);
                
                lista.add(computador);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger( ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }    
           return lista;
    
    }
    
    public static boolean alterar(Computador novoComputador) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, login, senha);

            comandoSQL = conexao.prepareStatement("UPDATE computador SET  ds_hd = ?,ds_processador = ? WHERE PK_ID = ?");
            comandoSQL.setString(1, novoComputador.getHD());
            comandoSQL.setString(2, novoComputador.getProcessador());
            comandoSQL.setInt(3,(int) novoComputador.getID());
            int linhasAfetadas = comandoSQL.executeUpdate();

            if(linhasAfetadas > 0){
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }
    
    
    public static boolean excluir(int idExcluir) {

        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(url, login, senha);

            comandoSQL = conexao.prepareStatement("DELETE from computador where pk_id = ?");
            comandoSQL.setInt(1, idExcluir);
            
            int linhasAfetadas = comandoSQL.executeUpdate();

            if(linhasAfetadas > 0){
                retorno = true;
            }

        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        return retorno;
    }
    
     public static ArrayList<Computador> listarPorNome(String busca){
           ArrayList<Computador> lista = new ArrayList<>();
           Connection conexao = null;
           PreparedStatement comandoSQL = null;
           ResultSet rs = null;
           
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            conexao = DriverManager.getConnection(url, login, senha);
            
            comandoSQL = conexao.prepareStatement("SELECT * FROM computador WHERE ds_processador = ?");
            comandoSQL.setString(1, busca);
             rs = comandoSQL.executeQuery();
            
            if(rs != null){
         
                while(rs.next()){
          
                int id = rs.getInt("pk_id");
                String marca = rs.getString("ds_marca");
               String hd = rs.getString("ds_hd");
                String processador = rs.getString("ds_processador");
                
                
                var computador = new Computador(id,hd,processador,marca);
                
                lista.add(computador);
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           Logger.getLogger(ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger( ComputadorDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }    
           return lista;
    
    }
   
    
}
