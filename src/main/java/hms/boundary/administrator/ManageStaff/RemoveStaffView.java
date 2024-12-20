package hms.boundary.administrator.ManageStaff;

import hms.boundary.InputHandler;
import hms.boundary.View;
import hms.exceptions.InvalidChoiceFormatException;
import hms.exceptions.InvalidChoiceValueException;

public class RemoveStaffView extends View {
    int choice;

    public RemoveStaffView(){
        choice=0;
    }
    
    @Override
    public void displayHeader() {
        displayBorderedText(WIDTH, "Remove Staff");
    }

    public int getRoleChoice() {
        System.out.println("Choose role (Doctor/Pharmacist/Receptionist): ");
		System.out.println("1: Doctor\r\n2: Pharmacist\r\n3: Receptionist\r\n4: Cancel");
        choice=0;
        try {
                choice = InputHandler.getChoice(1, 4);
            } catch (InvalidChoiceFormatException | InvalidChoiceValueException e) {
                return 4;
            }
        return choice;
    }

    public String getID(){
        System.out.println("Enter User ID of Staff:");
        return InputHandler.getString();
    }

    public void staffDoesNotExist(){
        System.out.println("Staff does not exist");
    }

    public void staffRemoveSuccessful(){
        System.out.println("Staff removal successful");
    }
}
