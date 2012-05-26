/**
 * Utility class for Hibernate usage.
 */
package fr.lalourche.model;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * @author Lalourche
 */
public final class HibernateUtil
{
  /** Factory for session. */
  private static final SessionFactory sessionFactory = buildSessionFactory();

  /** Default constructor. */
  private HibernateUtil()
  {
  }

  /**
   * Builds the session factory.
   *
   * @return the session factory.
   */
  private static SessionFactory buildSessionFactory()
  {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      return new AnnotationConfiguration().configure().buildSessionFactory();
    }
    catch (HibernateException ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }

  /**
   * Getter for session factory.
   *
   * @return session factory.
   */
  public static SessionFactory getSessionFactory()
  {
    return sessionFactory;
  }

}
