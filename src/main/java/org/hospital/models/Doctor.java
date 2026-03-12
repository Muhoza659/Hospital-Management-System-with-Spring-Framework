package org.hospital.models;
    public class Doctor {
        private String first_name;
        private String last_name;
        private String specialty;
        private String phone_number;
        private String email;

        public Doctor(String first_name, String last_name, String specialty, String phone_number, String email) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.specialty = specialty;
            this.phone_number = phone_number;
            this.email = email;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getSpecialty() {
            return specialty;
        }

        public void setSpecialty(String specialty) {
            this.specialty = specialty;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

