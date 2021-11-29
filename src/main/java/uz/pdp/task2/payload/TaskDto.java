package uz.pdp.task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskDto {
    @NotNull
    private String name;

    @NotNull
    private String text;

    @NotNull
    private boolean hasStar;

    @NotNull
    private String solution;

    @NotNull
    private String hint;

    @NotNull
    private String method;

    @NotNull
    private int categoryId;
}
