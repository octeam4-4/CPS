/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.planner;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.java.planner.vo.CourseVO;
import com.java.planner.vo.DegreeVO;
import com.java.planner.vo.FacultyVO;
import com.java.planner.vo.RoomVO;
import com.java.planner.vo.ScheduleVO;
import com.java.planner.vo.SectionVO;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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
public class ViewScheduleReport extends javax.swing.JFrame {

   
       
 
void load1() {
    
    
     Object degree[] = new Object[CoursePlanner.degreeMap.size()];

            int j = 0;
            Iterator<String> deg = CoursePlanner.degreeMap.keySet().iterator();
            while (deg.hasNext()) {
                degree[j] = deg.next();
                j++;
            }
            jComboBox3.removeAllItems();
            AutoCompleteSupport support2 = AutoCompleteSupport.install(
                    jComboBox3, GlazedLists.eventListOf(degree));
            Object semester[] = new Object[6];

            semester[0] = "2016SP";
            semester[1] = "2016SU";
            semester[2] = "2016FA";
            semester[3] = "2017SP";
            semester[4] = "2017SU";
            semester[5] = "2017FA";

            jComboBox1.removeAllItems();
            AutoCompleteSupport support3 = AutoCompleteSupport.install(
                    jComboBox1, GlazedLists.eventListOf(semester));
        try {

            s2.addColumn("GradSchool");
          
            s2.addColumn("Semester");
            s2.addColumn("Degree");
             s2.addColumn("Course");
            s2.addColumn("Section");
            s2.addColumn("Faculty");
           
                   
        
           


            jTable1.setModel(s2);
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
            dtcr.setHorizontalAlignment(SwingConstants.CENTER);
            jTable1.getColumnModel().getColumn(0).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(1).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(2).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(3).setCellRenderer(dtcr);
               jTable1.getColumnModel().getColumn(4).setCellRenderer(dtcr);
            jTable1.getColumnModel().getColumn(5).setCellRenderer(dtcr);
          

           
          
           
           
         

            jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_OFF);
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(280);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(280);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
             jTable1.getColumnModel().getColumn(4).setPreferredWidth(280);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(250);
       
            
           
          
          
           
           

            jTable1.getTableHeader().setForeground(new Color(006611));
            jTable1.setForeground(new Color(006611));
            String Ta = "Arial";
            int Bold = 0, size = 18;
            jTable1.getTableHeader().setFont(new Font(Ta, Bold, size));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

      
    
    /**
     * Creates new form ImportStudents
     */
    public ViewScheduleReport() {

        initComponents();
       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getMaximumWindowBounds();
        this.setSize(maxBounds.width, maxBounds.height);
        WindowClosingEventHandler();
        load1();
        
      

        Timer t = new Timer(1000, new sample());
        t.start();
    }

    public class sample implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Date d = new Date();
            SimpleDateFormat g1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat g = new SimpleDateFormat("hh:mm:ss a");
            setTitle("Course Summary");
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

    



      

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tot = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        tot.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        getContentPane().add(tot);
        tot.setBounds(12, 512, 199, 60);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(580, 90, 165, 34);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Select Semester");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(420, 80, 124, 46);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 51));
        jLabel3.setText("Schedule Report");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(600, 10, 230, 46);

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3);
        jComboBox3.setBounds(210, 90, 165, 34);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel4.setText("Select Degree");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(90, 80, 124, 46);

