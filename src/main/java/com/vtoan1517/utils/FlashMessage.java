package com.vtoan1517.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FlashMessage {

    public static final String SUCCESS = "success";
    public static final String DANGER = "danger";
    public static final String WARNING = "warning";
    public static final String INFO = "info";

    private String type;
    private String message;
}
