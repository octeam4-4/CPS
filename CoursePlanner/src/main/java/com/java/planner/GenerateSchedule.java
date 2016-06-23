/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.planner;

import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.java.planner.vo.CourseVO;
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
public class GenerateSchedule extends javax.swing.JFrame {

   
       
 

    void loaditem() {
    
            
           
        

	try {
            Map<String,CourseVO>  courseMap = CoursePlanner.courseMap;
            Iterator<CourseVO> iterator = courseMap.values().iterator();
		while(iterator.hasNext()){
                    CourseVO courseVO = iterator.next();
                     s2.addRow(new Object[]{courseVO.getNumber() , courseVO.getName(), courseVO.getDescription(), courseVO.getNumberOfHours(), courseVO.getCapacity(), courseVO.getAvailbleInfall(),courseVO.getAvailableInSpring(), courseVO.getAvailableInSummer(),courseVO.getPreCourses(), courseVO.getTeachers()});
	       
		}

	     }catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    void loaditemFromSheet() {
        try {
           

            
           
            String csvFile = FILEPATH;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";

	try {

		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
                    String[] input = line.split(cvsSplitBy);

                     s2.addRow(new Object[]{input[0] , input[1], input[2], input[3],input[4] , input[5], input[6], input[7],input[8],input[9]});

		       
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
    public GenerateSchedule() {

        initComponents();
       // setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/images/icon.png")));
        Rectangle maxBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().
                getMaximumWindowBounds();
        this.setSize(maxBounds.width, maxBounds.height);
        WindowClosingEventHandler();
        load1();
        loaditem();
   //loaditem();

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
                        CoursePlanner me = new CoursePlanner();
                        me.setVisible(true);
                        setVisible(false);
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
        try{
            
            Object plan[]=new Object[1];
            plan[0] = "2016/2017";
            jComboBox2.removeAllItems();
AutoCompleteSupport support = AutoCompleteSupport.install(
jComboBox2, GlazedLists.eventListOf(plan));
 Object degree[]=new Object[CoursePlanner.degreeMap.size()];
 
 int j=0;
 Iterator<String> deg = CoursePlanner.degreeMap.keySet().iterator();
 while(deg.hasNext()){
            degree[j] = deg.next();
            j++;
 }       
            jComboBox3.removeAllItems();
AutoCompleteSupport support2 = AutoCompleteSupport.install(
jComboBox3, GlazedLists.eventListOf(degree));
     Object semester[]=new Object[6];
  
            semester[0] = "2016SP";
semester[1] = "2016SU";
semester[2]="2016FA";
semester[3]="2017SP";
semester[4]="2017SU";
semester[5] ="2017FA";

       
            jComboBox1.removeAllItems();
AutoCompleteSupport support3 = AutoCompleteSupport.install(
jComboBox1, GlazedLists.eventListOf(semester));  
        } catch (Exception e) {
            e.printStackTrace();
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

        tot = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tot.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("Generate Schedule");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Term");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Select Semester");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Select Degree");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(1503, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1725, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tot, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(179, 179, 179)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(380, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    String semester =null;
    String degree = null;
        if(jComboBox1.getSelectedItem()!=null){
            semester = jComboBox1.getSelectedItem().toString();
         
     }
          if(jComboBox3.getSelectedItem()!=null){
            degree = jComboBox3.getSelectedItem().toString();
         
     }
          
          int eligibleStudentCount=0;
          ScheduleVO scheduleVO = new ScheduleVO();
          scheduleVO.setDegree(degree);
          scheduleVO.setSemester(semester);
          Map<String,Integer> courseStudentMap = new HashMap<String,Integer>();
          Iterator<StudentVO> iterator = CoursePlanner.studentList.iterator();
          while(iterator.hasNext()){
              StudentVO  studentVO = iterator.next();              
              if(studentVO.getDegree().equals(degree)){
                   Iterator<StudentCourseVO> iterator2 = CoursePlanner.studentCourseList.iterator();            
                  while(iterator2.hasNext()){
                      StudentCourseVO studentCourseVO = iterator2.next();
                      if(studentCourseVO.getId().equals(studentVO.getId()) && studentCourseVO.getSemester().equals(semester)){
                          if(courseStudentMap.containsKey(studentCourseVO.getCourseNumber())){
                          int studentCount = courseStudentMap.get(studentCourseVO.getCourseNumber());
                          courseStudentMap.put(studentCourseVO.getCourseNumber(), studentCount++);
                                  }
                          else{
                              courseStudentMap.put(studentCourseVO.getCourseNumber(), 1);
                          }
                      }
                  }
                  
              }
          }
          
          Iterator<Map.Entry<String,Integer>> coursesInDegree = courseStudentMap.entrySet().iterator();
          while(coursesInDegree.hasNext()){
             Entry<String,Integer> entry = coursesInDegree.next();
             String courseNumber = entry.getKey();
             CourseVO courseVO = CoursePlanner.courseMap.get(courseNumber);
             int studentCount = entry.getValue();
             Iterator<RoomVO> rooms = CoursePlanner.roomMap.values().iterator();
             Integer sectionNumber =0;
             Map<String,List<SectionVO>> sectionsPerCourse = new HashMap<String,List<SectionVO>>();
             Map<String,List<FacultyVO>> facultyPerCourse = new HashMap<String,List<FacultyVO>>();
              Map<String,List<FacultyVO>> facultyMap = new HashMap<String,List<FacultyVO>>();
              Iterator<FacultyVO> fIterator = CoursePlanner.facultyMap.values().iterator();
              FacultyVO facultyVO = null;
             String teachers = courseVO.getTeachers();
             while(teachers.length()>0){
                     String faculty = null;
                     if(teachers!=null){
                         faculty = teachers.indexOf(",")>0?teachers.substring(0, teachers.indexOf(",")) : teachers;
                         while(fIterator.hasNext()){
                            facultyVO = fIterator.next();
                            if(facultyVO.getLastName().contains(faculty) ){
                                if(facultyMap.get(courseNumber)!=null){
                                    List<FacultyVO> list = facultyMap.get(courseNumber);
                                    list.add(facultyVO);
                                facultyMap.put(courseNumber, list);
                                }
                                else{
                                     List<FacultyVO> list = new ArrayList<FacultyVO>();
                                    list.add(facultyVO);
                                facultyMap.put(courseNumber, list);
                                }
                         }
                         
                          if(teachers.indexOf(teachers.indexOf(","))>0){
                             teachers = teachers.substring(teachers.indexOf(",")+1,teachers.length()-1);
                         }
                         }
                         teachers= null;
                     }
                         } 
                   
       
             while(rooms.hasNext()){
                 RoomVO roomVO = rooms.next();
                 if(studentCount<=Integer.parseInt(courseVO.getCapacity()))
                 {                    
                     
                     sectionNumber++;
                     SectionVO sectionVO = new SectionVO();
                     sectionVO.setCourse(courseNumber);
                     List<FacultyVO> facultyVOList = facultyMap.get(courseNumber);
                     Iterator<FacultyVO> it = facultyVOList.iterator();
                     while(it.hasNext()){
                   FacultyVO facultyVO1 = it.next();
                   if(semester.contains("FA") && Integer.parseInt(facultyVO1.getMaxLoadfall())>= Integer.parseInt(courseVO.getNumberOfHours())){                  
                     sectionVO.setFaculty(facultyVO1.getFirstName() + facultyVO1.getLastName());
                     Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadfall())- Integer.parseInt(courseVO.getNumberOfHours()));
                     facultyVO1.setMaxLoadfall(newCapacity.toString());
                     break;
                   }
                   if(semester.contains("SU") && Integer.parseInt(facultyVO1.getMaxLoadSummer())>= Integer.parseInt(courseVO.getNumberOfHours())){                  
                     sectionVO.setFaculty(facultyVO1.getFirstName() + facultyVO1.getLastName());
                        Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSummer())- Integer.parseInt(courseVO.getNumberOfHours()));
                   
                     facultyVO1.setMaxLoadSummer(newCapacity.toString());
                     break;
                   }
                    if(semester.contains("SP") && Integer.parseInt(facultyVO1.getMaxLoadSpring())>= Integer.parseInt(courseVO.getNumberOfHours())){                  
                     sectionVO.setFaculty(facultyVO1.getFirstName() + facultyVO1.getLastName());
                       Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSpring())- Integer.parseInt(courseVO.getNumberOfHours()));                   
                     facultyVO1.setMaxLoadSpring(newCapacity.toString());
                     break;
                   }
                     }
                     
                     sectionVO.setSectionNumber(sectionNumber.toString());
                     sectionVO.setHeadCount(studentCount);
                     if(sectionsPerCourse.get(courseNumber)!=null){
                         List<SectionVO> sectionVOs = sectionsPerCourse.get(courseNumber);
                         sectionVOs.add(sectionVO);
                         sectionsPerCourse.put(courseNumber, sectionVOs);
                     }
                     else{
                          List<SectionVO> sectionVOs = new ArrayList<SectionVO>();
                          sectionVOs.add(sectionVO);
                          sectionsPerCourse.put(courseNumber, sectionVOs);
                     }
                     break;
                 }
                 else{
                     sectionNumber++;
                     SectionVO sectionVO = new SectionVO();
                     sectionVO.setCourse(courseNumber);
                     sectionVO.setSectionNumber(sectionNumber.toString());
                     sectionVO.setHeadCount(Integer.parseInt(roomVO.getCapacity()));
                     studentCount = studentCount-sectionVO.getHeadCount();
                      List<FacultyVO> facultyVOList = facultyMap.get(courseNumber);
                     Iterator<FacultyVO> it = facultyVOList.iterator();
                     while(it.hasNext()){
                   FacultyVO facultyVO1 = it.next();
                   if(semester.contains("FA") && Integer.parseInt(facultyVO1.getMaxLoadfall())>= Integer.parseInt(courseVO.getNumberOfHours())){                  
                     sectionVO.setFaculty(facultyVO1.getFirstName() + facultyVO1.getLastName());
                     Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadfall())- Integer.parseInt(courseVO.getNumberOfHours()));
                     facultyVO1.setMaxLoadfall(newCapacity.toString());
                     break;
                   }
                   if(semester.contains("SU") && Integer.parseInt(facultyVO1.getMaxLoadSummer())>= Integer.parseInt(courseVO.getNumberOfHours())){                  
                     sectionVO.setFaculty(facultyVO1.getFirstName() + facultyVO1.getLastName());
                        Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSummer())- Integer.parseInt(courseVO.getNumberOfHours()));
                   
