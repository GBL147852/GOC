package goc.Sources;

import goc.Frames.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;

public class Relatorios {
    static Sessao session = Sessao.getInstance();
    static Connection conn = Login.conexao();
    
    public static void carregaFrequencias() {
        try {
            DefaultComboBoxModel comboboxModel  = new DefaultComboBoxModel();
            switch(Integer.parseInt(RelatorioFrame.Frequencia.getSelection().getActionCommand())){
                case 1:
                    RelatorioFrame.dataEspecifica.setEnabled(false);
                    break;
                case 2:
                    RelatorioFrame.dataEspecifica.setEnabled(true);
                    comboboxModel.addElement("Todos os meses");
                    for(int i = 1; i <= 12 ; i++){
                        comboboxModel.addElement(i);
                    }
                    break;
                case 3:
                    RelatorioFrame.dataEspecifica.setEnabled(true);
                    Statement query = conn.createStatement();
                    comboboxModel.addElement("Todos os anos");
                    
                    ResultSet rs = query.executeQuery("SELECT Ano FROM media_avaliacoes GROUP BY Ano");
                    while(rs.next())
                        comboboxModel.addElement(rs.getString("Ano"));
                    
                    break;
            }
            RelatorioFrame.dataEspecifica.setModel(comboboxModel);
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void carregaTipoMedia() {
        DefaultComboBoxModel comboboxModel  = new DefaultComboBoxModel();
        switch(Integer.parseInt(RelatorioFrame.TipoMedia.getSelection().getActionCommand())){
            case 1:
                RelatorioFrame.comboAvLoja.setEnabled(false);
                break;
            case 2:
                RelatorioFrame.comboAvLoja.setEnabled(true);
                comboboxModel.addElement("Todas as avaliações");
                comboboxModel.addElement("Atendimento");
                comboboxModel.addElement("Variedade de Produtos");
                comboboxModel.addElement("Qualidade dos Produtos");
                comboboxModel.addElement("Preços");
                comboboxModel.addElement("Fila");
                comboboxModel.addElement("Estacionamento");
                comboboxModel.addElement("Atendimento do Caixa");
                comboboxModel.addElement("Açougue");
                comboboxModel.addElement("Padaria");
                comboboxModel.addElement("Frios");
                comboboxModel.addElement("Hortifruti");
                comboboxModel.addElement("Mercearia");
                comboboxModel.addElement("Lanchonete");
                comboboxModel.addElement("Adega");
                comboboxModel.addElement("Pegpese (Ambiente Geral)");
                break;
            case 3:
                RelatorioFrame.comboAvLoja.setEnabled(true);
                comboboxModel.addElement("Todas as Empresas");
                comboboxModel.addElement("1 - Interlagos");
                comboboxModel.addElement("2 - Taboão");
                comboboxModel.addElement("3 - Morumbi");
                comboboxModel.addElement("4 - Pinheiros");
                comboboxModel.addElement("6 - Jaguaré");
                comboboxModel.addElement("7 - Bolonha");
                
                break;
        }
        RelatorioFrame.comboAvLoja.setModel(comboboxModel);
    }
}