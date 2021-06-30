package guru.springframework.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* Command class is just a POJO class and not a component. It is used to bind data from and to front end  */

@NoArgsConstructor
@Getter
@Setter
public class CategoryCommand {
    private Long id;
    private String categoryName;
}
