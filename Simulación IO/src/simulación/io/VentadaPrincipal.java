/*
 * 
 */
package simulación.io;

import java.lang.System;                                                       // Paquete necesario para correr la simulacion por cierta cantidad de tiempo

/**
 *
 * @author 
 */
public class VentadaPrincipal extends javax.swing.JFrame {

    int correrSimulacion;                                                       // Variables globales a pasar a la clase principal SimulacionIO
    double corridaPorSimulacion;
    double tiempoT;
    boolean modoLento = false;
    boolean modoRapido = false;
    
    
    
    public VentadaPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        longitudcolaprioridad3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        correrSimulaciontxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tiempoTtxt = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        corridaPorSimulaciontxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        table2 = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jToggleButton2 = new javax.swing.JToggleButton();
        longitudcolaprioridad1a = new javax.swing.JLabel();
        longitudcolaprioridad2a = new javax.swing.JLabel();
        longitudcolamaquinas = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        longitudcolaprioridad1b = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        longitudcolaprioridad2b = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        longitudcolaprioridad1c = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        longitudcolaprioridad2c = new javax.swing.JLabel();

        longitudcolaprioridad3.setText("0");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tunga", 1, 24)); // NOI18N
        jLabel1.setText("Simulación Red LAN ");

        jRadioButton1.setText("Modo Lento");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Modo Rápido");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        correrSimulaciontxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        correrSimulaciontxt.setText("0");

        jLabel2.setText("Correr simulación: ");

        jLabel3.setText("veces");

        jLabel4.setText("Corrida de cada simulación:");

        jLabel6.setText("Tiempo T:");

        tiempoTtxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tiempoTtxt.setText("0.0");
        tiempoTtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tiempoTtxtActionPerformed(evt);
            }
        });

        jLabel7.setText("segundos");

        corridaPorSimulaciontxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        corridaPorSimulaciontxt.setText("0.0");

        jLabel5.setText("segundos");

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Reloj del Sistema", "Tiempo T", "Máquina con token"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table1);

        table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "Hilos Ocupados", "Tipo de Evento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table2);

        jLabel8.setText("Longitud Cola Prioridad 1 Máquina A:");

        jLabel9.setText("Longitud Cola Prioridad 2 Máquina A:");

        jLabel10.setText("Longitud Cola en Antivirus: ");

        jToggleButton2.setText("Ejecutar");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        longitudcolaprioridad1a.setText("0");

        longitudcolaprioridad2a.setText("0");

        longitudcolamaquinas.setText("0");

        jLabel12.setText("Longitud Cola Prioridad 1 Máquina B:");

        longitudcolaprioridad1b.setText("0");

        jLabel14.setText("Longitud Cola Prioridad 2 Máquina B:");

        longitudcolaprioridad2b.setText("0");

        jLabel16.setText("Longitud Cola Prioridad 1 Máquina C:");

        longitudcolaprioridad1c.setText("0");

        jLabel18.setText("Longitud Cola Prioridad 2 Máquina C:");

        longitudcolaprioridad2c.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jToggleButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2))
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(correrSimulaciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(corridaPorSimulaciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tiempoTtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)))
                        .addGap(78, 78, 78)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(18, 18, 18)
                                .addComponent(longitudcolaprioridad1c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(longitudcolaprioridad1a, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(longitudcolamaquinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(longitudcolaprioridad2a, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(longitudcolaprioridad1b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(longitudcolaprioridad2b, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(longitudcolaprioridad2c, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(205, 205, 205))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(correrSimulaciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(longitudcolaprioridad1a))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(longitudcolaprioridad2a))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(longitudcolaprioridad1b))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(longitudcolaprioridad2b)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(corridaPorSimulaciontxt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tiempoTtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(longitudcolaprioridad1c))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(longitudcolaprioridad2c))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(longitudcolamaquinas))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jToggleButton2)
                .addGap(57, 57, 57))
        );

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        
        // Se habilita la corrida en modo lento
        
        modoLento = true;
        modoRapido = false;
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        
    correrSimulacion = Integer.parseInt(correrSimulaciontxt.getText());
    corridaPorSimulacion = Double.parseDouble(corridaPorSimulaciontxt.getText());
    tiempoT = Double.parseDouble(tiempoTtxt.getText());
    
    int counter = 0;
    long totalSeconds = 0;
    long initialSeconds = 0;
    long finalSeconds = 0;
    int peekMethod;
    String eventName = "";
    //Calendar calendar = Calendar.getInstance();
    
    
    while (counter <= correrSimulacion){
        totalSeconds = 0;        
        ControladorEventos newEvent = new ControladorEventos();                 // Nueva  simulacion
        
        while(totalSeconds <= corridaPorSimulacion){
            System.out.println("Estoy en ciclo");
            System.out.println("cantidad de segundos: "+totalSeconds);
            initialSeconds = System.currentTimeMillis();//calendar.get(Calendar.SECOND);    
            System.out.println("segundos iniciales: "+initialSeconds);
            newEvent.setEventTime();                                            // Arranca la simulacion
            
            System.out.println("tiempo 1:"+newEvent.events.peek().nombre);
            System.out.println("tiempo 1:"+newEvent.events.peek().time);
            
            peekMethod = newEvent.events.peek().numEvent;
            
            switch(peekMethod){
                case 1:
                    System.out.println("valor del numEvent: "+newEvent.events.peek().numEvent);
                    System.out.println("nombre del numEvent: "+newEvent.events.peek().nombre);
                    newEvent.FileArrivesA();
                    eventName = "Máquina A recibe archivo";
                    System.out.println("primero");
                    break;
                case 2:
                    System.out.println("valor del numEvent: "+newEvent.events.peek().numEvent);
                    System.out.println("nombre del numEvent: "+newEvent.events.peek().nombre);
                    newEvent.fileArrivesB();
                    eventName = "Máquina B recibe archivo";
                    System.out.println("segundo");
                    break;                
                case 3:
                    System.out.println("valor del numEvent: "+newEvent.events.peek().numEvent);
                    System.out.println("nombre del numEvent: "+newEvent.events.peek().nombre);
                    newEvent.fileArrivesC();
                    eventName = "Máquina C recibe archivo";
                    System.out.println("tercero");
                    break;
                case 4:
                    newEvent.receivesTokenA();
                    eventName = "Máquina A recibe token";
                    break;
                case 5:
                    newEvent.receivesTokenB();
                    eventName = "Máquina B recibe token";
                    break;
                case 6:
                    newEvent.receivesTokenC();
                    eventName = "Máquina C recibe token";
                    break;
                case 7:
                    newEvent.tplama();
                    eventName = "Archivo en linea A";
                    break;
                case 8:
                    newEvent.tplamb();
                    eventName = "Archivo en linea B";
                    break;
                case 9:
                    newEvent.tplamc();
                    eventName = "Archivo en linea C";
                    break;
                case 10:
                    newEvent.lasa();
                    eventName = "Archivo llega al servidor";
                    break;
                case 11:
                    newEvent.sla();
                    eventName = "Archivo sale de servidor";
                    break;
                case 12:
                    newEvent.laar();
                    eventName = "Archivo llega a router";
                    break;
                case 13:
                    newEvent.srlt1();
                    eventName = "Sale por linea 1 de router";
                    break;
                case 14:
                    newEvent.srlt2();
                    eventName = "Sale por linea 2 de router";
                    break;
            }
            
            finalSeconds = System.currentTimeMillis();
            System.out.println("segundos finales "+finalSeconds);
            totalSeconds += ( (initialSeconds / 1000) + (finalSeconds / 1000) );
            System.out.println("nuevo total "+totalSeconds);
            
            longitudcolaprioridad1a.setText(""+newEvent.priorityFileA1.size());
            longitudcolaprioridad2a.setText(""+newEvent.priorityFileA2.size());
            longitudcolaprioridad1b.setText(""+newEvent.priorityFileB1.size());
            longitudcolaprioridad2b.setText(""+newEvent.priorityFileB2.size());
            longitudcolaprioridad1c.setText(""+newEvent.priorityFileC1.size());
            longitudcolaprioridad2c.setText(""+newEvent.priorityFileC2.size());
            longitudcolamaquinas.setText(""+newEvent.serverFiles.size());
            
            table1.setValueAt(newEvent.clock, 0, 0);
            table1.setValueAt(newEvent.tokenTime, 0, 1);
            //table1.setValueAt(newEvent., 0, 3);
            
            if(newEvent.transmisionLine1 && newEvent.transmisionLine2){         // dos lineas libres
                table2.setValueAt(2, 0, 0);                
            }else{
                if(newEvent.transmisionLine1 && newEvent.transmisionLine2){     // una linea libre
                    table2.setValueAt(1,0,0);
                }else{
                    table2.setValueAt(0,0,0);                                   // ninguna linea libre
                }
            }
            
            table2.setValueAt(eventName, 0, 1);
        }
        ++counter;
        System.out.println("sali de ciclo");
    }
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void tiempoTtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tiempoTtxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tiempoTtxtActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        
        // Se habilita la corrida en modo rapido
        
        modoRapido = true;
        modoLento = false;
    }//GEN-LAST:event_jRadioButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(VentadaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentadaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentadaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentadaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentadaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField correrSimulaciontxt;
    private javax.swing.JTextField corridaPorSimulaciontxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton2;
    public javax.swing.JLabel longitudcolamaquinas;
    public javax.swing.JLabel longitudcolaprioridad1a;
    public javax.swing.JLabel longitudcolaprioridad1b;
    public javax.swing.JLabel longitudcolaprioridad1c;
    public javax.swing.JLabel longitudcolaprioridad2a;
    public javax.swing.JLabel longitudcolaprioridad2b;
    public javax.swing.JLabel longitudcolaprioridad2c;
    public javax.swing.JLabel longitudcolaprioridad3;
    public javax.swing.JTable table1;
    public javax.swing.JTable table2;
    private javax.swing.JTextField tiempoTtxt;
    // End of variables declaration//GEN-END:variables
}
