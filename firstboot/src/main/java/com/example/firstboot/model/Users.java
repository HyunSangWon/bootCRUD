package com.example.firstboot.model;


import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Users {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="team_name")
    private String teamName;
    @Column(name="salary")
    private Integer salary;
    @Column(name="age")
    private Integer age;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
