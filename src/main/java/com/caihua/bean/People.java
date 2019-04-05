package com.caihua.bean;

import org.apache.ibatis.type.Alias;
import org.springframework.stereotype.Component;

@Component
@Alias("People")
public class People {

    private String idCard;
    private String name;
    private int age;
    private String gender;
    private String birth;
    private String hobby;

    public People() {
    }

    public People(String idCard, String name, int age, String gender, String birth, String hobby) {
        this.idCard = idCard;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birth = birth;
        this.hobby = hobby;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
