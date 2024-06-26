package com.HotUdon.util;

public class AddressParser {

    public static String addressExtraction(String fullAddress) {
        String[] parts = fullAddress.split(" ");
        if (parts.length >= 2) {
            return String.join(" ", parts[0], parts[1]);
            // 시와 구를 추출
        }
        return fullAddress; // 분할할 수 없는 주소는 전체 주소 반환
    }

}
