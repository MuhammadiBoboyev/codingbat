package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Category;
import uz.pdp.task2.entity.Language;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.CategoryDto;
import uz.pdp.task2.repository.CategoryRepository;
import uz.pdp.task2.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public ApiResponse addCategory(CategoryDto categoryDto) {
        List<Language> languageList = languageRepository.findAllById(categoryDto.getLanguagesId());
        if (languageList.size() != categoryDto.getLanguagesId().size())
            return new ApiResponse("language not found", false);
        categoryRepository.save(new Category(categoryDto.getName(), categoryDto.getNumberOfStars(), categoryDto.isSuccess(), languageList));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editCategory(int id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("category not found", false);
        List<Language> languageList = languageRepository.findAllById(categoryDto.getLanguagesId());
        if (languageList.size() != categoryDto.getLanguagesId().size())
            return new ApiResponse("language not found", false);
        Category category = optionalCategory.get();
        category.setName(category.getName());
        category.setSuccess(categoryDto.isSuccess());
        category.setNumberOfStars(categoryDto.getNumberOfStars());
        category.setLanguages(languageList);
        categoryRepository.save(category);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteCategory(int id) {
        if (!categoryRepository.existsById(id))
            return new ApiResponse("category not found", false);
        categoryRepository.deleteById(id);
        return new ApiResponse("success full deleted", true);
    }
}
