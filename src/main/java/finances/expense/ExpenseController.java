package finances.expense;

import finances.model.Expense;
import finances.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
