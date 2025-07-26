import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.text.SimpleDateFormat; // Import SimpleDateFormat
import java.util.Date; // Import java.util.Date

public final class RegistrationForm extends javax.swing.JFrame {

    // Removed the private JTextArea jTextAreaAddress as per previous correction
    // if not already done. jTextArea1 is used for address.

    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registration_db", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection Failed: " + e.getMessage());
        }
        return con;
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try {
            Connection con = getConnection();
            PreparedStatement pst = con.prepareStatement("SELECT * FROM users");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("contact")
                });
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    public RegistrationForm() {
        initComponents();

        // Ensure jTextArea1 is used for address, not a newly instantiated jTextAreaAddress
        // If you had:
        // jTextAreaAddress = new javax.swing.JTextArea();
        // jTextAreaAddress.setColumns(20);
        // jTextAreaAddress.setRows(5);
        // jScrollPane1.setViewportView(jTextAreaAddress);
        // REMOVE THOSE LINES.

        loadTable();

        buttonSubmit.addActionListener(e -> handleSubmit());
        buttonReset.addActionListener(e -> handleReset());
        buttonExit.addActionListener(e -> handleExit());
    }

    private void handleSubmit() {
        String id = jTextFieldID.getText();
        String name = jTextFieldName.getText();
        String contact = jTextFieldContact.getText();
        String gender = jRadioButtonMale.isSelected() ? "Male" : jRadioButtonFemale.isSelected() ? "Female" : "";
        String address = jTextArea1.getText(); // Use jTextArea1 for address

        // Get the Date object from JDateChooser
        Date selectedDate = jDateChooserDOB.getDate();
        String dob = ""; // Initialize dob string

        if (selectedDate != null) {
            // Define the desired date format for MySQL
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dob = sdf.format(selectedDate);
        } else {
            // If no date is selected, dob will remain empty, which will be caught by validation
        }

        if (id.isEmpty() || name.isEmpty() || contact.isEmpty() || gender.isEmpty() || dob.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            Connection con = getConnection();
            String sql = "INSERT INTO users (id, name, gender, dob, address, contact) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, Integer.parseInt(id));
            pst.setString(2, name);
            pst.setString(3, gender);
            pst.setString(4, dob); // Use the formatted dob string
            pst.setString(5, address);
            pst.setString(6, contact);

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registered Successfully!");

            con.close();
            handleReset(); // Reset the form after successful submission
            loadTable(); // Reload the table to show the new entry
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void handleReset() {
        jTextFieldID.setText("");
        jTextFieldName.setText("");
        jTextFieldContact.setText("");
        jTextArea1.setText(""); // Use jTextArea1 for address
        jDateChooserDOB.setDate(null);
        GenderButtonGroup.clearSelection();
    }

    private void handleExit() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
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

        GenderButtonGroup = new javax.swing.ButtonGroup();
        jLabelID = new javax.swing.JLabel();
        jLabelName = new javax.swing.JLabel();
        jLabelGender = new javax.swing.JLabel();
        jLabelDOB = new javax.swing.JLabel();
        jLabelAddress = new javax.swing.JLabel();
        jLabelContact = new javax.swing.JLabel();
        jLabelRegistrationForm = new javax.swing.JLabel();
        jTextFieldID = new javax.swing.JTextField();
        jTextFieldContact = new javax.swing.JTextField();
        jTextFieldName = new javax.swing.JTextField();
        jTextAreaAdress = new java.awt.ScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButtonMale = new javax.swing.JRadioButton();
        jRadioButtonFemale = new javax.swing.JRadioButton();
        checkbox1 = new java.awt.Checkbox();
        buttonSubmit = new java.awt.Button();
        buttonReset = new java.awt.Button();
        buttonExit = new java.awt.Button();
        jDateChooserDOB = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("REGISTRTATION FORM");
        setName(""); // NOI18N

        jLabelID.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelID.setText("ID");

        jLabelName.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelName.setText("Name");

        jLabelGender.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelGender.setText("Gender");

        jLabelDOB.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelDOB.setText("DOB");

        jLabelAddress.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelAddress.setText("Address");

        jLabelContact.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelContact.setText("Contact");

        jLabelRegistrationForm.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jLabelRegistrationForm.setText("Registration Form");

        jTextFieldID.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jTextFieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDActionPerformed(evt);
            }
        });

        jTextFieldContact.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jTextFieldContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldContactActionPerformed(evt);
            }
        });

        jTextFieldName.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jTextAreaAdress.add(jScrollPane2);

        GenderButtonGroup.add(jRadioButtonMale);
        jRadioButtonMale.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jRadioButtonMale.setText("Male");
        jRadioButtonMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMaleActionPerformed(evt);
            }
        });

        GenderButtonGroup.add(jRadioButtonFemale);
        jRadioButtonFemale.setFont(new java.awt.Font("Arial Narrow", 1, 14)); // NOI18N
        jRadioButtonFemale.setText("Female");

        checkbox1.setLabel("Accept Terms and Conditions");

        buttonSubmit.setLabel("Submit");

        buttonReset.setLabel("Reset");

        buttonExit.setLabel("Exit");

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "id", "name", "gender", "dob", "address", "contact"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Long.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabelRegistrationForm))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelAddress)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextAreaAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelName)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelGender)
                                    .addComponent(jLabelDOB)
                                    .addComponent(jLabelID))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jRadioButtonMale)
                                                .addGap(18, 18, 18)
                                                .addComponent(jRadioButtonFemale))
                                            .addComponent(jDateChooserDOB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(jTextFieldID))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelContact)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldContact, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(69, 69, 69)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelRegistrationForm)
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelName))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelContact)
                            .addComponent(jTextFieldContact, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelID)
                            .addComponent(jTextFieldID, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelGender)
                            .addComponent(jRadioButtonMale)
                            .addComponent(jRadioButtonFemale))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelDOB)
                            .addComponent(jDateChooserDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAddress)
                            .addComponent(jTextAreaAdress, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButtonMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButtonMaleActionPerformed

    private void jTextFieldContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldContactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldContactActionPerformed

    private void jTextFieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDActionPerformed


     public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RegistrationForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new RegistrationForm().setVisible(true));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GenderButtonGroup;
    private java.awt.Button buttonExit;
    private java.awt.Button buttonReset;
    private java.awt.Button buttonSubmit;
    private java.awt.Checkbox checkbox1;
    private com.toedter.calendar.JDateChooser jDateChooserDOB;
    private javax.swing.JLabel jLabelAddress;
    private javax.swing.JLabel jLabelContact;
    private javax.swing.JLabel jLabelDOB;
    private javax.swing.JLabel jLabelGender;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelName;
    private javax.swing.JLabel jLabelRegistrationForm;
    private javax.swing.JRadioButton jRadioButtonFemale;
    private javax.swing.JRadioButton jRadioButtonMale;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private java.awt.ScrollPane jTextAreaAdress;
    private javax.swing.JTextField jTextFieldContact;
    private javax.swing.JTextField jTextFieldID;
    private javax.swing.JTextField jTextFieldName;
    // End of variables declaration//GEN-END:variables

}
