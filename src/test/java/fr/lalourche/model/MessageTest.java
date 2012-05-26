/**
 * Test of Message persistence.
 */
package fr.lalourche.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
//    List<Message> messages = list();
  }

  /**
   * @throws java.lang.Exception
   *           if anything bad occurs.
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception
  {
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
  public final void write()
  {
    System.out.println("******* WRITE *******");
    Message m = new Message("Toto");
    m = save(m);
    m = read(m.getId());
    System.out.printf("%d %s \n", m.getId(), m.getValue());
  }

  /**
   * Lists the messages.
   * @return the message list.
   */
  private static List<Message> list()
  {
    SessionFactory sf = HibernateUtil.getSessionFactory();
    Session session = sf.openSession();

    List<Message> messages = session.createQuery("from MESSAGE").list();
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

    session.beginTransaction();

    Long id = (Long) session.save(message);
    message.setId(id);

    session.getTransaction().commit();

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

    session.beginTransaction();

    session.delete(message);

    session.getTransaction().commit();

    session.close();
  }
}
