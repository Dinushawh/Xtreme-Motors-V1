
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public final class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    
    Connection con = null;
    Statement stmt = null;
    
    public static String userid;
    
    public Home() {
        FlatLightLaf.setup();
        
        initComponents();
        
        con = Database_connection_CLASS.connection();
        
        setVales();
        System.out.println(userid);
        
        Viewall view = new Viewall ();
        jDesktopPane1.removeAll();
        jDesktopPane1.add(view).setVisible(true);
       
    }
    
    
    
    public void setVales(){
    
        try{
            stmt = con.createStatement();
            String query = "SELECT * FROM users ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
                {
                    if (userid.equals(rs.getString(3)) || userid.equals(rs.getString(5)))
                    {
                       
                        name.setText(rs.getString(6)+ " "+ rs.getString(7));
                        
                    }
                }
        }
        catch(Exception e){
        
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dashBoard = new javax.swing.JButton();
        vehicalDetails = new javax.swing.JButton();
        dashBoard2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        dashBoard4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        dashBoard.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        dashBoard.setForeground(new java.awt.Color(51, 51, 51));
        dashBoard.setText("Auto loan calculator");
        dashBoard.setContentAreaFilled(false);
        dashBoard.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dashBoard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashBoard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashBoardActionPerformed(evt);
            }
        });

        vehicalDetails.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        vehicalDetails.setForeground(new java.awt.Color(51, 51, 51));
        vehicalDetails.setText("View all vehicals");
        vehicalDetails.setContentAreaFilled(false);
        vehicalDetails.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        vehicalDetails.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        vehicalDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vehicalDetailsActionPerformed(evt);
            }
        });

        dashBoard2.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        dashBoard2.setForeground(new java.awt.Color(51, 51, 51));
        dashBoard2.setText("Add new vehical");
        dashBoard2.setContentAreaFilled(false);
        dashBoard2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dashBoard2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        dashBoard2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashBoard2ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/loginLogo-01.png"))); // NOI18N

        dashBoard4.setFont(new java.awt.Font("Segoe UI Light", 0, 18)); // NOI18N
        dashBoard4.setForeground(new java.awt.Color(51, 51, 51));
        dashBoard4.setText("Logout");
        dashBoard4.setContentAreaFilled(false);
        dashBoard4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dashBoard4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashBoard4ActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_male_user_50px.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        name.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        name.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        name.setText("jLabel3");
        name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1375, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vehicalDetails, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dashBoard, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                            .addComponent(dashBoard2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dashBoard4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(vehicalDetails, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dashBoard2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dashBoard, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 438, Short.MAX_VALUE)
                        .addComponent(dashBoard4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDesktopPane1))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashBoard4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashBoard4ActionPerformed
        // TODO add your handling code here:
        try{
            this.setVisible(false);
            Login load = new Login ();
            load.setVisible(true);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_dashBoard4ActionPerformed

    private void dashBoard2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashBoard2ActionPerformed
        // TODO add your handling code here:
        try{
            Addnewvehical view = new Addnewvehical ();
            jDesktopPane1.removeAll();
            jDesktopPane1.add(view).setVisible(true);
            
        }
        catch(Exception e){}
    }//GEN-LAST:event_dashBoard2ActionPerformed

    private void vehicalDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vehicalDetailsActionPerformed
        // TODO add your handling code here:
        try{
        
            Viewall view = new Viewall();
            jDesktopPane1.removeAll();
            jDesktopPane1.add(view).setVisible(true);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_vehicalDetailsActionPerformed

    private void dashBoardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashBoardActionPerformed
        // TODO add your handling code here:
        try
        {
            LoanCalculator view = new LoanCalculator ();
            jDesktopPane1.removeAll();
            jDesktopPane1.add(view).setVisible(true);

        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_dashBoardActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        try{
        
            Profile view = new Profile();
            jDesktopPane1.removeAll();
            jDesktopPane1.add(view).setVisible(true);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMouseClicked
        // TODO add your handling code here:
         try{
        
            Profile view = new Profile();
            jDesktopPane1.removeAll();
            jDesktopPane1.add(view).setVisible(true);
        }
        catch(Exception e){
        
        }
    }//GEN-LAST:event_nameMouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dashBoard;
    private javax.swing.JButton dashBoard2;
    private javax.swing.JButton dashBoard4;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel name;
    private javax.swing.JButton vehicalDetails;
    // End of variables declaration//GEN-END:variables
}
