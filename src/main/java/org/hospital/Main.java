package org.hospital;

import org.hospital.Services.HospitalService;
import org.hospital.config.AppConfig;
import org.hospital.models.MedicalRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        ApplicationContext myContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        HospitalService service = myContext.getBean(HospitalService.class);

    System.out.println(" Patients for Doctor 26 ");
    List<String> patients = service.getPatientsForDoctor(26);
        patients.forEach(p -> System.out.println("  → " + p));


        System.out.println("Medical Records for Patient");
    List<MedicalRecord> records = service.getMedicalRecordsForPatient(9);
        records.forEach(r -> System.out.println("  → " + r));


        System.out.println(" Appointment Count Per Doctor");
        service.printAppointmentCountPerDoctor();

        System.out.println(" Doctor Patient Count");
        service.printDoctorPatientCount();

        System.out.println(" Update Appointment");
        service.updateAppointmentStatus(37, "Completed");


        System.out.println(" Delete Patient");
        service.deletePatient(6);


        System.out.println(" Doctors with > 5 Patients");
        service.printDoctorsWithMoreThanFivePatients();


        System.out.println(" Patients with Multiple Diagnose");
        service.printPatientsWithMultipleDiagnoses();


        System.out.println(" Appointments Per Month ");
        service.printAppointmentsPerMonth();

        System.out.println("All tasks completed successfully!");
}
}