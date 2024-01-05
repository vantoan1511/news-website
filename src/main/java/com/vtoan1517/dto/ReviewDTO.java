package com.vtoan1517.dto;

import com.vtoan1517.entity.Article;
import com.vtoan1517.entity.Review;
import com.vtoan1517.entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReviewDTO extends BaseDTO {
    private String text;
    private Article article;
    private User user;
    private Review parent;
    private List<ReviewDTO> children = new ArrayList<>();
}
