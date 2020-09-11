package com.moise.gadsledersboards.others;

public class Submission {

    public String firstName, lastName, email, githubLink;

    public Submission(String firstName, String lastName, String email, String githubLink){
        this.firstName = firstName;
        this.lastName= lastName;
        this.email = email;
        this.githubLink= githubLink;
    }


    public boolean validate(){
        return firstName!=null&&firstName.length()>0
                &&email!=null&&email.length()>0
                &&lastName!=null&&lastName.length()>0
                &&githubLink!=null&&githubLink.length()>0;
    }

}
