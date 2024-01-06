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
    @Range(min = 1L, message = "Id bài viết không hợp lệ")
    private long articleId;
    @Range(min = 1L, message = "Id người dùng không hợp lệ")
    private long userId;
    private long parentId;
    private List<ReviewDTO> children = new ArrayList<>();
}
