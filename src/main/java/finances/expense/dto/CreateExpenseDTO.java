package finances.expense.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record CreateExpenseDTO(
        @NotNull(message =  "Date cannot be null")
        Date expenseDate,
        @NotNull(message =  "Amount cannot be null")
        Long amount,
        String comment,
        @NotBlank(message =  "Type cannot be blank")
        String type
) {}
