package javaapplication2;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

  public class Doctor extends Admin{
     int doctor_id;
    String doctor_name;
    String specialization;
    Scanner in=new Scanner(System.in);
    public Doctor(int doctor_id, String doctor_name, String specialization) {
        this.doctor_id = doctor_id;
        this.doctor_name = doctor_name;
        this.specialization = specialization;
    }
    
    public Doctor() {}
    
    @Override
    public String toString() {
        return "Doctor's ID=" + doctor_id + ", Doctor's Name :" + doctor_name + "\n";
    } 
    
    public void Managing_Appointments(){
        int choice=0;
        while (choice !=4){
        try {
        int c=0;
        File counter=new File("C:\\Users\\hp\\Desktop\\OOP\\Project\\src\\main\\java\\com\\mycompany\\project\\counter.txt");
        if(counter.exists()){
            Scanner input=new Scanner(counter);
            while(input.hasNextInt()){
                c=input.nextInt();
            }
        }
         String appid=c + ".txt";
         File schedule = new File("C:\\Users\\hp\\Desktop\\OOP\\Project\\src\\main\\java\\com\\mycompany\\project\\appiontments\\"+appid); 
         long x=schedule.length();
         if(schedule.exists()){
             if(x==0){System.out.println("There is no schedule");choice=4;}
             else
             {
                Scanner input=new Scanner(schedule);
                while(input.hasNext()){
                System.out.println(input.nextLine());
             }
         System.out.println("What is your Choice : \n 1.Accept \n 2.Cancel \n 3.Reschedule \n 4.Exit");
          int Choice= in.nextInt();
          if (choice == 4) 
          {
            System.out.println("Exiting Appointment Scheduling.");
            break;
          }
          FileWriter app=new FileWriter(schedule, true);   
        switch(Choice){
            case 1:
                app.append("Your requist accepted");
                app.close();
                break;
            case 2:
                app.append("Your requist cancelled");
                app.close();
                break;
            case 3:
                app.append("Please requiest another date");
                app.close();
                break;
                }
            }
        }
        }catch(Exception e){
            System.out.println(e);
        } 
    }
}
    
    public void Prescribe_medications (){
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
            String streport;
            File report = new File("C:\\Users\\hp\\Desktop\\OOP\\Project\\src\\main\\java\\com\\mycompany\\project\\Report patient "+appid); 
            FileWriter app=new FileWriter(report, true);  
            System.out.print("What is the report About :");
            String st=JavaApplication2.in.next();
            app.append(st);
            app.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void view_reports(){
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

