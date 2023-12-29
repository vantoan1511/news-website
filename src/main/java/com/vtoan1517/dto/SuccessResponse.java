package com.vtoan1517.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
public class SuccessResponse {

    private Date timestamp;
    private int status;
    private String message;
    private Object data;

}
