package com.vtoan1517.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class BaseDTO {
    private long id;
    private Date createdDate;
    private Date modifiedDate;
    private String createdBy;
    private String modifiedBy;
}
