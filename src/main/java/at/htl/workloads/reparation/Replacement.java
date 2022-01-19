package at.htl.workloads.reparation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Replacement {

    //region fields
    @Id
    @EmbeddedId
    private ReplacementId id;
    //endregion

    //region Constructor
    public Replacement(ReplacementId id) {
        this.id = id;
    }

    public Replacement() {
    }
    //endregion

    //region Getter and Setter
    public ReplacementId getId() {
        return id;
    }

    public void setId(ReplacementId id) {
        this.id = id;
    }
    //endregion
}
