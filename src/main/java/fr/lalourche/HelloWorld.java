package fr.lalourche;

import java.io.PrintStream;
import java.util.ResourceBundle;

import fr.lalourche.model.Message;

/**
 * @author Lalourche
 */
public class HelloWorld
{

  /** String to return. */
  private Message message_;

  /** String to return. */
  private PrintStream out_;

  /**
   * Constructor.
   */
  public HelloWorld()
  {
    this(System.out);
  }

  /**
   * Constructor.
   * @param out the output stream
   */
  public HelloWorld(PrintStream out)
  {
    ResourceBundle rb = ResourceBundle.getBundle("messages");
    String value = rb.getString("hello.message");
    message_ = new Message(value);
    out_ = out;

    // Save the message in database
    message_.save();
  }

  /**
   * @return the message
   */
  public final Message getMessage()
  {
    return message_;
  }

  /**
   * @return the message
   */
  public final String getMessageValue()
  {
    return message_.getValue();
  }

  /**
   * Main process.
   */
  public final void execute()
  {
    out_.println(message_.getValue());
  }

}
