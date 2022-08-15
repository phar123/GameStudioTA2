package sk.tuke.gamestudio.entity;

import javax.persistence.*;


@Entity
@Table(uniqueConstraints =
        {@UniqueConstraint(name = "UniqueCountry", columnNames = { "country" })}
)
    public class Country {
        @Id
        @GeneratedValue
        private long ident;

        @Column(nullable = false, length = 128)
        private String country;

        public Country() {
        }

        public void setIdent(long ident) {
            this.ident = ident;
        }

        public void addCountry(String country) {
            this.country = country;
        }

        public Country(String country) {

            this.country = country;
        }

        @Override
        public String toString() {
            return "Country{" + "ident=" + this.ident + "country=" + country + "}";
        }

        public long getIdent() {
            return ident;
        }

        public String getCountry() {
            return country;
        }
    }
