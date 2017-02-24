/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goc.Sources;

import goc.Frames.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author Admin
 */
public class Sessao {
    //<editor-fold defaultstate="collapsed" desc=" Declaração de variáveis estáticas globais ">
    private static Sessao instance;
    private static boolean perms_add;
    private static String nome;
    private static int ID;
    private static int Opiniao = 0;
    private static boolean edit = false;
    private static String select;
    private static ViewFrame visualizador;
    private static InserirFrame insercao;
    //</editor-fold>
    
    // Construtor privado (suprime o construtor público padrão).
    private Sessao() {}

    // Método público estático de acesso único ao objeto!
    public static Sessao getInstance() {
        if (instance == null)
            instance = new Sessao();
        return instance;
    }

    //<editor-fold defaultstate="collapsed" desc=" Iniciadores ">
    public void iniciarViewFrame(){
        try {
            visualizador = new ViewFrame();
        } catch (IOException ex) {
            Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void iniciarInserirFrame(){
        try {
            insercao = new InserirFrame();
        } catch (IOException ex) {
            Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Sessao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Setters ">
    public void setPerms(boolean perms_add){
        Sessao.perms_add = perms_add;
    }
    public void setNome(String nome){
        Sessao.nome = nome;
    }
    public void setID(int ID){
        Sessao.ID = ID;
    }
    public void setOpiniao(int Opiniao){
        Sessao.Opiniao = Opiniao;
    }
    public void setEdit(boolean edit){
        Sessao.edit = edit;
    }
    public void setResult(String select){
        Sessao.select = select;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Abrir e fechar frames ">
    public void abrirViewFrame(){
        visualizador.setVisible(true);
        View.atualizarDados();
    }
    public void fecharViewFrame(){
        visualizador.dispose();
        View.atualizarDados();
    }
    public void abrirInserirFrame(){
        insercao.setVisible(true);
        Inserir.carregarTemas();
        Inserir.carregarDados();
    }
    public void fecharInserirFrame(){
        insercao.dispose();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc=" Getters ">
    public boolean getPerms(){
        return Sessao.perms_add;
    }
    public String getNome(){
        return Sessao.nome;
    }
    public int getID(){
        return Sessao.ID;
    }
    public int getOpiniao(){
        return Sessao.Opiniao;
    }
    public boolean getEdit(){
        return Sessao.edit;
    }
    public String getSelect(){
        return Sessao.select;
    }
    //</editor-fold>
}
