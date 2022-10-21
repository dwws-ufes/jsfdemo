package br.ufes.inf.labes.mysystem.subsystem.domain;

import java.time.LocalDate;

import br.ufes.inf.labes.jbutler.ejb.persistence.PersistentObjectSupport;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class MyObject extends PersistentObjectSupport implements Comparable<MyObject> {
    @NotNull
    @Size(max = 100)
    private String myString;

    private int myNumber;

    private Boolean myBoolean;

    private LocalDate myDate;

    public String getMyString() {
        return myString;
    }

    public int getMyNumber() {
        return myNumber;
    }

    public Boolean getMyBoolean() {
        return myBoolean;
    }

    public LocalDate getMyDate() {
        return myDate;
    }

    public void setMyString(String myString) {
        this.myString = myString;
    }

    public void setMyNumber(int myNumber) {
        this.myNumber = myNumber;
    }

    public void setMyBoolean(Boolean myBoolean) {
        this.myBoolean = myBoolean;
    }

    public void setMyDate(LocalDate myDate) {
        this.myDate = myDate;
    }

    @Override
    public int compareTo(MyObject o) {
        int cmp = o.myNumber - myNumber;
        if (cmp != 0)
            return cmp;
        return myString.compareToIgnoreCase(o.myString);
    }

    @Override
    public String toString() {
        return myString;
    }
}
