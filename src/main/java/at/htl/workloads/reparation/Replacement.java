package at.htl.workloads.reparation;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Replacement {

    //region fields
    @Id
    @EmbeddedId
    private ReplacementId id;
    private int amount;
    //endregion

    //region Constructor
    public Replacement(ReplacementId id, int amount) {
        this.id = id;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    //endregion

    //region equals and hash
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Replacement that = (Replacement) o;
        return amount == that.amount && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount);
    }
    //endregion
}
