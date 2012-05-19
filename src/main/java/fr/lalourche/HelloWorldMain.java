package fr.lalourche;

/**
 * @author Lalourche
 *
 */
public final class HelloWorldMain
{

  /**
   * Default constructor (should never be used).
   */
  private HelloWorldMain()
  {
    super();
  }

  /**
   * Main method.
   * @param args not used
   */
  public static void main(String[] args)
  {
    HelloWorld hw = new HelloWorld();
    hw.execute();
  }

}
