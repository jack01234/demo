package com.example.demo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 标识枚举
 * <p>
 *     1.TRUE:是
 *     2.FALSE:否
 * </p>
 *
 * @author thank_wd
 * @version 5.0
 * @date 2016/5/12
 */
@Getter
@AllArgsConstructor
public enum FlagEnum {

    /**
     * 是
     */
    TRUE("TRUE","1", "是"),

    /**
     * 否
     */
    FALSE("FALSE","0", "否");

    private final String code;

    private final String key;

    private final String value;

    /**
     * 根据Code获取Value
     *
     * @param code  键
     * @return      值
     */
    public static String explain(String code) {
        for (FlagEnum flagEnum : FlagEnum.values()) {
            if (flagEnum.getCode().equals(code)) {
                return flagEnum.getValue();
            }
        }
        return null;
    }

}