        jButton2.setBackground(new java.awt.Color(57, 53, 53));
        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Schedule Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(790, 90, 200, 42);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "School", "Term", "Semester", "Degree", "Course Number", "Section", "Faculty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 190, 1190, 340);

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
        jButton7.setBounds(610, 560, 140, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 String semester = null;
        String degree = null;
        String term = null;
        if (jComboBox1.getSelectedItem() != null) {
            semester = jComboBox1.getSelectedItem().toString();

        }
        if (jComboBox3.getSelectedItem() != null) {
            degree = jComboBox3.getSelectedItem().toString();

        }
        int j=0;
        int eligibleStudentCount = 0;
        ScheduleVO scheduleVO = new ScheduleVO();
        Iterator<String> degreeIt = CoursePlanner.degreeMap.keySet().iterator();
        while (degreeIt.hasNext()) {
            String deg = degreeIt.next();
            if (deg.equals(degree)) {
                DegreeVO degreeVO = CoursePlanner.degreeMap.get(deg);
                scheduleVO.setDegree(degreeVO);
            }
        }
        scheduleVO.setTerm(term);
        scheduleVO.setSemester(semester);
        Map<String, Integer> courseStudentMap = new HashMap<String, Integer>();
        Iterator<StudentVO> iterator = CoursePlanner.studentList.iterator();
        while (iterator.hasNext()) {
            StudentVO studentVO = iterator.next();
            if (studentVO.getDegree().equals(degree)) {
                Iterator<StudentCourseVO> iterator2 = CoursePlanner.studentCourseList.iterator();
                while (iterator2.hasNext()) {
                    StudentCourseVO studentCourseVO = iterator2.next();
                    if (studentCourseVO.getId().equals(studentVO.getId())) {
                        if (courseStudentMap.containsKey(studentCourseVO.getCourseNumber())) {
                            int studentCount = courseStudentMap.get(studentCourseVO.getCourseNumber());
                            studentCount++;
                            courseStudentMap.put(studentCourseVO.getCourseNumber(), studentCount);
                        } else {
                            courseStudentMap.put(studentCourseVO.getCourseNumber(), 1);
                        }
                    }
                }

            }
        }
 Iterator<String> rooms = CoursePlanner.roomMap.keySet().iterator();
 List<String> roomList = new ArrayList<String>();
 while(rooms.hasNext()){
     roomList.add(rooms.next());
 }
 scheduleVO.setRoomList(roomList);
        Iterator<Map.Entry<String, Integer>> coursesInDegree = courseStudentMap.entrySet().iterator();
           Map<String, List<SectionVO>> sectionsPerCourse = new HashMap<String, List<SectionVO>>();
            Map<String, List<FacultyVO>> facultyPerCourse = new HashMap<String, List<FacultyVO>>();
            Map<String, List<FacultyVO>> facultyMap = new HashMap<String, List<FacultyVO>>();
           Integer sectionNumber = 0;
        while (coursesInDegree.hasNext()) {
            Map.Entry<String, Integer> entry = coursesInDegree.next();
            String courseNumber = entry.getKey();
            CourseVO courseVO = CoursePlanner.courseMap.get(courseNumber);
            if(courseVO==null){
                continue;
            }
            scheduleVO.setCapacity(courseVO.getCapacity());
            int studentCount = entry.getValue();
           
           
           Iterator<FacultyVO> fIterator = CoursePlanner.facultyMap.values().iterator();
            FacultyVO facultyVO = null;
            String teachers = courseVO.getTeachers();
            while (teachers != null && teachers.length() > 0) {
                String faculty = null;
                if (teachers != null) {
                    faculty = teachers.indexOf(',') > 0 ? teachers.substring(0, teachers.indexOf(',')) : teachers;
                    while (fIterator.hasNext()) {

                        facultyVO = fIterator.next();

                        if (facultyVO.getLastName().trim().equals(faculty.trim()) || facultyVO.getLastName().trim().equals(faculty.substring(1).trim())) {
                            if (facultyMap.get(courseNumber) != null) {
                                List<FacultyVO> list = facultyMap.get(courseNumber);
                                list.add(facultyVO);
                                facultyMap.put(courseNumber, list);
                            } else {
                                List<FacultyVO> list = new ArrayList<FacultyVO>();
                                list.add(facultyVO);
                                facultyMap.put(courseNumber, list);
                            }
                            if (teachers.indexOf(teachers.indexOf(',')) > 0) {
                                teachers = teachers.substring(teachers.indexOf(',') + 1, teachers.length() - 1);
                                break;
                            } else {
                                teachers = null;
                                break;
                            }
                        }

                    }
              teachers = null;
                }
            } 
            int totalRooms = CoursePlanner.roomMap.size();
            
                
                    if (studentCount <= Integer.parseInt(courseVO.getCapacity())) {

                        sectionNumber++;
                        SectionVO sectionVO = new SectionVO();
                        sectionVO.setCourse(courseVO);
                        List<FacultyVO> facultyVOList = facultyMap.get(courseNumber);
                        Iterator<FacultyVO> it = facultyVOList.iterator();
                        while (it.hasNext()) {
                            FacultyVO facultyVO1 = it.next();
                            if (semester.contains("FA") && Integer.parseInt(facultyVO1.getMaxLoadfall()) >= Integer.parseInt(courseVO.getNumberOfHours())) {
                                sectionVO.setFaculty(facultyVO1);
                                Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadfall()) - Integer.parseInt(courseVO.getNumberOfHours()));
                                facultyVO1.setMaxLoadfall(newCapacity.toString());
                                break;
                            }
                            if (semester.contains("SU") && Integer.parseInt(facultyVO1.getMaxLoadSummer()) >= Integer.parseInt(courseVO.getNumberOfHours())) {
                                sectionVO.setFaculty(facultyVO1);
                                Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSummer()) - Integer.parseInt(courseVO.getNumberOfHours()));

                                facultyVO1.setMaxLoadSummer(newCapacity.toString());
                                break;
                            }
                            if (semester.contains("SP") && Integer.parseInt(facultyVO1.getMaxLoadSpring()) >= Integer.parseInt(courseVO.getNumberOfHours())) {
                                sectionVO.setFaculty(facultyVO1);
                                Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSpring()) - Integer.parseInt(courseVO.getNumberOfHours()));
                                facultyVO1.setMaxLoadSpring(newCapacity.toString());
                                break;
                            }
                        }
                      
                         String roomNumber =  null;
                        if(j<totalRooms){
                        roomNumber = roomList.get(j);
                        }
                        else{
                            j=0;
                            roomNumber = roomList.get(j);
                        }
                        j++;
                        RoomVO roomVO = CoursePlanner.roomMap.get(roomNumber);
                        sectionVO.setRoomNumber(roomVO.getRoomNo());
                        sectionVO.setSectionNumber(sectionNumber.toString());
                        sectionVO.setHeadCount(studentCount);
                        if (sectionsPerCourse.get(courseNumber) != null) {
                            List<SectionVO> sectionVOs = sectionsPerCourse.get(courseNumber);
                            sectionVOs.add(sectionVO);
                            sectionsPerCourse.put(courseNumber, sectionVOs);
                        } else {
                            List<SectionVO> sectionVOs = new ArrayList<SectionVO>();
                            sectionVOs.add(sectionVO);
                            sectionsPerCourse.put(courseNumber, sectionVOs);
                        }
                        scheduleVO.setSections(sectionsPerCourse);
                       
                    } else {
                        while(studentCount>0){
                        sectionNumber++;
                        SectionVO sectionVO = new SectionVO();
                        sectionVO.setCourse(courseVO);
                        sectionVO.setSectionNumber(sectionNumber.toString());
                        String roomNumber =  null;
                        if(j<totalRooms){
                        roomNumber = roomList.get(j);
                        }
                        else{
                            j=0;
                            roomNumber = roomList.get(j);
                        }
                        j++;
                        RoomVO roomVO = CoursePlanner.roomMap.get(roomNumber);
                        sectionVO.setHeadCount(Integer.parseInt(roomVO.getCapacity()));
                        studentCount = studentCount - sectionVO.getHeadCount();
                        sectionVO.setRoomNumber(roomVO.getRoomNo());
                        List<FacultyVO> facultyVOList = facultyMap.get(courseNumber);
                        Iterator<FacultyVO> it = facultyVOList.iterator();
                        while (it.hasNext()) {
                            FacultyVO facultyVO1 = it.next();
                            if (semester.contains("FA") && Integer.parseInt(facultyVO1.getMaxLoadfall()) >= Integer.parseInt(courseVO.getNumberOfHours())) {
                                sectionVO.setFaculty(facultyVO1);
                                Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadfall()) - Integer.parseInt(courseVO.getNumberOfHours()));
                                facultyVO1.setMaxLoadfall(newCapacity.toString());
                                break;
                            }
                            if (semester.contains("SU") && Integer.parseInt(facultyVO1.getMaxLoadSummer()) >= Integer.parseInt(courseVO.getNumberOfHours())) {
                                sectionVO.setFaculty(facultyVO1);
                                Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSummer()) - Integer.parseInt(courseVO.getNumberOfHours()));

                                facultyVO1.setMaxLoadSummer(newCapacity.toString());
                                break;
                            }
                            if (semester.contains("SP") && Integer.parseInt(facultyVO1.getMaxLoadSpring()) >= Integer.parseInt(courseVO.getNumberOfHours())) {
                                sectionVO.setFaculty(facultyVO1);
                                Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSpring()) - Integer.parseInt(courseVO.getNumberOfHours()));
                                facultyVO1.setMaxLoadSpring(newCapacity.toString());
                                break;
                            }
                        }
                        if (sectionsPerCourse.get(courseNumber) != null) {
                            List<SectionVO> sectionVOs = sectionsPerCourse.get(courseNumber);
                            sectionVOs.add(sectionVO);
                            sectionsPerCourse.put(courseNumber, sectionVOs);
                        } else {
                            List<SectionVO> sectionVOs = new ArrayList<SectionVO>();
                            sectionVOs.add(sectionVO);
                            sectionsPerCourse.put(courseNumber, sectionVOs);
                        }
                        scheduleVO.setSections(sectionsPerCourse);
                    }
                    }
                
        }

                   Map<String, List<SectionVO>> courseSections = scheduleVO.getSections();
         Iterator<Map.Entry<String,List<SectionVO>>> iterator3 = courseSections.entrySet().iterator();
       while(iterator3.hasNext()){
             Map.Entry<String,List<SectionVO>> course = iterator3.next();
           
             List<SectionVO> sections = course.getValue();
             Iterator<SectionVO> sectionIt = sections.iterator();
            while(sectionIt.hasNext()){
                 SectionVO sec = sectionIt.next(); 
                   String facultyName = sec.getFaculty()!=null ? sec.getFaculty().getLastName()+ "," + sec.getFaculty().getFirstName() :"To Be Announced/NA";
                   
                   s2.addRow(new Object[]{scheduleVO.getDegree().getGradSchool(),  scheduleVO.getSemester(),
                       scheduleVO.getDegree().getDegreeCode(),sec.getCourse().getNumber(),sec.getSectionNumber(),
                       facultyName
                         });
             }
       }
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewScheduleReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewScheduleReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewScheduleReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewScheduleReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ViewScheduleReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
