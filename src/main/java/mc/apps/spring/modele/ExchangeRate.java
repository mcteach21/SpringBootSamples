package mc.apps.spring.modele;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "currency", "date" }))
@Entity
public class ExchangeRate {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private Double rate;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public ExchangeRate() {
    }

    public ExchangeRate(Long id, String currency, Double rate, Date date) {
        this.id = id;
        this.currency = currency;
        this.rate = rate;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
