package uz.pdp.task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDto {
    @NotNull
    private String name;

    @NotNull
    private Integer numberOfStars;

    @NotNull
    private boolean isSuccess;

    @NotNull
    private List<Integer> languagesId;
}
