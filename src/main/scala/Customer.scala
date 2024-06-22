import kyo.*
import java.util.UUID

case class Customer(id: UUID, name: String)

trait CustomerOps:
  def create(name: String): Customer < IOs
  def find(id: UUID): Option[Customer] < IOs
  def findAll: Streams[Customer]
