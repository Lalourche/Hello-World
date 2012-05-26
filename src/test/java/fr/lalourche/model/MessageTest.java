/**
 * Test of Message persistence.
 */
package fr.lalourche.model;

import java.io.File;
import java.util.List;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
    File emptyDatabaseDir = new File("./database/default");
    File currentDatabaseDir = new File("./database");
    FileUtils.copyDirectory(emptyDatabaseDir, currentDatabaseDir);
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
   * Write test.
   */
  @Test
  public final void mainTest()
  {
    System.out.println("******* WRITE *******");
    String s = "Toto";
    Message m = new Message(s);
    m = save(m);
    Long id = m.getId();
    Assert.assertTrue(id > 0);

    System.out.println("******* READ *******");
    m = read(id);
    System.out.println(m);
    Assert.assertEquals(m.getValue(), s);

    System.out.println("******* UPDATE *******");
    String newValue = "Titi";
    m.setValue(newValue);
    update(m);
    Assert.assertEquals(m.getId(), id);
    m = read(id);
    System.out.println(m);
    Assert.assertEquals(m.getValue(), newValue);

    System.out.println("******* LIST *******");
    List<Message> allMessages = list();
    Assert.assertEquals(allMessages.size(), 1);

    System.out.println("******* DELETE *******");
    m = read(id);
    delete(m);
    m = read(id);
    Assert.assertNull(m);
  }

  /**
   * Lists the messages.
   * @return the message list.
   */
  private static List<Message> list()
  {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();

    List<Message> messages = session.createQuery("from Message").list();
    session.close();
    return messages;
  }

  /**
   * Reads a message.
   * @param id the id of the message.
   * @return the message.
   */
  private static Message read(Long id)
  {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();

    Message message = (Message) session.get(Message.class, id);
    session.close();
    return message;
  }

  /**
   * Saves a message in database.
   * @param message the message to save.
   * @return the saved message.
   */
  private static Message save(Message message)
  {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();

    Transaction transaction = session.beginTransaction();

    Long id = (Long) session.save(message);
    session.flush();
    message.setId(id);

    transaction.commit();

    session.close();

    return message;
  }

  /**
   * Updates a message in database.
   * @param message the message to update.
   * @return the updated message.
   */
  private static Message update(Message message)
  {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();

    session.beginTransaction();

    session.merge(message);

    session.getTransaction().commit();

    session.close();

    return message;
  }

  /**
   * Deletes a message from database.
   * @param message the message to delete.
   */
  private static void delete(Message message)
  {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();

    Transaction transaction = session.beginTransaction();

    session.delete(message);

    transaction.commit();

    session.close();
  }
}
