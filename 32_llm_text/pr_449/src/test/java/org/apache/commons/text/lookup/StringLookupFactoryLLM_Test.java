package org.apache.commons.text.lookup;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.xml.XMLConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringLookupFactoryLLM_Test {

    @Test
    public void testAddDefaultStringLookupsMap() {
        final Map<String, StringLookup> stringLookupMap = new HashMap<>();
        StringLookupFactory.INSTANCE.addDefaultStringLookups(stringLookupMap);
        assertDefaultKeys(stringLookupMap);
    }

    @Test
    public void testAddDefaultStringLookupsNull() {
        StringLookupFactory.INSTANCE.addDefaultStringLookups(null);
    }

    @Test
    public void testDefaultStringLookupsHolder_allLookups() {
        final Properties props = new Properties();
        props.setProperty(StringLookupFactory.DEFAULT_STRING_LOOKUPS_PROPERTY,
                "BASE64_DECODER BASE64_ENCODER const, date, dns, environment "
                        + "file ,java, local_host properties, resource_bundle,script,system_properties "
                        + "url url_decoder , url_encoder, xml, xml_decoder, xml_encoder");
        checkDefaultStringLookupsHolder(props,
                "base64",
                StringLookupFactory.KEY_BASE64_DECODER,
                StringLookupFactory.KEY_BASE64_ENCODER,
                StringLookupFactory.KEY_CONST,
                StringLookupFactory.KEY_DATE,
                StringLookupFactory.KEY_ENV,
                StringLookupFactory.KEY_FILE,
                StringLookupFactory.KEY_JAVA,
                StringLookupFactory.KEY_LOCALHOST,
                StringLookupFactory.KEY_PROPERTIES,
                StringLookupFactory.KEY_RESOURCE_BUNDLE,
                StringLookupFactory.KEY_SYS,
                StringLookupFactory.KEY_URL_DECODER,
                StringLookupFactory.KEY_URL_ENCODER,
                StringLookupFactory.KEY_XML,
                StringLookupFactory.KEY_XML_DECODER,
                StringLookupFactory.KEY_XML_ENCODER,
                StringLookupFactory.KEY_DNS,
                StringLookupFactory.KEY_URL,
                StringLookupFactory.KEY_SCRIPT);
    }

    @Test
    public void testSingletons() {
        final StringLookupFactory stringLookupFactory = StringLookupFactory.INSTANCE;
        Assertions.assertSame(XmlDecoderStringLookup.INSTANCE, stringLookupFactory.xmlDecoderStringLookup());
        Assertions.assertSame(XmlEncoderStringLookup.INSTANCE, stringLookupFactory.xmlEncoderStringLookup());
    }

    @Test
    public void testXmlDecoderStringLookup() {
        final StringLookupFactory stringLookupFactory = StringLookupFactory.INSTANCE;
        Assertions.assertEquals("<element>", stringLookupFactory.xmlDecoderStringLookup().lookup("&lt;element&gt;"));
    }

    @Test
    public void testXmlEncoderStringLookup() {
        final StringLookupFactory stringLookupFactory = StringLookupFactory.INSTANCE;
        Assertions.assertEquals("&lt;element&gt;", stringLookupFactory.xmlEncoderStringLookup().lookup("<element>"));
    }

    private static void assertDefaultKeys(final Map<String, StringLookup> stringLookupMap) {
        assertMappedLookups(stringLookupMap,
                "base64",
                StringLookupFactory.KEY_BASE64_DECODER,
                StringLookupFactory.KEY_BASE64_ENCODER,
                StringLookupFactory.KEY_CONST,
                StringLookupFactory.KEY_DATE,
                StringLookupFactory.KEY_ENV,
                StringLookupFactory.KEY_FILE,
                StringLookupFactory.KEY_JAVA,
                StringLookupFactory.KEY_LOCALHOST,
                StringLookupFactory.KEY_PROPERTIES,
                StringLookupFactory.KEY_RESOURCE_BUNDLE,
                StringLookupFactory.KEY_SYS,
                StringLookupFactory.KEY_URL_DECODER,
                StringLookupFactory.KEY_URL_ENCODER,
                StringLookupFactory.KEY_XML,
                StringLookupFactory.KEY_XML_DECODER,
                StringLookupFactory.KEY_XML_ENCODER);
    }

    private static void assertMappedLookups(final Map<String, StringLookup> lookupMap, final String... keys) {
        final Set<String> remainingKeys = new HashSet<>(lookupMap.keySet());
        for (final String key : keys) {
            final String normalizedKey = StringLookupFactory.toKey(key);
            Assertions.assertNotNull(normalizedKey, () -> "Expected map to contain string lookup for key " + key);
            remainingKeys.remove(normalizedKey);
        }
        Assertions.assertTrue(remainingKeys.isEmpty(), () -> "Unexpected keys in lookup map: " + remainingKeys);
    }

    private static void checkDefaultStringLookupsHolder(final Properties props, final String... keys) {
        final StringLookupFactory.DefaultStringLookupsHolder holder =
                new StringLookupFactory.DefaultStringLookupsHolder(props);
        final Map<String, StringLookup> lookupMap = holder.getDefaultStringLookups();
        assertMappedLookups(lookupMap, keys);
    }
}