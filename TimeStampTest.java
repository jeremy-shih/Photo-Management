package photo_tester;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import photo_manager.TimeStamp;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TimeStampTest {

    private TimeStamp timeStamp;

    @BeforeEach
    void setUp(){
        this.timeStamp = new TimeStamp( "Test", "Test@abc" );
    }

    @AfterEach
    void tearDown(){
        this.timeStamp = null;
    }

    @Test
    void testNoChangeGetOldName(){
        assertEquals("Test", this.timeStamp.getOldName());
    }

    @Test
    void testNoChangeGetNewName(){
        assertEquals("Test@abc", this.timeStamp.getNewName());
    }

    @Test
    void testToString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm MM/dd/yy");
        Date date = new Date();
        assertEquals("New Name: Test@abc, Old Name: Test, " + dateFormat.format(date), this.timeStamp.toString());
    }

}
