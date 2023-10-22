package com.cedarsoftware.util.io;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 * Test cases for JsonReader / JsonWriter
 *
 * @author John DeRegnaucourt (jdereg@gmail.com)
 *         <br>
 *         Copyright (c) Cedar Software LLC
 *         <br><br>
 *         Licensed under the Apache License, Version 2.0 (the "License")
 *         you may not use this file except in compliance with the License.
 *         You may obtain a copy of the License at
 *         <br><br>
 *         http://www.apache.org/licenses/LICENSE-2.0
 *         <br><br>
 *         Unless required by applicable law or agreed to in writing, software
 *         distributed under the License is distributed on an "AS IS" BASIS,
 *         WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *         See the License for the specific language governing permissions and
 *         limitations under the License.
 */
class BigJsonTest
{
    @Test
    void testBigJsonToMaps()
    {
        String json = TestUtil.fetchResource("big5D.json");
        Map<String, Object> args = new HashMap<>();
        args.put(JsonReader.USE_MAPS, true);
        Map map = JsonReader.jsonToJava(json, args);
        assertEquals("big5D", map.get("ncube"));
        assertEquals(0L, map.get("defaultCellValue"));
        assertNotNull(map.get("axes"));
        assertNotNull(map.get("cells"));
    }

    @Test
    void testJsonIoVersusGson()
    {
        String json = TestUtil.fetchResource("big5D.json");

        Gson gson = new Gson();
        long start = System.nanoTime();
        gson.fromJson(json, Object.class);
        long stop = System.nanoTime();
        TestUtil.printLine("gson: " + ((stop - start) / 1000000L));

        start = System.nanoTime();
        JsonReader.jsonToJava(json);
        stop = System.nanoTime();
        TestUtil.printLine("json-io: " + ((stop - start) / 1000000L));
    }

    @Test
    void testGsonOnHugeFile()
    {
        String json = TestUtil.fetchResource("big5D.json");

        Gson gson = new Gson();
        long start = System.nanoTime();
        gson.fromJson(json, Object.class);
        long stop = System.nanoTime();
        TestUtil.printLine("gson: " + ((stop - start) / 1000000L));

        int i=0;
        while (i++ < 50)
        {
            gson = new Gson();
            start = System.nanoTime();
            gson.fromJson(json, Object.class);
            stop = System.nanoTime();
            TestUtil.printLine("gson: " + ((stop - start) / 1000000L));
        }
    }

    @Test
    void testJsonOnHugeFile()
    {
        String json = TestUtil.fetchResource("big5D.json");

        long start = System.nanoTime();
        Map<String, Object> args = new HashMap<>();
        args.put(JsonReader.USE_MAPS, true);

        JsonReader.jsonToJava(json, args);
        long stop = System.nanoTime();
        TestUtil.printLine("json-io: " + ((stop - start) / 1000000L));

        int i=0;
        while (i++ < 50)
        {
            start = System.nanoTime();
            JsonReader.jsonToJava(json, args);
            stop = System.nanoTime();
            TestUtil.printLine("json-io: " + ((stop - start) / 1000000L));
        }
    }
}