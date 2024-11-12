package com.cf.studentsociety.entity;

import java.util.Date;

public class Society {
    private Integer societyId;
    private Integer society_creator;
    private Student creator;
    private Date society_create_date;
    private String society_name;
    private String society_intro;
    private Integer society_status;

    public Integer getSocietyId() {
        return societyId;
    }

    public void setSocietyId(Integer societyId) {
        this.societyId = societyId;
    }

    public Integer getSociety_creator() {
        return society_creator;
    }

    public void setSociety_creator(Integer society_creator) {
        this.society_creator = society_creator;
    }

    public Student getCreator() {
        return creator;
    }

    public void setCreator(Student creator) {
        this.creator = creator;
    }

    public Date getSociety_create_date() {
        return society_create_date;
    }

    public void setSociety_create_date(Date society_create_date) {
        this.society_create_date = society_create_date;
    }

    public String getSociety_name() {
        return society_name;
    }

    public void setSociety_name(String society_name) {
        this.society_name = society_name;
    }

    public String getSociety_intro() {
        return society_intro;
    }

    public void setSociety_intro(String society_intro) {
        this.society_intro = society_intro;
    }

    public Integer getSociety_status() {
        return society_status;
    }

    public void setSociety_status(Integer society_status) {
        this.society_status = society_status;
    }
}
