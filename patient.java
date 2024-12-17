package javaapplication2;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

    class appointment{
    int id;
    String date;
    ArrayList<appointment> pedningappointments=new ArrayList<>();
    public void saveappointment(int id,String date,int patient_id,String patient_name){
        this.id=id;
        this.date=date;
        pedningappointments.add(this); 
        try {
        int c=0;
        File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\counter.txt");
        if(counter.exists()){
            Scanner input=new Scanner(counter);
            while(input.hasNextInt()){
                c=input.nextInt();
            }
        }
        c++;
        PrintWriter count=new PrintWriter(counter);   
        count.print(c);
        count.close();
        String appid=c + ".txt";
        File schedule = new File("\"C:\\\\Users\\\\pc\\\\Documents\\\\NetBeansProjects\\\\Project\\\\src\\\\main\\\\java\\\\com\\\\mycompany\\\\project\\\\appiontments\\"+appid); 
        FileWriter app=new FileWriter(schedule, true);     
        app.append("Patient info is : " + "\n" +patient_id+"  "+patient_name +"\nIs appointment Scheduling From the doctor's Id "+this.id+" in: " +this.date+"\n"+"\n");
        app.close();
        System.out.println("Your appointment was saved");
        }catch(Exception e){
            System.out.println(e);
        } 
    }
}
    
    //////////////////////////////////////////////START PATIENT CLASS

    class patient extends appointment
{
    int patient_id;
    String patient_name;

    @Override
    public String toString() {
        return "Patient{" + "patient_id=" + patient_id + ", patient_name=" + patient_name + '}';
    }
    
    public patient(int patient_id, String patient_name) {
        this.patient_id = patient_id;
        this.patient_name = patient_name;
        
    }
    
    void requestappointment(){
        int choice = 0;
        while (choice != 5) 
        {
            System.out.println("Choose Department (1, 2, 3, 4, 5 to Exit): ");
            System.out.println("\t1. Cardiology Clinic.");
            System.out.println("\t2. Dental Clinic.");
            System.out.println("\t3. Dermatology Clinic.");
            System.out.println("\t4. Emergency Clinic.");
            System.out.println("\t5. Exit");
            choice = JavaApplication2.in.nextInt();
            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            String departmentName = "";
            switch (choice) {
                case 1:
                    departmentName = "Cardiology";
                    break;
                case 2:
                    departmentName = "Dental";
                    break;
                case 3:
                    departmentName = "Dermatology";
                    break;
                case 4:
                    departmentName = "Emergency";
                    break;
                case 5:
                    choice =5;
                    break;
            }

           if(choice ==5){
           System.out.println("Exiting");
           }
           
           else if (JavaApplication2.Doctors.containsKey(choice)) {
                int counter=0;
                System.out.println("Available doctors in " + departmentName + " department:");
                for (Doctor doctor : JavaApplication2.Doctors.get(choice)) {
                    System.out.println(doctor);
                }
                System.out.println("choose Doctor's Id: ");
                int doctor_num=JavaApplication2.in.nextInt();
                
                System.out.println("Enter the date:");
                String date=JavaApplication2.in.next();
            saveappointment(doctor_num,date,patient_id,patient_name);
            }else 
                System.out.println("No doctors available in this department.");
        }
        
    }
    
    void Accessing_Medical_Record(){
    try{
        String streport="";
        File report = new File("C:\\Users\\hp\\Desktop\\OOP\\Project\\src\\main\\java\\com\\mycompany\\project\\Report.txt"); 
          if(report.exists()){
            Scanner input=new Scanner(report);
            while(input.hasNext()){
                streport+=input.next();
            }
        }
          System.out.println(streport);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    void Accessing_Billing(){
        int choice =0;
        if(choice!=4){
        int value =0;
        while(choice!=5){
        System.out.println("What did you take? \n 1.medicine \n 2.Requesting an appionment \n 3.Having a report \n 4.Your total \n 5.End ");
        choice=JavaApplication2.in.nextInt();
        switch(choice){
            case 1:
                value += 150;
                break;
            case 2:
                value += 200;
                break;
            case 3:
                value += 50;
                break;
            case 4:
                System.out.println("Your total amount is : "+ value);
                choice =5;
                break;
            case 5:
                choice =5;
                break;
            default:
                System.out.println("Invalid choice");
        }     
        }
        }
    }
}

