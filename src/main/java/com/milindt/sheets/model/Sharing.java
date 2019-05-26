package com.milindt.sheets.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Sharing {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(targetClass = String.class)
    private List <String> selections;

    @ElementCollection(targetClass = String.class)
    private List <String> emailIds;

    public Sharing() {
    }

    public void setSelections(List<String> selections) {
        this.selections = selections;
    }

    public List <String> getSelections() {
        return selections;
    }

    public List <String> getEmailIds() {
        return emailIds;
    }

    public void setEmailIds(List <String> emailIds) {
        this.emailIds = emailIds;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
