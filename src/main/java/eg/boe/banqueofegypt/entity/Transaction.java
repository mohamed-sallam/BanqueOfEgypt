package eg.boe.banqueofegypt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
@Entity
@Table(name = "transaction")
public record Transaction (

   @Id Long id,
  String payer,
  String payee,
  Integer amount,
  Status status,
  Date date
){
    enum Status{
        PENDING,
        SUCCESS,
        FAIL
    }
    
    public Transaction() {
        this(null, null, null, null, null, null);
    }
}
