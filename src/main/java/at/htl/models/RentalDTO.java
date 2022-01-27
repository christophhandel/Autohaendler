package at.htl.models;

import java.time.LocalDateTime;

public class RentalDTO {

    private Long vehicleId;
    private String tenantId;
    private LocalDateTime from;
    private LocalDateTime to;

    public RentalDTO(Long vehicleId, String tenantId, LocalDateTime from, LocalDateTime to) {
        this.vehicleId = vehicleId;
        this.tenantId = tenantId;
        this.from = from;
        this.to = to;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
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
}
