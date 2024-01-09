package com.vtoan1517.dto;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewDTO extends BaseDTO {
    @Size(min = 1, message = "Độ dài bình luận tối thiểu 1 ký tự")
    private String text;
    private String articleSlug;
    private String username;
    private long parentId;
    private List<ReviewDTO> children = new ArrayList<>();
}
