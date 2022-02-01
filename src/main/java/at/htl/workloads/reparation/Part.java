package at.htl.workloads.reparation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Part {

    //region fields
    @Id
    @EmbeddedId
    private PartId partId;
    private int amountStored;
    //endregion

    //region Constructor
    public Part(PartId partId, int amountStored) {
        this.partId = partId;
        this.amountStored = amountStored;
    }

    public Part() {
    }
    //endregion

    //region Getter and Setter
    public PartId getPartId() {
        return partId;
    }

    public void setPartId(PartId partId) {
        this.partId = partId;
    }

    public int getAmountStored() {
        return amountStored;
    }

    public void setAmountStored(int amountStored) {
        this.amountStored = amountStored;
    }
    //endregion

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return amountStored == part.amountStored && Objects.equals(partId, part.partId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId, amountStored);
    }
    //endregion
}
