package org.mateusjose98.application;



public abstract class UseCase<IN, OUT> {
    public abstract OUT execute(IN input);
}