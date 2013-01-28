package org.bill.RefactoringToGuava;

import com.google.common.base.Function;

import java.util.Collection;

public class Functional {
    public static <T> void each(Collection<T> collection, Function<T, T> function){
        for (T instance : collection){
            function.apply(instance);
        }
    }
}
