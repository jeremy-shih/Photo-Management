package photo_tester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import photo_manager.Tag;

import static org.junit.jupiter.api.Assertions.*;


class TagTest {

    private Tag tag;

    @BeforeEach
    void setUp(){
        this.tag = new Tag( "Test" );
    }

    @AfterEach
    void tearDown(){
        this.tag = null;
    }

    @Test
    void testGetName(){
        assertEquals("@Test", this.tag.getName());
    }

    @Test
    void testToString(){
        assertEquals("@Test", this.tag.toString());
    }

    @Test
    void testEqualsWithDifferentCase(){
        assertTrue(this.tag.equals(new Tag("test")));
    }

    @Test
    void testEqualsWithSameCase(){
        assertTrue(this.tag.equals(new Tag("Test")));
    }

    @Test
    void testEqualsWithDifferentTag(){
        assertFalse(this.tag.equals(new Tag("false")));
    }

}
