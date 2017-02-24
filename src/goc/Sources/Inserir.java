/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goc.Sources;

import goc.Frames.InserirFrame;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author Admin
 */



public class Inserir {
    static Sessao session = Sessao.getInstance();
    static Connection conn = Login.conexao();
    
    public static void carregarDados() {
        if(session.getEdit()){
            try {
                Statement query = conn.createStatement();
                ResultSet detalhesOpiniao = query.executeQuery("SELECT * FROM view_opiniao WHERE ID = '" + session.getOpiniao() + "'");
                detalhesOpiniao.next();
                if(detalhesOpiniao.getString("EmailCliente").equals("anonimo@anonimo.ano.ni")){
                    InserirFrame.AnonimoCheck.setSelected(true);
                    setAnonimo();
                }else{
                    setAnonimo();
                    InserirFrame.TextNome.setText(detalhesOpiniao.getString("NomeCliente"));
                    InserirFrame.TextEmail.setText(detalhesOpiniao.getString("EmailCliente"));
                    InserirFrame.TextFone.setText(detalhesOpiniao.getString("FoneCliente"));
                    if(detalhesOpiniao.getString("Newsletter") != null){
                        switch(Integer.parseInt(detalhesOpiniao.getString("Newsletter"))){
                            case 0:
                                InserirFrame.newsfeed.setSelected(InserirFrame.EmailNao.getModel(), true);
                                break;
                            case 1:
                                InserirFrame.newsfeed.setSelected(InserirFrame.EmailSim.getModel(), true);
                                break;
                        }
                    }
                }
                InserirFrame.TextData.setText(View.formatarData(detalhesOpiniao.getString("Data"),"yyyy-mm-dd","dd/mm/yyyy"));
                InserirFrame.Unidade.setSelectedIndex(Integer.parseInt(detalhesOpiniao.getString("Unidade")));
                
                Statement temasQuery = conn.createStatement();
                ResultSet temas = temasQuery.executeQuery("SELECT * FROM rel_tema_opiniao JOIN tema ON Tema_ID = tema.ID WHERE Opiniao_ID = '" + session.getOpiniao() + "'");
                while(temas.next()){
                    moverTema("Direita", temas.getString("Tema"));
                }
                
                
                carregarOpiniao(detalhesOpiniao.getString("Atendimento"), InserirFrame.Atendimento, InserirFrame.RuimAtendimento, InserirFrame.RegularAtendimento, InserirFrame.BomAtendimento, InserirFrame.OtimoAtendimento);
                carregarOpiniao(detalhesOpiniao.getString("Variedade_Prod"), InserirFrame.VariedadeProdutos, InserirFrame.RuimVarProd, InserirFrame.RegularVarProd, InserirFrame.BomVarProd, InserirFrame.OtimoVarProd);
                carregarOpiniao(detalhesOpiniao.getString("Qualidade_Prod"), InserirFrame.QualidadeProdutos, InserirFrame.RuimQualProd, InserirFrame.RegularQualProd, InserirFrame.BomQualProd, InserirFrame.OtimoQualProd);
                carregarOpiniao(detalhesOpiniao.getString("Precos"), InserirFrame.Precos, InserirFrame.RuimPrecos, InserirFrame.RegularPrecos, InserirFrame.BomPrecos, InserirFrame.OtimoPrecos);
                carregarOpiniao(detalhesOpiniao.getString("Fila"), InserirFrame.Fila, InserirFrame.RuimFila, InserirFrame.RegularFila, InserirFrame.BomFila, InserirFrame.OtimoFila);
                carregarOpiniao(detalhesOpiniao.getString("Estacionamento"), InserirFrame.Estacionamento, InserirFrame.RuimEstacionamento, InserirFrame.RegularEstacionamento, InserirFrame.BomEstacionamento, InserirFrame.OtimoEstacionamento);
                carregarOpiniao(detalhesOpiniao.getString("Atend_Caixa"), InserirFrame.AtendimentoCaixa, InserirFrame.RuimAtendCaixa, InserirFrame.RegularAtendCaixa, InserirFrame.BomAtendCaixa, InserirFrame.OtimoAtendCaixa);
                carregarOpiniao(detalhesOpiniao.getString("Acougue"), InserirFrame.Acougue, InserirFrame.RuimAcougue, InserirFrame.RegularAcougue, InserirFrame.BomAcougue, InserirFrame.OtimoAcougue);
                carregarOpiniao(detalhesOpiniao.getString("Padaria"), InserirFrame.Padaria, InserirFrame.RuimPadaria, InserirFrame.RegularPadaria, InserirFrame.BomPadaria, InserirFrame.OtimoPadaria);
                carregarOpiniao(detalhesOpiniao.getString("Frios"), InserirFrame.Frios, InserirFrame.RuimFrios, InserirFrame.RegularFrios, InserirFrame.BomFrios, InserirFrame.OtimoFrios);
                carregarOpiniao(detalhesOpiniao.getString("Hortifruti"), InserirFrame.Hortifruti, InserirFrame.RuimHortifruti, InserirFrame.RegularHortifruti, InserirFrame.BomHortifruti, InserirFrame.OtimoHortifruti);
                carregarOpiniao(detalhesOpiniao.getString("Mercearia"), InserirFrame.Mercearia, InserirFrame.RuimMercearia, InserirFrame.RegularMercearia, InserirFrame.BomMercearia, InserirFrame.OtimoMercearia);
                carregarOpiniao(detalhesOpiniao.getString("Lanchonete"), InserirFrame.Lanchonete, InserirFrame.RuimLanchonete, InserirFrame.RegularLanchonete, InserirFrame.BomLanchonete, InserirFrame.OtimoLanchonete);
                carregarOpiniao(detalhesOpiniao.getString("Adega"), InserirFrame.Adega, InserirFrame.RuimAdega, InserirFrame.RegularAdega, InserirFrame.BomAdega, InserirFrame.OtimoAdega);
                carregarOpiniao(detalhesOpiniao.getString("Pegpese"), InserirFrame.Pegpese, InserirFrame.RuimPegpese, InserirFrame.RegularPegpese, InserirFrame.BomPegpese, InserirFrame.OtimoPegpese);
                
                if(detalhesOpiniao.getString("Encontrou_prod") != null){
                    switch(Integer.parseInt(detalhesOpiniao.getString("Encontrou_prod"))){
                        case 0:
                            InserirFrame.EncontrouProduto.setSelected(InserirFrame.EncontrouNao.getModel(), true);
                            break;
                        case 1:
                            InserirFrame.EncontrouProduto.setSelected(InserirFrame.EncontrouSim.getModel(), true);
                            break;
                    }
                }
                
                InserirFrame.ProcuraProduto.setText(detalhesOpiniao.getString("Nome"));
                
                if(detalhesOpiniao.getString("Aviso_prod") != null){
                    switch(Integer.parseInt(detalhesOpiniao.getString("Aviso_prod"))){
                        case 0:
                            InserirFrame.AvisoProduto.setSelected(InserirFrame.AvisarNao.getModel(), true);
                            break;
                        case 1:
                            InserirFrame.AvisoProduto.setSelected(InserirFrame.AvisarSim.getModel(), true);
                            break;
                    }
                }
                
                carregarOpiniao(detalhesOpiniao.getString("Tipo_Comentario"), InserirFrame.TipoComentario, InserirFrame.ComentSugestao, InserirFrame.ComentReclamacao, InserirFrame.ComentDuvida, InserirFrame.ComentElogio);
                
                InserirFrame.Comentario.setText(detalhesOpiniao.getString("Texto_comentario"));
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro na consulta ao banco de dados!", "Erro de SQL!", 0);
                Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public static void carregarOpiniao(String avaliacao, ButtonGroup Grupo, JRadioButton Ruim, JRadioButton Regular, JRadioButton Bom, JRadioButton Otimo){
        if(avaliacao != null){
            switch(Integer.parseInt(avaliacao)){
                case 1:
                    Grupo.setSelected(Ruim.getModel(), true);
                    break;
                case 2:
                    Grupo.setSelected(Regular.getModel(), true);
                    break;
                case 3:
                    Grupo.setSelected(Bom.getModel(), true);
                    break;
                case 4:
                    Grupo.setSelected(Otimo.getModel(), true);
                    break;
            }
        }
    }
    
    public static void carregarTemas(){
        try {
            DefaultListModel listModel  = new DefaultListModel();
            Statement query = conn.createStatement();
            ResultSet rs = query.executeQuery("SELECT * FROM tema ORDER BY Tema");
            while(rs.next()){
                listModel.addElement(rs.getString("Tema"));
            }
            
            InserirFrame.listaTemasDB.setModel(listModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar temas!", "Erro de SQL!", 0);
            Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void ordenarListas(){
        DefaultListModel model = (DefaultListModel) InserirFrame.listaTemasDB.getModel();
        DefaultListModel modelSelected = (DefaultListModel) InserirFrame.listaTemasS.getModel();
        
        String[] stringsDB = new String[model.getSize()];
        for(int i=0;i<stringsDB.length;i++){
            stringsDB[i]=model.getElementAt(i).toString();
        }
        Arrays.sort(stringsDB);
        for(int i=0;i<stringsDB.length;i++){
            model.set(i,stringsDB[i]);
        }
        
        String[] stringsS = new String[modelSelected.getSize()];
        for(int i=0;i<stringsS.length;i++){
            stringsS[i]=modelSelected.getElementAt(i).toString();
        }
        Arrays.sort(stringsS);
        
        for(int i=0;i<stringsS.length;i++){
            modelSelected.set(i,stringsS[i]);
        }
        
        InserirFrame.listaTemasDB.setModel(model);
        InserirFrame.listaTemasS.setModel(modelSelected);
    }
    
    public static void adicionaNovoTema(){
        
        DefaultListModel model = (DefaultListModel) InserirFrame.listaTemasDB.getModel();
        DefaultListModel modelSelected = (DefaultListModel) InserirFrame.listaTemasS.getModel();
        
        
        String nome = JOptionPane.showInputDialog(null, "Digite o nome do novo tema:", "Inserir Tema", JOptionPane.PLAIN_MESSAGE);
        
        if(nome != null && !nome.equals("")){
            try {
                String temaInsert = "INSERT INTO tema (Tema) VALUES (?)";
                PreparedStatement stmt = conn.prepareStatement(temaInsert);
                stmt.setString(1, nome);
                stmt.execute();
                
                model.addElement(nome);
                modelSelected.addElement(nome);
                InserirFrame.listaTemasDB.setModel(model);
                InserirFrame.listaTemasS.setModel(modelSelected);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao adicionar novo tema!", "Erro de SQL!", 0);
            }
        }
        Inserir.ordenarListas();
    }
    
    public static void moverTema(String direcao, String tema){
        DefaultListModel model = (DefaultListModel) InserirFrame.listaTemasDB.getModel();
        DefaultListModel modelSelected = (DefaultListModel) InserirFrame.listaTemasS.getModel();
        if(direcao.equals("Direita")){
            if(tema != null && !tema.equals("")){
                modelSelected.addElement(tema);
                
                model.removeElement(tema);

                InserirFrame.listaTemasS.setModel(modelSelected);
                InserirFrame.listaTemasDB.setModel(model);
            }
        }else if(direcao.equals("Esquerda")){
            if(tema != null && !tema.equals("")){
                model.addElement(tema);
                
                modelSelected.removeElement(tema);

                InserirFrame.listaTemasDB.setModel(model);
                InserirFrame.listaTemasS.setModel(modelSelected);
            }
        }
        Inserir.ordenarListas();
    }
    
    public static void EncontrouSim(){
        if(InserirFrame.EncontrouSim.isSelected() && InserirFrame.jLabel32.getY() == 360){
            InserirFrame.jLabel28.setVisible(false);
            InserirFrame.ProcuraProduto.setVisible(false);
            InserirFrame.jScrollPane2.setVisible(false);
            InserirFrame.jLabel31.setVisible(false);
            InserirFrame.AvisarSim.setVisible(false);
            InserirFrame.AvisarNao.setVisible(false);
            InserirFrame.jLabel32.setLocation(InserirFrame.jLabel32.getX(), InserirFrame.jLabel32.getY()-175);
            InserirFrame.jSeparator3.setLocation(InserirFrame.jSeparator3.getX(), InserirFrame.jSeparator3.getY()-175);
            InserirFrame.ComentDuvida.setLocation(InserirFrame.ComentDuvida.getX(), InserirFrame.ComentDuvida.getY()-175);
            InserirFrame.ComentElogio.setLocation(InserirFrame.ComentElogio.getX(), InserirFrame.ComentElogio.getY()-175);
            InserirFrame.ComentReclamacao.setLocation(InserirFrame.ComentReclamacao.getX(), InserirFrame.ComentReclamacao.getY()-175);
            InserirFrame.ComentSugestao.setLocation(InserirFrame.ComentSugestao.getX(), InserirFrame.ComentSugestao.getY()-175);
            InserirFrame.buttonSalvar.setLocation(InserirFrame.buttonSalvar.getX(), InserirFrame.buttonSalvar.getY()-175);
            InserirFrame.jScrollPane1.setLocation(InserirFrame.jScrollPane1.getX(), InserirFrame.jScrollPane1.getY()-175);
        }
    }
    
    public static void EncontrouNao(){
        if(InserirFrame.EncontrouNao.isSelected() && InserirFrame.jLabel32.getY() == 185){
            InserirFrame.jLabel28.setVisible(true);
            InserirFrame.ProcuraProduto.setVisible(true);
            InserirFrame.jScrollPane2.setVisible(true);
            InserirFrame.jLabel31.setVisible(true);
            InserirFrame.AvisarSim.setVisible(true);
            InserirFrame.AvisarNao.setVisible(true);
            InserirFrame.jLabel32.setLocation(InserirFrame.jLabel32.getX(), InserirFrame.jLabel32.getY()+175);
            InserirFrame.jSeparator3.setLocation(InserirFrame.jSeparator3.getX(), InserirFrame.jSeparator3.getY()+175);
            InserirFrame.ComentDuvida.setLocation(InserirFrame.ComentDuvida.getX(), InserirFrame.ComentDuvida.getY()+175);
            InserirFrame.ComentElogio.setLocation(InserirFrame.ComentElogio.getX(), InserirFrame.ComentElogio.getY()+175);
            InserirFrame.ComentReclamacao.setLocation(InserirFrame.ComentReclamacao.getX(), InserirFrame.ComentReclamacao.getY()+175);
            InserirFrame.ComentSugestao.setLocation(InserirFrame.ComentSugestao.getX(), InserirFrame.ComentSugestao.getY()+175);
            InserirFrame.buttonSalvar.setLocation(InserirFrame.buttonSalvar.getX(), InserirFrame.buttonSalvar.getY()+175);
            InserirFrame.jScrollPane1.setLocation(InserirFrame.jScrollPane1.getX(), InserirFrame.jScrollPane1.getY()+175);
        }
    }
    
    public static void setAnonimo(){
        if(InserirFrame.AnonimoCheck.isSelected()){
            InserirFrame.TextNome.setEnabled(false);
            InserirFrame.TextFone.setEnabled(false);
            InserirFrame.TextEmail.setEnabled(false);
            InserirFrame.EmailSim.setEnabled(false);
            InserirFrame.EmailNao.setEnabled(false);
            
        }else{
            InserirFrame.TextNome.setEnabled(true);
            InserirFrame.TextFone.setEnabled(true);
            InserirFrame.TextEmail.setEnabled(true);
            InserirFrame.EmailSim.setEnabled(true);
            InserirFrame.EmailNao.setEnabled(true);
        }
        InserirFrame.TextNome.setText("");
        InserirFrame.TextFone.setText("");
        InserirFrame.TextEmail.setText("");
        InserirFrame.EmailSim.setSelected(false);
        InserirFrame.EmailNao.setSelected(false);
    }
    
    //Formata a data
    public static java.sql.Date formatDate (String date, String initDateFormat, String endDateFormat) throws ParseException {
        DateFormat format;
        format = new SimpleDateFormat("dd/MM/yyyy", Locale.US);

        java.util.Date data = format.parse(date);

        java.sql.Date sqlDate = new java.sql.Date(data.getTime());
        
        return sqlDate;
    }
    
    //insere o cliente
    public static void cliente() throws SQLException{
        int Cliente_ID = 0;
        if(!InserirFrame.TextEmail.getText().equals("") || !InserirFrame.TextFone.getText().equals("") || !InserirFrame.TextNome.getText().equals("") && !InserirFrame.AnonimoCheck.isSelected()){
            
            if(!session.getEdit()){
                Statement query = conn.createStatement();
                ResultSet result = query.executeQuery("SELECT ID FROM cliente WHERE nome = '" + InserirFrame.TextNome.getText() + "'");
                if(result.next()){
                    Cliente_ID = Integer.parseInt(result.getString("ID"));
                }else{
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery("SHOW TABLE STATUS WHERE `Name` = 'cliente'");
                    rs.next();
                    Cliente_ID = Integer.parseInt(rs.getString("Auto_increment"));
                }
            }
            String q = null;
            boolean achouCliente = false;
            if(session.getEdit()){
                Statement IDCliente = conn.createStatement();
                ResultSet resultID = IDCliente.executeQuery("SELECT cliente.ID FROM cliente JOIN opiniao ON Cliente_ID = cliente.ID WHERE opiniao.ID = '" + session.getOpiniao()+ "'");
                resultID.next();
                
                Statement ClienteExistente = conn.createStatement();
                ResultSet resultCliente = ClienteExistente.executeQuery("SELECT ID FROM cliente WHERE Nome = '" + InserirFrame.TextNome + "' AND Email = '" + InserirFrame.TextEmail + "' AND Telefone = '" + InserirFrame.TextFone + "'");
                if(resultCliente.next()){
                    achouCliente = true;
                    Cliente_ID = Integer.parseInt("ID");
                    Statement opinioesCliente = conn.createStatement();
                    ResultSet opinioes = opinioesCliente.executeQuery("SELECT * FROM opiniao WHERE Cliente_ID = '" + resultID.getString("ID") + "'");
                    opinioes.next(); // Pega a opinião com o cliente antigo e vê se tem mais opiniões deste cliente. 
                                    // Caso não tenha, ocorre a remoção do cliente do banco de dados por falta de opiniões associadas a ele.
                    if(!(opinioes.next())){
                        PreparedStatement pst = conn.prepareStatement("DELETE FROM cliente WHERE ID = ?");
                        pst.setInt(1, Integer.parseInt(resultID.getString("ID")));
                        pst.execute();
                    }
                }else{
                    q = "UPDATE cliente SET Nome = ?, Telefone = ?, Email = ?, Newsletter = ? WHERE ID = " + resultID.getString("ID");
                    Cliente_ID = Integer.parseInt(resultID.getString("ID"));
                }
            }else{
                q = "insert into cliente (Nome, Telefone, Email, Newsletter) " +
                        "SELECT ?, ?, ?, ? FROM dual " +
                        "WHERE NOT EXISTS("+
                        "   SELECT 1 FROM cliente"+
                        "   WHERE Nome = ? AND Email = ? AND Telefone = ?)";
            }
            
            if(!achouCliente){
                try {
                    PreparedStatement pst = conn.prepareStatement(q);
                    pst.setString( 1, InserirFrame.TextNome.getText() );
                    pst.setString( 2, InserirFrame.TextFone.getText() );
                    pst.setString( 3, InserirFrame.TextEmail.getText() );
                    if(InserirFrame.newsfeed.getSelection() != null)
                        pst.setInt( 4, Integer.parseInt(InserirFrame.newsfeed.getSelection().getActionCommand()) );
                    else
                        pst.setNull(4 , java.sql.Types.INTEGER);
                    if(!session.getEdit()){
                        pst.setString( 5, InserirFrame.TextNome.getText() );
                        pst.setString( 6, InserirFrame.TextEmail.getText() );
                        pst.setString( 7, InserirFrame.TextFone.getText() );
                    }
                    pst.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }else{
            Cliente_ID = 0;
        }
        opiniao(Cliente_ID);
        
    }
    
    public static void limparInserir() {
        session.iniciarInserirFrame();
    }
    
    //Insere os dados na tabela OPINIÃO
    public static void opiniao(int Cliente_ID){
        InserirFrame.TextNome.getText();
        
        if(!session.getEdit()){
            int Opiniao_ID = 0;
            Statement stmt;
            try {
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SHOW TABLE STATUS WHERE `Name` = 'opiniao'");
                rs.next();
                Opiniao_ID = Integer.parseInt(rs.getString("Auto_increment"));
                session.setOpiniao(Opiniao_ID);
            } catch (SQLException ex) {
                Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        try {
            String query;
            // the mysql insert statement
            if(session.getEdit()){
                query = "UPDATE opiniao SET Unidade = ?, Data = ?, Origem_opiniao = ?, Atendimento = ?, Variedade_Prod = ?,"
                        + " Qualidade_Prod = ?, Precos = ?, Fila = ?, Estacionamento = ?, Atend_Caixa = ?, Acougue = ?,"
                        + " Padaria = ?, Frios = ?, Hortifruti = ?, Mercearia = ?, Lanchonete = ?, Adega = ?, Pegpese = ?, "
                        + " Cliente_ID = ? WHERE ID = '" + session.getOpiniao() + "'";
            }else{
                query = " insert into opiniao (Unidade, Data, Origem_opiniao, "
                    + "Atendimento, Variedade_Prod, Qualidade_Prod, Precos, Fila, Estacionamento, Atend_Caixa, Acougue, Padaria, "
                    + "Frios, Hortifruti, Mercearia, Lanchonete, Adega, Pegpese, Cliente_ID, Inserido_Por)"
              + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            }
            
            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            if(InserirFrame.Unidade.getSelectedIndex() != 0 || InserirFrame.Unidade.getSelectedIndex() != 5 )
                preparedStmt.setInt (1, InserirFrame.Unidade.getSelectedIndex());
            else
                preparedStmt.setNull(1, java.sql.Types.INTEGER);
            
            try {
                preparedStmt.setDate (2, formatDate(InserirFrame.TextData.getText(),"dd/mm/yyyy", "yyyy-mm-dd"));
            } catch (ParseException ex) {
                Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
            }
            preparedStmt.setInt (3, InserirFrame.Origem_OP.getSelectedIndex()+1);
            
            if(InserirFrame.Atendimento.getSelection()!= null)
                preparedStmt.setInt (4, Integer.parseInt(InserirFrame.Atendimento.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(4, java.sql.Types.INTEGER);
            
            if(InserirFrame.VariedadeProdutos.getSelection()!= null)
                preparedStmt.setInt (5, Integer.parseInt(InserirFrame.VariedadeProdutos.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(5, java.sql.Types.INTEGER);
                
            if(InserirFrame.QualidadeProdutos.getSelection()!= null)
                preparedStmt.setInt (6, Integer.parseInt(InserirFrame.QualidadeProdutos.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(6, java.sql.Types.INTEGER);
            
            if(InserirFrame.Precos.getSelection()!= null)
                preparedStmt.setInt (7, Integer.parseInt(InserirFrame.Precos.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(7, java.sql.Types.INTEGER);
            
            if(InserirFrame.Fila.getSelection()!= null)
                preparedStmt.setInt (8, Integer.parseInt(InserirFrame.Fila.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(8, java.sql.Types.INTEGER);
            
            if(InserirFrame.Estacionamento.getSelection()!= null)
                preparedStmt.setInt (9, Integer.parseInt(InserirFrame.Estacionamento.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(9, java.sql.Types.INTEGER);
            
            if(InserirFrame.AtendimentoCaixa.getSelection()!= null)
                preparedStmt.setInt (10, Integer.parseInt(InserirFrame.AtendimentoCaixa.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(10, java.sql.Types.INTEGER);
            
            if(InserirFrame.Acougue.getSelection()!= null)
                preparedStmt.setInt (11, Integer.parseInt(InserirFrame.Acougue.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(11, java.sql.Types.INTEGER);
            
            if(InserirFrame.Padaria.getSelection()!= null)
                preparedStmt.setInt (12, Integer.parseInt(InserirFrame.Padaria.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(12, java.sql.Types.INTEGER);
            
            if(InserirFrame.Frios.getSelection()!= null)
                preparedStmt.setInt (13, Integer.parseInt(InserirFrame.Frios.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(13, java.sql.Types.INTEGER);
            
            if(InserirFrame.Hortifruti.getSelection()!= null)
                preparedStmt.setInt (14, Integer.parseInt(InserirFrame.Hortifruti.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(14, java.sql.Types.INTEGER);
            
            if(InserirFrame.Mercearia.getSelection()!= null)
                preparedStmt.setInt (15, Integer.parseInt(InserirFrame.Mercearia.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(15, java.sql.Types.INTEGER);
            
            if(InserirFrame.Lanchonete.getSelection()!= null)
                preparedStmt.setInt (16, Integer.parseInt(InserirFrame.Lanchonete.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(16, java.sql.Types.INTEGER);
            
            if(InserirFrame.Adega.getSelection()!= null)
                preparedStmt.setInt (17, Integer.parseInt(InserirFrame.Adega.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(17, java.sql.Types.INTEGER);
            
            if(InserirFrame.Pegpese.getSelection()!= null)
                preparedStmt.setInt (18, Integer.parseInt(InserirFrame.Pegpese.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(18, java.sql.Types.INTEGER);
            
            preparedStmt.setInt (19, Cliente_ID);
            
            if(!session.getEdit()) preparedStmt.setInt (20, session.getID());

            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(!session.getEdit()) tags(session.getOpiniao());
        produto(session.getOpiniao());
        comentario(session.getOpiniao());
        temas(session.getOpiniao());
        session.setEdit(false);
                
    }
    
    public static void produto(int Opiniao_ID){
        try {
            String query;
            // the mysql insert statement
            if(session.getEdit()){
                query = "UPDATE produto_procurado SET Encontrou_Prod = ?, Nome = ?, Aviso_prod = ? WHERE Opiniao_ID = '" + session.getOpiniao() + "'";
            }else{
                query = " insert into produto_procurado (Encontrou_Prod, Nome, Aviso_prod, Opiniao_ID)"
              + " values (?, ?, ?, ?)";
            }

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            if(InserirFrame.EncontrouProduto.getSelection()!= null) 
                preparedStmt.setInt (1, Integer.parseInt(InserirFrame.EncontrouProduto.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(1, java.sql.Types.INTEGER);
                
            preparedStmt.setString (2, InserirFrame.ProcuraProduto.getText());
            
            if(InserirFrame.AvisoProduto.getSelection()!= null) 
                preparedStmt.setInt (3, Integer.parseInt(InserirFrame.AvisoProduto.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(3, java.sql.Types.INTEGER);
                
            if(!session.getEdit()) preparedStmt.setInt (4, Opiniao_ID);

            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void comentario(int Opiniao_ID){
        try {
            String query ;
            // the mysql insert statement
            if(session.getEdit()){
                query = " UPDATE comentario SET Tipo_Comentario = ?, Texto_comentario = ? WHERE Opiniao_ID = '" + session.getOpiniao() + "'";
            }else{
                query = " insert into comentario (Tipo_Comentario, Texto_comentario, Opiniao_ID)"
              + " values (?, ?, ?)";
            }

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            if(InserirFrame.TipoComentario.getSelection() != null)
                preparedStmt.setInt (1, Integer.parseInt(InserirFrame.TipoComentario.getSelection().getActionCommand()));
            else
                preparedStmt.setNull(1, java.sql.Types.INTEGER);
            
            preparedStmt.setString (2, InserirFrame.Comentario.getText());
            if(!session.getEdit()) preparedStmt.setInt (3, Opiniao_ID);
            
            // execute the preparedstatement
            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void tags(int Opiniao_ID){
        try {
            
            if(!session.getEdit()){
                // the mysql insert statement
                String query = " insert into tags (Opiniao_ID, Acompanhar, Importante, Respondido)"
                        + " values (?, 0, 0, 0)";

                // create the mysql insert preparedstatement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1, Opiniao_ID);

                // execute the preparedstatement
                preparedStmt.execute();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void temas(int Opiniao_ID){
        try {
            int i = 0;
            String insert = "";
            String deleteAll = "DELETE FROM rel_tema_opiniao WHERE Opiniao_ID = '" + session.getOpiniao() + "'";
            PreparedStatement stmt = conn.prepareStatement(deleteAll);
            stmt.execute();
            stmt.close();
            for(i=0;i < InserirFrame.listaTemasS.getModel().getSize();i++){
                try {
                    Statement query = conn.createStatement();
                    ResultSet rs = query.executeQuery("SELECT * FROM tema WHERE Tema = '" + InserirFrame.listaTemasS.getModel().getElementAt(i) + "'");
                    // the mysql insert statement
                    rs.next();
                    insert = " INSERT INTO rel_tema_opiniao (Opiniao_ID, Tema_ID)"
                            + " VALUES (?, ?)";
                    
                    // create the mysql insert preparedstatement
                    PreparedStatement preparedStmt = conn.prepareStatement(insert);
                    
                    preparedStmt.setInt(1, Opiniao_ID);
                    preparedStmt.setInt(2, Integer.parseInt(rs.getString("ID")));
                    
                    // execute the preparedstatement
                    preparedStmt.execute();
                } catch (SQLException ex) {
                    Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Inserir.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void fecharInsert() {
        session.fecharInserirFrame();
    }

    public static void iniciarInsert() {
        session.iniciarInserirFrame();
    }

    public static void abrirInsert() {
        session.abrirInserirFrame();
    }
    
    public static void toggleRadio(JRadioButton botao){
        botao.setSelected(!botao.isSelected());
    }

    public static void desativarEdit() {
        session.setEdit(false);
    }
    
}
