
package javaapplication2;

import java.util.ArrayList;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;
import javafx.geometry.Pos; 
import javafx.collections.*;

 public class JavaApplication2 extends Application {
    public static Map<Integer, ArrayList<Doctor>> Doctors = new HashMap<>();
    public static ArrayList<String> department = new ArrayList<>();
    public static Map<Integer ,ArrayList<patient>> patients = new HashMap<>();
    public static Scanner in=new Scanner(System.in);
    public static int doc_c=0;
     
   
    public static void main(String[] args) {
        Inzialzeation();
        launch(args);
        int choice=0;
        man x=new man();
        while(choice!=4)
        {
        System.out.println("Who are you? \n 1.Admin \n 2.Doctor \n 3.Patient \n 4.Exit");
        choice= in.nextInt();
        if (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
        
        switch(choice){
            case 1:
                
                x.Adminmain();
                break;
                
            case 2:
                x.Doctormain();
                break;
                
            case 3:
                x.Patientmain();
                break;
            case 4:
                choice= 4;
        }
        

        }
        in.close();
    }
    private static void Inzialzeation(){
        try{
            
            File counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\doctors.txt");
            if(counter.exists()){
            Scanner input=new Scanner(counter);
            
            if(input.hasNext())
            JavaApplication2.doc_c=input.nextInt();
            while(input.hasNext()){
                Doctor a=new Doctor();
                a.doctor_id=input.nextInt();
                a.doctor_name=input.next();
                a.specialization=input.next();
            int departmentKey = switch(a.specialization){
                case "Cardiology"->1 ;
                case "Dental"->2 ;
                case "Dermatology"->3 ;
                case"Emergency"->4 ;
                default -> throw new IllegalArgumentException("Unknown specialization: " + a.specialization);
                };
                JavaApplication2.Doctors.putIfAbsent(departmentKey, new ArrayList<>());
                JavaApplication2.Doctors.get(departmentKey).add(a);
                }
            input.close();
            }
            
            counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\patients.txt");
            if (counter.exists()){
            Scanner input = new Scanner(counter);
            while(input.hasNext()){
                patient b=new patient(input.nextInt(),input.next());
                JavaApplication2.patients.putIfAbsent((b.patient_id), new ArrayList<>());
                JavaApplication2.patients.get(b.patient_id).add(b);
            }
            input.close();
            }
            counter=new File("C:\\Users\\pc\\Documents\\NetBeansProjects\\Project\\src\\main\\java\\com\\mycompany\\project\\departments.txt");
            Scanner input = new Scanner(counter);
            while(input.hasNext()){
                String c=input.next();
                JavaApplication2.department.add(c);
            }
            input.close();
            }
        catch(Exception e){
            System.out.println(e);
        }
    }
    String currentPatient=new String(" ");
    int currentPatientid;
    @Override
    public void start(Stage stage) throws Exception{
    Scene homeScene,checkAdmin,checkDoctor,checkPatient;
    
     //////////////////////////////////////////////////checkAdminScene
     Label adminidlabel = new Label("Name:");
     TextField adminidtf = new TextField ();  
     Label adminnamelabel = new Label("Id:");
     TextField adminnametf = new TextField ();
     
     HBox adminlogininput=new HBox(10,adminidlabel,adminidtf,adminnamelabel,adminnametf);
     
     Button adminlogin=new Button("Login");
     adminlogin.setOnAction(eh->{
         openAdminStage();
         stage.close();
         });
     
     Button backloginAdmin = new Button("Back");
     VBox adminloginlayout=new VBox(10,adminlogininput,adminlogin,backloginAdmin);
     
     checkAdmin=new Scene(adminloginlayout);
     
     ////////////////////////////////////////////////checkDoctorScene
     Label doctoridlabel = new Label("Name:");
     TextField doctoridtf = new TextField ();  
     Label doctornamelabel = new Label("Id:");
     TextField doctornametf = new TextField ();
     ObservableList<String> options = FXCollections.observableArrayList(department);
     ComboBox combobox=new ComboBox(options);

     HBox doctorlogininput=new HBox(10,doctoridlabel,doctornametf,doctornamelabel,doctoridtf,combobox);
     
     Button doctorlogin=new Button("Login");
     doctorlogin.setOnAction(eh->{
         final Doctor logdoc=new Doctor(Integer.parseInt(doctoridtf.getText()),doctornametf.getText(),combobox.getSelectionModel().getSelectedItem().toString());
         if (JavaApplication2.Doctors.containsKey(logdoc.doctor_id)){
             ArrayList<Doctor> doctorList = JavaApplication2.Doctors.get(logdoc.doctor_id);
        for (Doctor p : doctorList) {
            if (p.doctor_name.equals(logdoc.doctor_name)&&p.specialization.equals(logdoc.specialization)) {
                openDoctorStage();
                stage.close();
                break;
                }else{
                notfound();
                break;}
                }  
            }
         });
     
     Button backloginDoctor = new Button("Back");
     VBox doctorloginlayout=new VBox(10,doctorlogininput,doctorlogin,backloginDoctor);
     
     checkDoctor=new Scene(doctorloginlayout);
     
     ///////////////////////////////////////////////checkPatientScene
     
     Label patientidlabel = new Label("Name:");
     TextField patientnametf = new TextField ();
     Label patientnamelabel = new Label("Id:");
     TextField patientidtf = new TextField ();  
     
     HBox patientlogininput=new HBox(10,patientidlabel,patientnametf,patientnamelabel,patientidtf);
     
     Button patientlogin=new Button("Login");
     patientlogin.setOnAction(eh->{
     final patient logpat=new patient(Integer.parseInt(patientidtf.getText()),patientnametf.getText());
         if (JavaApplication2.patients.containsKey(logpat.patient_id)){
             ArrayList<patient> patientList = JavaApplication2.patients.get(logpat.patient_id);
        for (patient p : patientList) {
            if (p.patient_name.equals(logpat.patient_name)) {
                currentPatient = logpat.patient_name;
                currentPatientid = logpat.patient_id;
                openPatientStage();
                stage.close();
                }else{notfound();}
            }  
         }
     });
     Button backloginPatient = new Button("Back");
     VBox patientloginlayout=new VBox(10,patientlogininput,patientlogin,backloginPatient);
     
     checkPatient=new Scene(patientloginlayout);
     
    /////////////////////////////////////////////////////homeScene
     Label loginlabel=new Label("Login into :");
     Button openAdmin = new Button("Admin");
     openAdmin.setOnAction(e -> {stage.setScene(checkAdmin);});
     Button openDoctor = new Button("Doctor");
     openDoctor.setOnAction(e -> {stage.setScene(checkDoctor);});
     Button openPatient = new Button("Patient");
     openPatient.setOnAction(e -> {stage.setScene(checkPatient);});
     Button loginExit =new Button("Exit");
     loginExit.setOnAction(e->stage.close());

     HBox chooseLayout = new HBox(10, openAdmin,openDoctor, openPatient);
     VBox mainLayout = new VBox(10,loginlabel,chooseLayout,loginExit);
        
     homeScene = new Scene(mainLayout, 300, 200);
     
    //////////////////////////////////////////////////////setup back buttons    
     backloginAdmin.setOnAction(eh->stage.setScene(homeScene));
     backloginDoctor.setOnAction(eh->stage.setScene(homeScene));
     backloginPatient.setOnAction(eh->stage.setScene(homeScene));
     
    //////////////////////////////////////////////////////start on home page
     stage.setTitle("Login");
     stage.setScene(homeScene);
     stage.show();
 }
    private void notfound(){
       Stage notfoundstage = new Stage();
       Label notfoundmsg=new Label("User Does Not Exist");
       Button closemsg = new Button("Ok");
       closemsg.setOnAction(eh->{notfoundstage.close();});
       VBox errorbox=new VBox(10,notfoundmsg,closemsg);
       errorbox.setAlignment(Pos.CENTER);
       StackPane notfoundLayout = new StackPane(errorbox);
       Scene notfoundeScene = new Scene(notfoundLayout, 250, 150);
       notfoundstage.setTitle("Wrong Id");
       notfoundstage.setScene(notfoundeScene);
       notfoundstage.show();
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void openAdminStage() {
        Stage AdminStage = new Stage();
        Button closeEditWindow = new Button("Close Edit Window");
        closeEditWindow.setOnAction(e -> AdminStage.close());

        StackPane editLayout = new StackPane(closeEditWindow);
        Scene editScene = new Scene(editLayout, 500, 300);

        AdminStage.setTitle("Edit Window");
        AdminStage.setScene(editScene);
        AdminStage.show();
    }

    private void openDoctorStage() {
        Stage DoctorStage = new Stage();
        Button closeDeleteWindow = new Button("Close Delete Window");
        closeDeleteWindow.setOnAction(e -> DoctorStage.close());

        StackPane deleteLayout = new StackPane(closeDeleteWindow);
        Scene deleteScene = new Scene(deleteLayout,500, 300);

        DoctorStage.setTitle("Delete Window");
        DoctorStage.setScene(deleteScene);
        DoctorStage.show();
    }
    
    private void openPatientStage() {
        Stage PatientStage = new Stage();
        Scene PatientScene,reqappscene1,reqappscene2,reportsscene,billsscene;
        ////////////////////////////////////////////////////////////////////////////////
        ObservableList<String> reqappoptions = FXCollections.observableArrayList(department);
        ComboBox reqappcombobox=new ComboBox(reqappoptions);
        Button reqback = new Button("Back");
        Button reqnext = new Button("Next");
        HBox backnextreq=new HBox(20,reqback,reqnext);
        backnextreq.setAlignment(Pos.CENTER);
        
        VBox reqappscene1layout =new VBox(20,reqappcombobox,backnextreq);
        reqappscene1layout.setAlignment(Pos.CENTER);
        
        reqappscene1 = new Scene(reqappscene1layout, 500, 300);
        
        ObservableList<Doctor> reqAppOptions = FXCollections.observableArrayList();
        ComboBox<Doctor> doctorComboBox = new ComboBox<>(reqAppOptions);
        Button req2back = new Button("Back");
        req2back.setOnAction(eh->{PatientStage.setScene(reqappscene1);});
        Button reqDone = new Button("Request");
        
        HBox backDonereq=new HBox(20,req2back,reqDone);
        Label date=new Label("Date");
        TextField datetf=new TextField();
        HBox docdate=new HBox(20,doctorComboBox,date,datetf);
        
        VBox reqappscene2layout = new VBox(20, docdate,backDonereq);
        reqappscene2layout.setAlignment(Pos.CENTER);
        
        reqappscene2 = new Scene(reqappscene2layout, 500, 300);
        
        reqappcombobox.setOnAction(eh -> {
        String selectedDepartment = reqappcombobox.getSelectionModel().getSelectedItem().toString();
        for (ArrayList<Doctor> doctorList : Doctors.values()) {
            for (Doctor doctor : doctorList) {
                if (doctor.specialization.equals(selectedDepartment))
                    reqAppOptions.add(doctor);
                }
            }
        });

        reqnext.setOnAction(eh -> {
    if (reqappcombobox.getSelectionModel().getSelectedItem() != null) 
        PatientStage.setScene(reqappscene2);
     else 
        showAlert("Please select a department before proceeding.");
   });
        appointment z=new appointment();
        reqDone.setOnAction(eh->{z.saveappointment(doctorComboBox.getSelectionModel().getSelectedItem().doctor_id,datetf.getText(),currentPatientid,currentPatient);});
        ////////////////////////////////////////////////////////////////////////////////
        Button patreportback = new Button("Back");
        reportsscene = new Scene(patreportback, 500, 300);
        ////////////////////////////////////////////////////////////////////////////////
        Button patbillback = new Button("Back");
        billsscene = new Scene(patbillback, 500, 300);
        ///////////////////////////////////////////////////////////////////////////////
        Button RequestAppoinment = new Button("Request Appoinment");
        RequestAppoinment.setOnAction(eh->{PatientStage.setScene(reqappscene1);});
        
        Button ViewReports = new Button("View Reports");
        ViewReports.setOnAction(eh->{PatientStage.setScene(reportsscene);});
        
        Button ViewBills= new Button("View Bills");
        ViewBills.setOnAction(eh->{PatientStage.setScene(billsscene);});
        
        Button closePatientWindow = new Button("Exit");
        closePatientWindow.setOnAction(e -> PatientStage.close());

        Label welcomepateint=new Label("Welcome "+currentPatient+", what to do you want to do?");
        
        HBox PatientchooseLayout=new HBox(10,RequestAppoinment,ViewReports,ViewBills);
        PatientchooseLayout.setAlignment(Pos.CENTER);
        VBox PatientLayout=new VBox(10,welcomepateint,PatientchooseLayout,closePatientWindow);
        PatientLayout.setAlignment(Pos.CENTER);
        PatientScene = new Scene(PatientLayout, 500, 300);
        
        
        
        /////////reqbackbutton
        reqback.setOnAction(eh->{PatientStage.setScene(PatientScene);});
        /////////patreportbackbutton
        patreportback.setOnAction(eh->{PatientStage.setScene(PatientScene);});
        /////////patbillbutton
        patbillback.setOnAction(eh->{PatientStage.setScene(PatientScene);});
        
        PatientStage.setTitle("Patient");
        PatientStage.setScene(PatientScene);
        PatientStage.show();
    }
    private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
}
}
