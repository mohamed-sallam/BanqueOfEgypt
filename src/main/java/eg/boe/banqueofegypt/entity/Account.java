package eg.boe.banqueofegypt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Account")
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Pattern(regexp = "^[0-9]*$", message = "Id must be a number")
    private Long id;

    @Column(name = "name")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @Column(name = "address")
//    @Pattern(regexp = "^[A-Za-z0-9]+-[A-Za-z0-9]+-[A-Za-z0-9]+$", message = "Invalid address")
    private String address;

    @Column(name = "swift_code")
    @NotNull(message = "Swift code is required")
//    @Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$", message = "Invalid swift code")
    private String swiftCode;

    @Column(name = "balance")
    @NotBlank(message = "Balance is required")
    @Pattern(regexp = "^[0-9]*$", message = "Balance must be a number")
    @PositiveOrZero(message = "Balance must be positive or zero")
    private String balance;
    @Column(name = "url")
    private String url;
}
