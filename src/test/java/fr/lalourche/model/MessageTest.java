/**
 * Test of Message persistence.
 */
package fr.lalourche.model;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Lalourche
 */
public class MessageTest
{

  /**
   * @throws java.lang.Exception
   *           if anything bad occurs.
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception
  {
    // Start from default database
//    ResourceBundle rb = ResourceBundle.getBundle("configuration");
//    String emptyDatabaseDirName = rb.getString("database.empty.dir");
//    String currentDatabaseDirName = rb.getString("database.current.dir");
//    File emptyDatabaseDir = new File(emptyDatabaseDirName);
//    File currentDatabaseDir = new File(currentDatabaseDirName);
//
//    System.out.print("Reinitializing database from : ");
//    System.out.println(emptyDatabaseDir.getAbsolutePath());
//    System.out.println("to : " + currentDatabaseDir.getAbsolutePath());
//    FileUtils.copyDirectory(emptyDatabaseDir, currentDatabaseDir);

    // check that database is empty
    Assert.assertEquals(0, Entity.count(Message.class));
  }

  /**
   * @throws java.lang.Exception
   *           if anything bad occurs.
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
    // Add a small tempo in order for the JVM not to close too soon
    // and allow the complete writing of data into the database
    try {
      Thread.sleep(1000);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }

    // check that database is empty
    Assert.assertEquals(0, Entity.count(Message.class));
  }

  /**
   * @throws java.lang.Exception
   *           if anything bad occurs.
   */
  @Before
  public void setUp() throws Exception
  {
  }

  /**
   * @throws java.lang.Exception
   *           if anything bad occurs.
   */
  @After
  public void tearDown() throws Exception
  {
  }

  /**
   * CRUD test.
   */
  @Test
  public final void testCRUD()
  {
    System.out.println("******* CREATE *******");
    String s = "Toto";
    Message m = new Message(s);
    m.save();
    Long id = m.getId();
    Assert.assertTrue(id > 0);

    System.out.println("******* READ *******");
    Entity.read(id, Message.class);
    System.out.println(m);
    Assert.assertEquals(s, m.getValue());

    System.out.println("******* UPDATE *******");
    String newValue = "Titi";
    m.setValue(newValue);
    m.update();
    Assert.assertEquals(id, m.getId());
    m = (Message) Entity.read(id, Message.class);
    System.out.println(m);
    Assert.assertEquals(newValue, m.getValue());

    System.out.println("******* LIST *******");
    List<Message> allMessages = (List<Message>) Entity.list(Message.class);
    Assert.assertEquals(1, allMessages.size());

    System.out.println("******* DELETE *******");
    m = (Message) Entity.read(id, Message.class);
    m.delete();
    m = (Message) Entity.read(id, Message.class);
    Assert.assertNull(m);
    allMessages = (List<Message>) Entity.list(Message.class);
    Assert.assertEquals(0, allMessages.size());
  }

  /**
   * Test multiple entities handling.
   * Tests list and deleteAll
   */
  @Test
  public final void testMultiple()
  {
    // Create multiple messages
    String s = "Multiple";
    final int max = 100;
    for (int i = 0; i < max; i++) {
      Message m = new Message(s);
      m.save();
    }

    System.out.println("******* LIST *******");
    List<Message> allMessages = (List<Message>) Entity.list(Message.class);
    Assert.assertEquals(max, allMessages.size());

    System.out.println("******* DELETE ALL *******");
    Entity.deleteAll(Message.class);
    allMessages = (List<Message>) Entity.list(Message.class);
    Assert.assertEquals(0, allMessages.size());
  }
}
