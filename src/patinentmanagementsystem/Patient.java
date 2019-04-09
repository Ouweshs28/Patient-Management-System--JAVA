/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patinentmanagementsystem;

import java.io.Serializable;

/**
 *
 * @author Ouwesh
 */
public class Patient implements Serializable {
    private int PatientID;
    private String FirstName;
    private String LastName;
    private String MedicalCondition;
    private String NextAppointmentDate;
    private String PhoneNo;
    private String BillingStatus;
    private String Comments;
    private double Bill;

    public Patient(int id, String fname, String lname, String medicalcond, String date, String phoneno, String billstat, String comments, double bill) {

        PatientID = id;
        FirstName = fname;
        LastName = lname;
        MedicalCondition = medicalcond;
        NextAppointmentDate = date;
        PhoneNo = phoneno;
        BillingStatus = billstat;
        Comments = comments;
        Bill = bill;
    }

    public Patient() {
        PatientID = 0000;
        FirstName = "Patient First Name";
        LastName = "Patient Last Name";
        MedicalCondition = "Patient's Medical Condition";
        NextAppointmentDate = "DD/MM/YYYY";
        PhoneNo = "00000000";
        BillingStatus = "Paid / Pending";
        Comments = "Patient's Comments";
        Bill = 0.00;
    }

    /**
     * @return the PatientID
     */
    public int getPatientID() {
        return PatientID;
    }

    /**
     * @param PatientID the PatientID to set
     */
    public void setPatientID(int PatientID) {
        this.PatientID = PatientID;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the MedicalCondition
     */
    public String getMedicalCondition() {
        return MedicalCondition;
    }

    /**
     * @param MedicalCondition the MedicalCondition to set
     */
    public void setMedicalCondition(String MedicalCondition) {
        this.MedicalCondition = MedicalCondition;
    }

    /**
     * @return the NextAppointmentDate
     */
    public String getNextAppointmentDate() {
        return NextAppointmentDate;
    }

    /**
     * @param NextAppointmentDate the NextAppointmentDate to set
     */
    public void setNextAppointmentDate(String NextAppointmentDate) {
        this.NextAppointmentDate = NextAppointmentDate;
    }

    /**
     * @return the PhoneNo
     */
    public String getPhoneNo() {
        return PhoneNo;
    }

    /**
     * @param PhoneNo the PhoneNo to set
     */
    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    /**
     * @return the BillingStatus
     */
    public String getBillingStatus() {
        return BillingStatus;
    }

    /**
     * @param BillingStatus the BillingStatus to set
     */
    public void setBillingStatus(String BillingStatus) {
        this.BillingStatus = BillingStatus;
    }

    /**
     * @return the Comments
     */
    public String getComments() {
        return Comments;
    }

    /**
     * @param Comments the Comments to set
     */
    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    /**
     * @return the Bill
     */
    public double getBill() {
        return Bill;
    }

    /**
     * @param Bill the Bill to set
     */
    public void setBill(double Bill) {
        this.Bill = Bill;
    }


    public void Display() {
        System.out.printf("| %15s %15s %15s %20s %20s %15s %20s %20s %15s",
                getPatientID() + " |", getFirstName() + " |", getLastName() + " |", getMedicalCondition() + " |", getNextAppointmentDate() + " |",
                getPhoneNo() + " |", getBillingStatus() + " |", getComments() + " |", getBill() + " |\n");


    }

}
