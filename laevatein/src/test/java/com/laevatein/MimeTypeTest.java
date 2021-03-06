package com.laevatein;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author keishin.yokomaku
 * @since 2014/03/31
 */
@RunWith(RobolectricTestRunner.class)
public class MimeTypeTest {
    @Test
    public void oneOf() throws Exception {
        {
            Set<MimeType> set = MimeType.of(MimeType.JPEG);
            assertEquals(1, set.size());
            assertTrue(set.contains(MimeType.JPEG));
            assertFalse(set.contains(MimeType.PNG));
            assertFalse(set.contains(MimeType.GIF));
        }

        {
            Set<MimeType> set = MimeType.of(MimeType.PNG);
            assertEquals(1, set.size());
            assertFalse(set.contains(MimeType.JPEG));
            assertTrue(set.contains(MimeType.PNG));
            assertFalse(set.contains(MimeType.GIF));
        }

        {
            Set<MimeType> set = MimeType.of(MimeType.GIF);
            assertEquals(1, set.size());
            assertFalse(set.contains(MimeType.JPEG));
            assertFalse(set.contains(MimeType.PNG));
            assertTrue(set.contains(MimeType.GIF));
        }
    }

    @Test
    public void someOf() throws Exception {
        {
            Set<MimeType> set = MimeType.of(MimeType.JPEG, MimeType.PNG);
            assertEquals(2, set.size());
            assertTrue(set.contains(MimeType.JPEG));
            assertTrue(set.contains(MimeType.PNG));
            assertFalse(set.contains(MimeType.GIF));
        }

        {
            Set<MimeType> set = MimeType.of(MimeType.JPEG, MimeType.GIF);
            assertEquals(2, set.size());
            assertTrue(set.contains(MimeType.JPEG));
            assertFalse(set.contains(MimeType.PNG));
            assertTrue(set.contains(MimeType.GIF));
        }

        {
            Set<MimeType> set = MimeType.of(MimeType.PNG, MimeType.GIF);
            assertEquals(2, set.size());
            assertFalse(set.contains(MimeType.JPEG));
            assertTrue(set.contains(MimeType.PNG));
            assertTrue(set.contains(MimeType.GIF));
        }

        {
            Set<MimeType> set = MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.GIF);
            assertEquals(3, set.size());
            assertTrue(set.contains(MimeType.JPEG));
            assertTrue(set.contains(MimeType.PNG));
            assertTrue(set.contains(MimeType.GIF));
        }
    }

    @Test
    public void allOf() throws Exception {
        Set<MimeType> set = MimeType.allOf();
        assertEquals(3, set.size());
        assertTrue(set.contains(MimeType.JPEG));
        assertTrue(set.contains(MimeType.PNG));
        assertTrue(set.contains(MimeType.GIF));
    }

    @Test
    public void stringify() throws Exception {
        assertEquals("image/jpeg", MimeType.JPEG.toString());
        assertEquals("image/png", MimeType.PNG.toString());
        assertEquals("image/gif", MimeType.GIF.toString());
    }
}
