package hms.boundary.patient.appointment;

import java.time.LocalDate;

import hms.boundary.patient.record.UpdatePatientMedicalRecordView;

public class CompleteAppointmentView extends UpdatePatientMedicalRecordView {
    @Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Complete Appointment");
	}

    public void displayNoAppointments(LocalDate date) {
        System.out.println("No Appointments on " + date + "\nReturning to main menu.");
    }

    public void displayAppointmentChoice() {
        System.out.print("\nEnter Appointment start time: ");
    }

    public void displayServiceTypePrompt() {
        System.out.print("Enter service type (e.g. consultation, blood test, X-ray etc): ");
    }

    public void displayNotesPrompt() {
        System.out.println("Enter consultation notes:");
    }

    public void displayPrescriptionChoicePrompt() {
        System.out.print("Enter numer of prescriptions to give (1-10): ");
    }

    public void displayAddPrescriptionNamePrompt() {
        System.out.print("Enter Medicine to be added: ");
    }

    public void displayAddPrescriptionQtyPrompt() {
        System.out.print("Enter quantity of Medicine to be added: ");
    }
}
