package org.homework.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Note implements Serializable {
    private final String note;
    private final LocalDateTime localDateTime;
    private static final long serialVersionUID = 1L;

    public Note(String note, LocalDateTime localDateTime) {
        this.note = note;
        this.localDateTime = localDateTime;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getDateTime() {
        return localDateTime;
    }

    @Override
    public String toString() {
        return localDateTime.toString() + ": " + note;
    }
}
