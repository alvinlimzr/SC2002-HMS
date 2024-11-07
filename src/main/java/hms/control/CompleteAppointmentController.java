package hms.control;

import java.time.LocalDate;

import hms.boundary.InputHandler;
import hms.boundary.Prompt;
import hms.boundary.patient.ScheduleView;
import hms.boundary.patient.appointment.CompleteAppointmentView;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.Schedule;
import hms.entity.medicine.Medicine;
import hms.entity.record.AppointmentOutcomeRecord;
import hms.entity.user.Doctor;
import hms.entity.user.Patient;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;
import hms.exceptions.InvalidDateException;

public class CompleteAppointmentController extends Controller{
    private ScheduleView scheduleView;
    private CompleteAppointmentView completeAppointmentView;
    private Doctor doctor;
    private Patient patient;

    public CompleteAppointmentController(Doctor doctor, Patient patient) {
        this.scheduleView = new ScheduleView();
        this.completeAppointmentView = new CompleteAppointmentView();
        this.doctor = doctor;
        this.patient = patient;
    }

    public void navigate() {
        Prompt.displayDatePrompt();
        LocalDate date;
        try{
            date = InputHandler.getDate();
        } catch (InvalidDateException e) {
            return;
        }

        Schedule schedule = doctor.getSchedule();
        Appointment[] appointmentArr = (schedule.getScheduleMap()).get(date);
        scheduleView.displayAppointmentByDate(schedule, patientRepository, date);
        
        completeAppointmentView.displayChoice(appointmentArr.length);
        int choice;
        try{
            choice = InputHandler.getChoice(1, 9);
        } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
            return;
        }

        completeAppointmentView.displayServiceTypePrompt();
        String service = InputHandler.getString();
        completeAppointmentView.displayNotesPrompt();
        String notes = InputHandler.getString();

        Appointment appointment = appointmentArr[choice];
        AppointmentOutcomeRecord appointmentRecord = doctor.completeAppointment(patient, appointment, service, notes);


        completeAppointmentView.displayAddPrescriptionNamePrompt();
        String medName = InputHandler.getString();
        completeAppointmentView.displayAddPrescriptionQtyPrompt();
        int medQty = -1;
        while (medQty == -1){
            try{
                medQty = InputHandler.getChoice(0, 100);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                continue;
            }
        }
        appointmentRecord.addPrescribedMedicine(new Medicine(medName, medQty));
    }
}
