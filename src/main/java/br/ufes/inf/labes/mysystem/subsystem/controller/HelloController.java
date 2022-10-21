package br.ufes.inf.labes.mysystem.subsystem.controller;

import java.io.Serializable;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class HelloController implements Serializable {
    private static final String[] NAMES =
            {"JButler", "Jakarta EE 9", "PrimeFaces 10", "AdminFaces"};

    private int idx;

    private int count;

    private int multiplier;

    public String getName() {
        count++;
        idx = (idx + 1) % NAMES.length;
        return NAMES[idx];
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
      this.count = count;
    }

    public int getMultiplier() {
      return multiplier;
    }

    public void setMultiplier(int multiplier) {
      this.multiplier = multiplier;
    }

    public void resetCount() {
        count = 0;
    }

    public void multiply() {
        count *= multiplier;
    }
}
