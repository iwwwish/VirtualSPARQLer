/*
 * Copyright (C) 2014 Vishal Siramshetty <srmshtty[at]gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package de.fhg.scai.bio.gui;

import de.fhg.scai.bio.interfaces.PopupActionListener;
import de.fhg.scai.bio.interfaces.Mapping;
import de.fhg.scai.bio.interfaces.Prefix;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author Vishal Siramshetty <srmshtty[at]gmail.com>
 */
public class VirtualSparqler extends javax.swing.JFrame {
    
    public static String mappingFilePath = "/home/vishal/NetBeansProjects/VirtualSPARQLer/src/main/resources/mapping-iswc.ttl";
    public static List<String> connectionParameters;
    public static JPopupMenu prefixMenu;
    List<Prefix> prefixes;

    /**
     * Creates new form VirtualSparqler
     */
    public VirtualSparqler() {
        loadPrefixes();
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

        outerPanel = new javax.swing.JPanel();
        newConnection = new javax.swing.JButton();
        addressBar = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        queryPanel = new javax.swing.JPanel();
        execTimeLabel = new javax.swing.JLabel();
        executionTime = new javax.swing.JTextField();
        resCountLabel = new javax.swing.JLabel();
        resultTripleCount = new javax.swing.JTextField();
        executeQuery = new javax.swing.JButton();
        saveQuery = new javax.swing.JButton();
        queryScrollPane = new javax.swing.JScrollPane();
        queryArea = new javax.swing.JTextArea();
        addPrefixes = new javax.swing.JButton();
        resultPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultArea = new javax.swing.JTextArea();
        rpPanel = new javax.swing.JTabbedPane();
        propertyScrollPane = new javax.swing.JScrollPane();
        propertyEditorPanel = new javax.swing.JEditorPane();
        resourceScrollPane = new javax.swing.JScrollPane();
        resourceEditorPanel = new javax.swing.JEditorPane();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        editMenu = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VirtualSPARQLer");

        outerPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        newConnection.setText("Create Mapping");
        newConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newConnectionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout outerPanelLayout = new javax.swing.GroupLayout(outerPanel);
        outerPanel.setLayout(outerPanelLayout);
        outerPanelLayout.setHorizontalGroup(
            outerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newConnection, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressBar)
                .addContainerGap())
        );
        outerPanelLayout.setVerticalGroup(
            outerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newConnection)
                    .addComponent(addressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        queryPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Query Editor"));

        execTimeLabel.setText("Execution Time:");

        resCountLabel.setText("No. of Triples retrieved:");

        executeQuery.setText("Execute");

        saveQuery.setText("Save");

        queryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        queryArea.setColumns(20);
        queryArea.setRows(5);
        queryScrollPane.setViewportView(queryArea);

        addPrefixes.setText("Add Prefix");
        addPrefixes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPrefixesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout queryPanelLayout = new javax.swing.GroupLayout(queryPanel);
        queryPanel.setLayout(queryPanelLayout);
        queryPanelLayout.setHorizontalGroup(
            queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(queryPanelLayout.createSequentialGroup()
                .addComponent(execTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(executionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(resCountLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultTripleCount, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(addPrefixes, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(executeQuery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(queryScrollPane)
        );
        queryPanelLayout.setVerticalGroup(
            queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(queryPanelLayout.createSequentialGroup()
                .addComponent(queryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(queryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(execTimeLabel)
                    .addComponent(executionTime, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resCountLabel)
                    .addComponent(resultTripleCount, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(executeQuery)
                    .addComponent(addPrefixes)
                    .addComponent(saveQuery)))
        );

        resultPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Results"));

        resultArea.setEditable(false);
        resultArea.setColumns(20);
        resultArea.setRows(5);
        jScrollPane1.setViewportView(resultArea);

        javax.swing.GroupLayout resultPanelLayout = new javax.swing.GroupLayout(resultPanel);
        resultPanel.setLayout(resultPanelLayout);
        resultPanelLayout.setHorizontalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        resultPanelLayout.setVerticalGroup(
            resultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );

        propertyScrollPane.setViewportView(propertyEditorPanel);

        rpPanel.addTab("Properties", propertyScrollPane);

        resourceScrollPane.setViewportView(resourceEditorPanel);

        rpPanel.addTab("Resources", resourceScrollPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(queryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rpPanel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(queryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        fileMenu.setText("File");
        menuBar.add(fileMenu);

        editMenu.setText("Edit");
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(outerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(outerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newConnectionActionPerformed
        ConnectionParameters params = new ConnectionParameters();
        params.setVisible(true);
        params.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_newConnectionActionPerformed
    
    private String getPrefix(String abbreviation) {
        for (Prefix pref : prefixes) {
            if (pref.getAbbreviation().equals(abbreviation)) {
                return pref.toString();
            }
        }
        return null;
    }
    
    private void loadPrefixes() {
        File mapFile = new File(mappingFilePath);
        Mapping mapping = new Mapping(mapFile);
        prefixes = mapping.getPrefixes();
        
        prefixMenu = new JPopupMenu();
        
        ActionListener actionListener = new PopupActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPrefix = e.getActionCommand();
                String queryText = queryArea.getText();
                if (queryText.isEmpty()) {
                    queryArea.setText(getPrefix(selectedPrefix));
                } else {
                    if (queryText.contains(getPrefix(selectedPrefix))) {
                        
                    }
                    queryText = queryText + "\n" + getPrefix(selectedPrefix);
                    queryArea.setText(queryText);
                }
            }
        };
        
        for (Prefix pref : prefixes) {
            JCheckBoxMenuItem item = new JCheckBoxMenuItem(pref.getAbbreviation());
            item.addActionListener(actionListener);
            prefixMenu.add(item);
        }
    }

    private void addPrefixesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPrefixesActionPerformed
        prefixMenu.show(addPrefixes, 0, addPrefixes.getHeight());
    }//GEN-LAST:event_addPrefixesActionPerformed
    
    public static void testConnection(java.awt.event.ActionEvent evt) {
        System.out.println(connectionParameters.toString());
        if (!connectionParameters.isEmpty()) {
            System.out.println("Fetched Connection Parameters. Need to check the validity.");
        }
    }

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
            UIManager.installLookAndFeel("SeaGlass", "com.seaglasslookandfeel.SeaGlassLookAndFeel");
            UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            System.err.println("Seaglass LAF not available using Ocean.");
            try {
                UIManager.setLookAndFeel(new MetalLookAndFeel());
            } catch (UnsupportedLookAndFeelException e2) {
                System.err.println("Unable to use Ocean LAF using default.");
            }
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VirtualSparqler().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addPrefixes;
    private javax.swing.JTextField addressBar;
    private javax.swing.JMenu editMenu;
    private javax.swing.JLabel execTimeLabel;
    private javax.swing.JButton executeQuery;
    private javax.swing.JTextField executionTime;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton newConnection;
    private javax.swing.JPanel outerPanel;
    private javax.swing.JEditorPane propertyEditorPanel;
    private javax.swing.JScrollPane propertyScrollPane;
    private javax.swing.JTextArea queryArea;
    private javax.swing.JPanel queryPanel;
    private javax.swing.JScrollPane queryScrollPane;
    private javax.swing.JLabel resCountLabel;
    private javax.swing.JEditorPane resourceEditorPanel;
    private javax.swing.JScrollPane resourceScrollPane;
    private javax.swing.JTextArea resultArea;
    private javax.swing.JPanel resultPanel;
    private javax.swing.JTextField resultTripleCount;
    private javax.swing.JTabbedPane rpPanel;
    private javax.swing.JButton saveQuery;
    // End of variables declaration//GEN-END:variables
}
