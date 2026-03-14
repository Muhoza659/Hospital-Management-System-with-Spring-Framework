package org.hospital.DAO;

import org.hospital.models.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DoctorDAO {

    @Autowired
    private Connection connection;
    public void addDoctor(Doctor doctor) throws SQLException {
        String sql = "INSERT INTO doctors (first_name, last_name, specialty, phone_number, email) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, doctor.getFirst_name());
        ps.setString(2, doctor.getLast_name());
        ps.setString(3, doctor.getSpecialty());
        ps.setString(4, doctor.getPhone_number());
        ps.setString(5, doctor.getEmail());
        ps.executeUpdate();
        System.out.println("Doctors inserted sucessfully");

        }
    public List<String> findPatientsByDoctor(int doctorId) throws SQLException {
        String sql = "SELECT DISTINCT p.first_name, p.last_name " +
                "FROM patients p " +
                "JOIN appointments a ON a.patient_id = p.id " +
                "WHERE a.doctor_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, doctorId);
        ResultSet rs = ps.executeQuery();

        List<String> result = new ArrayList<>();
        while (rs.next())
            result.add(rs.getString("first_name") + " " + rs.getString("last_name"));
        return result;
    }
    public void printAppointmentCountPerDoctor() throws SQLException {
        String sql = "SELECT d.first_name, d.last_name, COUNT(a.id) AS total " +
                "FROM doctors d " +
                "LEFT JOIN appointments a ON a.doctor_id = d.id " +
                "GROUP BY d.id, d.first_name, d.last_name " +
                "ORDER BY total DESC";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("── Appointments per Doctor ──");
        while (rs.next())
            System.out.println("Dr. " + rs.getString("first_name") + " "
                    + rs.getString("last_name") + " " + rs.getInt("total") + " appointments");
    }

    public void printDoctorPatientCount() throws SQLException {
        String sql = "SELECT d.first_name, d.last_name, COUNT(DISTINCT dp.patient_id) AS total " +
                "FROM doctors d " +
                "LEFT JOIN doctor_patients dp ON dp.doctor_id = d.id " +
                "GROUP BY d.id, d.first_name, d.last_name " +
                "ORDER BY total DESC";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("── Doctors and Patient Count ──");
        while (rs.next())
            System.out.println("Dr. " + rs.getString("first_name") + " "
                    + rs.getString("last_name") + "  " + rs.getInt("total") + " patients");
    }
    public void printDoctorsWithMoreThanFivePatients() throws SQLException {
        String sql = "SELECT d.first_name, d.last_name, COUNT(DISTINCT dp.patient_id) AS cnt " +
                "FROM doctors d " +
                "JOIN doctor_patients dp ON dp.doctor_id = d.id " +
                "GROUP BY d.id, d.first_name, d.last_name " +
                "HAVING COUNT(DISTINCT dp.patient_id) > 5";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("── Doctors with more than 5 patients ──");
        while (rs.next())
            System.out.println("  Dr. " + rs.getString("first_name") + " "
                    + rs.getString("last_name") + " — " + rs.getInt("cnt") + " patients");
    }

}
