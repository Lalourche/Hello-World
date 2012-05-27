/**
 *  POJO for data persistence.
 */
package fr.lalourche.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Lalourche
 *
 */
@Entity
@Table(name = "MESSAGE")
public class Message extends fr.lalourche.model.Entity
{
  /** Message content. */
  @Column(name = "value")
  private String value_;

  /**
   * Constructor.
   */
  public Message()
  {
    value_ = "";
  }

  /**
   * Constructor.
   * @param value message value.
   */
  public Message(String value)
  {
    value_ = value;
  }

  /**
   * @return the value
   */
  public final String getValue()
  {
    return value_;
  }

  /**
   * @param value the value to set
   */
  public final void setValue(String value)
  {
    value_ = value;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public final String toString()
  {
    return super.toString() + " " + value_;
  }
}
