package fr.lalourche;

import java.io.PrintStream;
import java.util.ResourceBundle;

/**
 * @author Lalourche
 */
public class HelloWorld
{

  /** String to return. */
  private String message_;

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
    message_ = ResourceBundle.getBundle("messages").getString("hello.message");
    out_ = out;
  }

  /**
   * @return the message
   */
  public final String getMessage()
  {
    return message_;
  }

  /**
   * Main process.
   */
  public final void execute()
  {
    out_.println(message_);
  }

}
