package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "yoine")
@NamedQueries({
    @NamedQuery(
            name = "getYoine",
            query = "SELECT y.report FROM Yoine AS y WHERE y.report = :id and y.employee = :emp"
            ),
    @NamedQuery(
            name = "getCount",
            query = "SELECT COUNT(y) FROM Yoine AS y WHERE y.report = :id GROUP BY y.report"
            ),
    @NamedQuery(
            name = "getId",
            query = "SELECT y.id FROM Yoine AS y WHERE y.report = :id and y.employee = :emp"
            ),
})
@Entity
public class Yoine {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;
    
    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;
    
    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private Report report;
    
    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
