
package goc.Frames;

import goc.Sources.Login;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginFrame extends javax.swing.JFrame {

    
    public LoginFrame() throws IOException {
        initComponents();
        
        String root = new File("..").getCanonicalPath();
        
        if(!(root.equals("C:\\Users\\Admin\\Documents\\NetBeansProjects"))){
            java.io.File caminho = new java.io.File(root + "\\src\\goc\\img\\sdsds.jpg");
            uJPanelImagem1.setImagem(caminho);
        }
        
        this.getRootPane().setDefaultButton(buttonEntrar);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uJPanelImagem1 = new componentes.UJPanelImagem();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonEntrar = new javax.swing.JButton();
        textSenha = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        labelSenha = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        textUsuario = new componentes.UJTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GOC - Login");
        setBackground(java.awt.Color.black);
        setMinimumSize(new java.awt.Dimension(336, 356));
        setUndecorated(true);
        setResizable(false);
        setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 25, 25));

        uJPanelImagem1.setImagem(new java.io.File("C:\\Users\\Admin\\Documents\\NetBeansProjects\\GOC\\src\\goc\\img\\sdsds.jpg"));
        uJPanelImagem1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 78)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("G    C");
        uJPanelImagem1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 20, 230, 90));
        jLabel1.getAccessibleContext().setAccessibleName("G         C");

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goc/img/logo.png"))); // NOI18N
        uJPanelImagem1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 10, -1, -1));

        buttonEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goc/img/icoLogin.png"))); // NOI18N
        buttonEntrar.setBorder(new javax.swing.border.MatteBorder(null));
        buttonEntrar.setBorderPainted(false);
        buttonEntrar.setContentAreaFilled(false);
        buttonEntrar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        buttonEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEntrarActionPerformed(evt);
            }
        });
        uJPanelImagem1.add(buttonEntrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 48, 50));
        uJPanelImagem1.add(textSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 140, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/goc/img/icoFechar.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setPreferredSize(new java.awt.Dimension(24, 32));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        uJPanelImagem1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        labelSenha.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        labelSenha.setForeground(new java.awt.Color(255, 255, 255));
        labelSenha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSenha.setText("Senha");
        uJPanelImagem1.add(labelSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 40, 20));

        labelUsuario.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        labelUsuario.setForeground(new java.awt.Color(255, 255, 255));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelUsuario.setText("Usuário");
        uJPanelImagem1.add(labelUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, -1, -1));
        uJPanelImagem1.add(textUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 140, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(uJPanelImagem1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(uJPanelImagem1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
        );

        setBounds(0, 0, 336, 356);
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void buttonEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEntrarActionPerformed
        try {
            Login.logar(this);
        } catch (IOException ex) {
            Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonEntrarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    
    public static void main(String args[]) {
        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       

        
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LoginFrame().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(LoginFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton buttonEntrar;
    public static javax.swing.JButton jButton1;
    public static javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel labelSenha;
    public static javax.swing.JLabel labelUsuario;
    public static javax.swing.JPasswordField textSenha;
    public static componentes.UJTextField textUsuario;
    private componentes.UJPanelImagem uJPanelImagem1;
    // End of variables declaration//GEN-END:variables
}
