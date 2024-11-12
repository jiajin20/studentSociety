package com.cf.studentsociety.entity;

public class Position {
    private int positionId;
    private String positionName;
    private Integer positionSociety;
    private Integer positionLevel;

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Integer getPositionSociety() {
        return positionSociety;
    }

    public void setPositionSociety(Integer positionSociety) {
        this.positionSociety = positionSociety;
    }

    public Integer getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(Integer positionLevel) {
        this.positionLevel = positionLevel;
    }
}
