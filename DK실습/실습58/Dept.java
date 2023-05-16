package jpamvcexam.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "DEPT")
public class Dept {
    @Id
    @Column(name = "DEPTNO")
    private Long deptNo;

    @Column(name = "DNAME")
    private String dName;

    @Column(name = "LOC_CODE")
    private String locCode;

    @Override
    public String toString() {
        return "Dept{" +
               "deptNo=" + deptNo +
               ", dName='" + dName + '\'' +
               ", locCode='" + locCode + '\'' +
               '}';
    }

    public Long getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(Long deptNo) {
        this.deptNo = deptNo;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getLocCode() {
        return locCode;
    }

    public void setLocCode(String locCode) {
        this.locCode = locCode;
    }
}
