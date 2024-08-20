package eg.boe.banqueofegypt.entity;

import java.util.Map;

public enum Status {
    PENDING(0L), SUCCESS(200L),
    INSUFFICIENT_FUNDS(80090L), INVALID_DATA(99993L),
    DEST_TIMEOUT(80091L), SRC_TIMEOUT(80092L),
    FAILED(404L);

    public final static Map<Long, String> codeMessageMapper = Map.of(
            PENDING.code, "Pending",
            SUCCESS.code, "Success",
            INSUFFICIENT_FUNDS.code, "Insufficient funds",
            INVALID_DATA.code, "Invalid data",
            DEST_TIMEOUT.code, "Destination timeout",
            SRC_TIMEOUT.code, "Source timeout"
    );

    public final Long code;

    Status(Long code) {
        this.code = code;
    }
}
