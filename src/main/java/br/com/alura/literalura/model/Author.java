package br.com.alura.literalura.model;

import java.time.LocalDate;

public class Author {
    private String name;
    private LocalDate birthdate;
    private boolean isLiving;

    // Construtores, getters e setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public boolean isLiving() {
        return isLiving;
    }

    public void setLiving(boolean living) {
        isLiving = living;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", isLiving=" + isLiving +
                '}';
    }
}
