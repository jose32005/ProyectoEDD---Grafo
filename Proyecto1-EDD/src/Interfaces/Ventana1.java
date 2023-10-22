/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

/**
 *
 * @author evaas
 */
public class Ventana1 extends javax.swing.JFrame {

    /**
     * Creates new form Ventana1
     */
    public Ventana1() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        Salir = new javax.swing.JButton();
        MostrarSCCs = new javax.swing.JButton();
        ActualizarRepositorio = new javax.swing.JButton();
        CambiarArchivo = new javax.swing.JButton();
        MostrarGrafo = new javax.swing.JButton();
        ModificarGrafo = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Salir.setBackground(new java.awt.Color(0, 51, 255));
        Salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Salir.setForeground(new java.awt.Color(255, 255, 255));
        Salir.setText("Salir");
        Salir.setBorder(null);
        Salir.setFocusPainted(false);
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jPanel1.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 20, 60, 30));

        MostrarSCCs.setText("Mostrar SCCs");
        MostrarSCCs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MostrarSCCsActionPerformed(evt);
            }
        });
        jPanel1.add(MostrarSCCs, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 160, 70));

        ActualizarRepositorio.setText("Actualizar Repositorio");
        jPanel1.add(ActualizarRepositorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, 160, 70));

        CambiarArchivo.setText("Cargar Archivo");
        CambiarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CambiarArchivoActionPerformed(evt);
            }
        });
        jPanel1.add(CambiarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 160, 70));

        MostrarGrafo.setText("Mostrar Grafo");
        jPanel1.add(MostrarGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 160, 70));

        ModificarGrafo.setText("Modificar Grafo");
        ModificarGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarGrafoActionPerformed(evt);
            }
        });
        jPanel1.add(ModificarGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 140, 160, 70));

        Logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo.png"))); // NOI18N
        jPanel1.add(Logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));

        Fondo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondo3.jpg"))); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 770, 390));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 730, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
        //this.dispose();
    }//GEN-LAST:event_SalirActionPerformed

    private void MostrarSCCsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MostrarSCCsActionPerformed
        Funciones func = new Funciones();
        Grafo miGrafo = func.leer_txt();
        Graph graph = miGrafo.obtenerSCCs();
        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }//GEN-LAST:event_MostrarSCCsActionPerformed

    private void MostrarGrafoActionPerformed(java.awt.event.ActionEvent evt) {                                             
        Funciones func = new Funciones();
        Grafo miGrafo = func.leer_txt();
        Graph graph = miGrafo.llenarGraph();

        Viewer viewer = graph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
    }//GEN-LAST:event_MostrarSCCsActionPerformed  

    private void ModificarGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarGrafoActionPerformed
        Ventana2 v2 = new Ventana2(this);
    }//GEN-LAST:event_ModificarGrafoActionPerformed

    private void CambiarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CambiarArchivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CambiarArchivoActionPerformed

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
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Ventana1().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualizarRepositorio;
    private javax.swing.JButton CambiarArchivo;
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel Logo;
    private javax.swing.JButton ModificarGrafo;
    private javax.swing.JButton MostrarGrafo;
    private javax.swing.JButton MostrarSCCs;
    private javax.swing.JButton Salir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
