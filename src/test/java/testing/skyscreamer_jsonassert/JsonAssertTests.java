package testing.skyscreamer_jsonassert;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonAssertTests {
    @Test
    void testJSONAssert() throws JSONException {
        String actual = "{id:123,name:\"John\"}";
        JSONAssert.assertEquals("{id:123,name:\"John\"}", actual, JSONCompareMode.LENIENT);

    }
}
