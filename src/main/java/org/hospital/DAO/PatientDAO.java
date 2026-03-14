package org.hospital.DAO;
import org.hospital.models.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class PatientDAO {

    @Autowired
    private Connection connection;

    public void insertPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (first_name, last_name, date_of_birth, gender, phone_number, email) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, patient.getFirst_name());
        ps.setString(2, patient.getLast_name());
        ps.setDate(3, patient.getDob());
        ps.setString(4, patient.getGender());
        ps.setString(5, patient.getPhone_number());
        ps.setString(6, patient.getEmail());
        ps.executeUpdate();
        System.out.println("Patients inserted successfully");
    }
    public void linkDoctorToPatient(int doctorId, int patientId) throws SQLException {
        String sql = "INSERT INTO doctor_patient (doctor_id, patient_id) VALUES (?, ?) ";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, doctorId);
        ps.setInt(2, patientId);
        ps.executeUpdate();
    }
    public void deletePatient(int patientId) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, patientId);
        int rows = ps.executeUpdate();
        System.out.println(" Patient deleted");
    }
    public void printPatientsWithMultipleDiagnoses() throws SQLException {
        String sql = "SELECT p.first_name, p.last_name, COUNT(mr.id) AS cnt " +
                "FROM patients p " +
                "JOIN medical_records mr ON mr.patient_id = p.id " +
                "GROUP BY p.id, p.first_name, p.last_name " +
                "HAVING COUNT(mr.id) > 1";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("── Patients diagnosed more than once ──");
        while (rs.next())
            System.out.println("  " + rs.getString("first_name") + " "
                    + rs.getString("last_name") + " — "+ " diagnoses");
    }
}