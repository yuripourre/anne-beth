import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.languages.English;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EnglishTest {

    private English english;

    @Before
    public void setUp() {
        english = new English();
    }

    @Test
    public void testInterpolation() {
        BaseObject b = new BaseObject("random name");

        String sentence = english.sentence(Dictionary.STANDARD_LOOK_AT, b);
        Assert.assertEquals("It is just a random name.", sentence);
    }

}