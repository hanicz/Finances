package finances.model;

import finances.util.Generated;
import jakarta.persistence.*;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Entity
@Getter
@Setter
@Slf4j
@ToString
@Table(name = "FINANCE_INVOICE")
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date invoiceDate;
    private Long amount;
    private String comment;
}
