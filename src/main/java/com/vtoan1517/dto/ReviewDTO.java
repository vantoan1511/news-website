package com.vtoan1517.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDTO extends BaseDTO {
    @NotBlank(message = "Nội dung bình luận không được để trống")
    private String text;
    @NotBlank(message = "Alias bài viết không đươc rỗng")
    private String articleSlug;
    @NotBlank(message = "Username không được rỗng")
    private String username;
    private String userFirstName;
    private String userLastName;
    private String userAvatarUrl;
    private long rootId;
    private String parentText;
    private List<ReviewDTO> children = new ArrayList<>();
}
