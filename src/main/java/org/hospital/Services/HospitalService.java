package org.hospital.Services;
import org.hospital.DAO.*;
import org.hospital.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class HospitalService {

    @Autowired
    private DoctorDAO doctorDAO;

    @Autowired
    private PatientDAO patientDAO;

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private MedicalRecordDAO medicalRecordDAO;

    public List<String> getPatientsForDoctor(int doctorId) throws SQLException {
        return doctorDAO.findPatientsByDoctor(doctorId);
    }

    public List<MedicalRecord> getMedicalRecordsForPatient(int patientId) throws SQLException {
        return medicalRecordDAO.findByPatient(patientId);
    }

    public void printAppointmentCountPerDoctor() throws SQLException {
        doctorDAO.printAppointmentCountPerDoctor();
    }

    public void printDoctorPatientCount() throws SQLException {
        doctorDAO.printDoctorPatientCount();
    }

    public void updateAppointmentStatus(int appointmentId, String status) throws SQLException {
        appointmentDAO.updateStatus(appointmentId, status);
    }

    public void deletePatient(int patientId) throws SQLException {
        patientDAO.deletePatient(patientId);
    }

    public void printDoctorsWithMoreThanFivePatients() throws SQLException {
        doctorDAO.printDoctorsWithMoreThanFivePatients();
    }

    public void printPatientsWithMultipleDiagnoses() throws SQLException {
        patientDAO.printPatientsWithMultipleDiagnoses();
    }

    public void printAppointmentsPerMonth() throws SQLException {
        appointmentDAO.printAppointmentsPerMonth();
    }
}

