package finances.repository;

import finances.model.Expense;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {
    List<Expense> findByUserEmail(String userEmail);
    Optional<Expense> findByIdAndUserEmail(Long id, String userEmail);
}
