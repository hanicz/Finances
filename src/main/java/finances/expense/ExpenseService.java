package finances.expense;

import finances.model.Expense;
import finances.model.User;
import finances.repository.ExpenseRepository;
import finances.repository.UserRepository;
import finances.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    public List<Expense> getExpensesByUser(String userEmail) {
        return this.expenseRepository.findByUserEmail(userEmail);
    }

    public Expense createExpense(String userEmail, Expense expense) {
        User user = this.userService.getUserByEmail(userEmail);
        expense.setUser(user);

        return this.expenseRepository.save(expense);
    }
}
