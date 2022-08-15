package sk.tuke.gamestudio.entity;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint(name = "UniqueOccupation", columnNames = { "occupation" })}
)
public class Occupation {

    @Id
    @GeneratedValue
    private long ident;

    @Column(nullable = false, length = 32)
    private String occupation;

    public Occupation() {
    }


    public void setIdent(long ident) {
        this.ident = ident;
    }
public  Occupation(String occupation) {this.occupation = occupation;}
  /*  public void setOccupation(String occupation) {
        this.occupation = occupation;
    }*/
    @Override
    public String toString() {
        return "Occupation{" + "ident=" + this.ident + "occupation=" + occupation + "}";
    }

    public long getIdent() {
        return ident;
    }

    public String getOccupation() {
        return occupation;
    }

}
