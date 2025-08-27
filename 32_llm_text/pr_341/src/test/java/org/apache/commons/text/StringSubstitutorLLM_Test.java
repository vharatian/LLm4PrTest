package org.apache.commons.text;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StringSubstitutorLLM_Test {

    @Test
    public void testCreateInterpolatorDefaultLookups() {
        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();
        String text = interpolator.replace(
                "Base64 Decoder:        ${base64Decoder:SGVsbG9Xb3JsZCE=}\n"
                + "Base64 Encoder:        ${base64Encoder:HelloWorld!}\n"
                + "Java Constant:         ${const:java.awt.event.KeyEvent.VK_ESCAPE}\n"
                + "Date:                  ${date:yyyy-MM-dd}\n"
                + "Environment Variable:  ${env:USERNAME}\n"
                + "File Content:          ${file:UTF-8:src/test/resources/document.properties}\n"
                + "Java:                  ${java:version}\n"
                + "Localhost:             ${localhost:canonical-name}\n"
                + "Properties File:       ${properties:src/test/resources/document.properties::mykey}\n"
                + "Resource Bundle:       ${resourceBundle:org.apache.commons.text.example.testResourceBundleLookup:mykey}\n"
                + "System Property:       ${sys:user.dir}\n"
                + "URL Decoder:           ${urlDecoder:Hello%20World%21}\n"
                + "URL Encoder:           ${urlEncoder:Hello World!}\n"
                + "XML XPath:             ${xml:src/test/resources/document.xml:/root/path/to/node}\n");

        assertThat(text).contains("Base64 Decoder:        HelloWorld!");
        assertThat(text).contains("Base64 Encoder:        SGVsbG9Xb3JsZCE=");
        assertThat(text).contains("Java Constant:         27");
        assertThat(text).contains("Date:                  ");
        assertThat(text).contains("Environment Variable:  ");
        assertThat(text).contains("File Content:          ");
        assertThat(text).contains("Java:                  ");
        assertThat(text).contains("Localhost:             ");
        assertThat(text).contains("Properties File:       ");
        assertThat(text).contains("Resource Bundle:       ");
        assertThat(text).contains("System Property:       ");
        assertThat(text).contains("URL Decoder:           Hello World!");
        assertThat(text).contains("URL Encoder:           Hello%20World%21");
        assertThat(text).contains("XML XPath:             ");
    }

    @Test
    public void testCreateInterpolatorDocumentation() {
        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();
        String text = interpolator.replace(
                "OS name: ${sys:os.name}, user: ${env:USER}");

        assertThat(text).contains("OS name: ");
        assertThat(text).contains("user: ");
    }

    @Test
    public void testDefaultLookupsTable() {
        StringSubstitutor interpolator = StringSubstitutor.createInterpolator();
        assertEquals("SGVsbG9Xb3JsZCE=", interpolator.replace("${base64Encoder:HelloWorld!}"));
        assertEquals("HelloWorld!", interpolator.replace("${base64Decoder:SGVsbG9Xb3JsZCE=}"));
        assertEquals("27", interpolator.replace("${const:java.awt.event.KeyEvent.VK_ESCAPE}"));
        assertEquals("Hello World!", interpolator.replace("${urlDecoder:Hello%20World%21}"));
        assertEquals("Hello%20World%21", interpolator.replace("${urlEncoder:Hello World!}"));
    }
}