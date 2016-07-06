/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.planner;

import com.java.planner.vo.StudentCourseVO;
import com.java.planner.vo.StudentVO;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author sku263
 */
public class ViewStudentCourses extends javax.swing.JFrame {

    public class ExcelSheetReader {

      
        public java.util.List<String> getData(File file, String[] columns) throws SQLException {

            java.util.List<String> list = null;
            try {
               
                        list = new ArrayList<String>();
                    }
                   catch (Exception e) {
            } finally {
                
                return list;
            }
        }


        }

       
 

    void loaditem() {
        try {
           

            
           
            String csvFile = FILEPATH;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
                    String[] input = line.split(cvsSplitBy);

                     s2.addRow(new Object[]{input[0] , input[1], input[2], input[3], input[4]});		       
		}

	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
           }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String FILEPATH = "";

    /**
     * Creates new form ImportStudents
     */
    public ViewStudentCourses() {

        initComponents();
       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getMaximumWindowBounds();
        this.setSize(maxBounds.width, maxBounds.height);
        WindowClosingEventHandler();
        load1();
   load();

        Timer t = new Timer(1000, new sample());
        t.start();
    }

    public class sample implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date d = new Date();
            SimpleDateFormat g1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat g = new SimpleDateFormat("hh:mm:ss a");
            setTitle("Data Import");
        }
    }
