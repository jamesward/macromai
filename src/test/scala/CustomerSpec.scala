import com.dimafeng.testcontainers.GenericContainer
import kyo.*
import org.scalatest.BeforeAndAfterAll
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.images.PullPolicy
import sttp.client3.UriContext

import scala.compiletime.uninitialized
import scala.concurrent.{ExecutionContext, Future}

class CustomerSpec extends AsyncFlatSpec with Matchers with BeforeAndAfterAll:

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
