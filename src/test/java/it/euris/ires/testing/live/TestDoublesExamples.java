package it.euris.ires.testing.live;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestDoublesExamples {

  @Mock private List<String> mockList;

  @Spy private List<String> spyList = new ArrayList<>();

  @Test
  public void testMockList() {
    // by default, calling the methods of mock object will do nothing
    mockList.add("test");

    verify(mockList).add("test");
    assertEquals(0, mockList.size());
    assertNull(mockList.get(0));
  }

  @Test
  public void testSpyList() {
    // spy object will call the real method when not stub
    List<String> spyList2 = spy(new ArrayList<>());
    spyList2.add("test");

    verify(spyList2).add("test");
    assertEquals(1, spyList2.size());
    assertEquals("test", spyList2.get(0));
  }

  @Test
  public void testMockWithStub() {
    // try stubbing a method
    String expected = "Mock 100";
    when(mockList.get(100)).thenReturn(expected);

    assertEquals(expected, mockList.get(100));
  }

  @Test
  public void testSpyWithStub() {
    // stubbing a spy method will result the same as the mock object
    String expected = "Spy 100";
    // take note of using doReturn instead of when
    doReturn(expected).when(spyList).get(100);

    assertEquals(expected, spyList.get(100));
  }
}