void load() {
    
            
           
        

	try {
           
            Iterator<StudentCourseVO> iterator = CoursePlanner.studentCourseList.iterator();
		while(iterator.hasNext()){
                    StudentCourseVO StudentCourseVO = iterator.next();
                     String desc = null;
                    if(StudentCourseVO.getDesc().length()>10){
                    	desc = StudentCourseVO.getDesc().substring(0, 9);
                    }
                    else
                    	desc =StudentCourseVO.getDesc();
                     s2.addRow(new Object[]{StudentCourseVO.getId() , StudentCourseVO.getCourseNumber(), desc,StudentCourseVO.getSemester(), StudentCourseVO.getGrade()
                       });
	       
		}

	     }catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void WindowClosingEventHandler() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirmed = JOptionPane.showConfirmDialog(null, "Are you sure want redirect to home?", "Redirect to home page", JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    try {
                        if(!Login.isAdmin){
                        CoursePlanner me = new CoursePlanner();
                        me.setVisible(true);
                        setVisible(false);
                    }else{
                             CoursePlannerAdmin me = new CoursePlannerAdmin();
                        me.setVisible(true);
                        setVisible(false);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                } else {
                    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    public class sample2 extends DefaultTableModel {

        @Override
        public void addColumn(Object columnName) {
            super.addColumn(columnName);
        }

        @Override
        public void addRow(Object[] rowData) {
            super.addRow(rowData);
        }
    }
    sample2 s2 = new sample2();

    void load1() {
        try {

              s2.addColumn("Id");
            s2.addColumn("Course");
            s2.addColumn("Description");
             s2.addColumn("Semester");
            s2.addColumn("Grade");
           

            jTable1.setModel(s2);

            //jTable1.setModel(s2);
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
            dtcr.setHorizontalAlignment(SwingConstants.CENTER);
            jTable1.getColumnModel().getColumn(0).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(1).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(dtcr);
             jTable1.getColumnModel().getColumn(4).setCellRenderer(dtcr);
         

            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(250);
             jTable1.getColumnModel().getColumn(4).setPreferredWidth(250);

            jTable1.getTableHeader().setForeground(new Color(006611));
            jTable1.setForeground(new Color(006611));
            String Ta = "Arial";
            int Bold = 0, size = 18;
            jTable1.getTableHeader().setFont(new Font(Ta, Bold, size));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() {
        
for(int i=0;i<jTable1.getRowCount();i++)
{
    StudentCourseVO courseVO = new StudentCourseVO();
    courseVO.setId((String)jTable1.getValueAt(i, 0));
     courseVO.setCourseNumber((String)jTable1.getValueAt(i, 1));
      courseVO.setSemester((String)jTable1.getValueAt(i, 3));
       courseVO.setDesc((String)jTable1.getValueAt(i, 2));
            courseVO.setGrade((String)jTable1.getValueAt(i, 4));
       CoursePlanner.studentCourseList.add(courseVO);
}
JOptionPane.showMessageDialog(this, "Student Course Data Saved");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        tot = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTable1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jTable1.setForeground(new java.awt.Color(0, 102, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id", "Course", "description", "semester", "grade"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 110, 1010, 372);

        jButton10.setBackground(new java.awt.Color(0, 102, 51));
        jButton10.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setMnemonic('B');
        jButton10.setText("Import  StudentCourse data");
        jButton10.setToolTipText("Alt+b");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10);
        jButton10.setBounds(570, 510, 250, 40);

        jButton9.setBackground(new java.awt.Color(255, 51, 51));
        jButton9.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setMnemonic('S');
        jButton9.setText("Save");
        jButton9.setToolTipText("Alt+S");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9);
        jButton9.setBounds(840, 510, 140, 40);

        jButton7.setBackground(new java.awt.Color(57, 53, 53));
        jButton7.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setMnemonic('h');
        jButton7.setText("Back");
        jButton7.setToolTipText("Alt+H");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(990, 510, 140, 40);

        tot.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        getContentPane().add(tot);
        tot.setBounds(12, 516, 199, 60);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 51));
        jLabel1.setText("View Student Course Data");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(300, 36, 390, 30);

        jButton12.setBackground(new java.awt.Color(57, 53, 53));
        jButton12.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setMnemonic('S');
        jButton12.setText("Add Student Courses");
        jButton12.setToolTipText("Alt+S");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12);
        jButton12.setBounds(30, 510, 170, 40);

        jButton11.setBackground(new java.awt.Color(57, 53, 53));
        jButton11.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setMnemonic('r');
        jButton11.setText("Modify Student Courses");
        jButton11.setToolTipText("Alt+R");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11);
        jButton11.setBounds(220, 510, 158, 40);

        jButton8.setBackground(new java.awt.Color(57, 53, 53));
        jButton8.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 255, 255));
        jButton8.setMnemonic('r');
        jButton8.setText("Delete Student Courses");
        jButton8.setToolTipText("Alt+R");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(400, 510, 158, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        save();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

         try {
                        if(!Login.isAdmin){
                        CoursePlanner me = new CoursePlanner();
                        me.setVisible(true);
                        setVisible(false);
                    }else{
                             CoursePlannerAdmin me = new CoursePlannerAdmin();
                        me.setVisible(true);
                        setVisible(false);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed

        JFileChooser j = new JFileChooser();
        j.showOpenDialog(this);
        FILEPATH = j.getSelectedFile().getPath();
        loaditem();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        AddStudentCourses ast = new AddStudentCourses();
        ast.setVisible(true);
        setVisible(false);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        try{
            String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            String course =  jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
            ModifyStudentCourses std = new ModifyStudentCourses(id,course);
            std.setVisible(true);
            setVisible(false);
        }catch(Exception e){
            e.printStackTrace();;

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        try {
            String id = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            String  course = jTable1.getValueAt(jTable1.getSelectedRow(), 1).toString();
           int confirmed = JOptionPane.showConfirmDialog(this, "Confirm Delete, Continue ?");
            Iterator<StudentCourseVO> it = CoursePlanner.studentCourseList.iterator();
            while(it.hasNext()){
                StudentCourseVO studentVO = it.next();
                if(id.equals(studentVO.getId()) && course.equals(studentVO.getCourseNumber())){
                    if(confirmed==0){
                        CoursePlanner.studentCourseList.remove(studentVO);

                        break;
                    }
                }
            }
            ViewStudentCourses me = new ViewStudentCourses();
            me.setVisible(true);
            setVisible(false);
        }catch (Exception e) {
            e.printStackTrace();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewStudentCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewStudentCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewStudentCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewStudentCourses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewStudentCourses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
