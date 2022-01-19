package at.htl.workloads.reparation;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class ReplacementId implements Serializable {

    //region fields
    @ManyToOne
    private Part part;
    @ManyToOne
    private Reparation reparation;
    //endregion

    //region Constructor
    public ReplacementId(Part part, Reparation reparation) {
        this.part = part;
        this.reparation = reparation;
    }

    public ReplacementId() {
    }
    //endregion

    //region Getter and Setter
    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Reparation getReparation() {
        return reparation;
    }

    public void setReparation(Reparation reparation) {
        this.reparation = reparation;
    }
    //endregion
}
