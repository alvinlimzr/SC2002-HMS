package hms.boundary.patient;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import hms.boundary.View;
import hms.entity.appointment.Appointment;
import hms.entity.appointment.AppointmentStatus;
import hms.entity.appointment.Schedule;
import hms.entity.user.Doctor;
import hms.repository.PatientRepository;

public class ScheduleView extends View {

	public void displayDate(LocalDate date) {
		System.out.println("Date: " + date);
	}

	public void displayAvailabilityTable(Doctor doctor, LocalDate date) {
		System.out.println(String.format("Doctor: Dr. %s", doctor.getName()));
		System.out.println("|" + "-".repeat(WIDTH - 2) + "|");
		String format = "| %-" + 10 + "s | %-" + 5 + "s | %-" + (WIDTH - 25) + "s |\n";
		System.out.printf(format, "Date", "Time", "Availability");
		System.out.println("|" + "-".repeat(WIDTH - 2) + "|");
		for (LocalTime time : doctor.getAvailability(date)) {
			System.out.printf(format, date, time, "Available");
		}
		System.out.println();
	}

	public void displayAllAppointments(Schedule schedule, PatientRepository patientRepository){
		Map<LocalDate, Appointment[]> sm = schedule.getScheduleMap();
		for (LocalDate key : sm.keySet()){
			for (Appointment appt : sm.get(key)){
				if (appt.getAppointmentStatus() == AppointmentStatus.CONFIRMED){
					System.out.print(key +"\t" + appt.getTime() + ": " + 
					(patientRepository.getById(appt.getPatientId())).getName() //patient name
					+ "(" + appt.getPatientId() + ")");
				}
			}
		}
	}

	public void displayUpcomingAppointments(Schedule schedule, PatientRepository patientRepository) {
		Map<LocalDate, Appointment[]> sm = schedule.getScheduleMap();
		LocalDate date = LocalDate.now();
		LocalTime time = LocalTime.now();

		System.out.println("Today's appointments:");
		for (Appointment appt : sm.get(date)){
			if ((appt.getAppointmentStatus() == AppointmentStatus.CONFIRMED) && (appt.getTime().isAfter(time))){
				System.out.print(appt.getTime() + ": " + 
					(patientRepository.getById(appt.getPatientId())).getName() //patient name
					+ "(" + appt.getPatientId() + ")");
			}
		}

		System.out.println("\nTomorrow's appointments:");
		for (Appointment appt : sm.get((date.plusDays(1)))){
			if (appt.getAppointmentStatus() == AppointmentStatus.CONFIRMED){
				System.out.print(appt.getTime() + ": " + 
					(patientRepository.getById(appt.getPatientId())).getName() //patient name
					+ "(" + appt.getPatientId() + ")");
			}
		}
	}

	public void displayAppointmentByDate(Schedule schedule, PatientRepository patientRepository, LocalDate date){
		Appointment[] appointments = (schedule.getScheduleMap()).get(date);
		for (int i=0; i < appointments.length; i++){
			System.out.println((i+1) + ": " + appointments[i].getTime() + ": " + 
			(patientRepository.getById(appointments[i].getPatientId())).getName() //patient name
			+ "(" + appointments[i].getPatientId() + ")");
		}
	}
	
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Available Appointment Slots");
	}
}
