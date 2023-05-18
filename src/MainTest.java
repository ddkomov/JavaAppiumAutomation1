import org.junit.Test;

public class MainTest extends CoreTestCase {
   @Override
   public void typeStartMessage() {
      System.out.println("Current class is main test");
   }

   @Test
    public void myFirstTest() {
      this.typeStartMessage();
   }
   @Test
   public void mySecondTest() {
      this.typeStartMessage();
   }
}
