/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication2;

import java.util.ArrayList;

class man{
    
    public void Adminmain(){
        Admin admin=new Doctor();
        int choice=0;
        while(choice!=6){
        System.out.println("What do you want to do? \n 1.Staff \n 2.Patient \n 3.Department \n 4.View staff Reports \n 5.View patient reports \n 6.Exit");
        choice =JavaApplication2.in.nextInt();
        switch(choice){
            case 1:
                    System.out.println("What do you want to do? \n 1.edit \n 2.add \n 3.delete \n 4.list");
                    int choice_2 =JavaApplication2.in.nextInt();
                    switch(choice_2){
                        case 1: admin.edit_doc();
                            break; 
                        case 2: admin.add_doc();
                            break; 
                        case 3: admin.delete_doc();
                            break; 
                        case 4: admin.list_doc();
                            break; 
                        default:System.out.println("Invalide choice");
                    }
                break;
                
            case 2:
                System.out.println("What do you want to do? \n 1.edit \n 2.add \n 3.delete \n 4.list");
                    choice_2 =JavaApplication2.in.nextInt();
                    switch(choice_2){
                        case 1: admin.edit_patient();
                             break; 
                        case 2:  admin.add_patient();
                             break; 
                        case 3: admin.delete_patient();
                             break; 
                        case 4: admin.list_patient();
                             break; 
                        default:System.out.println("Invalide choice");
                    }
                break;
                
            case 3:
                System.out.println("What do you want to do? \n 1.edit \n 2.add \n 3.delete \n 4.list");
                    choice_2 =JavaApplication2.in.nextInt();
                    switch(choice_2){
                        case 1: admin.edit_depatrtment();
                            break; 
                        case 2: admin.add_depatrtment(); 
                            break; 
                        case 3: admin.delete_department();
                            break; 
                        case 4: admin.list_depatrtment();
                            break; 
                        default:System.out.println("Invalide choice");
                    }
                break;
            case 4:
                admin.view_report();
                break;
            case 5:
                admin.view_report();
                break;
            case 6:
                choice =6;
                break;
                
             default:
               System.out.println("Invalide choice");
        }
        }
    }  
    
    public void Patientmain(){
        System.out.println("What is your name? ");
        String name=JavaApplication2.in.next();
        System.out.println("What is your ID? ");
        int id=JavaApplication2.in.nextInt();
        patient patient = new patient(id,name);
        ArrayList <patient> patientt =new ArrayList<patient>();
        patientt.add(patient);
        JavaApplication2.patients.put(id, patientt);
        int choice=0;
        while(choice!=4){
        System.out.println("What do you want? \n 1.Request appointment \n 2.View report \n 3.view bills \n 4.Exit");
        choice =JavaApplication2.in.nextInt();
        switch(choice){
            case 1:
                patient.requestappointment();
                break;
                
            case 2:
               patient.Accessing_Medical_Record();
                break;
                
            case 3:
               patient.Accessing_Billing ();
                break;
            case 4:
                choice =4;
                break;
            default:
               System.out.println("Invalide choice");
        }
        // Patient schedules appointments
        }
    }
    
    public void Doctormain(){
        int choice=0;
        while(choice!=4){
        System.out.println("What do you want? \n 1.Managing_Appointments \n 2.Prescribe_medications \n 3.view reports \n 4.Exit");
        choice =JavaApplication2.in.nextInt();
        Doctor doctor=new Doctor();
        switch(choice){
            case 1:
              doctor.Managing_Appointments();
                break;
                
            case 2:
               doctor.Prescribe_medications();
                break;
                
            case 3:
              doctor.view_reports();
                break;
            case 4:
                choice =4;
                break;
            default:
               System.out.println("Invalide choice");
        }
    }
}
}

