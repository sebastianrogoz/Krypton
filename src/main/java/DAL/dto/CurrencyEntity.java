package DAL.dto;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Currency", uniqueConstraints = {
        @UniqueConstraint(columnNames = "ID"),
        @UniqueConstraint(columnNames = "SYMBOL")
})
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private int id;

    @Column(name = "SYMBOL", unique = true, nullable = false)
    private String symbol;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "PRICE_TIMESTAMP", nullable = false)
    private LocalDateTime priceTimestamp;


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
