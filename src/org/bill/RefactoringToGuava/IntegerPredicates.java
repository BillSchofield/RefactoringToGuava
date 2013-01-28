package org.bill.RefactoringToGuava;

import com.google.common.base.Predicate;

public class IntegerPredicates {
    public static class IsLargerThan implements Predicate<Integer> {
        private final Integer limit;

        public IsLargerThan(Integer limit) {
            this.limit = limit;
        }
        public boolean apply(Integer integer) {
            return integer > limit;
        }
    }
}
