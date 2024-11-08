package hms.boundary.patient.appointment;

import java.time.LocalDate;
import java.util.Map;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.appointment.Schedule;
import hms.repository.PatientRepository;

public class PendingRequestView extends View{
    public Appointment displayFirstPendingAppointments(Schedule schedule, PatientRepository patientRepository){
        Map<LocalDate, Appointment[]> sm = schedule.getScheduleMap();
        for (Map.Entry<LocalDate, Appointment[]> entry : sm.entrySet()){
            for (Appointment appt : entry.getValue()){
                if (appt!=null){
                    if (appt.getAppointmentStatus() == AppointmentStatus.PENDING){
                        System.out.println(entry.getKey() +"\t" + appt.getTime() + ": " + 
                        (patientRepository.getById(appt.getPatientId())).getName() //patient name
                        + "(" + appt.getPatientId() + ")");
                        System.out.print("Enter 1 to confirm and 0 to cancel: ");
                        return appt;
                    }
                }
            }
        }
        return new Appointment(null, null, null, null);
    }

    public void displayNoPending() {
        System.out.println("No pending Appointment requests.");
    }

    @Override
    public void displayHeader(){
        displayBorderedText(WIDTH, "Pending Requests");
    }
}