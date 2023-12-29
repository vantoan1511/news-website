package com.vtoan1517.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class Model<T> {

    private long[] ids;

    private int page;
    private int limit;
    private String sortBy;
    private String sortOrder;

    private long totalItems;
    private int totalPages;
    private T data;
}
