package hms.control.patient;

import java.time.LocalDate;

import hms.boundary.Prompt;
import hms.boundary.patient.AvailabilityView;
import hms.control.Controller;
import hms.entity.user.Doctor;

public class ScheduleController extends Controller {
	private final AvailabilityView scheduleView;

	public ScheduleController() {
		this.scheduleView = new AvailabilityView();
	}

	@Override
	public void navigate() {
		LocalDate date = Prompt.displayDatePrompt();
		if (date == null) return;
		
		this.scheduleView.displayHeader();
		this.scheduleView.displayDate(date);

		for (Doctor doctor : doctorRepository.getAll()) {
			this.scheduleView.displayAvailabilityTable(doctor, date);
		}
	}

}
