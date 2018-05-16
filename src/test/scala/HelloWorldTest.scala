import org.junit.runner.RunWith
import org.scalatest.FunSpec
import org.scalatest.junit.JUnitRunner


@RunWith(classOf[JUnitRunner]) class HelloWorldTest extends FunSpec {
  describe("A test to validate greetings") {
    it("When a greeting 'Hello World, Arun RAR' is passed, it prints the message as is") {
      val helloWorld = new HelloWorld()
      assert(helloWorld.greet("Hello World", "Arun", "RAR").equals("Hello World Arun RAR"))
    }
  }
}