package org.bill.RefactoringToGuava;

import java.util.Collection;
import java.util.List;

import static com.google.common.collect.Collections2.filter;
import static com.google.common.collect.Lists.newArrayList;
import static org.bill.RefactoringToGuava.Functional.each;
import static org.bill.RefactoringToGuava.Functions.Print;
import static org.bill.RefactoringToGuava.IntegerPredicates.IsLargerThan;

public class TrivialFilter {
    private final List<Integer> numbers;

    public TrivialFilter(Integer... numbers) {
        this.numbers = newArrayList(numbers);
    }

    public void forLoopWithIf(){
        for (Integer number : numbers){
            if (number > 2){
                System.out.println(number);
            }
        }
    }

    public void simpleFilter(){
        Collection<Integer> numbersLargerThanTwo = filter(numbers, new IsLargerThan(2));
        each(numbersLargerThanTwo, new Print());
    }
}
