package finances.expense;

import finances.expense.dto.CreateExpenseDTO;
import finances.expense.dto.UpdateExpenseDTO;
import finances.model.Expense;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/expense", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getExpensesByUser(@AuthenticationPrincipal UserDetails userDetails) {
        log.trace("Enter");
        return new ResponseEntity<>(this.expenseService.getExpensesByUser(userDetails.getUsername()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody CreateExpenseDTO expense) {
        log.trace("Enter");
        return new ResponseEntity<>(this.expenseService.createExpense(userDetails.getUsername(), expense),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Expense> updateExpense(@AuthenticationPrincipal UserDetails userDetails, @Valid @RequestBody UpdateExpenseDTO expense) {
        log.trace("Enter");
        return new ResponseEntity<>(this.expenseService.updateExpense(userDetails.getUsername(), expense),HttpStatus.OK);
    }
}
