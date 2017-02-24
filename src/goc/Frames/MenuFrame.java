
package goc.Frames;

import goc.Sources.Sessao;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class MenuFrame extends javax.swing.JFrame {

    
    
    Sessao session = Sessao.getInstance();
    public MenuFrame() throws IOException {
        initComponents();
        
        String root = new File("..").getCanonicalPath();
        
        if(!(root.equals("C:\\Users\\Admin\\Documents\\NetBeansProjects"))){
            java.io.File caminho = new java.io.File(root + "\\src\\goc\\img\\sdsds.jpg");
            uJPanelImagem1.setImagem(caminho);
        }
        session.setEdit(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        if(session.getNome() != null)
            jLabelSaudacao.setText("Olá " + session.getNome() + "!");
        
    }

    
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uJPanelImagem1 = new componentes.UJPanelImagem();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonRelatorios = new javax.swing.JButton();
        buttonInserir = new javax.swing.JButton();
        buttonVisualizar = new javax.swing.JButton();
        jLabelSaudacao = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 153, 0));
        setUndecorated(true);
        setResizable(false);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));

        uJPanelImagem1.setBackground(new java.awt.Color(51, 153, 0));
        uJPanelImagem1.setImagem(new java.io.File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\GOC\\src\\goc\\img\\sdsds.jpg"));
        uJPanelImagem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goc/img/icoLogout.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        uJPanelImagem1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 48, 48));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 78)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("G    C");
        uJPanelImagem1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 230, 90));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goc/img/logo.png"))); // NOI18N
        uJPanelImagem1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, -1, -1));

        buttonRelatorios.setText("Visualizar Relatórios");
        buttonRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRelatoriosActionPerformed(evt);
            }
        });
        uJPanelImagem1.add(buttonRelatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 320, 160, 70));

        buttonInserir.setText("Inserir Opinião");
        buttonInserir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonInserirActionPerformed(evt);
            }
        });
        uJPanelImagem1.add(buttonInserir, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 160, 70));

        buttonVisualizar.setText("Visualizar Opinião");
        buttonVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVisualizarActionPerformed(evt);
            }
        });
        uJPanelImagem1.add(buttonVisualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 320, 160, 70));

        jLabelSaudacao.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        jLabelSaudacao.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSaudacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        uJPanelImagem1.add(jLabelSaudacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 780, 80));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goc/img/usuario.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        uJPanelImagem1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 48, 48));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uJPanelImagem1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uJPanelImagem1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        setBounds(0, 0, 800, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        try {
            new LoginFrame().setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void buttonInserirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonInserirActionPerformed
        if(session.getPerms()){
            session.abrirInserirFrame();
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Acesso Negado!", "", 0);
        }
    }//GEN-LAST:event_buttonInserirActionPerformed

    private void buttonVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVisualizarActionPerformed
        session.abrirViewFrame();
        this.dispose();
    }//GEN-LAST:event_buttonVisualizarActionPerformed

    private void buttonRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRelatoriosActionPerformed
        new RelatorioFrame().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_buttonRelatoriosActionPerformed

    
    public static void main(String args[]) {
       
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new MenuFrame().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(MenuFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonInserir;
    private javax.swing.JButton buttonRelatorios;
    private javax.swing.JButton buttonVisualizar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelSaudacao;
    private componentes.UJPanelImagem uJPanelImagem1;
    // End of variables declaration//GEN-END:variables
}
