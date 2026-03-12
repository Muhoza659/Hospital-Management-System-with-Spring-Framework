package org.hospital.DAO;


import org.hospital.models.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;


@Repository
public class AppointmentDAO {

    @Autowired
    private Connection connection;

    public void insertAppointment(Appointment appt) throws SQLException {
        String sql = "INSERT INTO appointments (doctor_id, patient_id, appointment_date, status) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, appt.getDoctor_id());
        ps.setInt(2, appt.getPatient_id());
        ps.setTimestamp(3, appt.getAppointment_date());
        ps.setString(4, appt.getStatus());
        ps.executeUpdate();

        System.out.println("Appointment inserted ");
    }
    public void updateStatus(int appointmentId, String newStatus) throws SQLException {
        String sql = "UPDATE appointments SET status = ? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newStatus);
        ps.setInt(2, appointmentId);
        int rows = ps.executeUpdate();
        System.out.println("Appointment " + appointmentId +
                " updated to '" + newStatus + "', rows affected: " + rows);
    }
    public void printAppointmentsPerMonth() throws SQLException {
        String sql = "SELECT TO_CHAR(appointment_date, 'YYYY-MM') AS month, COUNT(*) AS total " +
                "FROM appointments " +
                "GROUP BY month " +
                "ORDER BY month";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        System.out.println("── Appointments Per Month ──");
        while (rs.next())
            System.out.println("appointments " + rs.getString("month") + " : " + rs.getInt("total"));
    }
}
