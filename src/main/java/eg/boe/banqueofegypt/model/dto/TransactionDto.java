package eg.boe.banqueofegypt.model.dto;

import eg.boe.banqueofegypt.entity.Account;
import jakarta.persistence.*;

import java.sql.Date;

public record TransactionDto(
        Long id,
        AccountDto payer,
        AccountDto payee,
        String amount,
        Status status,
        Date date
) {
    public TransactionDto() {
        this(null, null, null, null, null, null);
    }

    public enum Status {
        PENDING, SUCCESS, FAIL
    }
}
