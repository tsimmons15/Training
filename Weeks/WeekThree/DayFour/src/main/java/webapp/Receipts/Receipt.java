package webapp.Receipts;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Receipt {
    private double price;
    private String description;
    private LocalDateTime timestamp;

    public Receipt() {
        this.timestamp = LocalDateTime.now();
    }

    public Receipt(double price, String description, LocalDateTime timestamp) {
        this.price = price;
        this.description = description;
        this.timestamp = timestamp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
