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

    System.out.println(" Patients for Doctor by ID ");
    List<String> patients = service.getPatientsForDoctor(26);
        patients.forEach(p -> System.out.println("  → " + p));


    List<MedicalRecord> records = service.getMedicalRecordsForPatient(9);
        records.forEach(r -> System.out.println("  → " + r));

        service.printAppointmentCountPerDoctor();

        service.printDoctorPatientCount();

        System.out.println(" Update Appointment status");
        service.updateAppointmentStatus(37, "Completed");

        service.deletePatient(6);


        service.printDoctorsWithMoreThanFivePatients();

        service.printPatientsWithMultipleDiagnoses();

        service.printAppointmentsPerMonth();

        System.out.println("All tasks completed successfully!");
}
}