                     facultyVO1.setMaxLoadSummer(newCapacity.toString());
                     break;
                   }
                    if(semester.contains("SP") && Integer.parseInt(facultyVO1.getMaxLoadSpring())>= Integer.parseInt(courseVO.getNumberOfHours())){                  
                     sectionVO.setFaculty(facultyVO1.getFirstName() + facultyVO1.getLastName());
                       Integer newCapacity = (Integer.parseInt(facultyVO1.getMaxLoadSpring())- Integer.parseInt(courseVO.getNumberOfHours()));                   
                     facultyVO1.setMaxLoadSpring(newCapacity.toString());
                     break;
                   }
                     }
                      if(sectionsPerCourse.get(courseNumber)!=null){
                         List<SectionVO> sectionVOs = sectionsPerCourse.get(courseNumber);
                         sectionVOs.add(sectionVO);
                         sectionsPerCourse.put(courseNumber, sectionVOs);
                     }
                     else{
                          List<SectionVO> sectionVOs = new ArrayList<SectionVO>();
                          sectionVOs.add(sectionVO);
                          sectionsPerCourse.put(courseNumber, sectionVOs);
                     }
                 }
             }
             
             
              
              
          }
          
          
        
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(GenerateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GenerateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GenerateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GenerateSchedule.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GenerateSchedule().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel tot;
    // End of variables declaration//GEN-END:variables
}
