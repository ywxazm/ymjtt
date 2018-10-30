package com.ymjtt.test.vo;

import com.ymjtt.test.xdo.StudentDo;

public class StudentVo {

    private StudentDo studentDo;
    private Integer sex;

    public StudentDo getStudentDo() {
        return studentDo;
    }

    public void setStudentDo(StudentDo studentDo) {
        this.studentDo = studentDo;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentVo studentVo = (StudentVo) o;

        if (studentDo != null ? !studentDo.equals(studentVo.studentDo) : studentVo.studentDo != null) return false;
        return sex != null ? sex.equals(studentVo.sex) : studentVo.sex == null;
    }

    @Override
    public int hashCode() {
        int result = studentDo != null ? studentDo.hashCode() : 0;
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "studentDo=" + studentDo +
                ", sex=" + sex +
                '}';
    }
}
