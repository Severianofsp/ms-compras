package com.ibm.purchases.util;

public class DocumentUtil {

    public static Integer getOnlyNumbers(String document) {
        return Integer
                .parseInt(document
                        .replaceAll("\\D", ""));
    }
}
