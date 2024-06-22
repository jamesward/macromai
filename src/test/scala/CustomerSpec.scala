import kyo.*
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers


class CustomerSpec extends AsyncFlatSpec with Matchers:

  val customerWorks =
    Envs.use[CustomerOps]:
      customerOps =>
        defer:
          val created: Customer = await:
            customerOps.create("asdf")

          val found: Option[Customer] = await:
            customerOps.find(created.id)

          found shouldBe Some(created)

  /*
   * Test Environment:
   *   Run this test with a mock
   */
  "customer" should "work" in:
    customerWorks

  /*
   * Test Environment:
   *   Run this test with a testcontainers postgres database
   */
  "customer" should "work with a database" in :
    customerWorks
