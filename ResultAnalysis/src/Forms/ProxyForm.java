package Forms;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class ProxyForm extends javax.swing.JFrame {

    /**
     * Creates new form ProxyForm
     */
    public ProxyForm() {
        initComponents();
        this.setTitle("Campulse Result analysis : Set Proxy");

        try {
            String ProxyIP, SecureIP;
            Integer ProxyPort, SecurePort;
            Properties prop = new Properties();
            prop.load(new FileInputStream("config.ini"));
            String SysProx = prop.getProperty("SysProxy");
            String noProx = prop.getProperty("NoProxy");

            if (noProx == null) {
                RB_sysProxy.setSelected(true);
                System.out.println("System proxy selected by default");
            } else if (noProx.equals("true")) {
                RB_noProxy.setSelected(true);
                TF_secureIp.setEnabled(false);
                Spin_SecurePort.setEnabled(false);
                TF_ProxyIp.setEnabled(false);
                Spin_ProxyPort.setEnabled(false);
                CB_sameProxy.setEnabled(false);
            } else if (SysProx.equals("true")) {
                RB_sysProxy.setSelected(true);
                TF_secureIp.setEnabled(false);
                Spin_SecurePort.setEnabled(false);
                TF_ProxyIp.setEnabled(false);
                Spin_ProxyPort.setEnabled(false);
                CB_sameProxy.setEnabled(false);
            } else {
                ProxyIP = prop.getProperty("HTTPProxy");
                ProxyPort = Integer.parseInt(prop.getProperty("HTTPPort"));
                SecureIP = prop.getProperty("HTTPSProxy");
                SecurePort = Integer.parseInt(prop.getProperty("HTTPSPort"));

                RB_manProxy.setSelected(true);
                TF_ProxyIp.setText(ProxyIP);
                TF_secureIp.setText(SecureIP);
                Spin_ProxyPort.setValue(ProxyPort);
                Spin_SecurePort.setValue((Object) SecurePort);

            }

        } catch (IOException ex) {
            RB_sysProxy.setSelected(true);
            TF_secureIp.setEnabled(false);
            Spin_SecurePort.setEnabled(false);
            TF_ProxyIp.setEnabled(false);
            Spin_ProxyPort.setEnabled(false);
            CB_sameProxy.setEnabled(false);
            System.out.println("Can not read config file...");
        }

        //Remove comma from Jspinners
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(Spin_ProxyPort, "#");

        Spin_ProxyPort.setEditor(editor);
        JSpinner.NumberEditor editor1 = new JSpinner.NumberEditor(Spin_SecurePort, "#");

        Spin_SecurePort.setEditor(editor1);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG_Proxy = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        RB_noProxy = new javax.swing.JRadioButton();
        RB_manProxy = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        TF_ProxyIp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        Spin_ProxyPort = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        TF_secureIp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Spin_SecurePort = new javax.swing.JSpinner();
        CB_sameProxy = new javax.swing.JCheckBox();
        B_Save = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        RB_sysProxy = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Configure Proxy to access Internet", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        BG_Proxy.add(RB_noProxy);
        RB_noProxy.setText("No Proxy");
        RB_noProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_noProxyActionPerformed(evt);
            }
        });
        RB_noProxy.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                RB_noProxyPropertyChange(evt);
            }
        });

        BG_Proxy.add(RB_manProxy);
        RB_manProxy.setText("Manual Proxy Configuaration");
        RB_manProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_manProxyActionPerformed(evt);
            }
        });

        jLabel1.setText("HTTP proxy :");

        jLabel2.setText("Port :");

        jLabel3.setText("Secure proxy :");

        jLabel4.setText("Port :");

        CB_sameProxy.setText("Use the same for secure proxy");
        CB_sameProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_sameProxyActionPerformed(evt);
            }
        });

        B_Save.setText("Save");
        B_Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_SaveActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        BG_Proxy.add(RB_sysProxy);
        RB_sysProxy.setText("Use System Proxy");
        RB_sysProxy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RB_sysProxyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RB_sysProxy)
                    .addComponent(RB_manProxy)
                    .addComponent(CB_sameProxy)
                    .addComponent(RB_noProxy)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(TF_secureIp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel4))
                                .addComponent(jButton2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TF_ProxyIp, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Spin_ProxyPort, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(B_Save, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Spin_SecurePort, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Spin_ProxyPort, Spin_SecurePort});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {TF_ProxyIp, TF_secureIp});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RB_noProxy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RB_sysProxy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(RB_manProxy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(TF_ProxyIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(Spin_ProxyPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CB_sameProxy)
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(TF_secureIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(Spin_SecurePort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(B_Save)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Spin_ProxyPort, Spin_SecurePort});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {TF_ProxyIp, TF_secureIp});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CB_sameProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_sameProxyActionPerformed

        if (CB_sameProxy.isSelected()) {
            TF_secureIp.setEnabled(false);
            Spin_SecurePort.setEnabled(false);
        } else {
            TF_secureIp.setEnabled(true);
            Spin_SecurePort.setEnabled(true);
        }
    }//GEN-LAST:event_CB_sameProxyActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void B_SaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_SaveActionPerformed
        boolean noProx = false;
        boolean sysProx = false;
        BufferedWriter out = null;
        FileWriter fstream;

        try {
            fstream = new FileWriter("config.ini");
            out = new BufferedWriter(fstream);
        } catch (IOException ex) {
            Logger.getLogger(ProxyForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (RB_noProxy.isSelected()) {
            noProx = true;
        } else {
            noProx = false;
        }
        if (RB_sysProxy.isSelected()) {
            sysProx = true;
        } else {
            sysProx = false;
        }

        try {
            out.write("NoProxy=" + noProx + "\n");
            out.write("SysProxy=" + sysProx + "\n");
        } catch (IOException ex) {
            Logger.getLogger(ProxyForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (RB_manProxy.isSelected() && (TF_ProxyIp.getText().equals("") || ((Integer) Spin_ProxyPort.getValue()) == 0)) {
            JOptionPane.showMessageDialog(null, "Please Enter IP and Port Value");
        } else if (RB_manProxy.isSelected() && !CB_sameProxy.isSelected() && (TF_secureIp.getText().equals("") || ((Integer) Spin_SecurePort.getValue()) == 0)) {
            JOptionPane.showMessageDialog(null, "Please Enter Secure IP and Secure Port Value");
        } else if (RB_manProxy.isSelected()) {

            String HTTPPROXY = TF_ProxyIp.getText();
            int HTTPPORT = (Integer) Spin_ProxyPort.getValue();
            String SECUREPROXY = TF_secureIp.getText();
            int SECUREPORT = (Integer) Spin_SecurePort.getValue();

            if (CB_sameProxy.isSelected()) {
                SECUREPROXY = HTTPPROXY;
                SECUREPORT = HTTPPORT;
            }
            try {
                // Create file 
                out.write("HTTPProxy=" + HTTPPROXY + "\n");
                out.write("HTTPPort=" + HTTPPORT + "\n");
                out.write("HTTPSProxy=" + SECUREPROXY + "\n");
                out.write("HTTPSPort=" + SECUREPORT + "\n");

                //Close the output stream
            } catch (Exception e) {//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }
        }
        try {
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ProxyForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_B_SaveActionPerformed

    private void RB_noProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_noProxyActionPerformed
        TF_secureIp.setEnabled(false);
        Spin_SecurePort.setEnabled(false);
        TF_ProxyIp.setEnabled(false);
        Spin_ProxyPort.setEnabled(false);
        CB_sameProxy.setEnabled(false);

        TF_secureIp.setText("");
        Spin_SecurePort.setValue(new Integer(0));
        TF_ProxyIp.setText("");
        Spin_ProxyPort.setValue(new Integer(0));
    }//GEN-LAST:event_RB_noProxyActionPerformed

    private void RB_manProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_manProxyActionPerformed
        TF_secureIp.setEnabled(true);
        Spin_SecurePort.setEnabled(true);
        TF_ProxyIp.setEnabled(true);
        Spin_ProxyPort.setEnabled(true);
        CB_sameProxy.setEnabled(true);
    }//GEN-LAST:event_RB_manProxyActionPerformed

    private void RB_noProxyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_RB_noProxyPropertyChange

    }//GEN-LAST:event_RB_noProxyPropertyChange

    private void RB_sysProxyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RB_sysProxyActionPerformed
        TF_secureIp.setEnabled(false);
        Spin_SecurePort.setEnabled(false);
        TF_ProxyIp.setEnabled(false);
        Spin_ProxyPort.setEnabled(false);
        CB_sameProxy.setEnabled(false);

        TF_secureIp.setText("");
        Spin_SecurePort.setValue(new Integer(0));
        TF_ProxyIp.setText("");
        Spin_ProxyPort.setValue(new Integer(0));
    }//GEN-LAST:event_RB_sysProxyActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BG_Proxy;
    private javax.swing.JButton B_Save;
    private javax.swing.JCheckBox CB_sameProxy;
    private javax.swing.JRadioButton RB_manProxy;
    private javax.swing.JRadioButton RB_noProxy;
    private javax.swing.JRadioButton RB_sysProxy;
    private javax.swing.JSpinner Spin_ProxyPort;
    private javax.swing.JSpinner Spin_SecurePort;
    private javax.swing.JTextField TF_ProxyIp;
    private javax.swing.JTextField TF_secureIp;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
