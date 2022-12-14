/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package org.apache.poi.hsmf.datatypes;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Verifies that the Chunks class is actually setup properly and hasn't been changed in ways
 * that will break the library.
 */
public final class TestChunkData {
    @Test
    void testChunkCreate() {
        Chunk chunk;

        chunk = new StringChunk(0x0200, Types.createCustom(0x001E));
        assertEquals("__substg1.0_0200001E", chunk.getEntryName());
        assertEquals(0x0200, chunk.getChunkId());
        assertEquals(0x001E, chunk.getType().getId());

        chunk = new StringChunk("__substg1.0_", 0x0200, Types.createCustom(0x001E));
        assertEquals("__substg1.0_0200001E", chunk.getEntryName());
        assertEquals(0x0200, chunk.getChunkId());
        assertEquals(0x001E, chunk.getType().getId());

        chunk = new StringChunk("__substg1.0_", 0x0200, Types.getById(0x001E));
        assertEquals("__substg1.0_0200001E", chunk.getEntryName());
        assertEquals(0x0200, chunk.getChunkId());
        assertEquals(0x001E, chunk.getType().getId());

        /* test the lower and upper limits of the chunk ids */
        chunk = new StringChunk(0x0000, Types.createCustom(0x001E));
        assertEquals("__substg1.0_0000001E", chunk.getEntryName());

        chunk = new StringChunk(0xFFFF, Types.createCustom(0x001E));
        assertEquals("__substg1.0_FFFF001E", chunk.getEntryName());

        chunk = new StringChunk(0xFFFF, Types.createCustom(0x001F));
        assertEquals("__substg1.0_FFFF001F", chunk.getEntryName());
    }

    @Test
    void testTextBodyChunk() {
        StringChunk chunk = new StringChunk(0x1000, Types.UNICODE_STRING);
        assertEquals(chunk.getChunkId(), MAPIProperty.BODY.id);
    }

    @Test
    void testDisplayToChunk() {
        StringChunk chunk = new StringChunk(0x0E04, Types.UNICODE_STRING);
        assertEquals(chunk.getChunkId(), MAPIProperty.DISPLAY_TO.id);
    }


    @Test
    void testDisplayCCChunk() {
        StringChunk chunk = new StringChunk(0x0E03, Types.UNICODE_STRING);
        assertEquals(chunk.getChunkId(), MAPIProperty.DISPLAY_CC.id);
    }

    @Test
    void testDisplayBCCChunk() {
        StringChunk chunk = new StringChunk(0x0E02, Types.UNICODE_STRING);
        assertEquals(chunk.getChunkId(), MAPIProperty.DISPLAY_BCC.id);
    }

    @Test
    void testSubjectChunk() {
        Chunk chunk = new StringChunk(0x0037, Types.UNICODE_STRING);
        assertEquals(chunk.getChunkId(), MAPIProperty.SUBJECT.id);
    }

    @Test
    void testWritePreCalculatedProperties() throws IOException {
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            StoragePropertiesChunk storagePropertiesChunk = new StoragePropertiesChunk(null);
            PropertyValue.LongPropertyValue attachSize =
                    new PropertyValue.LongPropertyValue(MAPIProperty.ATTACH_SIZE, 6L,  new byte[0]);
            PropertyValue currentValue = new PropertyValue(MAPIProperty.DISPLAY_BCC, 6L, new byte[0]);
            attachSize.setValue(3934266);
            storagePropertiesChunk.setProperty(attachSize);
            storagePropertiesChunk.setProperty(currentValue);
            List<PropertyValue> propertyValue= storagePropertiesChunk.writePreCalculatedProperties(stream);
            assertEquals(propertyValue.size(),1);
        }
    }
}
