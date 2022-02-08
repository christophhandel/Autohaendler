package at.htl.models;

import at.htl.workloads.ownership.VehicleTransfer;

public class VehicleTransferDTO {

    private Long vehicleId;
    private String ownerId;
    private Long id;

    public VehicleTransferDTO(Long vehicleId, String ownerId, Long id) {
        this.vehicleId = vehicleId;
        this.ownerId = ownerId;
        this.id = id;
    }

    public VehicleTransferDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static VehicleTransferDTO fromVehicleTransfer(VehicleTransfer vt) {
        VehicleTransferDTO vehicleTransferDTO = new VehicleTransferDTO();
        vehicleTransferDTO.setVehicleId(vt.getVehicle().getId());
        vehicleTransferDTO.setOwnerId(vt.getNewOwner().getSvNr());
        vehicleTransferDTO.setId(vt.getId());
        return vehicleTransferDTO;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
