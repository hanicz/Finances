package finances.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finances.util.Generated;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Data
@Builder
@Slf4j
@ToString
@Table(name = "FINANCE_EXPENSE")
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date expenseDate;
    private Long amount;
    private String comment;
    private String type;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;
}
