package hms.model;

import java.time.LocalDate;
import java.util.List;

import hms.Validation;
import hms.appointment.Appointment;
import hms.attributes.BloodType;
import hms.attributes.Gender;
import hms.record.AppointmentOutcomeRecord;
import hms.record.MedicalRecord;

public class Patient extends User {
	private final MedicalRecord medicalRecord;

	public Patient(MedicalRecord medicalRecord, String password) {
		super(medicalRecord.getId(), password, medicalRecord.getName(), medicalRecord.getGender());
		this.medicalRecord = medicalRecord;
	}

	public void ViewMedicalRecord() {
	}

	public void setPhoneNumber(String phoneNumber) {
		this.medicalRecord.setPhoneNumber(phoneNumber);
	}

	public boolean setEmailAddress(String emailAddress) {
		if (Validation.validateEmailAddress(emailAddress)) {
			this.medicalRecord.setEmailAddress(emailAddress);
			return true;
		}
		return false;
	}

	public String getId() {
		return this.medicalRecord.getId();
	}

	public String getName() {
		return this.medicalRecord.getName();
	}

	public LocalDate getDateOfBirth() {
		return this.medicalRecord.getDateOfBirth();
	}

	public Gender getGender() {
		return this.medicalRecord.getGender();
	}

	public BloodType getBloodType() {
		return this.medicalRecord.getBloodType();
	}

	public String getPhoneNumber() {
		return this.medicalRecord.getPhoneNumber();
	}

	public String getEmailAddress() {
		return this.medicalRecord.getEmailAddress();
	}

	public List<AppointmentOutcomeRecord> getAppointmentOutcomeRecordList() {
		return this.medicalRecord.getAppointmentOutcomeRecordList();
	}

	public boolean scheduleAppointment(Doctor doctor, Appointment appointment) {
		return doctor.scheduleAppointment(appointment);
	}

	public boolean rescheduleAppointment(Doctor prevDoctor, Doctor newDoctor, Appointment oldAppointment,
			Appointment newAppointment) {
		if (newDoctor.scheduleAppointment(newAppointment)) {
			prevDoctor.cancelAppointment(oldAppointment);
			return true;
		}
		return false;
	}

	public void cancelAppointment(Doctor doctor, Appointment appointment) {
		doctor.cancelAppointment(appointment);
	}

	public void addAppointmentOutcomeRecord(AppointmentOutcomeRecord appointmentOutcomeRecord) {
		this.medicalRecord.addAppointmentOutcomeRecord(appointmentOutcomeRecord);
	}

}