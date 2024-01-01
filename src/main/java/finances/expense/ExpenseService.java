package finances.expense;

import finances.exception.ItemNotFoundException;
import finances.expense.dto.CreateExpenseDTO;
import finances.expense.dto.UpdateExpenseDTO;
import finances.model.Expense;
import finances.model.User;
import finances.repository.ExpenseRepository;
import finances.user.UserService;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Expense createExpense(String userEmail, CreateExpenseDTO expenseDTO) {
        User user = this.userService.getUserByEmail(userEmail);
        Expense expense = Expense.builder()
                .expenseDate(expenseDTO.expenseDate())
                .amount(expenseDTO.amount())
                .comment(expenseDTO.comment())
                .type(expenseDTO.type())
                .user(user)
                .build();

        return this.expenseRepository.save(expense);
    }

    @Transactional
    public Expense updateExpense(String userEmail, UpdateExpenseDTO expenseDTO) {
        Expense expense = this.expenseRepository.findByIdAndUserEmail(expenseDTO.id(), userEmail).orElseThrow(() -> new ItemNotFoundException(String.format("Expense not found with id: %s", expenseDTO.id())));

        expense.setExpenseDate(expenseDTO.expenseDate());
        expense.setType(expenseDTO.type());
        expense.setComment(expenseDTO.comment());
        expense.setAmount(expenseDTO.amount());

        return expense;
    }
}
