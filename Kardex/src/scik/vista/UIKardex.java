package scik.vista;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellRenderer;
import scik.controlador.kardex.IKardex;

public class UIKardex extends javax.swing.JFrame
{
    private IKardex interfaz;
    private ListSelectionModel cellSelectionModel;
    
    public UIKardex(IKardex interfaz)
    {
        this.setVisible(true);
        this.setTitle("MANTENIMIENTO DE KARDEX");
        this.getContentPane().setBackground(new Color(255, 255, 255));
        initComponents();
        setLocationRelativeTo(null);
        
        this.interfaz = interfaz;
        interfaz.cargar(tblRegistrosKC);
        
        cellSelectionModel = tblRegistrosKC.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        cellSelectionModel.addListSelectionListener(new ListSelectionListener()
        {
            public void valueChanged(ListSelectionEvent e)
            {
                interfaz.actualizar(tblRegistrosKC, tblRegistrosKD, txtCan, txtPreUni);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel19 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblRegistrosKC = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int row, int column)
            {
                Component c = super.prepareRenderer(r, row, column);
                c.setBackground(Color.WHITE);
                if((column == 0 || column == 2)&& !isCellSelected(row, column))
                {
                    c.setBackground(new Color(228, 251, 219));
                }
                else if(isCellSelected(row, column))
                {
                    c.setBackground(Color.BLUE);
                }
                return c;
            }
        }
        ;
        jPanel3 = new javax.swing.JPanel();
        btnInsertarKC = new javax.swing.JButton();
        btnEliminarKC = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCan = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPreUni = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRegistrosKD = new javax.swing.JTable()
        {
            public Component prepareRenderer(TableCellRenderer r, int row, int column)
            {
                Component c = super.prepareRenderer(r, row, column);
                c.setBackground(Color.WHITE);
                if(column == 0 && !isCellSelected(row, column))
                {
                    c.setBackground(new Color(228, 251, 219));
                }
                else if(isCellSelected(row, column))
                {
                    c.setBackground(Color.BLUE);
                }
                return c;
            }
        }
        ;
        btnInsertarKD = new javax.swing.JButton();
        btnModificarKD = new javax.swing.JButton();
        btnEliminarKD = new javax.swing.JButton();
        btnMenu = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/icono.png")).getImage());
        setResizable(false);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("MANTENIMIENTO - KARDEX");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Kardex Cabecera", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        tblRegistrosKC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblRegistrosKC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Codigo de Producto", "Producto", "Codigo de Almacen", "Almacen", "Estado"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                true, false, true, false, true
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblRegistrosKC);
        if (tblRegistrosKC.getColumnModel().getColumnCount() > 0)
        {
            tblRegistrosKC.getColumnModel().getColumn(0).setMinWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(0).setMaxWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(2).setMinWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(2).setMaxWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(4).setMinWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblRegistrosKC.getColumnModel().getColumn(4).setMaxWidth(70);
        }

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnInsertarKC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertarKC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/insertar.png"))); // NOI18N
        btnInsertarKC.setText("INSERTAR");
        btnInsertarKC.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnInsertarKCActionPerformed(evt);
            }
        });

        btnEliminarKC.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnEliminarKC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/eliminar.png"))); // NOI18N
        btnEliminarKC.setText("ELIMINAR");
        btnEliminarKC.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEliminarKCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInsertarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnInsertarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarKC, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Cantidad:");

        txtCan.setEditable(false);
        txtCan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Precio Unitario:");

        txtPreUni.setEditable(false);
        txtPreUni.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCan, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPreUni, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCan, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPreUni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Kardex Detalle"));

        tblRegistrosKD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Codigo", "Año", "Mes", "Dia", "Documento", "Nro. Doc.", "Operacion", "Cantidad", "Val. Unitario", "Val. Total", "Saldo Cantidad", "Saldo Val. Unitario", "Saldo Val. Total", "Observaciones", "Estado"
            }
        )
        {
            Class[] types = new Class []
            {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean []
            {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex)
            {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblRegistrosKD);
        if (tblRegistrosKD.getColumnModel().getColumnCount() > 0)
        {
            tblRegistrosKD.getColumnModel().getColumn(0).setMinWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(0).setPreferredWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(0).setMaxWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(1).setMinWidth(40);
            tblRegistrosKD.getColumnModel().getColumn(1).setPreferredWidth(40);
            tblRegistrosKD.getColumnModel().getColumn(1).setMaxWidth(40);
            tblRegistrosKD.getColumnModel().getColumn(2).setMinWidth(30);
            tblRegistrosKD.getColumnModel().getColumn(2).setPreferredWidth(30);
            tblRegistrosKD.getColumnModel().getColumn(2).setMaxWidth(30);
            tblRegistrosKD.getColumnModel().getColumn(3).setMinWidth(30);
            tblRegistrosKD.getColumnModel().getColumn(3).setPreferredWidth(30);
            tblRegistrosKD.getColumnModel().getColumn(3).setMaxWidth(30);
            tblRegistrosKD.getColumnModel().getColumn(4).setMinWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(4).setPreferredWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(4).setMaxWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(5).setMinWidth(80);
            tblRegistrosKD.getColumnModel().getColumn(5).setPreferredWidth(80);
            tblRegistrosKD.getColumnModel().getColumn(5).setMaxWidth(80);
            tblRegistrosKD.getColumnModel().getColumn(6).setMinWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(6).setPreferredWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(6).setMaxWidth(70);
            tblRegistrosKD.getColumnModel().getColumn(14).setMinWidth(50);
            tblRegistrosKD.getColumnModel().getColumn(14).setPreferredWidth(50);
            tblRegistrosKD.getColumnModel().getColumn(14).setMaxWidth(50);
        }

        btnInsertarKD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnInsertarKD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/insertar.png"))); // NOI18N
        btnInsertarKD.setText("INSERTAR");
        btnInsertarKD.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnInsertarKDActionPerformed(evt);
            }
        });

        btnModificarKD.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnModificarKD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/modificar.png"))); // NOI18N
        btnModificarKD.setText("MODIFICAR ÚLTIMO REGISTRO");
        btnModificarKD.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnModificarKDActionPerformed(evt);
            }
        });

        btnEliminarKD.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btnEliminarKD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/eliminar.png"))); // NOI18N
        btnEliminarKD.setText("ELIMINAR ÚLTIMO REGISTRO");
        btnEliminarKD.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnEliminarKDActionPerformed(evt);
            }
        });

        btnMenu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/scik/recursos/menu.png"))); // NOI18N
        btnMenu.setText("Volver al Menu");
        btnMenu.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnMenuActionPerformed(evt);
            }
        });

        btnReporte.setBackground(new java.awt.Color(0, 128, 213));
        btnReporte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnReporte.setText("Generar Formato");
        btnReporte.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnMenu)
                        .addGap(156, 156, 156)
                        .addComponent(btnInsertarKD, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarKD)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarKD)
                        .addGap(162, 162, 162)
                        .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMenu)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnInsertarKD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                        .addComponent(btnReporte, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificarKD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEliminarKD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInsertarKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarKCActionPerformed
        //interfaz.insertarKC();
    }//GEN-LAST:event_btnInsertarKCActionPerformed

    private void btnInsertarKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertarKDActionPerformed
        //interfaz.insertarKD(this.tblRegistrosKC);
    }//GEN-LAST:event_btnInsertarKDActionPerformed

    private void btnModificarKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarKDActionPerformed
        //interfaz.modificarKD(tblRegistrosKC);
    }//GEN-LAST:event_btnModificarKDActionPerformed

    private void btnEliminarKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarKDActionPerformed
        //interfaz.eliminarKD(tblRegistrosKC);
    }//GEN-LAST:event_btnEliminarKDActionPerformed

    private void btnEliminarKCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarKCActionPerformed
        //interfaz.eliminarKC(tblRegistrosKC);
    }//GEN-LAST:event_btnEliminarKCActionPerformed

    private void btnMenuActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnMenuActionPerformed
    {//GEN-HEADEREND:event_btnMenuActionPerformed
        interfaz.menu();
    }//GEN-LAST:event_btnMenuActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnReporteActionPerformed
    {//GEN-HEADEREND:event_btnReporteActionPerformed
        //interfaz.generarReporte(tblRegistrosKC);
    }//GEN-LAST:event_btnReporteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminarKC;
    private javax.swing.JButton btnEliminarKD;
    private javax.swing.JButton btnInsertarKC;
    private javax.swing.JButton btnInsertarKD;
    private javax.swing.JButton btnMenu;
    private javax.swing.JButton btnModificarKD;
    private javax.swing.JButton btnReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblRegistrosKC;
    private javax.swing.JTable tblRegistrosKD;
    private javax.swing.JTextField txtCan;
    private javax.swing.JTextField txtPreUni;
    // End of variables declaration//GEN-END:variables
}
