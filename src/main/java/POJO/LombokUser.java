package POJO;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LombokUser {
    private int id;
    private String name;
    private int age;
}
