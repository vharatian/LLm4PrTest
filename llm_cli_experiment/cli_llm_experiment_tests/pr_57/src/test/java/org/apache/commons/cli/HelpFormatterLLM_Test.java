package org.apache.commons.cli;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class HelpFormatterLLM_Test {

    private static final String EOL = System.getProperty("line.separator");

    @Test
    public void testLicenseHeaderFormat() {
        String expectedHeader = "Licensed to the Apache Software Foundation (ASF) under one or more" + EOL +
                                "contributor license agreements.  See the NOTICE file distributed with" + EOL +
                                "this work for additional information regarding copyright ownership." + EOL +
                                "The ASF licenses this file to You under the Apache License, Version 2.0" + EOL +
                                "(the \"License\"); you may not use this file except in compliance with" + EOL +
                                "the License.  You may obtain a copy of the License at" + EOL +
                                EOL +
                                "    http://www.apache.org/licenses/LICENSE-2.0" + EOL +
                                EOL +
                                "Unless required by applicable law or agreed to in writing, software" + EOL +
                                "distributed under the License is distributed on an \"AS IS\" BASIS," + EOL +
                                "WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied." + EOL +
                                "See the License for the specific language governing permissions and" + EOL +
                                "limitations under the License.";

        String actualHeader = HelpFormatter.class.getPackage().getImplementationTitle();
        assertEquals("License header format", expectedHeader, actualHeader);
    }
}