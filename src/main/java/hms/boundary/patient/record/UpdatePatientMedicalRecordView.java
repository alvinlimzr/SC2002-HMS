package hms.boundary.patient.record;

public class UpdatePatientMedicalRecordView extends AppointmentOutcomeRecordView {
    @Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Patient Appointment Records");
	}

    public void displayChoices() {
        String options = "Please select an option:\r\n" + "1. Add Prescription\r\n"
				+ "2. Add Consultation Notes\r\n" + "3. Return to Main Menu";
        String prompt = "Enter choice (1-3): ";

        System.out.println(options);
        System.out.print(prompt);
    }

    public void displayNoRecords() {
        System.out.print("No Appointment records found.");
    }
    
    public void displayApptOptionPrompt() {
        System.out.print("Please enter the Appointment number: ");
    }

    public void displayAddConsultationNotesPrompt() {
        System.out.println("Enter consultation notes to be added:");
    }

    public void displayAddPrescriptionNamePrompt() {
        System.out.print("Enter Medicine to be added:");
    }

    public void displayAddPrescriptionQtyPrompt() {
        System.out.print("Enter quantity of Medicine to be added:");
    }

    public void displayReturnMenu() {
		System.out.println("Returning to Menu.");
	}
}
