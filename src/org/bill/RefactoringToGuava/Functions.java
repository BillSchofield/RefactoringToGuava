package org.bill.RefactoringToGuava;

import com.google.common.base.Function;

public class Functions {
    public static class Print <T> implements Function<T, T> {
        public T apply(T value) {
            System.out.println(value.toString());
            return value;
        }
    }
}
