package com.prog2.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MyWindow {
	
	private JFrame frmPcProject;
	private JTextField teacherFirstName;
	private JTextField teacherLastName;
	private JTextField teacherSIN;
	private JTextField teacherEmail;
	private JTextField teacherAddress;
	private JTextField teacherSpecialty;
	private JTextField teacherHours;
	private JTextField staffFirstName;
	private JTextField staffLastName;
	private JTextField staffEmail;
	private JTextField staffAddress;
	private JTextField tbStaffDuty;
	private JTextField staffSIN;
	private JTextField tbStaffWorkload;
	private JTextField tbStaffDepartment;
	private JTextField teacherDeptID;
	private JTextField teacherDegree;
	private JTextArea employeeInfoTextArea;
	private JPanel addEmployeePanel;
	private static ArrayList<Department> departmentList;
	public static String path = "./school_data.ser";
	private JLabel lblNewLabel_12;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// load department list	
		departmentList = getDepartments();

		// display main window
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyWindow window = new MyWindow();
					window.frmPcProject.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyWindow() {
		initialize();
		// populate employee info tab when GUI window is opened
		refreshEmployeesList();
	}
	
	// check if the teacher we are trying to input already exists in the system
	public boolean teacherIsDuplicate(Teacher t, ArrayList<Teacher> tlist) throws DuplicateTeacherException{
		boolean isDuplicate = false;
		for (int i = 0; i < tlist.size(); i++) {
			if(tlist.get(i).equals(t)) {
				isDuplicate = true;
			}
		}
		return isDuplicate;
	}
	
	// check if the staff member we are trying to input already exists in the system
	public boolean staffIsDuplicate(Staff s, ArrayList<Staff> slist) throws DuplicateStaffException{
		boolean isDuplicate = false;
		for (int i = 0; i < slist.size(); i++) {
			if(slist.get(i).equals(s)) {
				isDuplicate = true;
			}
		}
		return isDuplicate;
	}
	
	// refreshes the displayed list of employees
	public void refreshEmployeesList() {
		String output = "";
		for (int i = 0; i < departmentList.size(); i++) {
			output += departmentList.get(i).toString()+ "\n";
		}
		employeeInfoTextArea.setText(output.trim());
	}
	
	// clear entry fields when employee added successfully
	public void clearFields() {
			
		for (int i = 0; i < addEmployeePanel.getComponentCount(); i++) {
			Component c = addEmployeePanel.getComponent(i);
			if (c instanceof JTextField) {
				JTextField textField = (JTextField) c;
				textField.setText("");
			}
			else {
				// Uncheck all check boxes
				if (c instanceof JCheckBox) {
					JCheckBox chkBox = (JCheckBox) c;
					chkBox.setSelected(false);
				}
			}
		}
	}
	
	public static ArrayList<Department> getDepartments() {
		// INITIALIZE DEPARTMENT LIST
		ArrayList<Department> departments = new ArrayList<Department>();
		departments.add(new Department(new ArrayList<Teacher>(), new ArrayList<Staff>(), "BIOLOGY", "1"));
		departments.add(new Department(new ArrayList<Teacher>(), new ArrayList<Staff>(), "CHEMISTRY", "2"));
		departments.add(new Department(new ArrayList<Teacher>(), new ArrayList<Staff>(), "PHYSICS", "3"));
		
		// read departments from file IF IT EXISTS
	    try ( FileInputStream fis = new FileInputStream(path)) {
	        // step 2 create an ObjectInputStream object
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        // step 3 call readObject() to read the object from a file
	        departments = (ArrayList<Department>) ois.readObject();
	    } catch (Exception e) {	        
	    }
	    
	    return departments;
	}
	
		public void saveDepartments(ArrayList<Department> d) {
		
	    try (FileOutputStream fos = new FileOutputStream(path)) {
	        // step 2 create an ObjectOutputStream object
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        // step 3 call writeObject() to write the object to a file
	        oos.writeObject(d);
	    } catch (Exception e) {
	    	e.getStackTrace();
	    }
	}
	
	// validates the users input. returns an error message if the input is invalid.
	// otherwise returns a null string. 
	public String getValidationFailureMessage(String employeeType, 
			JTextField LastNameField, 
			JTextField FirstNameField, 
			JTextField addressField,
			JTextField emailField, 
			JTextField sinField, 
			JTextField deptField, 
			JTextField dutyField, 
			JTextField workloadField, 
			JTextField degreeField, 
			JTextField specialtyField, 
			JTextField hoursWorkedField) {
		
		if (FirstNameField.getText().equals("")) {
			return "first name required";
		}		
		if (LastNameField.getText().equals("")) {
			return "last name required";
		}
		
		if (addressField.getText().equals("")) {
			return "address required";
		}
	
		if (emailField.getText().equals("")) {
			return "email required";
		}
		try {
			Integer.parseInt(sinField.getText());
		} catch(NumberFormatException n){
			return "valid SIN required";
		}
		try {
			Integer.parseInt(deptField.getText());
		} catch(NumberFormatException n){
			return "valid department ID required";
		}
		if(employeeType.equals("staff")) {
			if (dutyField.getText().equals("")) {
				return "duty required";
			}
			try {
				int workload = Integer.parseInt(workloadField.getText());
				if(workload > 40) {
					return "workload cannot exceed 40 hours";
				}
			} catch(NumberFormatException n){
				return "valid workload required";
			}
		}
		if(employeeType.equals("teacher")) {
			if (degreeField.getText().equals("")) {
				return "degree required";
			}
			String degree = degreeField.getText().toLowerCase();
			if(!degree.equals("bachelor") && !degree.equals("master") && !degree.equals("phd")) {
				return "please enter BACHELOR, MASTER or PHD";
			}
			if (specialtyField.getText().equals("")) {
				return "specialty required";
			}
			try {
				Integer.parseInt(hoursWorkedField.getText());
			} catch(NumberFormatException n){
				return "hours worked required";
			}
		}
		return null;
	}
	
	// Initialize the contents of the frame.	
	private void initialize() {
		frmPcProject = new JFrame();
		frmPcProject.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				saveDepartments(departmentList);
				// serialize here
			}
		});
		frmPcProject.setTitle("PC2 Project");
		frmPcProject.setBounds(100, 100, 567, 560);
		frmPcProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPcProject.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 533, 510);
		frmPcProject.getContentPane().add(tabbedPane);

		addEmployeePanel = new JPanel();
		tabbedPane.addTab("Add Employee", null, addEmployeePanel, null);
		addEmployeePanel.setLayout(null);

		JLabel teacherLabel = new JLabel("TEACHER");
		teacherLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		teacherLabel.setBounds(10, 11, 118, 35);
		addEmployeePanel.add(teacherLabel);

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 44, 171, 2);
		addEmployeePanel.add(separator);

		JLabel firstName = new JLabel("First Name");
		firstName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		firstName.setBounds(10, 57, 108, 14);
		addEmployeePanel.add(firstName);

		teacherFirstName = new JTextField();
		teacherFirstName.setBounds(85, 55, 96, 20);
		addEmployeePanel.add(teacherFirstName);
		teacherFirstName.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 91, 70, 14);
		addEmployeePanel.add(lblNewLabel_2);

		teacherLastName = new JTextField();
		teacherLastName.setBounds(85, 89, 96, 20);
		addEmployeePanel.add(teacherLastName);
		teacherLastName.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("SIN NUMBER");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 301, 82, 14);
		addEmployeePanel.add(lblNewLabel_3);

		teacherSIN = new JTextField();
		teacherSIN.setBounds(95, 299, 86, 20);
		addEmployeePanel.add(teacherSIN);
		teacherSIN.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Email");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(10, 122, 49, 14);
		addEmployeePanel.add(lblNewLabel_4);

		teacherEmail = new JTextField();
		teacherEmail.setBounds(85, 120, 96, 20);
		addEmployeePanel.add(teacherEmail);
		teacherEmail.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(10, 155, 49, 14);
		addEmployeePanel.add(lblNewLabel_5);

		teacherAddress = new JTextField();
		teacherAddress.setBounds(85, 151, 96, 20);
		addEmployeePanel.add(teacherAddress);
		teacherAddress.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Specialty");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(10, 184, 70, 14);
		addEmployeePanel.add(lblNewLabel_6);

		teacherSpecialty = new JTextField();
		teacherSpecialty.setBounds(85, 182, 96, 20);
		addEmployeePanel.add(teacherSpecialty);
		teacherSpecialty.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("Degree");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(10, 214, 49, 14);
		addEmployeePanel.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("Hours Worked");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(10, 356, 108, 14);
		addEmployeePanel.add(lblNewLabel_8);

		teacherHours = new JTextField();

		teacherHours.setBounds(105, 354, 49, 20);
		addEmployeePanel.add(teacherHours);
		teacherHours.setColumns(10);

		JCheckBox partTimeCheckBox = new JCheckBox("PART TIME");

		partTimeCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		partTimeCheckBox.setBounds(6, 322, 99, 23);
		addEmployeePanel.add(partTimeCheckBox);

		JCheckBox deanCheckBox = new JCheckBox("DEPARTMENT DEAN");

		deanCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		deanCheckBox.setBounds(107, 322, 153, 23);
		addEmployeePanel.add(deanCheckBox);

		JLabel lblNewLabel_9 = new JLabel("Department ID");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9.setBounds(10, 256, 118, 14);
		addEmployeePanel.add(lblNewLabel_9);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(270, 44, 2, 381);
		addEmployeePanel.add(separator_1);

		JLabel lblNewLabel_10 = new JLabel("STAFF");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_10.setBounds(468, 21, 49, 14);
		addEmployeePanel.add(lblNewLabel_10);

		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(346, 44, 171, 2);
		addEmployeePanel.add(separator_2);

		JLabel lblNewLabel_1_1 = new JLabel("First Name");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(346, 58, 108, 14);
		addEmployeePanel.add(lblNewLabel_1_1);

		staffFirstName = new JTextField();
		staffFirstName.setColumns(10);
		staffFirstName.setBounds(421, 55, 96, 20);
		addEmployeePanel.add(staffFirstName);

		JLabel lblNewLabel_2_1 = new JLabel("Last Name");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2_1.setBounds(346, 91, 70, 14);
		addEmployeePanel.add(lblNewLabel_2_1);

		staffLastName = new JTextField();
		staffLastName.setColumns(10);
		staffLastName.setBounds(421, 89, 96, 20);
		addEmployeePanel.add(staffLastName);

		staffEmail = new JTextField();
		staffEmail.setColumns(10);
		staffEmail.setBounds(421, 120, 96, 20);
		addEmployeePanel.add(staffEmail);

		staffAddress = new JTextField();
		staffAddress.setColumns(10);
		staffAddress.setBounds(421, 153, 96, 20);
		addEmployeePanel.add(staffAddress);

		tbStaffDuty = new JTextField();
		tbStaffDuty.setColumns(10);
		tbStaffDuty.setBounds(421, 212, 96, 20);
		addEmployeePanel.add(tbStaffDuty);

		JLabel lblNewLabel_4_1 = new JLabel("Email");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4_1.setBounds(346, 122, 49, 14);
		addEmployeePanel.add(lblNewLabel_4_1);

		JLabel lblNewLabel_5_1 = new JLabel("Address");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5_1.setBounds(346, 155, 49, 14);
		addEmployeePanel.add(lblNewLabel_5_1);

		JLabel lblNewLabel_11 = new JLabel("Duty");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_11.setBounds(346, 214, 49, 14);
		addEmployeePanel.add(lblNewLabel_11);

		JLabel lblNewLabel_3_1 = new JLabel("SIN NUMBER");
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3_1.setBounds(346, 185, 82, 14);
		addEmployeePanel.add(lblNewLabel_3_1);

		staffSIN = new JTextField();
		staffSIN.setColumns(10);
		staffSIN.setBounds(431, 182, 86, 20);
		addEmployeePanel.add(staffSIN);

		JLabel lblNewLabel_8_1 = new JLabel("Workload");
		lblNewLabel_8_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8_1.setBounds(346, 252, 108, 14);
		addEmployeePanel.add(lblNewLabel_8_1);

		tbStaffWorkload = new JTextField();
		tbStaffWorkload.setColumns(10);
		tbStaffWorkload.setBounds(468, 250, 49, 20);
		addEmployeePanel.add(tbStaffWorkload);

		JLabel lblNewLabel_9_1 = new JLabel("Department ID");
		lblNewLabel_9_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_9_1.setBounds(346, 281, 118, 14);
		addEmployeePanel.add(lblNewLabel_9_1);

		tbStaffDepartment = new JTextField();
		tbStaffDepartment.setColumns(10);
		tbStaffDepartment.setBounds(447, 279, 70, 20);
		addEmployeePanel.add(tbStaffDepartment);

		JButton btnAddTeacher = new JButton("ADD TEACHER");

