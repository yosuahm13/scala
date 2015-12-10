package service

import com.google.inject.ImplementedBy
import scala.concurrent.Future
import models._

@ImplementedBy(classOf[ApplicationService])
trait IApplicationService extends IService {
  def getListItems(cart_id: Long): Future[Seq[OrderDetail]]
  def addItem(cart_id: Long, product_id: Int): Future[Unit]
  def removeItem(cart_id: Long, product_id: Int): Future[Unit]
}