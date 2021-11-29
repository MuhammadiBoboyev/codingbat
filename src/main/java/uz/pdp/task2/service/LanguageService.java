package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.Language;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.LanguageDto;
import uz.pdp.task2.payload.UserDto;
import uz.pdp.task2.repository.LanguageRepository;
import uz.pdp.task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;

    public List<Language> getLanguage() {
        return languageRepository.findAll();
    }

    public Language getLanguageById(int id) {
        return languageRepository.findById(id).orElse(null);
    }

    public ApiResponse addLanguage(LanguageDto languageDto) {
        if (languageRepository.existsByName(languageDto.getName()))
            return new ApiResponse("this name is already exists", false);
        languageRepository.save(new Language(languageDto.getName()));
        return new ApiResponse("success full added", true);
    }

    public ApiResponse editLanguage(int id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("language not found", false);
        if (languageRepository.existsByNameAndIdNot(languageDto.getName(), id))
            return new ApiResponse("this name is already exists", false);
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());
        languageRepository.save(language);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteLanguage(int id) {
        if (!languageRepository.existsById(id))
            return new ApiResponse("language not found", false);
        languageRepository.deleteById(id);
        return new ApiResponse("success full deleted", true);
    }

}
