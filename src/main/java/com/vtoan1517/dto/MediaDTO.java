package com.vtoan1517.dto;

import lombok.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MediaDTO extends BaseDTO {

    private CommonsMultipartFile file;
    private String title;
    private String url;
    private String directory;
}
