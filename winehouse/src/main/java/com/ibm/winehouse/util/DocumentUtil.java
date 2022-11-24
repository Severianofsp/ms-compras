package com.ibm.winehouse.util;

public class DocumentUtil {

    public static Integer getOnlyNumbers(String document) {
        return Integer
                .parseInt(document
                        .replaceAll("\\D", ""));
    }
}
