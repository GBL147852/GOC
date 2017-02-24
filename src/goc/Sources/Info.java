/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goc.Sources;

import goc.Frames.InfoFrame;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


/**
 *
 * @author Admin
 */
public class Info {
    static Sessao session = Sessao.getInstance();
    static Connection conn = Login.conexao();
            
    public static void carregarView(){session.abrirViewFrame();}
    public static void carregarInsert(){session.abrirInserirFrame();}
    
    public static void carregarInformacoes() throws SQLException, ParseException{
        int ID_Opiniao = session.getOpiniao();
        if(ID_Opiniao != 0){
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM view_opiniao WHERE ID = " + ID_Opiniao);
            rs.next();
            
            Map<Integer,String> avaliacao = new HashMap<Integer,String>();
            
            avaliacao.put(1,"Ruim");
            avaliacao.put(2,"Regular");
            avaliacao.put(3,"Bom");
            avaliacao.put(4,"Ótimo");
            
            InfoFrame.labelNome.setText(rs.getString("NomeCliente"));
            InfoFrame.labelEmail.setText(rs.getString("EmailCliente"));
            InfoFrame.labelTelefone.setText(rs.getString("FoneCliente"));
            InfoFrame.labelData.setText(View.formatarData(rs.getString("Data"),"yyyy-mm-dd","dd/mm/yyyy"));
            
            if(rs.getString("Newsletter") != null){
                switch(Integer.parseInt(rs.getString("Newsletter"))){
                    case 0:
                        InfoFrame.labelOfertas.setText("Não");
                        break;
                    case 1:
                        InfoFrame.labelOfertas.setText("Sim");
                        break;
                }
            }
            
            
            switch(Integer.parseInt(rs.getString("Unidade"))){
                case 1:
                    InfoFrame.labelUnidade.setText("Interlagos");
                    break;
                case 2:
                    InfoFrame.labelUnidade.setText("Taboão");
                    break;
                case 3:
                    InfoFrame.labelUnidade.setText("Morumbi");
                    break;
                case 4:
                    InfoFrame.labelUnidade.setText("Pinheiros");
                    break;
                case 6:
                    InfoFrame.labelUnidade.setText("Jaguaré");
                    break;
                case 7:
                    InfoFrame.labelUnidade.setText("Bolonha");
                    break;
            }
            
            if(rs.getString("Atendimento") != null)
                InfoFrame.labelAvAtendimento.setText(avaliacao.get(Integer.parseInt(rs.getString("Atendimento"))));
            
            if(rs.getString("Variedade_Prod") != null)
                InfoFrame.labelAvVarProd.setText(avaliacao.get(Integer.parseInt(rs.getString("Variedade_Prod"))));
            
            if(rs.getString("Qualidade_Prod") != null)
                InfoFrame.labelAvQualProd.setText(avaliacao.get(Integer.parseInt(rs.getString("Qualidade_Prod"))));
            
            if(rs.getString("Precos") != null)
                InfoFrame.labelAvPrecos.setText(avaliacao.get(Integer.parseInt(rs.getString("Precos"))));
            
            if(rs.getString("Fila") != null)
                InfoFrame.labelAvFila.setText(avaliacao.get(Integer.parseInt(rs.getString("Fila"))));
            
            if(rs.getString("Estacionamento") != null)
                InfoFrame.labelAvEstacionamento.setText(avaliacao.get(Integer.parseInt(rs.getString("Estacionamento"))));
            
            if(rs.getString("Atend_Caixa") != null)
                InfoFrame.labelAvAtendCaixa.setText(avaliacao.get(Integer.parseInt(rs.getString("Atend_Caixa"))));
            
            if(rs.getString("Acougue") != null)
                InfoFrame.labelAvAcougue.setText(avaliacao.get(Integer.parseInt(rs.getString("Acougue"))));
            
            if(rs.getString("Padaria") != null)
                InfoFrame.labelAvPadaria.setText(avaliacao.get(Integer.parseInt(rs.getString("Padaria"))));
            
            if(rs.getString("Frios") != null)
                InfoFrame.labelAvFrios.setText(avaliacao.get(Integer.parseInt(rs.getString("Frios"))));
            
            if(rs.getString("Hortifruti") != null)
                InfoFrame.labelAvHortifruti.setText(avaliacao.get(Integer.parseInt(rs.getString("Hortifruti"))));
            
            if(rs.getString("Mercearia") != null)
                InfoFrame.labelAvMercearia.setText(avaliacao.get(Integer.parseInt(rs.getString("Mercearia"))));
            
            if(rs.getString("Lanchonete") != null)
                InfoFrame.labelAvLanchonete.setText(avaliacao.get(Integer.parseInt(rs.getString("Lanchonete"))));
            
            if(rs.getString("Adega") != null)
                InfoFrame.labelAvAdega.setText(avaliacao.get(Integer.parseInt(rs.getString("Adega"))));
            
            if(rs.getString("Pegpese") != null)
                InfoFrame.labelAvPegpese.setText(avaliacao.get(Integer.parseInt(rs.getString("Pegpese"))));
            
            if(rs.getString("Encontrou_prod") != null){
                if(Integer.parseInt(rs.getString("Encontrou_prod")) != 0)
                    InfoFrame.labelEncontrou.setText("Sim");
                else
                    InfoFrame.labelEncontrou.setText("Não");
            }
            
            if(rs.getString("Nome") != null){
                InfoFrame.labelProdProcura.setText(rs.getString("Nome"));
            }
            
            if(rs.getString("Aviso_prod") != null)
                if(rs.getString("Aviso_prod").equals("1"))
                    InfoFrame.labelAvisar.setText("Sim");
            
            if(rs.getString("Tipo_Comentario") != null){
                switch(Integer.parseInt(rs.getString("Tipo_Comentario"))){
                    case 1:
                        InfoFrame.labelTipoComentario.setText("Sugestão");
                        break;
                    case 2:
                        InfoFrame.labelTipoComentario.setText("Reclamação");
                        break;
                    case 3:
                        InfoFrame.labelTipoComentario.setText("Dúvidas");
                        break;
                    case 4:
                        InfoFrame.labelTipoComentario.setText("Elogios");
                        break;
                    default:
                        InfoFrame.labelTipoComentario.setText("Nenhum");
                        break;
                }
            }
            
            if(rs.getString("Texto_comentario") != null){
                InfoFrame.labelComentario.setText(rs.getString("Texto_comentario"));
            }
            
            if(Integer.parseInt(rs.getString("tagRespondido")) == 1)
                InfoFrame.checkRespondido.setSelected(true);
            if(Integer.parseInt(rs.getString("tagAcompanhar")) == 1)
                InfoFrame.checkAcompanhar.setSelected(true);
            if(Integer.parseInt(rs.getString("tagImportante")) == 1)
                InfoFrame.checkImportante.setSelected(true);
            
            
            carregarTemas(ID_Opiniao);
        }
    }
    
