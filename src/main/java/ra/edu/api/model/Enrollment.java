package ra.edu.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Enrollment {
    private Integer id;
    private String studentName;
    private Integer courseId;
}
