package org.mateusjose98.infrastructure;

import org.mateusjose98.application.UseCase;

public class Main {
    public static void main(String[] args) {
        new UseCase().execute();
        System.out.println("Hello world!");
    }
}