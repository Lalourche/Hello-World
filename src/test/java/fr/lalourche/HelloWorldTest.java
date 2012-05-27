package fr.lalourche;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import junit.framework.Assert;

import fr.lalourche.model.Entity;
import fr.lalourche.model.Message;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



/**
 * @author Lalourche
 *
 */
public class HelloWorldTest
{

  /** Expected string. */
  private static final String HELLO_WORLD_STRING = "Hello World !";

  /** Instance of class to test. */
  private static HelloWorld hw_;

  /** Id of the persisted message. */
  private static Long id_;

  /** Content of the test output stream. */
  private static ByteArrayOutputStream outContent_;


  /**
   * @throws java.lang.Exception if anything bad happens.
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
    outContent_ = new ByteArrayOutputStream();
  }

  /**
   * @throws java.lang.Exception if anything bad happens.
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception if anything bad happens.
   */
  @Before
  public final void setUp() throws Exception
  {
    hw_ = new HelloWorld(new PrintStream(outContent_));
    id_ = hw_.getMessage().getId();
  }

  /**
   * @throws java.lang.Exception if anything bad happens.
   */
  @After
  public void tearDown() throws Exception
  {
  }

  /**
   * Test the Hello world string.
   */
  @Test
  public final void testMessage()
  {
    Assert.assertEquals(HELLO_WORLD_STRING, hw_.getMessageValue());
  }

  /**
   * Test the Hello world execution.
   */
  @Test
  public final void testExecute()
  {
    hw_.execute();
    String expected = HELLO_WORLD_STRING + System.getProperty("line.separator");
    Assert.assertEquals(expected, outContent_.toString());
  }

  /**
   * Test the database persistence.
   */
  @Test
  public final void testPersistence()
  {
    Message m = (Message) Entity.read(id_, Message.class);
    Assert.assertEquals(HELLO_WORLD_STRING, m.getValue());
  }

}
