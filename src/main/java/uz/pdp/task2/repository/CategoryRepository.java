package uz.pdp.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.entity.Language;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
