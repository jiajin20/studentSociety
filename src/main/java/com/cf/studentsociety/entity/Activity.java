package com.cf.studentsociety.entity;

import java.util.Date;

public class Activity {
    private Integer activityId;
    private Integer activity_societyId;
    private String activity_name;
    private String activity_intro;
    private Date activity_start_time;
    private Date activity_end_time;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getActivity_societyId() {
        return activity_societyId;
    }

    public void setActivity_societyId(Integer activity_societyId) {
        this.activity_societyId = activity_societyId;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_intro() {
        return activity_intro;
    }

    public void setActivity_intro(String activity_intro) {
        this.activity_intro = activity_intro;
    }

    public Date getActivity_start_time() {
        return activity_start_time;
    }

    public void setActivity_start_time(Date activity_start_time) {
        this.activity_start_time = activity_start_time;
    }

    public Date getActivity_end_time() {
        return activity_end_time;
    }

    public void setActivity_end_time(Date activity_end_time) {
        this.activity_end_time = activity_end_time;
    }
}
