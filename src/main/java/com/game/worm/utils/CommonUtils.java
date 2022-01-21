package com.game.worm.utils;

import java.util.Arrays;

public final class CommonUtils {
    public static String getStackTraceElements(Exception e){
        StackTraceElement[] stackElements;
        StringBuilder result = new StringBuilder();
        if(e.getCause() == null){
            stackElements = e.getStackTrace();
        } else {
            stackElements = e.getCause().getStackTrace();
        }

        if (stackElements == null || stackElements.length == 0) {
            return null;
        }

        for (StackTraceElement stackElement : stackElements) {
            result.append(stackElement);
            result.append('\n');
        }

        return result.toString();
    }
}
