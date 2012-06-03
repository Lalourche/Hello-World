/**
 *  Abstract POJO for data persistence.
 */
package fr.lalourche.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateHelper;


/**
 * @author Lalourche
 *
 */
@MappedSuperclass
public abstract class Entity
{

  /** Id. */
  @Id
  @GeneratedValue
  private Long id_;

  /**
   * @return the id
   */
  public final Long getId()
  {
    return id_;
  }

  /**
   * @param id the id to set
   */
  public final void setId(Long id)
  {
    id_ = id;
  }

  /**
   * Lists the entities.
   * @param clazz the class to query
   * @return the entities list.
   */
  public static List<? extends Entity> list(Class<? extends Entity> clazz)
  {
    Session session = HibernateHelper.getInstance().getSession();

    String query = "from " + clazz.getSimpleName();
    List<Message> messages = session.createQuery(query).list();

//    HibernateHelper.getInstance().closeSession();

    return messages;
  }

  /**
   * Reads an entity.
   * @param id the id of the entity
   * @param clazz the class to query
   * @return the entity.
   */
  public static Entity read(Long id, Class<? extends Entity> clazz)
  {
    Session session = HibernateHelper.getInstance().getSession();

    Entity e = (Entity) session.get(clazz, id);

//    HibernateHelper.getInstance().closeSession();

    return e;
  }

  /**
   * Saves current entity in database.
   */
  public final void save()
  {
    Session session = HibernateHelper.getInstance().getSession();

    Transaction transaction = session.beginTransaction();

    Long id = (Long) session.save(this);
    session.flush();
    setId(id);

    transaction.commit();

//    HibernateHelper.getInstance().closeSession();
  }

  /**
   * Updates current entity in database.
   */
  public final void update()
  {
    Session session = HibernateHelper.getInstance().getSession();

    session.beginTransaction();

    session.merge(this);

    session.getTransaction().commit();

//    HibernateHelper.getInstance().closeSession();
  }

  /**
   * Deletes current entity from database.
   */
  public final void delete()
  {
    Session session = HibernateHelper.getInstance().getSession();

    Transaction transaction = session.beginTransaction();

    session.delete(this);

    transaction.commit();

//    HibernateHelper.getInstance().closeSession();
  }

  /**
   * Delete all the entities of a specified class.
   * @param clazz the class to delete
   */
  public static void deleteAll(Class<? extends Entity> clazz)
  {
    Session session = HibernateHelper.getInstance().getSession();

    Transaction transaction = session.beginTransaction();

    String query = "delete from " + clazz.getSimpleName();
    session.createQuery(query).executeUpdate();

    transaction.commit();

//    HibernateHelper.getInstance().closeSession();
  }

  /**
   * Counts all the entities of a specified class.
   * @param clazz the class to count
   * @return the count
   */
  public static long count(Class<? extends Entity> clazz)
  {
    Long result;

    Session session = HibernateHelper.getInstance().getSession();

    String query = "select count(*) from " + clazz.getSimpleName();
    result = (Long) session.createQuery(query).iterate().next();

//    HibernateHelper.getInstance().closeSession();

    return result.longValue();
  }

  //CHECKSTYLE:OFF There is a need to implement this method
  //also in the mother class.
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "(" + id_ + ")";
  }
 //CHECKSTYLE:ON
}
