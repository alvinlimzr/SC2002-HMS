package hms.boundary.administrator.InventoryManagement;

import java.util.List;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class SetStockWarningView extends View {
	@Override
	public void displayHeader() {
		displayBorderedText(WIDTH, "Set Stock Warning Amount");
	}

	public int MedicineIndexPrompt(List<String> medicineNames) {
		int choice;
		System.out.println("Enter Index of Medicine to be set:");
		try {
			choice = InputHandler.getChoice(1, medicineNames.size());
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			return -1;
		}
		return choice;
	}

	public int MedicineAmountPrompt() {
		int amount;
		System.out.println("Enter Amount of Medicine to be set as Warning:");
		try {
			amount = InputHandler.getChoice(1, 9999);
		} catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
			amount = -1;
		}
		return amount;
	}

	public void SuccessfulChangePrompt() {
		System.out.println("Warning succesfully changed");
		System.out.println("-".repeat(WIDTH));
	}
}
