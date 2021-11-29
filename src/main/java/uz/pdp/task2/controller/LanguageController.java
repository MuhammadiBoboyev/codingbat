package uz.pdp.task2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task2.entity.Language;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.LanguageDto;
import uz.pdp.task2.service.LanguageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    @Autowired
    LanguageService languageService;

    @GetMapping
    public ResponseEntity<List<Language>> getLanguage() {
        return ResponseEntity.status(HttpStatus.OK).body(languageService.getLanguage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable int id) {
        Language language = languageService.getLanguageById(id);
        return ResponseEntity.status(language == null ? HttpStatus.NOT_FOUND : HttpStatus.OK).body(language);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addLanguage(@Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editLanguage(@PathVariable int id, @Valid @RequestBody LanguageDto languageDto) {
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.NOT_FOUND).body(apiResponse);
    }
}
