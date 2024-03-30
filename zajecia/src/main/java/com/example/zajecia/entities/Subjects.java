package com.example.zajecia.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="refIdPrzed", scope=Subjects.class)
public class Subjects {
    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "przedm_seq")
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @Column
    private String teacherName;

    public Subjects() {

    }

    @JsonIgnore
    @ManyToMany(mappedBy = "assignedSubjects")
    private Set<Students> studentsSet = new HashSet<>();


    //---------------------------------------------//

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
