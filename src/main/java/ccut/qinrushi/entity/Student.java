package ccut.qinrushi.entity;

import org.json.JSONObject;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @Column(unique = true,length = 36)
    private String studentId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String age;

    public Student() {
    }

    public Student(String studentId, String name, String age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.accumulate("studentId",studentId);
        jsonObject.accumulate("name",name);
        jsonObject.accumulate("age",age);
        return jsonObject.toString();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

