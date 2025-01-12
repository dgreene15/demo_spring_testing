package podam;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PodamTestPojo {
    int id;
    String description;
    List<String> items;
}
