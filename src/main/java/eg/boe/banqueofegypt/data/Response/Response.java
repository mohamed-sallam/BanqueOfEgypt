package eg.boe.banqueofegypt.data.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    private Integer code;
    private String message;
    private T data;
}
