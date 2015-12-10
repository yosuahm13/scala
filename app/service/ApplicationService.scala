package service

import javax.inject.Inject
import repository.IApplicationRepository
import models._
import scala.concurrent.Future

class ApplicationService @Inject() (repo: IApplicationRepository ) extends IApplicationService {
  def getListItems(cart_id: Long): Future[Seq[OrderDetail]] = repo.list(cart_id)
  
  def addItem(cart_id: Long, product_id: Int): Future[Unit] = repo.add(cart_id, product_id)
  
  def removeItem(cart_id: Long, product_id: Int): Future[Unit] = repo.remove(cart_id, product_id)
}