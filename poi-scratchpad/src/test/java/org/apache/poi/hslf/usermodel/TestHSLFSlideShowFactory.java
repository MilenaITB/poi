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

package org.apache.poi.hslf.usermodel;

import org.apache.poi.hssf.HSSFTestDataSamples;
import org.apache.poi.sl.usermodel.BaseTestSlideShowFactory;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.sl.usermodel.SlideShowFactory;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public final class TestHSLFSlideShowFactory extends BaseTestSlideShowFactory {
    @Test
    void testFactory() throws Exception {
        testFactory("pictures.ppt", "Password_Protected-hello.ppt", "hello");
    }

    @Test
    void testBug65634() throws IOException {
        File file = HSSFTestDataSamples.getSampleFile("workbook.xml");
        try (FileInputStream fis = new FileInputStream(file)) {
            try {
                SlideShow slideShow = SlideShowFactory.create(fis);
                if (slideShow != null) slideShow.close();
                fail("SlideShowFactory.create should have failed");
            } catch (IOException ie) {
                assertEquals("Can't open slideshow - unsupported file type: XML", ie.getMessage());
            }
        }
        try {
            SlideShow slideShow = SlideShowFactory.create(file);
            if (slideShow != null) slideShow.close();
            fail("SlideShowFactory.create should have failed");
        } catch (IOException ie) {
            assertEquals("Can't open slideshow - unsupported file type: XML", ie.getMessage());
        }
    }
}
