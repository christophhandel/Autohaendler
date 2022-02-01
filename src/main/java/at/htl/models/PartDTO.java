package at.htl.models;

public class PartDTO {
    private String partType;
    private String description;
    private int amountStored;

    public PartDTO() {
    }

    public PartDTO(String partType, String description, int amountStored) {
        this.partType = partType;
        this.description = description;
        this.amountStored = amountStored;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountStored() {
        return amountStored;
    }

    public void setAmountStored(int amountStored) {
        this.amountStored = amountStored;
    }
}
