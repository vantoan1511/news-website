package com.vtoan1517.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO extends BaseDTO {

    @NotBlank(message = "Tiêu đề bài viết không được để trống")
    private String title = "Bài viết chưa có tiêu đề";
    private String slug;
    private String description;
    private String content;
    private String thumbnailUrl;
    private boolean featured = false;
    private long traffic = 0;
    @NotBlank(message = "Truy cập bài viết không được rỗng")
    private String accessCode;
    @NotBlank(message = "Chuyên mục bài viết không được rỗng")
    private String categoryCode;
    private String categoryName;
    @NotBlank(message = "Trạng thái bài viết không được rỗng")
    private String statusCode = "draft";
    private String statusName = "Bản nháp";

}
