/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.planner;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.Timer;

import com.java.planner.vo.CourseVO;
import com.java.planner.vo.DegreePlanVO;
import com.java.planner.vo.DegreeVO;
import com.java.planner.vo.FacultyVO;
import com.java.planner.vo.RoomVO;
import com.java.planner.vo.SectionVO;
import com.java.planner.vo.StudentCourseVO;
import com.java.planner.vo.StudentVO;
import com.java.planner.vo.UserVO;

import sun.util.locale.StringTokenIterator;

/**
 *
 * @author sku263
 */
public class CoursePlanner extends javax.swing.JFrame {


    public static List<StudentCourseVO> studentCourseList = new ArrayList<StudentCourseVO>();
    public static List<StudentVO> studentList = new ArrayList<StudentVO>();
   //  public static List<CourseVO> courseList = new ArrayList<CourseVO>();
      public static Map<String,CourseVO> courseMap = new HashMap<String,CourseVO>();
       public static Map<String,FacultyVO> facultyMap = new HashMap<String,FacultyVO>();
          public static Map<String,DegreeVO> degreeMap = new HashMap<String,DegreeVO>();
           public static Map<String,UserVO> userMap = new HashMap<String,UserVO>();
            public static Map<String,RoomVO> roomMap = new HashMap<String,RoomVO>();
            public static Map<String,SectionVO> sectionMap = new HashMap<String,SectionVO>();
              public static Map<String,DegreePlanVO> planMap = new HashMap<String,DegreePlanVO>();
    /**
     * Creates new form CoursePlanner
     */
              
public class sample implements ActionListener
    {
    public void actionPerformed(ActionEvent e)
    {
    Date dd=new Date();
    SimpleDateFormat gg1=new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat gg=new SimpleDateFormat("hh:mm:ss a");
    date1.setText(gg1.format(dd));
    time1.setText(gg.format(dd));
    date1.setFont(new Font("Calibri", 1, 40));
    time1.setFont(new Font("Calibri", 1, 80));
    }
    }
    public CoursePlanner() {
        initComponents();
    //    loadStudents();
    //    loadStudentCourses();
        loadCourses();
        loadDegree();
        loadFaculty();
        loadDegreePlan();
        Timer to1=new Timer(1000,new sample());
        to1.start(); 
    }
    
    
  
    	  private void loadStudents(){
    	        BufferedReader br = null;
    		String line = "";
    		String cvsSplitBy = ",";

    		try {

    			br = new BufferedReader(new FileReader("STU.DUMP.csv"));
    			br.readLine();
    			while ((line = br.readLine()) != null) {
    	                    String[] input = line.split(cvsSplitBy);
    	                    
    	                    
    	                    StudentVO studentVO = new StudentVO();
    	                    studentVO.setId(input[0]);
    	                     studentVO.setDegree(input[1]);
    	                      studentVO.setGradDate(input[2]);
    	                    
    	                       CoursePlanner.studentList.add(studentVO);
    			       
    			}

    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    	
    	           }catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	  }
    	  
    	  private void loadStudentCourses(){
  	        BufferedReader br = null;
  		String line = "";
  		String cvsSplitBy = ",";

  		try {

  			br = new BufferedReader(new FileReader("STC.DUMP.csv"));
  			br.readLine();
  			while ((line = br.readLine()) != null) {
  	                    String[] input = line.split(cvsSplitBy);
  	                    
  	                    
  	                    StudentCourseVO studentVO = new StudentCourseVO();
  	                    studentVO.setId(input[0]);
  	                     studentVO.setCourseNumber(input[1]);
  	                      studentVO.setSemester(input[3]);
  	                      studentVO.setDesc(input[2]);
  	                      studentVO.setGrade(input[4]);
  	                    
  	                       CoursePlanner.studentCourseList.add(studentVO);
  			       
  			}

  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  	
  	           }catch (Exception e) {
  	            e.printStackTrace();
  	        }
  	  }

    	  private void loadCourses(){
    	        BufferedReader br = null;
    		String line = "";
    		String cvsSplitBy = ",";

    		try {

    			br = new BufferedReader(new FileReader("TestDataCourses.csv"));
    			br.readLine();
    			while ((line = br.readLine()) != null) {
    	                    String[] input = line.split(cvsSplitBy);
    	                    
    	                    
    	                    CourseVO courseVO = new CourseVO();
    	                   courseVO.setNumber(input[0]);
    	                   courseVO.setName(input[1]);
    	                   courseVO.setDescription(input[2]);
    	                   courseVO.setNumberOfHours(input[3]);
    	                   courseVO.setCapacity(input[4]);
    	                   courseVO.setAvailbleInfall(input[5]);
    	                   courseVO.setAvailableInSummer(input[7]);
    	                   courseVO.setAvailableInSpring(input[6]);
    	                   courseVO.setPreCourses(input[8]);
    	                   courseVO.setTeachers(input[9]);
    	                   CoursePlanner.courseMap.put(courseVO.getNumber(), courseVO);
    			       
    			}

    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    	
    	           }catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	  }

