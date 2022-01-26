package at.htl.workloads.ownership;

import at.htl.workloads.vehicle.Vehicle;
import at.htl.workloads.person.Tenant;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Rental {

    //region fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vehicle vehicle;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Tenant tenant;
    @Column(name = "r_from")
    private LocalDateTime from;
    @Column(name = "r_to")
    private LocalDateTime to;
    //endregion

    //region Constructor
    public Rental( Vehicle vehicle, Tenant tenant, LocalDateTime from, LocalDateTime to) {
        this.vehicle = vehicle;
        this.tenant = tenant;
        this.from = from;
        this.to = to;
    }

    public Rental() {
    }
    //endregion

    //region Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }
    //endregion
}