    public static void update_tags() throws SQLException{
        int ID = session.getOpiniao();
        // the mysql insert statement
        String query = " UPDATE tags SET Acompanhar = ?, Importante = ?, Respondido = ? WHERE Opiniao_ID = ?";

        // create the mysql insert preparedstatement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        if(InfoFrame.checkAcompanhar.isSelected())
            preparedStmt.setInt(1, 1);
        else
            preparedStmt.setInt(1, 0);

        if(InfoFrame.checkImportante.isSelected())
            preparedStmt.setInt(2, 1);
        else
            preparedStmt.setInt(2, 0);
        
        if(InfoFrame.checkRespondido.isSelected())
            preparedStmt.setInt(3, 1);
        else
            preparedStmt.setInt(3, 0);
        
        preparedStmt.setInt(4, ID);

        // execute the preparedstatement
        preparedStmt.execute();
    }
    
    public static void carregarTemas(int Opiniao_ID){
        Statement query;
        ResultSet rs;
        try {
            query = conn.createStatement();
            rs = query.executeQuery("SELECT * FROM rel_tema_opiniao JOIN tema ON ID = Tema_ID WHERE Opiniao_ID = " + Opiniao_ID);
            int i = 1;
            while(rs.next()){
                switch(i){
                    case 1:
                        InfoFrame.labelTema1.setText(rs.getString("Tema"));
                        break;
                    case 2:
                        InfoFrame.labelTema2.setText(rs.getString("Tema"));
                        break;
                    case 3:
                        InfoFrame.labelTema3.setText(rs.getString("Tema"));
                        break;
                }
                i++;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao carregar os temas do banco de dados!","Erro ao carregar temas",JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void abrirInserir() {
        session.abrirInserirFrame();
    }
}
