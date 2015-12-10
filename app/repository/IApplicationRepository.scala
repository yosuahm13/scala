package repository

import com.google.inject.ImplementedBy
import models._
import scala.concurrent.Future

@ImplementedBy(classOf[ApplicationRepository])
trait IApplicationRepository extends IRepository {
  def list(cart_id: Long): Future[Seq[OrderDetail]]
  def add(cart_id: Long, product_id: Int): Future[Unit]
  def remove(cart_id: Long, product_id: Int): Future[Unit]
}