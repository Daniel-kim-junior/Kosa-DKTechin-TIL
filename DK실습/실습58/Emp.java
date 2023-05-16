package jpamvcexam.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "EMP")
public class Emp {
    public Long getEmpNo() {
        return empNo;
    }

    @Override
    public String toString() {
        return "Emp{" +
               "empNo=" + empNo +
               ", eName='" + eName + '\'' +
               ", job='" + job + '\'' +
               ", mgr=" + mgr +
               ", hireDate=" + hireDate +
               ", sal=" + sal +
               ", comm=" + comm +
               ", deptNo=" + dept +
               '}';
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public Integer getSal() {
        return sal;
    }

    public void setSal(Integer sal) {
        this.sal = sal;
    }

    public Integer getComm() {
        return comm;
    }

    public void setComm(Integer comm) {
        this.comm = comm;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept deptNo) {
        this.dept = deptNo;
    }

    @Id
    @Column(name = "EMPNO")
    private Long empNo;

    @Column(name = "ENAME")
    private String eName;

    @Column(name = "JOB")
    private String job;

    @Column(name = "MGR")
    private Integer mgr;

    @Column(name = "HIREDATE")
    private LocalDateTime hireDate;

    @Column(name = "SAL")
    private Integer sal;

    @Column(name = "COMM")
    private Integer comm;

    @ManyToOne
    @JoinColumn(name = "DEPTNO")
    private Dept dept;
}
