package org.hospital.DAO;


import org.hospital.models.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MedicalRecordDAO {

    @Autowired
    private Connection connection;

    public void insertMedicalRecord(MedicalRecord record) throws SQLException {
        String sql = "INSERT INTO medical_records (patient_id, doctor_id, diagnosis, treatment) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, record.getPatientId());
        ps.setInt(2, record.getDoctorId());
        ps.setString(3, record.getDiagnosis());
        ps.setString(4, record.getTreatment());
        ps.executeUpdate();


        System.out.println(" Medical record inserted");
    }
    public List<MedicalRecord> findByPatient(int patientId) throws SQLException {
        String sql = "SELECT * FROM medical_records WHERE patient_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, patientId);
        ResultSet rs = ps.executeQuery();

        List<MedicalRecord> list = new ArrayList<>();
        System.out.println("Medical Records for Patient");
        while (rs.next()) {
            MedicalRecord mr = new MedicalRecord(
                    rs.getInt("patient_id"),
                    rs.getInt("doctor_id"),
                    rs.getString("diagnosis"),
                    rs.getString("treatment"));
                  list.add(mr);
        }
        return list;
    }
}
