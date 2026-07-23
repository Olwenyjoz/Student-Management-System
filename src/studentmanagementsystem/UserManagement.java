
package studentmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class UserManagement extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserManagement.class.getName());

    Connection cn;
    PreparedStatement pst;
    ResultSet rs;
    
    public UserManagement() {
        initComponents();
        
        
    setLocationRelativeTo(null);
    setResizable(false);

    myConnection();
    
    ImageIcon icon = new ImageIcon(getClass().getResource("/resources/icons/app.png"));
    setIconImage(icon.getImage());

    displayUsers();
    
    totalUsers();
    
    //Tabel appearance on selecting a user
    usertable.setSelectionBackground(new java.awt.Color(46,204,113)); 
    usertable.setSelectionForeground(java.awt.Color.WHITE);
    
    //Column widths
    usertable.getColumnModel().getColumn(0).setPreferredWidth(60);   // User ID
    usertable.getColumnModel().getColumn(1).setPreferredWidth(180);  // Full Name
    usertable.getColumnModel().getColumn(2).setPreferredWidth(120);  // Username
    usertable.getColumnModel().getColumn(3).setPreferredWidth(80);   // Role
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fullname = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        password = new javax.swing.JPasswordField();
        role = new javax.swing.JComboBox<>();
        add = new javax.swing.JButton();
        search = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        usertable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        totalusers = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 58, 95));
        jLabel1.setText("STUDENT MANAGEMENT SYSTEM ");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(52, 73, 94));
        jLabel2.setText("USER MANAGEMENT");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(70, 70, 70));
        jLabel3.setText("Full Name: ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(70, 70, 70));
        jLabel4.setText("Username: ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(70, 70, 70));
        jLabel5.setText("Password: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(70, 70, 70));
        jLabel6.setText("Role: ");

        fullname.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        username.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        password.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        role.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", " " }));

        add.setBackground(new java.awt.Color(46, 204, 113));
        add.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Add User");
        add.addActionListener(this::addActionPerformed);

        search.setBackground(new java.awt.Color(52, 152, 219));
        search.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        search.setForeground(new java.awt.Color(255, 255, 255));
        search.setText("Search");
        search.addActionListener(this::searchActionPerformed);

        update.setBackground(new java.awt.Color(243, 156, 18));
        update.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Update");
        update.addActionListener(this::updateActionPerformed);

        delete.setBackground(new java.awt.Color(231, 76, 60));
        delete.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("Delete");
        delete.addActionListener(this::deleteActionPerformed);

        clear.setBackground(new java.awt.Color(149, 165, 166));
        clear.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        clear.setForeground(new java.awt.Color(255, 255, 255));
        clear.setText("Clear");
        clear.addActionListener(this::clearActionPerformed);

        back.setBackground(new java.awt.Color(52, 73, 94));
        back.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("Back");
        back.addActionListener(this::backActionPerformed);

        usertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "User  ID", "Full Name", "Username", "Role"
            }
        ));
        usertable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                usertableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(usertable);
        if (usertable.getColumnModel().getColumnCount() > 0) {
            usertable.getColumnModel().getColumn(1).setResizable(false);
            usertable.getColumnModel().getColumn(2).setResizable(false);
            usertable.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(70, 70, 70));
        jLabel7.setText("Total Users: ");

        totalusers.setEditable(false);
        totalusers.setBackground(new java.awt.Color(255, 255, 255));
        totalusers.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalusers.setForeground(new java.awt.Color(102, 102, 102));
        totalusers.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totalusers.addActionListener(this::totalusersActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(add)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(search)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(update)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(delete)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(clear)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addGap(34, 34, 34)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(fullname, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                                        .addComponent(username)
                                        .addComponent(password))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(back)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(totalusers, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(search)
                    .addComponent(update)
                    .addComponent(delete)
                    .addComponent(clear))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(totalusers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_clearActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        addUser();
    }//GEN-LAST:event_addActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        // TODO add your handling code here:
        searchUser();
    }//GEN-LAST:event_searchActionPerformed

    private void usertableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usertableMouseClicked
        // TODO add your handling code here:
        int row = usertable.getSelectedRow();

    if (row >= 0) {

        fullname.setText(usertable.getValueAt(row, 1).toString());
        username.setText(usertable.getValueAt(row, 2).toString());
        role.setSelectedItem(usertable.getValueAt(row, 3).toString());

        try {

            pst = cn.prepareStatement(
                    "SELECT password FROM users WHERE username=?");

            pst.setString(1, username.getText());

            rs = pst.executeQuery();

            if (rs.next()) {

                password.setText(rs.getString("password"));

            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this,
                    ex.getMessage());

        }

    }

    }//GEN-LAST:event_usertableMouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        updateUser();
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        deleteUser();
    }//GEN-LAST:event_deleteActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        Dashboard dashboard = new Dashboard();

        dashboard.setVisible(true);

        dispose();
    }//GEN-LAST:event_backActionPerformed

    private void totalusersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalusersActionPerformed
        // TODO add your handling code here:
        totalUsers();
    }//GEN-LAST:event_totalusersActionPerformed

    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserManagement().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton back;
    private javax.swing.JButton clear;
    private javax.swing.JButton delete;
    private javax.swing.JTextField fullname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPasswordField password;
    private javax.swing.JComboBox<String> role;
    private javax.swing.JButton search;
    private javax.swing.JTextField totalusers;
    private javax.swing.JButton update;
    private javax.swing.JTextField username;
    private javax.swing.JTable usertable;
    // End of variables declaration//GEN-END:variables

    private void myConnection() {

    try {

        cn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/student_management_system",
                "root",
                "");

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                "Database connection failed.\n" + ex.getMessage());

         }

    }
    
    
    private void clear() {

    fullname.setText("");
    username.setText("");
    password.setText("");
    role.setSelectedIndex(0);

    usertable.clearSelection();

    fullname.requestFocus();

    }
    
    
    private void displayUsers() {

    try {

        DefaultTableModel model = (DefaultTableModel) usertable.getModel();

        model.setRowCount(0);

        pst = cn.prepareStatement(
                "SELECT user_id, full_name, username, role FROM users");

        rs = pst.executeQuery();

        while (rs.next()) {

            model.addRow(new Object[]{

                rs.getInt("user_id"),
                rs.getString("full_name"),
                rs.getString("username"),
                rs.getString("role")

            });
            

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());

        }

    }
    
    
    private void addUser() {

    String fullName = fullname.getText().trim();
    String userName = username.getText().trim();
    String pass = String.valueOf(password.getPassword()).trim();
    String userRole = role.getSelectedItem().toString();

    // Validation
    if (fullName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Full Name.");
        fullname.requestFocus();
        return;
    }

    if (userName.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Username.");
        username.requestFocus();
        return;
    }

    if (pass.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter Password.");
        password.requestFocus();
        return;
    }

    try {

        // Check if username already exists
        pst = cn.prepareStatement(
                "SELECT username FROM users WHERE username=?");

        pst.setString(1, userName);

        rs = pst.executeQuery();

        if (rs.next()) {

            JOptionPane.showMessageDialog(this,
                    "Username already exists.");

            username.requestFocus();
            return;
        }

        // Create User object
        User user = new User();

        user.setFullName(fullName);
        user.setUsername(userName);
        user.setPassword(pass);
        user.setRole(userRole);

        // Insert into database
        pst = cn.prepareStatement(
                "INSERT INTO users(full_name, username, password, role) VALUES(?,?,?,?)");

        pst.setString(1, user.getFullName());
        pst.setString(2, user.getUsername());
        pst.setString(3, user.getPassword());
        pst.setString(4, user.getRole());

        pst.executeUpdate();

        JOptionPane.showMessageDialog(this,
                "User added successfully.");

        clear();
        displayUsers();

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());

        }

    }
    
    
   private void searchUser() {

    String userName = username.getText().trim();

    if (userName.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please enter the Username to search.");

        username.requestFocus();
        return;
    }

    try {

        pst = cn.prepareStatement(
                "SELECT * FROM users WHERE username=?");

        pst.setString(1, userName);

        rs = pst.executeQuery();

        if (rs.next()) {

            fullname.setText(rs.getString("full_name"));
            username.setText(rs.getString("username"));
            password.setText(rs.getString("password"));
            role.setSelectedItem(rs.getString("role"));

            // Highlight the row in JTable
            for (int i = 0; i < usertable.getRowCount(); i++) {

                if (usertable.getValueAt(i, 2).toString().equals(userName)) {

                    usertable.setRowSelectionInterval(i, i);

                    usertable.scrollRectToVisible(
                            usertable.getCellRect(i, 0, true));

                    break;
                }
            }

        } else {

            JOptionPane.showMessageDialog(this,
                    "User not found.");

            clear();

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());

        }

    }
   
   
   
   private void updateUser() {

    String fullName = fullname.getText().trim();
    String userName = username.getText().trim();
    String pass = String.valueOf(password.getPassword()).trim();
    String userRole = role.getSelectedItem().toString();

    // Validation
    if (userName.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please enter Username.");

        username.requestFocus();
        return;
    }

    if (pass.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please enter Password.");

        password.requestFocus();
        return;
    }

    // Confirmation
    int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to update this user?",
            "Confirm Update",
            JOptionPane.YES_NO_OPTION);

    if (option != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        // SQL Update Statement
        String sql = "UPDATE users "
                   + "SET full_name=?, password=?, role=? "
                   + "WHERE username=?";

        pst = cn.prepareStatement(sql);

        pst.setString(1, fullName);
        pst.setString(2, pass);
        pst.setString(3, userRole);
        pst.setString(4, userName);

        int updated = pst.executeUpdate();

        if (updated > 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "User updated successfully.");

            displayUsers();

            // Highlight the updated user
            for (int i = 0; i < usertable.getRowCount(); i++) {

                if (usertable.getValueAt(i, 2).toString().equals(userName)) {

                    usertable.setRowSelectionInterval(i, i);

                    usertable.scrollRectToVisible(
                            usertable.getCellRect(i, 0, true));

                    break;
                }
            }

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "User not found.");

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(
                this,
                ex.getMessage());

        }

    }
   
   
   private void deleteUser() {

    String userName = username.getText().trim();

    if (userName.isEmpty()) {

        JOptionPane.showMessageDialog(this,
                "Please select or search a user to delete.");

        username.requestFocus();
        return;
    }

    int option = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete this user?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION);

    if (option != JOptionPane.YES_OPTION) {
        return;
    }

    try {

        pst = cn.prepareStatement(
                "DELETE FROM users WHERE username=?");

        pst.setString(1, userName);

        int deleted = pst.executeUpdate();

        if (deleted > 0) {

            JOptionPane.showMessageDialog(this,
                    "User deleted successfully.");

            displayUsers();
            clear();

        } else {

            JOptionPane.showMessageDialog(this,
                    "User not found.");

        }

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage());

        }

    }
   
   
   private void totalUsers() {
    try {
        String sql = "SELECT COUNT(*) AS total FROM users";
        pst = cn.prepareStatement(sql);
        rs = pst.executeQuery();

        if (rs.next()) {
            totalusers.setText(rs.getString("total"));
        }

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}

