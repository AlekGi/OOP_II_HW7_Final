package org.homework.model;

import org.homework.model.service.IFileOperations;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Notebook implements Serializable, IFileOperations {
    private final List<Note> notes;
    private static final long serialVersionUID = 1L;

    public Notebook() {
        this.notes = new ArrayList<>();
    }

    public void addNotes(Note note) {
        notes.add(note);
    }

    public List<Note> getNotesForDay(LocalDateTime dateDay) {
        return notes.stream().filter(note -> note.getDateTime().toLocalDate()
                .isEqual(dateDay.toLocalDate())).sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }

    public List<Note> getNotesForWeek(LocalDateTime startWeek) {
        LocalDateTime endOfWeek = startWeek.plusWeeks(1);
        return notes.stream().filter(note ->
                !note.getDateTime().isBefore(startWeek) && !note.getDateTime().isAfter(endOfWeek))
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }

    public List<Note> getNotesForMonth(LocalDateTime startMonth) {
        LocalDateTime endMonth = startMonth.plusMonths(1);
        return notes.stream().filter(note ->
                !note.getDateTime().isBefore(startMonth) && !note.getDateTime().isAfter(endMonth))
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }

    public List<Note> getNotesForYear(LocalDateTime startYear) {
        LocalDateTime endYear = startYear.plusYears(1);
        return notes.stream().filter(note ->
                !note.getDateTime().isBefore(startYear) && !note.getDateTime().isAfter(endYear))
                .sorted(Comparator.comparing(Note::getDateTime))
                .collect(Collectors.toList());
    }

    @Override
    public void saveToFile(String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Note note : notes){
                bw.write(note.toString());
                bw.newLine();
            }
        }
    }

    @Override
    public void loadFromFile(String path) throws IOException, ClassNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] s = line.split(": " , 2);
                LocalDateTime localDateTime = LocalDateTime.parse(s[0]);
                String note = s[1];
                notes.add(new Note(note, localDateTime));
            }
        }
    }
}
