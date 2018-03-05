package photo_tester;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import photo_manager.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class PhotoTest {

    private Photo photo;

    @BeforeEach
    void setUp(){
        this.photo = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/test.jpg");
    }

    @AfterEach
    void tearDown(){
        this.photo = null;
    }

    @Test
    void testPhotoAddTagNoWhiteSpaces(){
        this.photo.addTag(new Tag("123"));
        assertEquals("test@123.jpg", this.photo.toString());
        this.photo.addTag(new Tag("567"));
        assertEquals("test@123@567.jpg", this.photo.toString());
    }

    @Test
    void testPhotoAddTagWhiteSpacesBefore(){
        this.photo.addTag(new Tag("     123"));
        assertEquals("test@123.jpg", this.photo.toString());
    }

    @Test
    void testPhotoAddTagWhiteSpacesAfter(){
        this.photo.addTag(new Tag("123    "));
        assertEquals("test@123.jpg", this.photo.toString());
    }

    @Test
    void testPhotoAddTagWhiteSpacesInMiddle(){
        this.photo.addTag(new Tag("1 2 3"));
        assertEquals("test@1 2 3.jpg", this.photo.toString());
        this.photo.addTag(new Tag("3   4"));
        assertEquals("test@1 2 3@3   4.jpg", this.photo.toString());
    }

    @Test
    void testPhotoAddTagLowerCaseLetters(){
        this.photo.addTag(new Tag("abc"));
        assertEquals("test@abc.jpg", this.photo.toString());
        this.photo.addTag(new Tag("hello"));
        assertEquals("test@abc@hello.jpg", this.photo.toString());
        this.photo.addTag(new Tag("bye"));
        assertEquals("test@abc@hello@bye.jpg", this.photo.toString());
    }

    @Test
    void testPhotoAddTagUpperCaseLetters(){
        this.photo.addTag(new Tag("ABC"));
        assertEquals("test@ABC.jpg", this.photo.toString());
        this.photo.addTag(new Tag("JJJJ"));
        assertEquals("test@ABC@JJJJ.jpg", this.photo.toString());
    }

    @Test
    void testPhotoAddTagSmybols(){
        this.photo.addTag(new Tag("-/!?"));
        assertEquals("test@-/!?.jpg", this.photo.toString());
        this.photo.addTag(new Tag(":;'[]"));
        assertEquals("test@-/!?@:;'[].jpg", this.photo.toString());
    }

    @Test
    void testPhotoSetName(){
        this.photo.setName("helloworld.jpg" );
        assertEquals("helloworld.jpg", this.photo.toString());
        this.photo.setName("byeworld.bmp" );
        assertEquals("byeworld.bmp", this.photo.toString());
        this.photo.setName("Toronto.gif" );
        assertEquals("Toronto.gif", this.photo.toString());
    }

    @Test
    void testPhotoGetPhotoName(){
        assertEquals("test.jpg", this.photo.getPhotoName());
    }

    @Test
    void testGetPhotoType(){
        assertEquals(".jpg", this.photo.getPhotoType());
        Photo photo2 = new Photo("/Users/test2.gif");
        assertEquals(".gif", photo2.getPhotoType());
        Photo photo3 = new Photo("/Users/test3.png");
        assertEquals(".png", photo3.getPhotoType());
        Photo photo4 = new Photo("/Users/test4.bmp");
        assertEquals(".bmp", photo4.getPhotoType());
        Photo photo5 = new Photo("/Users/test5.JPG");
        assertEquals(".JPG", photo5.getPhotoType());
        Photo photo6 = new Photo("/Users/test6.PNG");
        assertEquals(".PNG", photo6.getPhotoType());
        Photo photo7 = new Photo("/Users/test7.GIF");
        assertEquals(".GIF", photo7.getPhotoType());
        Photo photo8 = new Photo("/Users/test8.BMP");
        assertEquals(".BMP", photo8.getPhotoType());
    }

    @Test
    void testGetAllTags(){
        this.photo.addTag(new Tag("abc"));
        assertEquals("@abc", this.photo.getAllTags());
        this.photo.addTag(new Tag("hello"));
        assertEquals("@abc@hello", this.photo.getAllTags());
        this.photo.addTag(new Tag("bye"));
        assertEquals("@abc@hello@bye", this.photo.getAllTags());
        this.photo.addTag(new Tag("-/!?"));
        assertEquals("@abc@hello@bye@-/!?", this.photo.getAllTags());
        this.photo.addTag(new Tag("123"));
        assertEquals("@abc@hello@bye@-/!?@123", this.photo.getAllTags());
    }

    @Test
    void testGetPhotoLogKeys(){
        this.photo.addTag(new Tag("ccc"));
        assertEquals("test.jpg  test@ccc.jpg  ", this.photo.getPhotoLogKeys());
        this.photo.addTag(new Tag("NOYES"));
        assertEquals("test.jpg  test@ccc@NOYES.jpg  test@ccc.jpg  ", this.photo.getPhotoLogKeys());
        this.photo.addTag(new Tag("-/!?"));
        assertEquals("test.jpg  test@ccc@NOYES@-/!?.jpg  test@ccc@NOYES.jpg  test@ccc.jpg  ",
                this.photo.getPhotoLogKeys());
        this.photo.addTag(new Tag("123"));
        assertEquals("test.jpg  test@ccc@NOYES@-/!?.jpg  test@ccc@NOYES@-/!?@123.jpg  " +
                "test@ccc@NOYES.jpg  test@ccc.jpg  ", this.photo.getPhotoLogKeys());
    }

    @Test
    void testGetNameLog(){
        this.photo.addTag(new Tag("abc"));
        ArrayList<String> test = new ArrayList<>();
        test.add("test.jpg");
        test.add("test@abc.jpg" );
        assertEquals(test, this.photo.getNameLog());
        this.photo.addTag(new Tag("hello"));
        test.add("test@abc@hello.jpg");
        assertEquals(test, this.photo.getNameLog());
    }

    @Test
    void testRemoveDifferentTag(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("123"));
        this.photo.addTag(new Tag("???"));
        this.photo.removeTag(new Tag("abc") );
        assertEquals("@123@???", this.photo.getAllTags());
        this.photo.removeTag(new Tag("123"));
        assertEquals("@???", this.photo.getAllTags());
    }

    @Test
    void testRemoveSameTag(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("123"));
        this.photo.addTag(new Tag("???"));
        this.photo.removeTag(new Tag("abc") );
        assertEquals("@123@???", this.photo.getAllTags());
        this.photo.removeTag(new Tag("abc") );
        assertEquals("@123@???", this.photo.getAllTags());
    }

    @Test
    void testToString(){
        this.photo.addTag(new Tag("abc"));
        assertEquals("test@abc.jpg", this.photo.toString());
        this.photo.addTag(new Tag("888"));
        assertEquals("test@abc@888.jpg", this.photo.toString());
        this.photo.addTag(new Tag("!!!"));
        assertEquals("test@abc@888@!!!.jpg", this.photo.toString());

    }

    @Test
    void testDifferentPhotosSameTagsEquals(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("888"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/photo1.jpg");
        photo1.addTag(new Tag("abc"));
        photo1.addTag(new Tag("888"));
        assertFalse(this.photo.equals(photo1));
    }

    @Test
    void testDifferentPhotosDifferentTagsEquals(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("888"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/photo1.jpg");
        photo1.addTag(new Tag("abc"));
        photo1.addTag(new Tag("888"));
        photo1.addTag(new Tag("google"));
        assertFalse(this.photo.equals(photo1));
    }

    @Test
    void testSamePhotoSameTagsEquals(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("888"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/test.jpg");
        photo1.addTag(new Tag("abc"));
        photo1.addTag(new Tag("888"));
        assertTrue(this.photo.equals(photo1));
    }

    @Test
    void testSamePhotoDifferentTagsEquals(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("888"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/test.jpg");
        photo1.addTag(new Tag("abc"));
        photo1.addTag(new Tag("888"));
        photo1.addTag(new Tag("google"));
        assertTrue(this.photo.equals(photo1));
    }

    @Test
    void testSamePhotoSameTagsTransferValues(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("888"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/test.jpg");
        photo1.addTag(new Tag("abc"));
        photo1.addTag(new Tag("888"));
        this.photo.transferValues(photo1);
        assertEquals(photo1.getOldTags(),this.photo.getOldTags());
        assertEquals(photo1.getAllTags(),this.photo.getAllTags());
        assertEquals(photo1.getNameLog(),this.photo.getNameLog());
        assertEquals(photo1.getTimeStampsString(),this.photo.getTimeStampsString());
        assertEquals(photo1.getPhotoName(),this.photo.getPhotoName());
        assertEquals(photo1.getPhotoLogKeys(),this.photo.getPhotoLogKeys());
    }

    @Test
    void testSamePhotoDifferentTagsTransferValues(){
        this.photo.addTag(new Tag("abc"));
        this.photo.addTag(new Tag("888"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/test.jpg");
        photo1.addTag(new Tag("jeff"));
        photo1.addTag(new Tag("8k88"));
        photo1.addTag(new Tag("google"));
        this.photo.transferValues(photo1);
        assertEquals(photo1.getOldTags(),this.photo.getOldTags());
        assertEquals(photo1.getAllTags(),this.photo.getAllTags());
        assertEquals(photo1.getNameLog(),this.photo.getNameLog());
        assertEquals(photo1.getTimeStampsString(),this.photo.getTimeStampsString());
        assertEquals(photo1.getPhotoName(),this.photo.getPhotoName());
        assertEquals(photo1.getPhotoLogKeys(),this.photo.getPhotoLogKeys());

    }

    @Test
    void testDifferentPhotoSameTagsTransferValues(){
        this.photo.addTag(new Tag("jeremy"));
        this.photo.addTag(new Tag("8#88"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/Photo1.jpg");
        photo1.addTag(new Tag("jeremy"));
        photo1.addTag(new Tag("8#88"));
        this.photo.transferValues(photo1);
        assertEquals(photo1.getOldTags(),this.photo.getOldTags());
        assertEquals(photo1.getAllTags(),this.photo.getAllTags());
        assertEquals(photo1.getNameLog(),this.photo.getNameLog());
        assertEquals(photo1.getTimeStampsString(),this.photo.getTimeStampsString());
        assertEquals(photo1.getPhotoName(),this.photo.getPhotoName());
        assertEquals(photo1.getPhotoLogKeys(),this.photo.getPhotoLogKeys());
    }

    @Test
    void testDifferentPhotoDifferentTagsTransferValues(){
        this.photo.addTag(new Tag("alex"));
        this.photo.addTag(new Tag("jeff"));
        Photo photo1 = new Photo("/Users/jeremy/shihjere/group_0489/phase2/src/photo_tester/Photo1.jpg");
        photo1.addTag(new Tag("!!!"));
        photo1.addTag(new Tag("hello"));
        photo1.addTag(new Tag("--134"));
        this.photo.transferValues(photo1);
        assertEquals(photo1.getOldTags(),this.photo.getOldTags());
        assertEquals(photo1.getAllTags(),this.photo.getAllTags());
        assertEquals(photo1.getNameLog(),this.photo.getNameLog());
        assertEquals(photo1.getTimeStampsString(),this.photo.getTimeStampsString());
        assertEquals(photo1.getPhotoName(),this.photo.getPhotoName());
        assertEquals(photo1.getPhotoLogKeys(),this.photo.getPhotoLogKeys());

    }

}