    	  private void loadDegree(){
  	        BufferedReader br = null;
  		String line = "";
  		String cvsSplitBy = ",";

  		try {

  			br = new BufferedReader(new FileReader("TestDataDegrees.csv"));
  			br.readLine();
  			while ((line = br.readLine()) != null) {
  	                    String[] input = line.split(cvsSplitBy);
  	                    
  	                    
  	                  DegreeVO degreeVO = new DegreeVO();
  	                degreeVO.setDegreeCode(input[0]);
  	                degreeVO.setDegreeName(input[1]);
  	                degreeVO.setGradSchool(input[2]);
  	                degreeVO.setForecast(input[3]);   
  	                   CoursePlanner.degreeMap.put(degreeVO.getDegreeCode(), degreeVO);
  			       
  			}

  		} catch (FileNotFoundException e) {
  			e.printStackTrace();
  		} catch (IOException e) {
  	
  	           }catch (Exception e) {
  	            e.printStackTrace();
  	        }
  	  }

    	  
    	  private void loadFaculty(){
    	        BufferedReader br = null;
    		String line = "";
    		String cvsSplitBy = ",";

    		try {

    			br = new BufferedReader(new FileReader("TestDataFaculty.csv"));
    			br.readLine();
    			while ((line = br.readLine()) != null) {
    	                    String[] input = line.split(cvsSplitBy);
    	                    
    	                    
    	                  FacultyVO facultyVO = new FacultyVO();
    	                  facultyVO.setLastName(input[0]);
    	                  facultyVO.setFirstName(input[1]);
    	                  facultyVO.setGradSchool(input[2]);
    	                  facultyVO.setDegree(input[3]);   
    	                  facultyVO.setTitle(input[4]);
    	                  facultyVO.setDyasToTeach(input[5]);
    	                  facultyVO.setMaxLoadfall(input[6]);
    	                  facultyVO.setMaxLoadSpring(input[7]);
    	                   facultyVO.setMaxLoadSummer(input[8]);
    	                  CoursePlanner.facultyMap.put(facultyVO.getLastName()+facultyVO.getFirstName(), facultyVO);
    	                   
    			       
    			}

    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    	
    	           }catch (Exception e) {
    	            e.printStackTrace();
    	        }
    	  }

      	  
    	  private void loadDegreePlan(){
    	        BufferedReader br = null;
    		String line = "";
    		String cvsSplitBy = ",";

    		try {

    			br = new BufferedReader(new FileReader("TestDataDegreePlanReq.csv"));
    			br.readLine();
    			while ((line = br.readLine()) != null) {
    	                    String[] input = line.split(cvsSplitBy);    	                    
    	                    
    	                  DegreePlanVO degreeVO = new DegreePlanVO();
    	                  degreeVO.setDegreeCode(input[0]);
    	                  degreeVO.setDepartment(input[1]);
    	                  degreeVO.setElectiveHrs(input[2]);
    	                  degreeVO.setRequiredHrs(input[3]);  
    	                  degreeVO.seteCourseAppended(input[4]);
    	                  degreeVO.setrCoursesAppended(input[5]);
    	                  String electiveCourses = input[2];
    	                  List<String> eCourses = new ArrayList<String>();
 	                	 
    	                  if(electiveCourses!=null ){
    	                	  StringTokenizer tokenizer = new StringTokenizer(electiveCourses, "|");
    	                      while(tokenizer.hasMoreTokens()){
    	                		  eCourses.add(tokenizer.nextToken());
    	                	  }
    	                  }
    	                  degreeVO.setElectiveCourses(eCourses);
    	                  List<String> rCourses = new ArrayList<String>();  	                	
    	                  String requiredCourses = input[4];
    	                  if(requiredCourses!=null ){
    	                	  StringTokenizer tokenizer = new StringTokenizer(requiredCourses, "|");
    	                    while(tokenizer.hasMoreTokens()){
    	                		  rCourses.add(tokenizer.nextToken());
    	                	  }
    	                  }
    	                  degreeVO.setRequiredCourses(rCourses);
    	                 
    	                   CoursePlanner.planMap.put(degreeVO.getDegreeCode(), degreeVO);
    			}

    		} catch (FileNotFoundException e) {
    			e.printStackTrace();
    		} catch (IOException e) {
    	
    	           }catch (Exception e) {
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

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        time1 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenu10 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu9 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 102, 204));

        jButton1.setBackground(new java.awt.Color(255, 51, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(12, 51, 118));
        jLabel2.setText("@2016");

        time1.setForeground(new java.awt.Color(153, 36, 213));

        date1.setForeground(new java.awt.Color(163, 86, 159));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(12, 51, 118));
        jLabel3.setText("Welcome Director");