//				else {
//					Teacher teacher = new Teacher(null, null, 0, null, null, null, null, false, 0);
//				}

		btnAddTeacher.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddTeacher.setBounds(28, 402, 153, 23);
		addEmployeePanel.add(btnAddTeacher);

		JButton btnAddStaff = new JButton("ADD STAFF");
		
		btnAddStaff.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddStaff.setBounds(346, 402, 153, 23);
		addEmployeePanel.add(btnAddStaff);

		lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_12.setBounds(10, 436, 507, 41);
		addEmployeePanel.add(lblNewLabel_12);

		teacherDeptID = new JTextField();
		teacherDeptID.setBounds(124, 250, 57, 20);
		addEmployeePanel.add(teacherDeptID);
		teacherDeptID.setColumns(10);

		teacherDegree = new JTextField();
		teacherDegree.setBounds(95, 213, 86, 20);
		addEmployeePanel.add(teacherDegree);
		teacherDegree.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("BIOLOGY = 1 / CHEMISTRY = 2 / PHYSICS = 3");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel.setBounds(10, 276, 238, 14);
		addEmployeePanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("BACHELOR / MASTER / PHD");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
		lblNewLabel_1.setBounds(10, 231, 171, 14);
		addEmployeePanel.add(lblNewLabel_1);
		
		JPanel employeesPanel = new JPanel();
		tabbedPane.addTab("Employee Information", null, employeesPanel, null);
		employeesPanel.setLayout(null);

		employeeInfoTextArea = new JTextArea();
		employeeInfoTextArea.setEditable(false);
		employeeInfoTextArea.setVisible(true);
		employeeInfoTextArea.setBounds(0, 0, 520, 470);
		employeeInfoTextArea.setLineWrap(true);
		
		JScrollPane scroll = new JScrollPane (employeeInfoTextArea, 
		JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVisible(true);
		scroll.setBounds(0, 0, 530, 480);
		//scroll.setBounds(0, 0, 582, 523);
		employeesPanel.add(scroll);
		
		btnAddStaff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String error = getValidationFailureMessage(
						"staff",
						staffLastName,
						staffFirstName,
						staffAddress,
						staffEmail,
						staffSIN,
						tbStaffDepartment,
						tbStaffDuty,
						tbStaffWorkload,
						null,
						null,
						null);
				if(error != null){
					lblNewLabel_12.setText(error);
					return;
				}
				else {
					try {
						Staff staff = new Staff(staffFirstName.getText(),
								staffLastName.getText(), 
								Integer.parseInt(staffSIN.getText()), 
								staffEmail.getText(), 
								staffAddress.getText(), 
								tbStaffDuty.getText(), 
								Integer.parseInt(tbStaffWorkload.getText()));
						
						boolean recordAdded = false;
						for (int j = 0; j < departmentList.size(); j++) {

							if (tbStaffDepartment.getText().equals(departmentList.get(j).getDeptID())) {
								boolean isDuplicate = staffIsDuplicate(staff,
										departmentList.get(j).getStaff());
								if (isDuplicate) {
									throw new DuplicateStaffException("staff member already exists");
								} else {
									departmentList.get(j).getStaff().add(staff);
									lblNewLabel_12.setText("staff member added successfully");
									recordAdded = true;
								}
							}

						}
						if(recordAdded) {
							clearFields();
							refreshEmployeesList();
						}
						else {
							throw new DepartmentNotFoundException("department not found");
						}
					} catch (DepartmentNotFoundException e2) {
						lblNewLabel_12.setText(e2.getMessage());
					} catch (DuplicateStaffException e4) {
						lblNewLabel_12.setText(e4.getMessage());
					} catch (Exception e1) {
						lblNewLabel_12.setText(e1.getMessage());
					}
				}
			}
		
		});

		btnAddTeacher.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// return if input is invalid
				String error = getValidationFailureMessage(
						"teacher",
						teacherLastName,
						teacherFirstName,
						teacherAddress,
						teacherEmail,
						teacherSIN,
						teacherDeptID,
						null,
						null,
						teacherDegree,
						teacherSpecialty,
						teacherHours);
				if(error != null){
					lblNewLabel_12.setText(error);
					return;
				} 
				
				// add the entry	
				try {
					if (deanCheckBox.isSelected()) {
						Dean dean = new Dean(teacherFirstName.getText(), teacherLastName.getText(),
								Integer.parseInt(teacherSIN.getText()), teacherEmail.getText(),
								teacherAddress.getText(), teacherSpecialty.getText(), teacherDegree.getText(),
								!partTimeCheckBox.isSelected(), Integer.parseInt(teacherHours.getText()));
						boolean recordAdded = false;
						for (int j = 0; j < departmentList.size(); j++) {

							if (teacherDeptID.getText().equals(departmentList.get(j).getDeptID())) {
								boolean isDuplicate = teacherIsDuplicate(dean,
										departmentList.get(j).getTeachers());
								if (isDuplicate) {
									throw new DuplicateTeacherException("dean already exists");
								} else {
									departmentList.get(j).getTeachers().add(dean);
									lblNewLabel_12.setText("dean added successfully");
									recordAdded = true;
								}
							}

						}
						if(recordAdded) {
							clearFields();
							refreshEmployeesList();
						}
						else {
							throw new DepartmentNotFoundException("department not found");
						}
					}
					else {
						Teacher teacher = new Teacher(teacherFirstName.getText(), teacherLastName.getText(),
								Integer.parseInt(teacherSIN.getText()), teacherEmail.getText(),
								teacherAddress.getText(), teacherSpecialty.getText(), teacherDegree.getText(),
								!partTimeCheckBox.isSelected(), Integer.parseInt(teacherHours.getText()));
					
					boolean recordAdded = false;
					for (int j = 0; j < departmentList.size(); j++) {

						if (teacherDeptID.getText().equals(departmentList.get(j).getDeptID())) {
							boolean isDuplicate = teacherIsDuplicate(teacher,
									departmentList.get(j).getTeachers());
							if (isDuplicate) {
								throw new DuplicateTeacherException("teacher already exists");
							} else {
								departmentList.get(j).getTeachers().add(teacher);
								lblNewLabel_12.setText("teacher added successfully");
								recordAdded = true;
							}
						}
					}
					if(recordAdded) {
						clearFields();
						refreshEmployeesList();
					}
					else {
						throw new DepartmentNotFoundException("department not found");
					}}
				} catch (DepartmentNotFoundException e2) {
					lblNewLabel_12.setText(e2.getMessage());
				} catch (DuplicateTeacherException e4) {
					lblNewLabel_12.setText(e4.getMessage());
				} catch (Exception e1) {
//					System.out.println(e1.getMessage());
					lblNewLabel_12.setText(e1.getMessage());
				}}

			

		});
	}
}
