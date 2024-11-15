package com.cf.studentsociety.entity;

import java.util.Date;

public class Member {
    private Integer memberId;
    private Integer MemberStudentId;
    private Student memberInfo;
    private Date joinDate;
    private Integer memberStatus;
    private String memberPosition;
    private String member_associationId;
    private String member_societyId;
    public String getMember_societyId() {
        return member_societyId;
    }

    public void setMember_societyId(String member_societyId) {
        this.member_societyId = member_societyId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getMemberStudentId() {
        return MemberStudentId;
    }

    public void setMemberStudentId(Integer memberStudentId) {
        MemberStudentId = memberStudentId;
    }

    public Student getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(Student memberInfo) {
        this.memberInfo = memberInfo;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public String getMemberPosition() {
        return memberPosition;
    }

    public void setMemberPosition(String memberPosition) {
        this.memberPosition = memberPosition;
    }

    public String getMember_associationId() {
        return member_associationId;
    }

    public void setMember_associationId(String member_associationId) {
        this.member_associationId = member_associationId;
    }
}