        jMenuBar1.setBackground(new java.awt.Color(0, 204, 204));
        jMenuBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuBar1MouseClicked(evt);
            }
        });

        jMenu1.setBackground(new java.awt.Color(0, 204, 204));
        jMenu1.setText("Manage Students");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });

        jMenuItem1.setText("View Students");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("View Student Courses");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem2MouseClicked(evt);
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator1);

        jMenuItem3.setText("Exit");
        jMenuItem3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem3MouseClicked(evt);
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        jMenu2.setBackground(new java.awt.Color(0, 204, 204));
        jMenu2.setText("Manage Faculty");

        jMenuItem11.setText("View Faculty");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuBar1.add(jMenu2);

        jMenu3.setBackground(new java.awt.Color(0, 204, 204));
        jMenu3.setText("Manage Courses");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem4.setText("View Courses");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuBar1.add(jMenu3);

        jMenu4.setBackground(new java.awt.Color(0, 204, 204));
        jMenu4.setText("Manage Degree");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });

        jMenuItem5.setText("View Degree");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem5);

        jMenuBar1.add(jMenu4);

        jMenu10.setBackground(new java.awt.Color(0, 204, 204));
        jMenu10.setText("Manage Degree Plan");

        jMenuItem14.setText("View Plan");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu10.add(jMenuItem14);

        jMenuBar1.add(jMenu10);

        jMenu5.setBackground(new java.awt.Color(0, 204, 204));
        jMenu5.setText("Manage Users");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });

        jMenuItem6.setText("View Users");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuBar1.add(jMenu5);

        jMenu7.setBackground(new java.awt.Color(0, 204, 204));
        jMenu7.setText("Manage Sections");

        jMenuItem7.setText("View Sections");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem7);

        jMenuBar1.add(jMenu7);

        jMenu9.setBackground(new java.awt.Color(0, 204, 204));
        jMenu9.setText("Manage Rooms");

        jMenuItem9.setText("Manage Rooms");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu9.add(jMenuItem9);

        jMenuBar1.add(jMenu9);

        jMenu8.setBackground(new java.awt.Color(0, 204, 204));
        jMenu8.setText("Manage Schedule");

        jMenuItem8.setText("Generate Schedule");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem8);

        jMenuItem13.setText("Test Schedule");
        jMenu8.add(jMenuItem13);

        jMenuBar1.add(jMenu8);

        jMenu6.setBackground(new java.awt.Color(0, 204, 204));
        jMenu6.setText("View Reports");

        jMenuItem10.setText("Schedule Reports");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem10);

        jMenuItem12.setText("Student Reports");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem12);

        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(429, 429, 429)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(336, 336, 336)
                .addComponent(jButton1))
            .addGroup(layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(399, 399, 399)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(date1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(time1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
      ViewStudents importStudents = new ViewStudents();
importStudents.setVisible(true);
  setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
  
        ViewStudentCourses importStudents = new ViewStudentCourses();
importStudents.setVisible(true);
  setVisible(false);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu5MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jMenuItem2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2MouseClicked

    private void jMenuItem3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem3MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3MouseClicked

    private void jMenuBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuBar1MouseClicked
       
// TODO add your handling code here:
    }//GEN-LAST:event_jMenuBar1MouseClicked

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
         ViewCourses courses = new ViewCourses();
        courses.setVisible(true);
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jMenu3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
         ViewCourses courses = new ViewCourses();
        courses.setVisible(true);
        setVisible(false);//
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
ViewSections sections = new ViewSections();
sections.setVisible(true);
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
ViewFaculty faculty = new ViewFaculty();
faculty.setVisible(true);
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
ViewDegree degree = new ViewDegree();
degree.setVisible(true);
        setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        ViewUsers users = new ViewUsers();
users.setVisible(true);
        setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
ViewRooms rooms = new ViewRooms();
rooms.setVisible(true);
setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
ViewScheduleReport sr = new ViewScheduleReport();
sr.setVisible(true);
setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
       ViewStudentReport sr = new ViewStudentReport();
sr.setVisible(true);
setVisible(false); // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
Login login = new Login();
login.setVisible(true);
        setVisible(false);
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
ViewDegreePlan degreePlan = new ViewDegreePlan();
degreePlan.setVisible(true);
        setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
GenerateSchedule generateSchedule = new GenerateSchedule();
generateSchedule.setVisible(true);
        setVisible(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem8ActionPerformed

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
            java.util.logging.Logger.getLogger(CoursePlanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CoursePlanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CoursePlanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CoursePlanner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CoursePlanner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu10;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenu jMenu9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel time1;
    // End of variables declaration//GEN-END:variables
}
