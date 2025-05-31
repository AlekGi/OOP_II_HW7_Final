package org.homework.presenter;

import org.homework.model.Note;
import org.homework.model.Notebook;
import org.homework.viewer.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class NotebookPresenter {
    private Notebook notebook;
    private DisplayMessage displayMessage;
    private DisplayNotebook displayNotebook;
    private InputView inputView;
    private NotebookView notebookView;
    private DateTimeInput dateTimeInput;

    public NotebookPresenter(Notebook notebook, DisplayMessage displayMessage, DisplayNotebook displayNotebook,
                             InputView inputView, NotebookView notebookView, DateTimeInput dateTimeInput) {
        this.notebook = notebook;
        this.displayMessage = displayMessage;
        this.displayNotebook = displayNotebook;
        this.inputView = inputView;
        this.notebookView = notebookView;
        this.dateTimeInput = dateTimeInput;
    }

    public void addNote() {
        LocalDateTime dateTime = dateTimeInput.getDateTimeInput();
        displayMessage.displayMessage("Введите название записи:");
        String note = inputView.userInput();
        notebook.addNotes(new Note(note, dateTime));
        displayMessage.displayMessage("Запись добавлена");
    }

    public void showNotesForDay() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Note> notes = notebook.getNotesForDay(dateTime);
        displayNotebook.displayNote(notes);
    }

    public void showNotesForWeek() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Note> notes = notebook.getNotesForWeek(dateTime);
        displayNotebook.displayNote(notes);
    }

    public void showNotesForMonth() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Note> notes = notebook.getNotesForMonth(dateTime);
        displayNotebook.displayNote(notes);
    }

    public void showNotesForYear() {
        LocalDateTime dateTime = LocalDateTime.now();
        List<Note> notes = notebook.getNotesForYear(dateTime);
        displayNotebook.displayNote(notes);
    }

    public void saveNotesToFile() {
        String fileName = "src/main/resources/notes.txt";
        try {
            notebook.saveToFile(fileName);
        } catch (IOException e) {
            displayMessage.displayMessage("Ошибка сохранения файла: " +
                    e.getMessage());
        }
    }
    public void loadNotesFromFile() {
        String fileName = "src/main/resources/notes.txt";
        try {
            notebook.loadFromFile(fileName);
        } catch (IOException | ClassNotFoundException e) {
            displayMessage.displayMessage("Ошибка загрузки файла: " +
                    e.getMessage());
        }
    }


    public void handleCommand(String command) throws IOException, ClassNotFoundException {
        loadNotesFromFile();
        switch (command) {
            case "1":
                addNote();
                break;
            case "2":
                displayMessage.displayMessage("Записи за этот день: ");
                showNotesForDay();
                break;
            case "3":
                displayMessage.displayMessage("Записи за эту неделю: ");
                showNotesForWeek();
                break;
            case "4":
                displayMessage.displayMessage("Записи за месяц: ");
                showNotesForMonth();
                break;
            case "5":
                displayMessage.displayMessage("Записи за год: ");
                showNotesForYear();
                break;
            case "6":
                saveNotesToFile();
                displayMessage.displayMessage("Файл успешно сохранён");
                break;
            case "7":
                loadNotesFromFile();
                displayMessage.displayMessage("Файл успешно загружен");
                break;
            case "0":
                System.exit(0);
            default:
                displayMessage.displayMessage("Неизвестная команда");
        }
    }
}
