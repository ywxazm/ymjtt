package com.ymjtt.test.dto;

import java.io.Serializable;

public class StudentAlia implements Serializable {

    private Integer stuId;
    private String stuName;
    private Integer stuAge;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getStuAge() {
        return stuAge;
    }

    public void setStuAge(Integer stuAge) {
        this.stuAge = stuAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentAlia that = (StudentAlia) o;

        if (stuId != null ? !stuId.equals(that.stuId) : that.stuId != null) return false;
        if (stuName != null ? !stuName.equals(that.stuName) : that.stuName != null) return false;
        return stuAge != null ? stuAge.equals(that.stuAge) : that.stuAge == null;
    }

    @Override
    public int hashCode() {
        int result = stuId != null ? stuId.hashCode() : 0;
        result = 31 * result + (stuName != null ? stuName.hashCode() : 0);
        result = 31 * result + (stuAge != null ? stuAge.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StudentAlia{" +
                "stuId=" + stuId +
                ", stuName='" + stuName + '\'' +
                ", stuAge=" + stuAge +
                '}';
    }
}
