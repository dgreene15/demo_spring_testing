package podam;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class TestPojo {
    int id;
    String description;
    List<String> items;
}
