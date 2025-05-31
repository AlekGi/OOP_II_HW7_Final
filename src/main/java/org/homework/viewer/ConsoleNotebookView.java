package org.homework.viewer;

import org.homework.model.Note;
import org.homework.presenter.NotebookPresenter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ConsoleNotebookView implements NotebookView {
    private NotebookPresenter presenter;
    private Scanner scanner;

    public ConsoleNotebookView() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void setPresenter(NotebookPresenter presenter) {

    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void displayNote(List<Note> notes) {
        for (Note note : notes) {
            System.out.println("Запись: " + note.getNote() + ", дата записи: " + note.getDateTime());
        }
    }

    @Override
    public String userInput(){
        return scanner.nextLine();
    }

    @Override
    public LocalDateTime getDateTimeInput() {
        System.out.println("Укажите, когда необходимо оповестить о записи, в формате (день-месяц-год ЧЧ:мм): ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(input, formatter);
    }
}
