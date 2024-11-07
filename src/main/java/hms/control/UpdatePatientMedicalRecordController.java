package hms.control;

import hms.boundary.InputHandler;
import hms.boundary.patient.record.UpdatePatientMedicalRecordView;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class UpdatePatientMedicalRecordController extends Controller {
    private Patient patient;
    private UpdatePatientMedicalRecordView updatePatientMedicalRecordView;

    public UpdatePatientMedicalRecordController(Patient patient) {
        this.patient = patient;
        this.updatePatientMedicalRecordView = new UpdatePatientMedicalRecordView();
    }

    @Override
    public void navigate() {
        updatePatientMedicalRecordView.displayHeader();
        if (patient.getAppointmentOutcomeRecordList().isEmpty()){
            updatePatientMedicalRecordView.displayNoRecords();
            updatePatientMedicalRecordView.displayReturnMenu();
            return;
        }

        updatePatientMedicalRecordView.displayRecords(patient);
        updatePatientMedicalRecordView.displayChoices();
        int choice;
        try{
            choice = InputHandler.getChoice(1, 3);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			choice = -1;
			return;
		}

        AppointmentOutcomeRecord apptRecord = chooseAppointmentOutcomeRecord();

        switch (choice) {
        case 1: 
            updatePatientMedicalRecordView.displayAddPrescriptionNamePrompt();
            String medName = InputHandler.getString();
            updatePatientMedicalRecordView.displayAddPrescriptionQtyPrompt();
            int medQty = -1;
            while (medQty == -1){
                try{
                    medQty = InputHandler.getChoice(0, 100);
                } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                    continue;
                }
            }
            apptRecord.addPrescribedMedicine(new Medicine(medName, medQty));
            break;
        case 2: 
            updatePatientMedicalRecordView.displayAddConsultationNotesPrompt();
            String notes = InputHandler.getString();
            apptRecord.appendConsultationNotes(notes);
            break;
        case 3: //view personal schedule
            updatePatientMedicalRecordView.displayReturnMenu();
            break;
        default:
        }
    }

    public AppointmentOutcomeRecord chooseAppointmentOutcomeRecord() {
        updatePatientMedicalRecordView.displayApptOptionPrompt();
        int i = -1;
        while (i == -1){
            try{
                i = InputHandler.getChoice(1, patient.getAppointmentOutcomeRecordList().size());
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                continue;
            }
        }

        return (patient.getAppointmentOutcomeRecordList()).get(i-1);
    }
}
