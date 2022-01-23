package at.htl.models;

public class ReplacementDTO {
    private String partType;
    private String partDescription;
    private Long reparationId;
    private int amount;

    public ReplacementDTO(String partType, String partDescription, Long reparationId, int amount) {
        this.partType = partType;
        this.partDescription = partDescription;
        this.reparationId = reparationId;
        this.amount = amount;
    }

    public ReplacementDTO() {
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public void setPartDescription(String partDescription) {
        this.partDescription = partDescription;
    }

    public Long getReparationId() {
        return reparationId;
    }

    public void setReparationId(Long reparationId) {
        this.reparationId = reparationId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
