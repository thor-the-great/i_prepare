/**
 * Copyright (c) 2019 Ariba, Inc. All rights reserved. Patents pending.
 */
package stream_api;

import java.util.Collections;
import java.util.List;

public class Test2 {

    public static void main(String[] args) {
        List<String> col = List.of("Test1", "Test2");

        col = Collections.EMPTY_LIST;

        String res = col.stream()
            .findFirst()
            .orElseThrow(RuntimeException::new);

        System.out.println(res);
    }
}
