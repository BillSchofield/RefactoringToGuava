package org.bill.RefactoringToGuava;

public class Main {

    public static void main(String[] args) {
        TrivialFilter filterExample = new TrivialFilter(1, 2, 3, 4, 5);
        filterExample.forLoopWithIf();
        filterExample.simpleFilter();
    }
}
