package hms.boundary.patient.appointment;

import hms.boundary.patient.record.UpdatePatientMedicalRecordView;

public class CompleteAppointmentView extends UpdatePatientMedicalRecordView {
    @Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Complete Appointment");
	}

    public void displayChoice(int size) {
        System.out.println("Enter choice (1-" + size + "):");
    }

    public void displayServiceTypePrompt() {
        System.out.print("Enter service type (e.g. consultation, blood test, X-ray etc): ");
    }

    public void displayNotesPrompt() {
        System.out.println("Enter consultation notes:");
    }

    public void displayAddPrescriptionNamePrompt() {
        System.out.print("Enter Medicine to be added:");
    }

    public void displayAddPrescriptionQtyPrompt() {
        System.out.print("Enter quantity of Medicine to be added:");
    }
}
