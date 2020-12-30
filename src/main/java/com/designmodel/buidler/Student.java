package com.designmodel.buidler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class Student {
    private int id;
    private String name;
    private Date birth;
    private int age;
    private int sex;
    private String grade;

    public Student(StudentBuilder studentBuilder) {
        this.id = studentBuilder.id;
        this.name = studentBuilder.name;
        this.birth = studentBuilder.birth;
        this.age = studentBuilder.age;
        this.sex = studentBuilder.sex;
        this.grade = studentBuilder.grade;
    }


   public static class StudentBuilder {
        private int id;
        private String name;
        private Date birth;
        private int age;
        private int sex;
        private String grade;

        public  StudentBuilder Builder(int id, String name) {
            this.id = id;
            this.name = name;
            return this;
        }

        public StudentBuilder withBirth(Date birth){
            this.birth = birth;
            return this;
        }
        public StudentBuilder withAge(int age){
            this.age = age;
            return this;
        }
        public StudentBuilder withSex(int sex){
            this.sex = sex;
            return this;
        }
        public StudentBuilder withGrade(String grade){
            this.grade = grade;
            return this;
        }
        public Student build(){
            return new Student(this);
        }
    }
    public static void main(String[] args) {
        Student 小妹 = new StudentBuilder().Builder(1, "小妹").withAge(20).build();
        System.out.println(小妹);
    }
}
