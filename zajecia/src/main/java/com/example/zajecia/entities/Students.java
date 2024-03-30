package com.example.zajecia.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class,
        property="refId", scope=Students.class)
public class Students {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String idUcznia;

    @Column
    private String name;

    @Column(name = "birthDate", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
    private DateTime birthDate;

    public Students(){


    }

    @ManyToMany
    @JoinTable(name = "students_subjects",
            joinColumns = @JoinColumn(name = "students_id"),
            inverseJoinColumns = @JoinColumn(name = "subjects_id")
    )
    private Set<Subjects> assignedSubjects = new HashSet<>();

    public Set<Subjects> getAssignedSubjects() {
        return assignedSubjects;
    }

    public void setAssignedSubjects(Set<Subjects> assignedSubjects) {
        this.assignedSubjects = assignedSubjects;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_Legity")
    private StudentCards studentCards;

    public void setAssignedStudentCards(StudentCards assignedStudentCards){
        this.studentCards = assignedStudentCards;
    }


    //indeksSZ_id
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_szafki")
    private Locker locker;

    public void setAssignedLocker(Locker assignedLocker){
        this.locker = assignedLocker;
    }


    //-----------------------------------------------------//

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdUcznia() {
        return idUcznia;
    }

    public void setIdUcznia(String idUcznia) {
        this.idUcznia = idUcznia;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }
}
