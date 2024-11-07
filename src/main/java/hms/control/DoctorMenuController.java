package hms.control;

import hms.boundary.InputHandler;
import hms.boundary.patient.ScheduleView;
import hms.boundary.patient.record.MedicalRecordView;
import hms.boundary.DoctorMenuView;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class DoctorMenuController extends Controller {
    private final DoctorMenuView doctorMenuView;
    private Doctor doctor;

    public DoctorMenuController(Doctor doctor){
        this.doctor=doctor;
        this.doctorMenuView=new DoctorMenuView(doctor);
    }

    @Override
    public void navigate() {
        int choice = 0;
        do{
            this.doctorMenuView.displayHeader();
            this.doctorMenuView.displayOptions();

            try{
                choice = InputHandler.getChoice(1, 9);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				// Continue loop if invalid choice
				choice = -1;
				continue;
			}
            
            ScheduleView scheduleView = new ScheduleView();

            switch (choice) {
            case 1: //view patient medical records
                MedicalRecordView medicalRecordView = new MedicalRecordView(choosePatient());
                medicalRecordView.displayMedicalRecord();
                break;
            case 2: //update patient medical records
                UpdatePatientMedicalRecordController updatePatientMedicalRecordController = new UpdatePatientMedicalRecordController(choosePatient());
                updatePatientMedicalRecordController.navigate();
                break;
            case 3: //view personal schedule (all appointments in DB)
                scheduleView.displayAllConfirmedAppointments(doctor.getSchedule(), patientRepository);
                break;
            case 4: //set availability for appointments
                SetDoctorAvailabilityController setDoctorAvailabilityController = new SetDoctorAvailabilityController(doctor);
                setDoctorAvailabilityController.navigate();
                break;
            case 5: // accept or decline appt requests
                PendingRequestController pendingRequestController = new PendingRequestController(doctor);
                pendingRequestController.navigate();
                break;
            case 6: //view all appts today & tmr
                scheduleView.displayUpcomingAppointments(doctor.getSchedule(), patientRepository);
                break;
            case 7: //record appt outcome
                CompleteAppointmentController completeAppointmentController = new CompleteAppointmentController(doctor, choosePatient());
                completeAppointmentController.navigate();
                break;
            case 8: //change password
                ChangePasswordController changePasswordController = new ChangePasswordController(doctor);
                changePasswordController.navigate();
                break;
            case 9: //logout
                System.out.println("Logging out.");
                break;
            default:
            }
        } while (choice < 9);
    }
    
    private Patient choosePatient() {
        doctorMenuView.displayPatientIDPrompt();
        String patientID = InputHandler.getString();
        return (Patient) LoginController.getUser(patientID, "DOCTOR LOGIN");
    }

}