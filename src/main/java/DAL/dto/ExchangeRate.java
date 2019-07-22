package DAL.dto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kr_exchange_rates", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "SYMBOL")
})
public class ExchangeRate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "SYMBOL", unique = true, nullable = false)
    private String symbol;

    @Column(name = "VALUE")
    private double value;

    @Column(name = "PRICE_TIMESTAMP", nullable = false)
    private LocalDateTime priceTimestamp;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getPriceTimestamp() {
        return priceTimestamp;
    }

    public void setPriceTimestamp(LocalDateTime priceTimestamp) {
        this.priceTimestamp = priceTimestamp;
    }
}
