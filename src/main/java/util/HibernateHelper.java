package util;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

/**
 * @author Lalourche
 */
public final class HibernateHelper
{
  /**
   * HibernateHelper singleton.
   */
  private static HibernateHelper singleton_ = new HibernateHelper();
  /**
   * Session factory.
   */
  private SessionFactory factory_;
  /**
   * Current session.
   */
  private ThreadLocal<Session> currentSession_ = new ThreadLocal<Session>();

  /**
   * Default constructor.
   * @throws HibernateException if anything wrong occurs
   */
  private HibernateHelper() throws HibernateException
  {
  }

  /**
   * Return the singleton.
   * @return the singleton
   */
  public static HibernateHelper getInstance()
  {
    return singleton_;
  }

  /**
   * Builds the session factory.
   * @return the session factory.
   */
  private synchronized SessionFactory getFactory()
  {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      return getConfiguration().configure().buildSessionFactory();
    }
    catch (HibernateException ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  /**
   * Closes all.
   * @throws HibernateException if anything wrong occurs.
   */
  private synchronized void close() throws HibernateException
  {
    closeSession();
    if (factory_ != null) {
      factory_.close();
      factory_ = null;
    }
  }

  /**
   * Gets the configuration.
   * @return the configuration
   * @throws MappingException if anything wrong occurs.
   */
  private Configuration getConfiguration() throws MappingException
  {
    Configuration config = new AnnotationConfiguration();
    return config;
  }

  /**
   * Opens the session.
   * @return the session.
   * @throws HibernateException if anything wrong occurs.
   */
  private Session openSession() throws HibernateException
  {
    Session session = getFactory().openSession();
    return session;
  }

  /**
   * Gets current session.
   * @return the session.
   * @throws HibernateException if anything wrong occurs.
   */
  public Session getSession() throws HibernateException
  {
    Session session = currentSession_.get();
    if (session == null || !session.isOpen()) {
      session = openSession();
      currentSession_.set(session);
    }
    return session;
  }

  /**
   * Closes the session.
   * @throws HibernateException if anything wrong occurs.
   */
  public void closeSession() throws HibernateException
  {
    Session session = currentSession_.get();
    if (session != null && session.isOpen()) {
      session.close();
    }
    currentSession_.set(null);
  }

  /**
   * Saves an object in database.
   * @param object the object to save
   * @throws HibernateException if anything wrong occurs.
   */
  public void save(Object object) throws HibernateException
  {
    getSession().save(object);
  }

  /**
   * Deletes an object in database.
   * @param object the object to delete
   * @throws HibernateException if anything wrong occurs.
   */
  public void delete(Object object) throws HibernateException
  {
    getSession().delete(object);
  }

  /**
   * To string.
   * @return the string.
   */
  public String toString()
  {
    return "HibernateHelper";
  }
}
