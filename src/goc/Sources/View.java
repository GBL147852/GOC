/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goc.Sources;

import goc.Frames.ViewFrame;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

import javax.swing.table.*;

/**
 *
 * @author Admin
 */
public class View {
    static Sessao session = Sessao.getInstance();
    static Connection conn = Login.conexao();
    
    public static void fecharView(){session.fecharViewFrame();}
    
    public static DefaultComboBoxModel populateTemas(){
        
        try {
            DefaultComboBoxModel comboModel = new DefaultComboBoxModel();
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM tema ORDER BY Tema");
            comboModel.addElement("Todos os Temas");
            comboModel.addElement("Sem tema definido");
            while(rs.next()){
                comboModel.addElement(rs.getString("Tema"));
            }
            return comboModel;
        } catch (SQLException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void adicionarLinha(int codigo, String unidade, String cliente, String Origem_OP, String Data){
        DefaultTableModel yourModel = (DefaultTableModel) ViewFrame.jTable1.getModel();
        yourModel.addRow(new Object[]{codigo, unidade, cliente, Origem_OP, Data});
    }
    
    public static void setOpiniao(int ID){
        session.setOpiniao(ID);
    }
    
    public static void atualizarDados(){
        
        try {
            int Unidade = ViewFrame.Unidade.getSelectedIndex();
            int OrigemOP = ViewFrame.Origem_OP.getSelectedIndex();
            int mes = ViewFrame.Mes.getSelectedIndex();
            int ano = ViewFrame.Ano.getSelectedIndex()+2014;
            int tipoComentario = Integer.parseInt(ViewFrame.filtroComentarios.getSelection().getActionCommand());
            String Tema = ViewFrame.Temas.getSelectedItem().toString();
            String nomeCliente  = ViewFrame.nomeCliente.getText();
            String produtoProcurado = ViewFrame.prodProcurado.getText();
            String conteudoComentario = ViewFrame.opiniao.getText();
            
            DefaultTableModel model = (DefaultTableModel) ViewFrame.jTable1.getModel();
            model.setRowCount(0);
            Statement quantQuery = conn.createStatement();
            Statement query = conn.createStatement();
            String quant = "SELECT count(*) AS quantidade FROM "
                    + "(((opiniao JOIN cliente ON Cliente_ID = cliente.ID) JOIN comentario ON comentario.Opiniao_ID = opiniao.ID) "
                    + "JOIN produto_procurado ON produto_procurado.Opiniao_ID = Opiniao.ID) ";
            String select = "SELECT opiniao.ID AS ID, Unidade, cliente.Nome AS Nome, Origem_opiniao, Data, Tipo_Comentario FROM "
                    + "(((opiniao JOIN cliente ON Cliente_ID = cliente.ID) JOIN comentario ON comentario.Opiniao_ID = opiniao.ID) "
                    + "JOIN produto_procurado ON produto_procurado.Opiniao_ID = Opiniao.ID) ";
            if(!Tema.equals("Todos os Temas")){
                select += "LEFT JOIN rel_tema_opiniao ON rel_tema_opiniao.Opiniao_ID = Opiniao.ID LEFT JOIN tema ON rel_tema_opiniao.Tema_ID = tema.ID ";
                quant += "LEFT JOIN rel_tema_opiniao ON rel_tema_opiniao.Opiniao_ID = Opiniao.ID LEFT JOIN tema ON rel_tema_opiniao.Tema_ID = tema.ID ";
            }
            if( Unidade != 0 || OrigemOP != 0 || mes != 0 || ano != 2014 || tipoComentario != 5 || !Tema.equals("Todos os Temas") ||
                    !nomeCliente.equals("") || !produtoProcurado.equals("") || !conteudoComentario.equals("")){
                select += "WHERE ";
                quant += "WHERE ";
                
                if(!Tema.equals("Todos os Temas")){
                    if(!Tema.equals("Sem tema definido")){
                        select += "Tema = '" + Tema + "' AND ";
                        quant += "Tema = '" + Tema + "' AND ";
                    }else{
                        select += "Tema IS NULL AND ";
                        quant += "Tema IS NULL AND ";
                    }
                }
                
                if(!nomeCliente.equals("")){
                    select += "cliente.Nome LIKE '%" + nomeCliente + "%' AND ";
                    quant += "cliente.Nome LIKE '%" + nomeCliente + "%' AND ";
                }
                
                if(!produtoProcurado.equals("")){
                    select += "produto_procurado.Nome LIKE '%" + produtoProcurado + "%' AND ";
                    quant += "produto_procurado.Nome LIKE '%" + produtoProcurado + "%' AND ";
                }
                
                if(!conteudoComentario.equals("")){
                    select += "comentario.Texto_comentario LIKE '%" + conteudoComentario + "%' AND ";
                    quant += "comentario.Texto_comentario LIKE '%" + conteudoComentario + "%' AND ";
                }
                
                if(Unidade != 0){
                    select += "Unidade = '" + Unidade + "' AND ";
                    quant += "Unidade = '" + Unidade + "' AND ";
                }else{
                    select += "Unidade != '" + Unidade + "' AND ";
                    quant += "Unidade != '" + Unidade + "' AND ";
                }
                
                if(OrigemOP != 0){
                    select += "Origem_opiniao = '" + OrigemOP + "' AND ";
                    quant += "Origem_opiniao = '" + OrigemOP + "' AND ";
                }else{
                    select += "Origem_opiniao != '" + OrigemOP + "' AND ";
                    quant += "Origem_opiniao != '" + OrigemOP + "' AND ";
                }
                
                switch(tipoComentario){
                    case 0:
                        select += "(Texto_Comentario = '') AND ";
                        quant += "Texto_Comentario = '' AND ";
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        select += "Tipo_Comentario = '" + tipoComentario + "' AND ";
                        quant += "Tipo_Comentario = '" + tipoComentario + "' AND ";
                        break;
                    case 5:
                        select += "Texto_Comentario != '5' AND ";
                        quant += "Texto_Comentario != '5' AND ";
                        break;
                        
                }
                
                if(ano != 2014){
                    if(mes != 0){
                        select += "Data >= '" + ano + "-" + mes + "-01' AND Data < '"+ ano + "-" + mes + "-31'";
                        quant += "Data >= '" + ano + "-" + mes + "-01' AND Data < '"+ ano + "-" + mes + "-31'";
                    }else{
                        select += "Data >= '" + ano + "-01-01' AND Data < '"+ ano + "-12-31'";
                        quant += "Data >= '" + ano + "-01-01' AND Data < '"+ ano + "-12-31'";
                    }
                }else{
                    select += "Data IS NOT NULL";
                    quant += "Data IS NOT NULL";
                }
            }
            select += " ORDER BY Data DESC";
            session.setResult(select);
            ResultSet quantidade = quantQuery.executeQuery(quant);
            quantidade.next();
            ViewFrame.labelQuantidade.setText(quantidade.getString("quantidade") + " opiniões encontradas");
            ResultSet result = query.executeQuery(select);
            
            while(result.next()){
                int ID = Integer.parseInt(result.getString("ID"));
                int un = Integer.parseInt(result.getString("Unidade"));
                String unidade = null;
                switch(un){
                    case 1:
                        unidade = "Interlagos";
                        break;
                    case 2:
                        unidade = "Taboão";
                        break;
                    case 3:
                        unidade = "Morumbi";
                        break;
                    case 4:
                        unidade = "Pinheiros";
                        break;
                    case 6:
                        unidade = "Jaguaré";
                        break;
                    case 7:
                        unidade = "Bolonha";
                        break;
                }
                String nome = result.getString("Nome");
                int Origem = Integer.parseInt(result.getString("Origem_opiniao"));
                String OrigemOpcao = null;
                switch(Origem){
                    case 1:
                        OrigemOpcao = "Folheto";
                        break;
                    case 2:
                        OrigemOpcao = "Facebook";
                        break;
                    case 3:
                        OrigemOpcao = "Instagram";
                        break;
                    case 4:
                        OrigemOpcao = "Email/Site";
                        break;
                    case 5:
                        OrigemOpcao = "Telefone";
                        break;
                    case 6:
                        OrigemOpcao = "Presencial";
                        break;
                }
                String Data = formatarData(result.getString("Data"), "yyyy-mm-dd", "dd/mm/yyyy");
                adicionarLinha(ID,unidade,nome,OrigemOpcao,Data);
            }
            ViewFrame.jTable1.clearSelection();
            ViewFrame.buttonVisualizar.setVisible(false);
            ViewFrame.buttonExcluir.setVisible(false);
            ViewFrame.buttonEditar.setVisible(false);
        } catch (SQLException | ParseException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static int verificaSelecao(){
        int linha = ViewFrame.jTable1.getSelectedRow();
        int codigo = Integer.parseInt(ViewFrame.jTable1.getValueAt(linha, 0).toString());
        
        if(linha == -1){
            ViewFrame.buttonVisualizar.setVisible(false);
            ViewFrame.buttonExcluir.setVisible(false);
            ViewFrame.buttonEditar.setVisible(false);
        }else{
            ViewFrame.buttonVisualizar.setVisible(true);
            ViewFrame.buttonExcluir.setVisible(true);
            ViewFrame.buttonEditar.setVisible(true);
        }
        return codigo;
    }
    
    public static String formatarData (String date, String formatoInicial, String formatoFinal) throws ParseException {
        DateFormat formatString = new SimpleDateFormat(formatoFinal, Locale.US);
        DateFormat formatObject = new SimpleDateFormat(formatoInicial, Locale.US);

        String sqlDate = formatString.format(formatObject.parse(date));
        
        return sqlDate;
    }

    public static void iniciarView() {
        session.iniciarViewFrame();
    }

    public static void abrirView() {
        session.abrirViewFrame();
    }
    
    public static void deletarOpiniao(){
        if(session.getPerms()){
            if(JOptionPane.showConfirmDialog(null, "Deseja mesmo excluir esta opinião?", "Confirme a ação", JOptionPane.YES_NO_OPTION) == 0){
                try {
                    
                    Statement IDCliente = conn.createStatement();
                    ResultSet resultID = IDCliente.executeQuery("SELECT Cliente_ID FROM opiniao WHERE ID = '" + verificaSelecao()+ "'");
                    resultID.next();
                    int Cliente_ID = Integer.parseInt(resultID.getString("Cliente_ID"));
                    
                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = conn.prepareStatement(" DELETE FROM opiniao WHERE ID = ?");
                    preparedStmt.setInt (1, verificaSelecao());
                    
                    // execute the preparedstatement
                    preparedStmt.execute();
                    preparedStmt.close();
                    
                    Statement quantidadeOpinioes = conn.createStatement();
                    ResultSet opinioes = quantidadeOpinioes.executeQuery("SELECT * FROM opiniao WHERE Cliente_ID = '" + Cliente_ID + "'");
                    
                    if(!opinioes.next()){
                        preparedStmt = conn.prepareStatement("DELETE FROM cliente WHERE ID = ?");
                        preparedStmt.setInt (1, Cliente_ID);

                        // execute the preparedstatement
                        preparedStmt.execute();
                        preparedStmt.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            JOptionPane.showMessageDialog(null, "Acesso Negado!", "", 0);
        }
        View.atualizarDados();
    }
    
    public static void editarOpiniao(){
        session.setEdit(true);
        session.setOpiniao(verificaSelecao());
        session.abrirInserirFrame();
    }
}
