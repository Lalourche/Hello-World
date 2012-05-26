/**
 *  POJO for data persistence.
 */
package fr.lalourche.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Lalourche
 *
 */
@Entity
@Table(name = "MESSAGE")
public class Message
{
  /** Message Id. */
  @Id
  @GeneratedValue
  private Long id_;

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
}
