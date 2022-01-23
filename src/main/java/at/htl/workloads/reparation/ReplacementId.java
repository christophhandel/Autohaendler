package at.htl.workloads.reparation;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ReplacementId implements Serializable {

    //region fields
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Part part;
    @ManyToOne(cascade = CascadeType.ALL)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReplacementId that = (ReplacementId) o;
        return Objects.equals(part, that.part) && Objects.equals(reparation, that.reparation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(part, reparation);
    }
}
