
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public final class Profile extends javax.swing.JInternalFrame {

    /**
     * Creates new form Profile
     */
    
    Connection con = null;
    Statement stmt = null;
    public static String userid,lname,fname,originalUID,originalUsername,originalEmail,originalPw;
    public Profile() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI UIE = (BasicInternalFrameUI)this.getUI();
        UIE.setNorthPane(null);
        con = Database_connection_CLASS.connection();
        setDetails(userid);
        
        changeName.setLocationRelativeTo(null);changeUserName.setLocationRelativeTo(null);changeEmailaddress.setLocationRelativeTo(null);changePasswordStep01.setLocationRelativeTo(null);
        changePasswordStep02.setLocationRelativeTo(null);changeSecurityQuestionStep01.setLocationRelativeTo(null);changeSecurityQuestionStep02.setLocationRelativeTo(null);deleteAccountStep01.setLocationRelativeTo(null);
        deleteAccountStep02.setLocationRelativeTo(null);
        
        
    }
    
    public void setDetails( String userid){
    
        try{
            
            stmt = con.createStatement();
            String query = "SELECT * FROM users ";
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
                {
                    if (userid.equals(rs.getString(3)) ||userid.equals(rs.getString(5)) )
                    {
                        Profile.originalUID = rs.getString(2);
                        System.out.println(originalUID);
                        
                        mainName.setText(rs.getString(6) + " "+ rs.getString(7));mainEmail.setText(rs.getString(5));
                        
                        changeFullname.setText(rs.getString(6) + " "+ rs.getString(7));
                        changeUsername.setText(rs.getString(3));
                        changeEmail.setText(rs.getString(5));email.setText(rs.getString(5));
                        changeFname.setText(rs.getString(6));changeLname.setText(rs.getString(7));Profile.originalEmail = rs.getString(7);
                        username.setText(rs.getString(3));Profile.originalUsername = rs.getString(3);
                        
                        Profile.originalPw = rs.getString(4);
                        System.out.println(originalPw);
                        
                        
                        Profile.fname = rs.getString(6);Profile.lname = rs.getString(7);
                          
                    }
                }
        
        }
        catch(SQLException e){
        
        }
    }
    
    public void changeUsername() throws SQLException{
    
        try{
        
            if(changeFname.getText().equals(fname) && changeLname.getText().equals(lname) ){
            
                JOptionPane.showMessageDialog (null, "Nothing changed!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            else{
            
                 System.out.println(originalUID);
                stmt = con.createStatement();
                
                
                String Query = "UPDATE `users` SET `first_name`=? ,`last_name` = ? WHERE `userid` = ?;";
                
                PreparedStatement preparedStmt = con.prepareStatement(Query);
                preparedStmt.setString(1, changeFname.getText());
                preparedStmt.setString(2, changeLname.getText());
                preparedStmt.setString(3, originalUID);
                preparedStmt.execute();
                
                setDetails(userid);
                JOptionPane.showMessageDialog (null, "Name suceessfully changed", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                changeName.setVisible(false);
                
                
                
            }
            
        }
        catch(HeadlessException e){
        
        }
    }
    public void hideErrors(){
    
        try{
            ok.setVisible(false);cansel.setVisible(false);
        }
        catch(Exception e){
        
        }
    }
    
    public void checkUsername(){
    
        try{
            stmt = con.createStatement();
            String userid = username.getText();

            if( username.getText().equals("") )
            {
                 hideErrors();
                cansel.setVisible(true);
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
                        hideErrors();
                        loopcheck = true;
                        cansel.setVisible(true);

                    }
                }
                if(!loopcheck){
                    hideErrors();
                    ok.setVisible(true);
                }
                }
        
        }
        catch(SQLException e){
            System.out.println("Error checking username");
        }
    }
    
    public void updateUsername (){
    
        try{
            
            if(username.getText().equals(originalUsername) ){
            
                JOptionPane.showMessageDialog (null, "Nothing changed!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            else{
            
                
                stmt = con.createStatement();
                
                
                String Query = "UPDATE `users` SET `username`=?  WHERE `userid` = ?;";
                
                PreparedStatement preparedStmt = con.prepareStatement(Query);
                preparedStmt.setString(1, username.getText());
                preparedStmt.setString(2, originalUID);
                preparedStmt.execute();
                
                setDetails(username.getText());
                JOptionPane.showMessageDialog (null, "Username suceessfully changed", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                changeUserName.setVisible(false);
                
                
            }
        }
        catch(HeadlessException | SQLException e){
        
        }
        
        
    }
    
    public void updateEmail (){
    
        try{
            
            if(email.getText().equals(originalEmail) ){
            
                JOptionPane.showMessageDialog (null, "Nothing changed!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
            }
            else{
                
                boolean loopcheck = false;
                stmt = con.createStatement();
                String query = "SELECT * FROM users ";
                String emailch = email.getText();
                ResultSet rsd = stmt.executeQuery(query);
                while(rsd.next())
                {
                    if (emailch.equals(rsd.getString(5)))
                    {
                        
                        loopcheck = true;
                        JOptionPane.showMessageDialog (null, "Email address already exsist", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                        UIManager UI=new UIManager();
                        UIManager.put("OptionPane.background", Color.white);
                        UIManager.put("Panel.background", Color.white);

                    }
                }
                if(!loopcheck){
                    stmt = con.createStatement();
                    String Query = "UPDATE `users` SET `email`=?  WHERE `userid` = ?;";

                    PreparedStatement preparedStmt = con.prepareStatement(Query);
                    preparedStmt.setString(1, email.getText());
                    preparedStmt.setString(2, originalUID);
                    preparedStmt.execute();

                    setDetails(username.getText());
                    JOptionPane.showMessageDialog (null, "email suceessfully changed", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                    UIManager UI=new UIManager();
                    UIManager.put("OptionPane.background", Color.white);
                    UIManager.put("Panel.background", Color.white);
                    changeEmailaddress.setVisible(false);
                
                
                }
 
            }
        }
        catch(HeadlessException | SQLException e){
        
        }
        
        
    }
    
    public void checkPassword (){
    
        try{
        
            if(originalPw.equals(password.getText())){
                changePasswordStep01.setVisible(false);changePasswordStep02.setVisible(true); 
            }
            else{
            
                JOptionPane.showMessageDialog (null, "Password Does not match", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
            }
            
        }
        catch(Exception e){
        
        }
    }
    
    public void changePassword (){
    
        try{
            
            if(password1.getText().equals(password2.getText()) ){
            
                stmt = con.createStatement();
                
                
                String Query = "UPDATE `users` SET `password`=?  WHERE `userid` = ?;";
                
                PreparedStatement preparedStmt = con.prepareStatement(Query);
                preparedStmt.setString(1, password1.getText());
                preparedStmt.setString(2, originalUID);
                preparedStmt.execute();
                
                setDetails(username.getText());
                JOptionPane.showMessageDialog (null, "Password suceessfully changed", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                changePasswordStep02.setVisible(false);
                
            }
            else{
                
                JOptionPane.showMessageDialog (null, "Nothing changed!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
   
            }
        
        }
        catch(Exception e){
        
        }
    }
    
    public void checkPassword2 (){
    
        try{
        
            if(originalPw.equals(password3.getText())){
                changeSecurityQuestionStep01.setVisible(false);changeSecurityQuestionStep02.setVisible(true); 
            }
            else{
            
                JOptionPane.showMessageDialog (null, "Password Does not match", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
            }
            
        }
        catch(Exception e){
        
        }
    }
    
    public void changeSecurityQuestion(){
    
        try{
            
            if(sqAnswer.getText().equals("") ){
            
                
                JOptionPane.showMessageDialog (null, "Nothing changed!", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                
                
            }
            else{
                
                stmt = con.createStatement();
                
                
                String Query = "UPDATE `users` SET `security_question`=?,`security_answer`=?  WHERE `userid` = ?;";
                
                PreparedStatement preparedStmt = con.prepareStatement(Query);
                preparedStmt.setString(1, sqQuestion.getSelectedItem().toString());
                preparedStmt.setString(2, sqAnswer.getText());
                preparedStmt.setString(3, originalUID);
                preparedStmt.execute();
                
                setDetails(username.getText());
                JOptionPane.showMessageDialog (null, "Security Question suceessfully changed", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                changeSecurityQuestionStep02.setVisible(false);
                
                
   
            }
        
        }
        catch(Exception e){
        
        }
    }
    
    public void deleteAccount(){
    
        try{
            if((originalEmail.equals(userName.getText()) || originalUsername.equals(userName.getText()) ) && originalPw.equals(jPasswordField1.getText())){
            
                stmt = con.createStatement();
                String Query3 = "DELETE FROM `users` WHERE `userid` = ?;";
                PreparedStatement preparedStmt2 = con.prepareStatement(Query3);
                preparedStmt2.setString(1, originalUID);
                preparedStmt2.execute();
                
                JOptionPane.showMessageDialog (null, "Your account has been sucessfully deleted PLEASE RE RUN THE PROGRAME AGAIN", "SUCCESS!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
                deleteAccountStep02.setVisible(false);
                
                System.exit(0);
            }
            else{
            
                JOptionPane.showMessageDialog (null, "Username password does not match", "ERROR!", JOptionPane.INFORMATION_MESSAGE);
                UIManager UI=new UIManager();
                UIManager.put("OptionPane.background", Color.white);
                UIManager.put("Panel.background", Color.white);
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

        changeName = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        changeFname = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        changeLname = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        changeUserName = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        ok = new javax.swing.JLabel();
        cansel = new javax.swing.JLabel();
        changeEmailaddress = new javax.swing.JFrame();
        jPanel5 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        changePasswordStep01 = new javax.swing.JFrame();
        jPanel6 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        changePasswordStep02 = new javax.swing.JFrame();
        jPanel7 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        password1 = new javax.swing.JPasswordField();
        password2 = new javax.swing.JPasswordField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        changeSecurityQuestionStep01 = new javax.swing.JFrame();
        jPanel8 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        password3 = new javax.swing.JPasswordField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        changeSecurityQuestionStep02 = new javax.swing.JFrame();
        jPanel9 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        sqQuestion = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        sqAnswer = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        deleteAccountStep01 = new javax.swing.JFrame();
        jPanel11 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        deletedata1 = new javax.swing.JButton();
        deleteAccountStep02 = new javax.swing.JFrame();
        jPanel12 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        userName = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        changeUsernameBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        mainName = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        mainEmail = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        changeUsername = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        changeFullname = new javax.swing.JLabel();
        changeEmail = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        changeSecurity = new javax.swing.JButton();
        changePassword = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        deleteAccount = new javax.swing.JButton();
        changeFullnameBtn = new javax.swing.JButton();
        changeEmailBtn = new javax.swing.JButton();

        changeName.setTitle("Xtreme motors | Change account name");
        changeName.setSize(new java.awt.Dimension(600, 275));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel9.setText("Change full name");

        changeFname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("First name");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Last name");

        changeLname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N

        jButton1.setText("Change name");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBox1.setText(" If changed your username details cannot undone");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeFname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeLname, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeFname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeLname, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox1)
                .addGap(26, 26, 26)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changeNameLayout = new javax.swing.GroupLayout(changeName.getContentPane());
        changeName.getContentPane().setLayout(changeNameLayout);
        changeNameLayout.setHorizontalGroup(
            changeNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changeNameLayout.setVerticalGroup(
            changeNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changeUserName.setTitle("Xtreme motors | Change username");
        changeUserName.setSize(new java.awt.Dimension(475, 275));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setText("Change username");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel12.setText("Change username");

        jLabel13.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Username");

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

        jCheckBox2.setText(" If changed your username details cannot undone");

        ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_ok_20px.png"))); // NOI18N

        cansel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_cancel_20px.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jCheckBox2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(username))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cansel)
                                .addComponent(ok))))
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cansel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changeUserNameLayout = new javax.swing.GroupLayout(changeUserName.getContentPane());
        changeUserName.getContentPane().setLayout(changeUserNameLayout);
        changeUserNameLayout.setHorizontalGroup(
            changeUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changeUserNameLayout.setVerticalGroup(
            changeUserNameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changeEmailaddress.setTitle("Xtreme motors | Change email");
        changeEmailaddress.setSize(new java.awt.Dimension(475, 275));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jButton3.setText("Change Email");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel14.setText("Change email");

        jLabel15.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("email");

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

        jCheckBox3.setText(" If changed your username details cannot undone");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email)))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(144, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jCheckBox3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changeEmailaddressLayout = new javax.swing.GroupLayout(changeEmailaddress.getContentPane());
        changeEmailaddress.getContentPane().setLayout(changeEmailaddressLayout);
        changeEmailaddressLayout.setHorizontalGroup(
            changeEmailaddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changeEmailaddressLayout.setVerticalGroup(
            changeEmailaddressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changePasswordStep01.setTitle("Xtreme motors | Change password ");
        changePasswordStep01.setSize(new java.awt.Dimension(425, 275));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jButton4.setText("Next");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel16.setText("Enter old password");

        jLabel17.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Password");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(password)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changePasswordStep01Layout = new javax.swing.GroupLayout(changePasswordStep01.getContentPane());
        changePasswordStep01.getContentPane().setLayout(changePasswordStep01Layout);
        changePasswordStep01Layout.setHorizontalGroup(
            changePasswordStep01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changePasswordStep01Layout.setVerticalGroup(
            changePasswordStep01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changePasswordStep02.setTitle("Xtreme motors | Change password ");
        changePasswordStep02.setSize(new java.awt.Dimension(700, 375));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jButton5.setText("Change password");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel18.setText("Change your password");

        jLabel19.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("New password");

        jLabel20.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("New password");

        jLabel21.setFont(new java.awt.Font("Segoe UI Semilight", 1, 13)); // NOI18N
        jLabel21.setText("Password must contain");

        jLabel22.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jLabel22.setText("At leaset 1 upper case letter (A-Z)");

        jLabel23.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jLabel23.setText("At leaset 1 number (0-9)");

        jLabel24.setFont(new java.awt.Font("Segoe UI Semilight", 0, 11)); // NOI18N
        jLabel24.setText("at least 8 characters");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(225, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(password2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(password1)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(password1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(password2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changePasswordStep02Layout = new javax.swing.GroupLayout(changePasswordStep02.getContentPane());
        changePasswordStep02.getContentPane().setLayout(changePasswordStep02Layout);
        changePasswordStep02Layout.setHorizontalGroup(
            changePasswordStep02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changePasswordStep02Layout.setVerticalGroup(
            changePasswordStep02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changeSecurityQuestionStep01.setTitle("Xtreme motors | Change security question");
        changeSecurityQuestionStep01.setPreferredSize(new java.awt.Dimension(500, 300));
        changeSecurityQuestionStep01.setSize(new java.awt.Dimension(500, 300));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jButton6.setText("Next");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Password");

        jLabel26.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel26.setText("Enter password");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(password3)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(password3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changeSecurityQuestionStep01Layout = new javax.swing.GroupLayout(changeSecurityQuestionStep01.getContentPane());
        changeSecurityQuestionStep01.getContentPane().setLayout(changeSecurityQuestionStep01Layout);
        changeSecurityQuestionStep01Layout.setHorizontalGroup(
            changeSecurityQuestionStep01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        changeSecurityQuestionStep01Layout.setVerticalGroup(
            changeSecurityQuestionStep01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        changeSecurityQuestionStep02.setTitle("Xtreme motors | Change security question");
        changeSecurityQuestionStep02.setSize(new java.awt.Dimension(700, 300));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jLabel27.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel27.setText("Change security question");

        sqQuestion.setFont(new java.awt.Font("Segoe UI Semilight", 0, 14)); // NOI18N
        sqQuestion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "In what city were you born?", "What is the name of your favorite pet?", "What is your mother's maiden name?", "What high school did you attend?", "What is the name of your first school?", "What was the make of your first car?", "What was your favorite food as a child?", "Where did you meet your spouse?" }));

        jLabel28.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 102, 102));
        jLabel28.setText("Select security question");

        jLabel29.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 102, 102));
        jLabel29.setText("Answer");

        jButton7.setText("Change security question");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sqQuestion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sqAnswer)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sqAnswer)
                    .addComponent(sqQuestion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout changeSecurityQuestionStep02Layout = new javax.swing.GroupLayout(changeSecurityQuestionStep02.getContentPane());
        changeSecurityQuestionStep02.getContentPane().setLayout(changeSecurityQuestionStep02Layout);
        changeSecurityQuestionStep02Layout.setHorizontalGroup(
            changeSecurityQuestionStep02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        changeSecurityQuestionStep02Layout.setVerticalGroup(
            changeSecurityQuestionStep02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        deleteAccountStep01.setTitle("Xtreme motors | Delete user account");
        deleteAccountStep01.setPreferredSize(new java.awt.Dimension(800, 175));
        deleteAccountStep01.setSize(new java.awt.Dimension(700, 200));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jLabel32.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel32.setText("User account will be permanently deleted from the system This action cannot be undone!");

        deletedata1.setBackground(new java.awt.Color(255, 0, 51));
        deletedata1.setForeground(new java.awt.Color(255, 255, 255));
        deletedata1.setText("Delete");
        deletedata1.setPreferredSize(new java.awt.Dimension(63, 30));
        deletedata1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedata1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(deletedata1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(141, 141, 141))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(deletedata1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(54, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout deleteAccountStep01Layout = new javax.swing.GroupLayout(deleteAccountStep01.getContentPane());
        deleteAccountStep01.getContentPane().setLayout(deleteAccountStep01Layout);
        deleteAccountStep01Layout.setHorizontalGroup(
            deleteAccountStep01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        deleteAccountStep01Layout.setVerticalGroup(
            deleteAccountStep01Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        deleteAccountStep02.setTitle("Xtreme motors | Delete user account");
        deleteAccountStep02.setResizable(false);
        deleteAccountStep02.setSize(new java.awt.Dimension(375, 550));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel33.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel33.setText("Erase account details");

        jLabel34.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setText("Password");

        jLabel35.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setText("Username or email");

        jLabel30.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel30.setText("By deleting your account, you will no longer be able to ");

        jLabel31.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel31.setText("on the server. Al of your data and assets will automatically");

        jLabel36.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel36.setText("log into or restore your account or the data you stored ");

        jLabel37.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel37.setText("be deleted from servers in accordance with ");

        jLabel38.setFont(new java.awt.Font("Segoe UI Semilight", 0, 13)); // NOI18N
        jLabel38.setText("any contractual obligation.");

        jLabel39.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel39.setText("Verify your identity");

        jButton8.setText("Permenently Delete Account");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(userName)
                            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPasswordField1)
                            .addComponent(jLabel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel33)
                .addGap(18, 18, 18)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel37)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout deleteAccountStep02Layout = new javax.swing.GroupLayout(deleteAccountStep02.getContentPane());
        deleteAccountStep02.getContentPane().setLayout(deleteAccountStep02Layout);
        deleteAccountStep02Layout.setHorizontalGroup(
            deleteAccountStep02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        deleteAccountStep02Layout.setVerticalGroup(
            deleteAccountStep02Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        changeUsernameBtn.setBackground(new java.awt.Color(53, 126, 199));
        changeUsernameBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_edit_20px.png"))); // NOI18N
        changeUsernameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeUsernameBtnActionPerformed(evt);
            }
        });

        mainName.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        mainName.setForeground(new java.awt.Color(0, 102, 102));
        mainName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainName.setText("Dinusha Weerakoon");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_male_user_120px_1.png"))); // NOI18N

        mainEmail.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        mainEmail.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        mainEmail.setText("Sri lanka");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(mainName, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(mainEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainName, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainEmail)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 22)); // NOI18N
        jLabel2.setText("Settings");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("Delete account");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Full name");

        changeUsername.setFont(new java.awt.Font("Segoe UI Semilight", 0, 22)); // NOI18N
        changeUsername.setText("Company");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Username");

        changeFullname.setFont(new java.awt.Font("Segoe UI Semilight", 0, 22)); // NOI18N
        changeFullname.setText("Company");

        changeEmail.setFont(new java.awt.Font("Segoe UI Semilight", 0, 22)); // NOI18N
        changeEmail.setText("Company");

        jLabel6.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Email");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Basic info");

        changeSecurity.setText("Change security option");
        changeSecurity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSecurityActionPerformed(evt);
            }
        });

        changePassword.setText("change password");
        changePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changePasswordActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Semilight", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Security");

        deleteAccount.setText("Delete Account");
        deleteAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccountActionPerformed(evt);
            }
        });

        changeFullnameBtn.setBackground(new java.awt.Color(53, 126, 199));
        changeFullnameBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_edit_20px.png"))); // NOI18N
        changeFullnameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeFullnameBtnActionPerformed(evt);
            }
        });

        changeEmailBtn.setBackground(new java.awt.Color(53, 126, 199));
        changeEmailBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Icons/icons8_edit_20px.png"))); // NOI18N
        changeEmailBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeEmailBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(changePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(changeSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deleteAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 545, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                    .addComponent(changeFullname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeFullnameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(changeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeUsernameBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(changeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeEmailBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(changeFullname, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(changeFullnameBtn)
                                .addGap(8, 8, 8)))
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(changeUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changeUsernameBtn)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(changeEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(changeSecurity, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(changePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(changeEmailBtn))
                .addContainerGap(193, Short.MAX_VALUE))
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
    }// </editor-fold>//GEN-END:initComponents

    private void changeFullnameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeFullnameBtnActionPerformed
        // TODO add your handling code here:
        changeName.setVisible(true);
    }//GEN-LAST:event_changeFullnameBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        // TODO add your handling code here:
            try{
                changeUsername();
            }
            catch(SQLException e){
            
            }   
    }//GEN-LAST:event_jButton1ActionPerformed

    private void changeUsernameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeUsernameBtnActionPerformed
        // TODO add your handling code here:
        changeUserName.setVisible(true);
    }//GEN-LAST:event_changeUsernameBtnActionPerformed

    private void usernameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyPressed
        // TODO add your handling code here:
        checkUsername();
    }//GEN-LAST:event_usernameKeyPressed

    private void usernameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyReleased
        // TODO add your handling code here:
        checkUsername();
    }//GEN-LAST:event_usernameKeyReleased

    private void usernameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameKeyTyped
        // TODO add your handling code here:
        checkUsername();
    }//GEN-LAST:event_usernameKeyTyped

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         updateUsername ();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        updateEmail();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailKeyPressed

    private void emailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_emailKeyReleased

    private void emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_emailKeyTyped

    private void changeEmailBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeEmailBtnActionPerformed
        // TODO add your handling code here:
        changeEmailaddress.setVisible(true);
    }//GEN-LAST:event_changeEmailBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        checkPassword ();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void changePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changePasswordActionPerformed
        // TODO add your handling code here:
        changePasswordStep01.setVisible(true);
    }//GEN-LAST:event_changePasswordActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        changePassword ();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void changeSecurityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSecurityActionPerformed
        // TODO add your handling code here:'
        changeSecurityQuestionStep01.setVisible(true);
    }//GEN-LAST:event_changeSecurityActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
         checkPassword2 ();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        changeSecurityQuestion();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void deleteAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAccountActionPerformed
        // TODO add your handling code here:
        deleteAccountStep01.setVisible(true);
        
    }//GEN-LAST:event_deleteAccountActionPerformed

    private void deletedata1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedata1ActionPerformed
        // TODO add your handling code here:
        deleteAccountStep01.setVisible(false);deleteAccountStep02.setVisible(true);
        
    }//GEN-LAST:event_deletedata1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        deleteAccount();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cansel;
    private javax.swing.JLabel changeEmail;
    private javax.swing.JButton changeEmailBtn;
    private javax.swing.JFrame changeEmailaddress;
    private javax.swing.JTextField changeFname;
    private javax.swing.JLabel changeFullname;
    private javax.swing.JButton changeFullnameBtn;
    private javax.swing.JTextField changeLname;
    private javax.swing.JFrame changeName;
    private javax.swing.JButton changePassword;
    private javax.swing.JFrame changePasswordStep01;
    private javax.swing.JFrame changePasswordStep02;
    private javax.swing.JButton changeSecurity;
    private javax.swing.JFrame changeSecurityQuestionStep01;
    private javax.swing.JFrame changeSecurityQuestionStep02;
    private javax.swing.JFrame changeUserName;
    private javax.swing.JLabel changeUsername;
    private javax.swing.JButton changeUsernameBtn;
    private javax.swing.JButton deleteAccount;
    private javax.swing.JFrame deleteAccountStep01;
    private javax.swing.JFrame deleteAccountStep02;
    private javax.swing.JButton deletedata1;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel mainEmail;
    private javax.swing.JLabel mainName;
    private javax.swing.JLabel ok;
    private javax.swing.JPasswordField password;
    private javax.swing.JPasswordField password1;
    private javax.swing.JPasswordField password2;
    private javax.swing.JPasswordField password3;
    private javax.swing.JTextField sqAnswer;
    private javax.swing.JComboBox<String> sqQuestion;
    private javax.swing.JTextField userName;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
