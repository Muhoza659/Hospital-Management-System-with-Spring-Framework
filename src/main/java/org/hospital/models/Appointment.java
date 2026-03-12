package org.hospital.models;


import java.sql.Timestamp;

public class Appointment {
    private int doctor_id;
    private int patient_id;
    private Timestamp appointment_date;
    private String status;

    public Appointment(int doctor_id, int patient_id, Timestamp appointment_date, String status) {
        this.doctor_id = doctor_id;
        this.patient_id = patient_id;
        this.appointment_date = appointment_date;
        this.status = status;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public Timestamp getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(Timestamp appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}