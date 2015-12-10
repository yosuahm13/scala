package repository

import models._
import scala.concurrent.Future
import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global

class ApplicationRepository extends IApplicationRepository {
  
  val orderDetail = TableQuery[OrderDetails]  
  
  def list(cart_id: Long): Future[Seq[OrderDetail]] = 
    try db.run(     
      orderDetail.filter { x => x.cartId === cart_id }.result
    ) finally db.close()
  
  def add(cart_id: Long, product_id: Int): Future[Unit] = {
    try {
      val insert = DBIO.seq(
          orderDetail += new OrderDetail(None, cart_id, product_id)
      )
      db.run(insert)      
    }
  }
  
  def remove(cart_id: Long, product_id: Int): Future[Unit] = {
    try {
      val delete = DBIO.seq(
          orderDetail.filter { x => x.cartId === cart_id && x.productId === product_id }.delete
      )
      db.run(delete)      
    }
  }
}

