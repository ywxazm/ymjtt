package com.ywx.ymjtt.test.xdo;

import java.io.Serializable;

public class StudentDo implements Serializable {

    private Integer id;     //id
    private String name;    //姓名
    private Integer age;    //年纪

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentDo studentDo = (StudentDo) o;

        if (id != null ? !id.equals(studentDo.id) : studentDo.id != null) return false;
        if (name != null ? !name.equals(studentDo.name) : studentDo.name != null) return false;
        return age != null ? age.equals(studentDo.age) : studentDo.age == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
