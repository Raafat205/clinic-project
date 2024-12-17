package javaapplication2;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public abstract class Admin {

    public abstract void Managing_Appointments();
    public abstract void Prescribe_medications();
    public abstract void view_reports();

    /////////////////////////////////////////////////EDITS
    
    void edit_doc() {
        list_doc();
        System.out.print("Which department does the doctor belong to? \n1. Cardiology Clinic.\n2. Dental Clinic.\n3. Dermatology Clinic.\n4. Emergency Clinic.\n");
        int d_s = JavaApplication2.in.nextInt();
        if (JavaApplication2.Doctors.containsKey(d_s)) {
            System.out.print("id :");
            int id=JavaApplication2.in.nextInt();
            ArrayList<Doctor> doctors = JavaApplication2.Doctors.get(d_s);
            
            for (Doctor doctor : doctors) {
                if(doctor.doctor_id ==id){
                System.out.println("What do you want to edit? \n1.Name \n2.Specialization \n3.Exit");
                
                int choice = JavaApplication2.in.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter  the new name of the doctor: ");
                        String newName = JavaApplication2.in.next();
                        doctor.doctor_name = newName;
                        System.out.println("Doctor's name was updated to " + newName);
                        break;
                        
                    
                    case 2:
                        System.out.print("Enter the new specialization for the doctor : ");
                        String newSpecialization = JavaApplication2.in.next();
                        doctor.specialization = newSpecialization;
                        System.out.println("Doctor's specialization was updated to " + newSpecialization);
                        break;
                    
                    case 3:
                        System.out.println("Exiting doctor edit.");
                        break;
                    
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            try(RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt", "rw")){
                 raf.setLength(0);
                 raf.writeBytes(String.valueOf(JavaApplication2.doc_c) + "\n");
            for (int department = 1; department <= JavaApplication2.department.size(); department++) {
                if (JavaApplication2.Doctors.containsKey(department)) {
                    for (Doctor z : JavaApplication2.Doctors.get(department)) {
                        raf.writeBytes(z.doctor_id+ " " + z.doctor_name+ " " + z.specialization + "\n");
                    }
                }
            }
            }catch(Exception e){
            System.out.println(e);
        }
            break;
        }System.out.println("Doctor not found.");
            }
        }else {
            System.out.println("Department not found.");
        }
    }

    void edit_patient() {
    System.out.println("What is the ID of the patient you want to edit?");
    int id = JavaApplication2.in.nextInt();

    if (JavaApplication2.patients.containsKey(id)) {
        ArrayList<patient> patients = JavaApplication2.patients.get(id);

        for (patient patient : patients) {
            System.out.println("Editing Patient: " + patient);
            System.out.println("What do you want to edit? \n1.Name \n2.Patient ID \n3.Exit");
            int choice = JavaApplication2.in.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the new name of the patient: ");
                    String newName = JavaApplication2.in.next();
                    patient.patient_name = newName;
                    System.out.println("Patient's name updated to " + newName);
                    break;

                case 2:
                    System.out.print("Enter the new ID for the patient: ");
                    int newId = JavaApplication2.in.nextInt();
                    if (!JavaApplication2.patients.containsKey(newId)) {
                        JavaApplication2.patients.remove(id);
                        patient.patient_id = newId;  
                        JavaApplication2.patients.put(newId, new ArrayList<>(List.of(patient)));  
                        System.out.println("Patient's ID was updated to " + newId);
                    } else {
                        System.out.println("New ID already exists. Please choose a different ID.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting patient edit.");
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }

        
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt", "rw")) {
            raf.setLength(0);  // Clear the file

            for (Map.Entry<Integer, ArrayList<patient>> entry : JavaApplication2.patients.entrySet()) {
                for (patient patient : entry.getValue()) {
                    raf.writeBytes(patient.patient_id + " " + patient.patient_name + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }
    } else {
        System.out.println("Patient with ID " + id + " not found.");
    }
}
    
    void edit_depatrtment() {
    System.out.println("What is the name of the department you want to edit?");
    String departmentName = JavaApplication2.in.next();

    if (JavaApplication2.department.contains(departmentName)) {
        System.out.println("Department found: " + departmentName);
        System.out.println("What do you want to do? \n1.Change Department Name \n2.Exit");
        int choice = JavaApplication2.in.nextInt();

        switch (choice) {
            case 1:
                System.out.print("Enter new department name: ");
                String newDepartmentName = JavaApplication2.in.next();
                int index = JavaApplication2.department.indexOf(departmentName);
                JavaApplication2.department.set(index, newDepartmentName);
                System.out.println("Department name updated to " + newDepartmentName);
                break;
           
            case 2:
                System.out.println("Exiting department edit.");
                break;

            default:
                System.out.println("Invalid choice, please try again.");
        }

        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt", "rw")) {
            raf.setLength(0);

            for (String dept : JavaApplication2.department) {
                raf.writeBytes(dept + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }
    } else {
        System.out.println("Department " + departmentName + " not found.");
    }
}

     /////////////////////////////////////////////////ADDS
    
    void add_doc() {
        System.out.print("What is the Doctor's name: ");
        String d_n = JavaApplication2.in.next();
        System.out.print("What is the Doctor's Id: ");
        int d_id = JavaApplication2.in.nextInt();
        System.out.print("What is the departement: \n1. Cardiology Clinic.\n2. Dental Clinic.\n3. Dermatology Clinic.\n4. Emergency Clinic.\n");
        int d_s = JavaApplication2.in.nextInt();
        
        String dKey = switch(d_s){
                case 1->"Cardiology" ;
                case 2-> "Dental";
                case 3-> "Dermatology";
                case 4-> "Emergency";
                default -> throw new IllegalArgumentException("Unknown specialization: " + d_s);
        };
        Doctor doctor = new Doctor(d_id, d_n, dKey);
        JavaApplication2.Doctors.get(d_s).add(doctor);
        JavaApplication2.doc_c++;
        try{      
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt");
            RandomAccessFile raf = new RandomAccessFile(counter, "rw");
            raf.seek(0);
            raf.writeBytes(String.valueOf(JavaApplication2.doc_c) + "\n");

            raf.seek(raf.length());
            raf.writeBytes(d_id + " " + d_n + " " + dKey + "\n");
            
            raf.close();
        }catch(Exception e){
            System.out.println(e);
        }
         System.out.println("Doctor added successfully.");
    }

    void add_patient() {
        System.out.print("What is the patient's name: ");
        String p_n = JavaApplication2.in.next();
        System.out.print("What is the patient's Id: ");
        int p_id = JavaApplication2.in.nextInt();
        patient patient = new patient(p_id, p_n);
        
        if (JavaApplication2.patients.containsKey(p_id)) {
                System.out.println("id is already exists");
            }else{
            JavaApplication2.patients.put(p_id, new ArrayList<>());
            JavaApplication2.patients.get(p_id).add(patient);
        try{      
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt");
            RandomAccessFile raf = new RandomAccessFile(counter, "rw");

            raf.seek(raf.length());
            raf.writeBytes(p_id + " " + p_n + " " + "\n");
            
            raf.close();
        }catch(Exception e){
            System.out.println(e);
        }
         System.out.println("Patient added successfully.");
    }
    }

    void add_depatrtment() {
        System.out.print("What is the department's name: ");
        String d_n = JavaApplication2.in.next();
        JavaApplication2.department.add(d_n);
        try{      
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt");
            RandomAccessFile raf = new RandomAccessFile(counter, "rw");

            raf.seek(raf.length());
            raf.writeBytes(d_n + "\n");
            
            raf.close();
        }catch(Exception e){
            System.out.println(e);
        }
         System.out.println("Department added successfully.");
    }
    
     /////////////////////////////////////////////////DELETES

    void delete_doc() {
        int d_s = 0;
    while (true) {
        System.out.print("Which department does the doctor belong to? \n1. Cardiology Clinic.\n2. Dental Clinic.\n3. Dermatology Clinic.\n4. Emergency Clinic.\n");
        d_s = JavaApplication2.in.nextInt();
        
        if (d_s >= 1 && d_s <= 4) {
            break;
        } else {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
        }
    }

    System.out.print("Enter the Doctor's ID to delete: ");
    int d_id = JavaApplication2.in.nextInt();
    boolean doctorFound = false;
    if (JavaApplication2.Doctors.containsKey(d_s)) {
        List<Doctor> doctorsList = JavaApplication2.Doctors.get(d_s);
        for (Doctor doctor : doctorsList) {
            if (doctor.doctor_id== d_id) {
                doctorsList.remove(doctor);
                doctorFound = true;
                break;
            }
        }
    }

    if (doctorFound) {
        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt", "rw")) {
            JavaApplication2.doc_c--;
            raf.setLength(0);
            raf.writeBytes(String.valueOf(JavaApplication2.doc_c) + "\n");
            for (int department = 1; department <= JavaApplication2.department.size(); department++) {
                if (JavaApplication2.Doctors.containsKey(department)) {
                    for (Doctor doctor : JavaApplication2.Doctors.get(department)) {
                        raf.writeBytes(doctor.doctor_id+ " " + doctor.doctor_name+ " " + doctor.specialization + "\n");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }

        System.out.println("Doctor with ID " + d_id + " has been successfully deleted.");
    } else {
        System.out.println("Doctor with ID " + d_id + " not found in the selected department.");
    }
    }

    void delete_patient() {
    System.out.print("Enter the patient's ID to delete: ");
    int p_id = JavaApplication2.in.nextInt();

    if (JavaApplication2.patients.containsKey(p_id)) {
        JavaApplication2.patients.remove(p_id);

        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt", "rw")) {
            raf.setLength(0);

            for (Map.Entry<Integer, ArrayList<patient>> entry : JavaApplication2.patients.entrySet()) {
                for (patient patient : entry.getValue()) {
                    raf.writeBytes(patient.patient_id + " " + patient.patient_name + "\n");
                }
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }

        System.out.println("Patient with ID " + p_id + " has been successfully deleted.");
    } else {
        System.out.println("Patient with ID " + p_id + " not found.");
    }
}
    
    void delete_department() {
    System.out.print("Enter the department's name to delete: ");
    String d_n = JavaApplication2.in.next();

    if (JavaApplication2.department.contains(d_n)) {
        JavaApplication2.department.remove(d_n);

        try (RandomAccessFile raf = new RandomAccessFile("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt", "rw")) {
            raf.setLength(0);

            for (String department : JavaApplication2.department) {
                raf.writeBytes(department + "\n");
            }
        } catch (Exception e) {
            System.out.println("Error updating the file: " + e.getMessage());
        }

        System.out.println("Department " + d_n + " has been successfully deleted.");
    } else {
        System.out.println("Department " + d_n + " not found.");
    }
}
    
     /////////////////////////////////////////////////VIEWS

    void list_doc() {System.out.println(JavaApplication2.Doctors);}

    void list_patient() { System.out.println(JavaApplication2.patients);}

    void list_depatrtment() {System.out.println(JavaApplication2.department);}
    
    void view_report(){
    try{
            int c=0;
            File counter=new File("C:\\Users\\hp\\Desktop\\OOP\\Project\\src\\main\\java\\com\\mycompany\\project\\counter.txt");
            if(counter.exists()){
            Scanner input=new Scanner(counter);
            while(input.hasNextInt()){
                c=input.nextInt();
            }
        }
                String appid=c + ".txt";
                File report = new File("C:\\Users\\hp\\Desktop\\OOP\\Project\\src\\main\\java\\com\\mycompany\\project\\Report patient "+appid); 
                Scanner input=new Scanner(report);
                while(input.hasNext()){
                System.out.println(input.nextLine());
            }
                input.close();
            }catch(Exception e){
            System.out.println(e);
        }
    }
}
