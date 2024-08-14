package eg.boe.banqueofegypt.entity;

import java.util.Map;

public enum Status {
    PENDING(0), SUCCESS(200),
    INSUFFICIENT_FUNDS(80090), INVALID_DATA(99993),
    DEST_TIMEOUT(80091), SRC_TIMEOUT(80092);

    public final static Map<Integer, String> codeMessageMapper = Map.of(
            PENDING.code, "Pending",
            SUCCESS.code, "Success",
            INSUFFICIENT_FUNDS.code, "Insufficient funds",
            INVALID_DATA.code, "Invalid data",
            DEST_TIMEOUT.code, "Destination timeout",
            SRC_TIMEOUT.code, "Source timeout"
    );

    public final Integer code;

    Status(Integer code) {
        this.code = code;
    }
}
