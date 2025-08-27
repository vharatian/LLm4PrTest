package org.apache.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PathUtilsLLM_Test {

    @Test
    public void testRelativize() throws IOException {
        // Setup
        Path parent = Paths.get("/parent");
        List<Path> paths = Arrays.asList(
            Paths.get("/parent/child1"),
            Paths.get("/parent/child2"),
            Paths.get("/parent/child3")
        );

        // Execute
        List<Path> relativePaths = PathUtils.relativize(paths, parent, true, null);

        // Verify
        assertEquals(3, relativePaths.size());
        assertEquals(Paths.get("child1"), relativePaths.get(0));
        assertEquals(Paths.get("child2"), relativePaths.get(1));
        assertEquals(Paths.get("child3"), relativePaths.get(2));
    }
}