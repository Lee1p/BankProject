package com.bank.util;

public class InputValidator {
    public static boolean isValidOption(String input, int min, int max) {
        return input.matches("\\d+") && Integer.parseInt(input) >= min && Integer.parseInt(input) <= max;
    }
}


// 선택: 숫자가아닌 다른 입력문이 들어올경우 유효성을 처리하는 유틸리티 클래스
// 재사용성↑ 입력 검증이 중복될경우 사용한다.