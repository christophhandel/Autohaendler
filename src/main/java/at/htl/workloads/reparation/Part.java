package at.htl.workloads.reparation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

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
}
