package org.apache.commons.codec.language;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoderAbstractTest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Caverphone2LLM_Test extends StringEncoderAbstractTest<Caverphone2> {

    @Override
    protected Caverphone2 createStringEncoder() {
        return new Caverphone2();
    }

    @Test
    public void testCaverphoneRevisitedCommonCodeAT11111111() throws EncoderException {
        this.checkEncodingVariations("AT11111111", new String[]{
            "add",
            "aid",
            "at",
            "art",
            "eat",
            "earth",
            "head",
            "hit",
            "hot",
            "hold",
            "hard",
            "heart",
            "it",
            "out",
            "old"});
    }

    @Test
    public void testCaverphoneRevisitedExamples() throws EncoderException {
        final String[][] data = {{"Stevenson", "STFNSN1111"}, {"Peter", "PTA1111111"}};
        this.checkEncodings(data);
    }

    @Test
    public void testCaverphoneRevisitedRandomNameKLN1111111() throws EncoderException {
        this.checkEncodingVariations("KLN1111111", new String[]{
            "Cailean",
            "Calan",
            "Calen",
            "Callahan",
            "Callan",
            "Callean",
            "Carleen",
            "Carlen",
            "Carlene",
            "Carlin",
            "Carline",
            "Carlyn",
            "Carlynn",
            "Carlynne",
            "Charlean",
            "Charleen",
            "Charlene",
            "Charline",
            "Cherlyn",
            "Chirlin",
            "Clein",
            "Cleon",
            "Cline",
            "Cohleen",
            "Colan",
            "Coleen",
            "Colene",
            "Colin",
            "Colleen",
            "Collen",
            "Collin",
            "Colline",
            "Colon",
            "Cullan",
            "Cullen",
            "Cullin",
            "Gaelan",
            "Galan",
            "Galen",
            "Garlan",
            "Garlen",
            "Gaulin",
            "Gayleen",
            "Gaylene",
            "Giliane",
            "Gillan",
            "Gillian",
            "Glen",
            "Glenn",
            "Glyn",
            "Glynn",
            "Gollin",
            "Gorlin",
            "Kalin",
            "Karlan",
            "Karleen",
            "Karlen",
            "Karlene",
            "Karlin",
            "Karlyn",
            "Kaylyn",
            "Keelin",
            "Kellen",
            "Kellene",
            "Kellyann",
            "Kellyn",
            "Khalin",
            "Kilan",
            "Kilian",
            "Killen",
            "Killian",
            "Killion",
            "Klein",
            "Kleon",
            "Kline",
            "Koerlin",
            "Kylen",
            "Kylynn",
            "Quillan",
            "Quillon",
            "Qulllon",
            "Xylon"});
    }

    @Test
    public void testCaverphoneRevisitedRandomNameTN11111111() throws EncoderException {
        this.checkEncodingVariations("TN11111111", new String[]{
            "Dan",
            "Dane",
            "Dann",
            "Darn",
            "Daune",
            "Dawn",
            "Ddene",
            "Dean",
            "Deane",
            "Deanne",
            "DeeAnn",
            "Deeann",
            "Deeanne",
            "Deeyn",
            "Den",
            "Dene",
            "Denn",
            "Deonne",
            "Diahann",
            "Dian",
            "Diane",
            "Diann",
            "Dianne",
            "Diannne",
            "Dine",
            "Dion",
            "Dione",
            "Dionne",
            "Doane",
            "Doehne",
            "Don",
            "Donn",
            "Doone",
            "Dorn",
            "Down",
            "Downe",
            "Duane",
            "Dun",
            "Dunn",
            "Duyne",
            "Dyan",
            "Dyane",
            "Dyann",
            "Dyanne",
            "Dyun",
            "Tan",
            "Tann",
            "Teahan",
            "Ten",
            "Tenn",
            "Terhune",
            "Thain",
            "Thaine",
            "Thane",
            "Thanh",
            "Thayne",
            "Theone",
            "Thin",
            "Thorn",
            "Thorne",
            "Thun",
            "Thynne",
            "Tien",
            "Tine",
            "Tjon",
            "Town",
            "Towne",
            "Turne",
            "Tyne"});
    }

    @Test
    public void testCaverphoneRevisitedRandomNameTTA1111111() throws EncoderException {
        this.checkEncodingVariations("TTA1111111", new String[]{
            "Darda",
            "Datha",
            "Dedie",
            "Deedee",
            "Deerdre",
            "Deidre",
            "Deirdre",
            "Detta",
            "Didi",
            "Didier",
            "Dido",
            "Dierdre",
            "Dieter",
            "Dita",
            "Ditter",
            "Dodi",
            "Dodie",
            "Dody",
            "Doherty",
            "Dorthea",
            "Dorthy",
            "Doti",
            "Dotti",
            "Dottie",
            "Dotty",
            "Doty",
            "Doughty",
            "Douty",
            "Dowdell",
            "Duthie",
            "Tada",
            "Taddeo",
            "Tadeo",
            "Tadio",
            "Tati",
            "Teador",
            "Tedda",
            "Tedder",
            "Teddi",
            "Teddie",
            "Teddy",
            "Tedi",
            "Tedie",
            "Teeter",
            "Teodoor",
            "Teodor",
            "Terti",
            "Theda",
            "Theodor",
            "Theodore",
            "Theta",
            "Thilda",
            "Thordia",
            "Tilda",
            "Tildi",
            "Tildie",
            "Tildy",
            "Tita",
            "Tito",
            "Tjader",
            "Toddie",
            "Toddy",
            "Torto",
            "Tuddor",
            "Tudor",
            "Turtle",
            "Tuttle",
            "Tutto"});
    }

    @Test
    public void testCaverphoneRevisitedRandomWords() throws EncoderException {
        this.checkEncodingVariations("RTA1111111", new String[]{"rather", "ready", "writer"});
        this.checkEncoding("SSA1111111", "social");
        this.checkEncodingVariations("APA1111111", new String[]{"able", "appear"});
    }

    @Test
    public void testEndMb() throws EncoderException {
        final String[][] data = {{"mb", "M111111111"}, {"mbmb", "MPM1111111"}};
        this.checkEncodings(data);
    }

    @Test
    public void testIsCaverphoneEquals() throws EncoderException {
        final Caverphone2 caverphone = new Caverphone2();
        assertFalse(caverphone.isEncodeEqual("Peter", "Stevenson"), "Caverphone encodings should not be equal");
        assertTrue(caverphone.isEncodeEqual("Peter", "Peady"), "Caverphone encodings should be equal");
    }

    @Test
    public void testSpecificationExamples() throws EncoderException {
        final String[][] data = {
            {"Peter", "PTA1111111"},
            {"ready", "RTA1111111"},
            {"social", "SSA1111111"},
            {"able", "APA1111111"},
            {"Tedder", "TTA1111111"},
            {"Karleen", "KLN1111111"},
            {"Dyun", "TN11111111"}};
        this.checkEncodings(data);
    }
}