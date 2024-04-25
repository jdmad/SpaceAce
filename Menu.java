package spaceace;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.JFrame;

public class Menu extends javax.swing.JFrame {

    public Menu(GameStates gs) {
        initComponents();
        setSize(ScreenDimensions.width, ScreenDimensions.height);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TutorialButton = new javax.swing.JButton();
        StartGameButton = new javax.swing.JButton();
        LoadGameButton = new javax.swing.JButton();
        OptionsButton = new javax.swing.JButton();
        CreditsButton = new javax.swing.JButton();
        ExitGameButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SpaceAce");
        setName("MenuFrame"); // NOI18N

        TutorialButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TutorialButton.setText("Tutorial");
        TutorialButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        StartGameButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        StartGameButton.setText("Start Game");
        StartGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartGameButtonActionPerformed(evt);
            }
        });

        LoadGameButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        LoadGameButton.setText("Load Game");

        OptionsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        OptionsButton.setText("Options");
        OptionsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptionsButtonActionPerformed(evt);
            }
        });

        CreditsButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CreditsButton.setText("Credits");
        CreditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditsButtonActionPerformed(evt);
            }
        });

        ExitGameButton.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        ExitGameButton.setText("Exit Game");
        ExitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitGameButtonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SpaceAceMenuScreen.png"))); // NOI18N
        jLabel1.setIconTextGap(0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(TutorialButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(StartGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LoadGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OptionsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CreditsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ExitGameButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(325, 325, 325))
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(144, 144, 144))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(TutorialButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(StartGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoadGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(OptionsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ExitGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StartGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartGameButtonActionPerformed
        SpaceAce s = new SpaceAce();                //declare new instance of Game Window
        
        s.setExtendedState(JFrame.MAXIMIZED_BOTH);  //set window height and width to maximized screen
        
        //set window icon to file from SpaceAce src folder
        URL myURL = ClassLoader.getSystemResource("images/SpaceAceIcon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image myImage = kit.createImage(myURL);
        s.setIconImage(myImage);
        
        Thread t1 = new Thread(s);                  //create new thread with SpaceAce class as argument
        t1.start();
        this.dispose();
    }//GEN-LAST:event_StartGameButtonActionPerformed

    private void ExitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitGameButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_ExitGameButtonActionPerformed

    private void CreditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditsButtonActionPerformed
        Credits credits = new Credits();
        this.dispose();
        credits.setVisible(true);
    }//GEN-LAST:event_CreditsButtonActionPerformed

    private void OptionsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptionsButtonActionPerformed
        Options options = new Options();
        this.dispose();
        options.setVisible(true);
    }//GEN-LAST:event_OptionsButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //Icon introIcon;
        //jLabel1.setIcon(introIcon);
        
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GameStates gs2 = GameStates.MENU;
                new Menu(gs2).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreditsButton;
    private javax.swing.JButton ExitGameButton;
    private javax.swing.JButton LoadGameButton;
    private javax.swing.JButton OptionsButton;
    private javax.swing.JButton StartGameButton;
    private javax.swing.JButton TutorialButton;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
