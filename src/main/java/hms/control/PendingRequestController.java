package hms.control;

import hms.boundary.InputHandler;
import hms.boundary.patient.appointment.PendingRequestView;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.appointment.Schedule;
import hms.entity.user.Doctor;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class PendingRequestController extends Controller {
    PendingRequestView pendingRequestView;
    private Doctor doctor;

    public PendingRequestController(Doctor doctor) {
        this.pendingRequestView = new PendingRequestView();
        this.doctor = doctor;
    }

    @Override
    public void navigate() {
        int choice;
        Schedule schedule = doctor.getSchedule();
        Appointment appointment = pendingRequestView.displayFirstPendingAppointments(schedule, patientRepository);
        if (appointment.getPatientId() == null) {
            pendingRequestView.displayNoPending();
            return;
        }
        while (appointment.getPatientId() != null){
            try{
                choice = InputHandler.getChoice(0, 1);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
				// Continue loop if invalid choice
				choice = -1;
				break;
			}
            switch (choice) {
            case 0:
                appointment.setAppointmentStatus(AppointmentStatus.CANCELLED);
                break;
            case 1:
                appointment.setAppointmentStatus(AppointmentStatus.CONFIRMED);
            default:

            schedule = doctor.getSchedule();
            appointment = pendingRequestView.displayFirstPendingAppointments(schedule, patientRepository);
            }
        }
        System.out.println("No more pending Appointments.");
    }
}
