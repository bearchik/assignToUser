package ut.ru.bearchik.plugin.assignToUser;

import org.junit.Test;
import ru.bearchik.plugin.assignToUser.api.MyPluginComponent;
import ru.bearchik.plugin.assignToUser.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}