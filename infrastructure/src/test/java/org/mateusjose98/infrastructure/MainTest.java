package org.mateusjose98.infrastructure;

import org.junit.jupiter.api.Test;
import org.mateusjose98.application.UseCase;

public class MainTest {

    @Test
    public void testMain() {
        new UseCase().execute();
    }
}
