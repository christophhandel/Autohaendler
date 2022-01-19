package at.htl.workloads.reparation;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PartId implements Serializable {

    //region fields
    private String partType;
    private String description;
    //endregion

    //region Constructor
    public PartId(String partType, String description) {
        this.partType = partType;
        this.description = description;
    }

    public PartId() {
    }
    //endregion

    //region Getter and Setter
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
    //endregion

    //region equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartId partId = (PartId) o;
        return Objects.equals(partType, partId.partType) && Objects.equals(description, partId.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partType, description);
    }
    //endregion
}
