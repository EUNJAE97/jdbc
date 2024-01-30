package com.ohgiraffers;

public class ActorDTO {
    private int actId ;
    private String firstName ;

    private String lastName ;

    public ActorDTO() {
    }

    public ActorDTO(int actId, String firstName, String lastName) {
        this.actId = actId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getActId() {
        return actId;
    }

    public void setActId(int actId) {
        this.actId = actId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "ActorDTO{" +
                "actId=" + actId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
