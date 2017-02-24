/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goc.Sources;

import goc.Frames.LoginFrame;
import goc.Frames.MenuFrame;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Login {
    static Sessao session = Sessao.getInstance();
    public static Connection conexao(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");  // MySQL database connection
            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.221:3306/goc?" + "user=admin&password=admin&verifyServerCertificate=false&useSSL=true");     
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível conectar-se ao banco de dados!");
        }
        return conn;
    }
    public static boolean validate_login(String username,String password) {
        try{           
            Connection conn = conexao();
            PreparedStatement pst = conn.prepareStatement("Select * from usuario where User=? and Password=?");
            pst.setString(1, username); 
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                session.setPerms(rs.getBoolean("Permissao_Add"));
                session.setNome(rs.getString("Nome"));
                session.setID(rs.getInt("ID"));
                session.iniciarViewFrame();
                session.iniciarInserirFrame();
                return true;
            }
            return false;            
        }
        catch(SQLException e){
            return false;
        }       
    }
    public static void logar(LoginFrame frame) throws IOException{
        if(LoginFrame.textUsuario.getText().length()==0)  // Checking for empty field
            JOptionPane.showMessageDialog(null, "Campo de usuario vazio!");
        else if(LoginFrame.textSenha.getPassword().length==0)  // Checking for empty field
            JOptionPane.showMessageDialog(null, "Campo de senha vazio!");
        else{
            String user = LoginFrame.textUsuario.getText();   // Collecting the input
            char[] pass = LoginFrame.textSenha.getPassword(); // Collecting the input
            String pwd = String.copyValueOf(pass);  // converting from array to string
            if(validate_login(user,pwd)){
                frame.dispose();
                new MenuFrame().setVisible(true);
            }else{
               JOptionPane.showMessageDialog(null, "Login e Senha INCORRETOS!");
            }
        }
    }

}


