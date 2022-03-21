
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public final class Signup extends javax.swing.JFrame {

    /**
     * Creates new form SignUpPage01
     */
    
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    String loop1,loop2,newUserID;
    
    public Signup() {
        FlatLightLaf.setup();
        initComponents();
        
        con = Database_connection_CLASS.connection();
        generateUserID ();
        hideErrors1();hideErrors2();
   
    }
    public void generateUserID (){
    
        try{
            String itemidset = null;
            stmt = con.createStatement();
            ResultSet rsd = stmt.executeQuery("SELECT MAX(userid) FROM users");
            rsd.next();
            rsd.getString("MAX(userid)");
            if (rsd.getString("MAX(userid)") == null) {
                itemidset = "u00001";System.out.println(itemidset);
                this.newUserID = itemidset;
            } 
            else {
                long id = Long.parseLong(rsd.getString("MAX(userid)").substring(2, rsd.getString("MAX(userid)").length()));
                id++;
                itemidset = ("u" + String.format("%05d", id));
                System.out.println(itemidset);
                this.newUserID = itemidset;
            }
        }
        catch(NumberFormatException | SQLException e){
            System.out.println("Error Genarate user id");
        }
    }
    
    public void hideErrors1(){
    
        try{
            ok.setVisible(false);cansel.setVisible(false);        
        }
        catch(Exception e){
            System.out.println("Error disabling error icons");
        }
    }
     public void hideErrors2(){
    
        try{
            ok1.setVisible(false);cansel1.setVisible(false);
        
        }
        catch(Exception e){
            System.out.println("Error disabling error icons");
        }
    }
    
    public void usernameCheck(){
    
        try{
            stmt = con.createStatement();
            String userid = username.getText();

            if( username.getText().equals("") )
            {
                hideErrors1();
                cansel.setVisible(true);
                this.loop1 = "1";
            }
            else
            {
                boolean loopcheck = false;
                stmt = con.createStatement();
                String query = "SELECT * FROM users ";
                String Username = username.getText();
                ResultSet rsd = stmt.executeQuery(query);
                while(rsd.next())
                {
                    if (Username.equals(rsd.getString(3)))
                    {
                        hideErrors1();
                        loopcheck = true;
                        cansel.setVisible(true);
                        this.loop1 = "1";
                    }
                }
                if(!loopcheck){
                    hideErrors1();
                    ok.setVisible(true);
                    this.loop1 = "2";
                }
                }
        
        }
        catch(SQLException e){
            System.out.println("Error checking username");
        }
    }
    
    public void emalCheck(){
    
        try{
            stmt = con.createStatement();
            String Email = email.getText();

            if( email.getText().equals("") )
            {
                hideErrors2();
                cansel1.setVisible(true);
                this.loop2 = "1";
            }
            else
            {
                boolean loopcheck = false;
                stmt = con.createStatement();
                String query = "SELECT * FROM users ";
                String EmailCheck = email.getText();
                ResultSet rsd = stmt.executeQuery(query);
                while(rsd.next())
                {
                    if (EmailCheck.equals(rsd.getString(5)))
                    {
                        hideErrors2();
                        loopcheck = true;
                        cansel1.setVisible(true);
                        this.loop2 = "1";
                    }
                }
                if(!loopcheck){
                    hideErrors2();
                    ok1.setVisible(true);
                    this.loop2 = "2";
                }
                }
        
        }
        catch(SQLException e){
            System.out.println("Error checking username");
        }
    }
    
    public void proceed (){
    
        try{
            if("".equals(username.getText()) || "".equals(email.getText()) || "".equals(firstname.getText()) || "".equals(lastname.getText()) || "".equals(password.getText()) || "".equals(confirmpassword.getText()) || "".equals(securityAnswer.getText()) ){
            
                JOptionPane.showMessageDialog (null, "feilds are empty", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            else{
                
                if("1".equals(loop1) || "1".equals(loop2))
                {
                    JOptionPane.showMessageDialog (null, "Something went wrong", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                    UIManager UI=new UIManager();
                    UIManager.put("OptionPane.background", Color.white);
                    UIManager.put("Panel.background", Color.white);
                }      
                else{
                       
                    if(!password.getText().equals(confirmpassword.getText())){
                        
                        JOptionPane.showMessageDialog (null, "Passwords does not match", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                        UIManager UI=new UIManager();
                        UIManager.put("OptionPane.background", Color.white);
                        UIManager.put("Panel.background", Color.white);
                    }
                    else{
                        
                        saveData();
                    }
                }
            }       
            
        }
        catch(HeadlessException e){
            System.out.println("Error cheking data");
        }
    }
    
    public void saveData(){
        try{
            
            stmt = con.createStatement();
            String query = "INSERT INTO users (userid, username, password, email, first_name, last_name,security_question, security_answer) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, newUserID);
            preparedStmt.setString(2, username.getText());
            preparedStmt.setString(3, password.getText());
            preparedStmt.setString(4, email.getText());
            preparedStmt.setString(5, firstname.getText());
            preparedStmt.setString(6, lastname.getText());
            preparedStmt.setString(7, securityQuestion.getSelectedItem().toString());
            preparedStmt.setString(8, securityAnswer.getText());
            preparedStmt.execute();
            
            JOptionPane.showMessageDialog (null, "Account has been created successfully", "success!", JOptionPane.INFORMATION_MESSAGE);
            UIManager UI=new UIManager();
            UIManager.put("OptionPane.background", Color.white);
            UIManager.put("Panel.background", Color.white);
            
            this.setVisible(false);
            Login ld = new Login();
            ld.setVisible(true);
        
        }
        catch(HeadlessException | SQLException e){
            System.out.println("error saving data");
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

        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        button1 = new Assests.Button();
        firstname = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        lastname = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        ok = new javax.swing.JLabel();
        cansel = new javax.swing.JLabel();
        cansel1 = new javax.swing.JLabel();
        ok1 = new javax.swing.JLabel();
        confirmpassword = new javax.swing.JPasswordField();
        password = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        securityQuestion = new javax.swing.JComboBox<>();
        securityAnswer = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        BackgroundImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 50, 72));
        jLabel2.setText("Create New  Account");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 90, 560, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jLabel4.setText("One account. All of Google working for you.");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 630, 20));

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel6.setText("Username");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 400, 30));

        username.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        username.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usernameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameKeyTyped(evt);
            }
        });
        jPanel3.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 400, 40));

        email.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                emailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                emailKeyTyped(evt);
            }
        });
        jPanel3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 400, 40));

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setText("Email ");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 250, 400, 30));

        button1.setBackground(new java.awt.Color(0, 98, 102));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Create Account");
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel3.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 640, 230, 40));

        firstname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel3.add(firstname, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 240, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel8.setText("Answer");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 490, 160, 30));

        lastname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        lastname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastnameActionPerformed(evt);
            }
        });
        jPanel3.add(lastname, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 360, 240, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel9.setText("Last name");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 330, 160, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel10.setText("Password");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 410, 160, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel11.setText("Confirm password");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 410, 160, 30));

        jCheckBox1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jCheckBox1.setText("by creating an account you agree to the terms and conditions of company");
        jPanel3.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 590, 510, -1));

        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_ok_20px.png"))); // NOI18N
        jPanel3.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 200, -1, 40));

        cansel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_cancel_20px.png"))); // NOI18N
        jPanel3.add(cansel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 200, -1, 40));

        cansel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_cancel_20px.png"))); // NOI18N
        jPanel3.add(cansel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 280, -1, 40));

        ok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_ok_20px.png"))); // NOI18N
        jPanel3.add(ok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 280, -1, 40));

        confirmpassword.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel3.add(confirmpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 440, 240, 40));

        password.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel3.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 240, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("First name");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 330, 160, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel13.setText("Security question");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 490, 160, 30));

        securityQuestion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        securityQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?", "What high school did you attend?", "What is the name of your first school?", "What was the make of your first car?", "What was your favorite food as a child?", "Where did you meet your spouse?" }));
        securityQuestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                securityQuestionActionPerformed(evt);
            }
        });
        jPanel3.add(securityQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 520, 240, 40));

        securityAnswer.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        jPanel3.add(securityAnswer, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 520, 240, 40));

        jButton1.setBackground(new java.awt.Color(53, 126, 199));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_back_to_20px_1.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 700, 100, 30));

        BackgroundImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/MEMO2-01.png"))); // NOI18N
        BackgroundImage.setText("jLabel1");
        jPanel3.add(BackgroundImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1400, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lastnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastnameActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        try{
        
            usernameCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_usernameKeyPressed

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
        // TODO add your handling code here:
        try{
        
            usernameCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_usernameKeyReleased

    private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
        // TODO add your handling code here:
        try{
        
            usernameCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_usernameKeyTyped

    private void emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyPressed
        // TODO add your handling code here:
        try{
        
            emalCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_emailKeyPressed

    private void emailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyReleased
        // TODO add your handling code here:
        try{
        
            emalCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_emailKeyReleased

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
        // TODO add your handling code here:
        try{
        
            emalCheck();
        }
        catch(Exception e){
        
            System.out.println("Error ckecking");
        }
    }//GEN-LAST:event_emailKeyTyped

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
        try{
        
            proceed();
        }
        catch(Exception e){
            System.out.println("Error in save button");
        }
    }//GEN-LAST:event_button1ActionPerformed

    private void securityQuestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_securityQuestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_securityQuestionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Login load = new Login ();
        load.setVisible (true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BackgroundImage;
    private Assests.Button button1;
    private javax.swing.JLabel cansel;
    private javax.swing.JLabel cansel1;
    private javax.swing.JPasswordField confirmpassword;
    private javax.swing.JTextField email;
    private javax.swing.JTextField firstname;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField lastname;
    private javax.swing.JLabel ok;
    private javax.swing.JLabel ok1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField securityAnswer;
    private javax.swing.JComboBox<String> securityQuestion;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
