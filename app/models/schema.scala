package models

import slick.driver.H2Driver.api._
import play.api.libs.json.Json

case class Order(id: Option[Long], comment: String)

class Orders(tag: Tag) extends Table[Order](tag, "ORDER") {
  def id = column[Long]("ORDER_ID", O.PrimaryKey, O.AutoInc)
  def comment = column[String]("COMMENT")
  
  def * = (id.?, comment) <> (Order.tupled, Order.unapply)
}

case class Product(id: Option[Int] = None, name: String, price: Double, stock: Int)

class Products(tag: Tag) extends Table[Product](tag, "PRODUCT") {
  def id = column[Int]("PRODUCT_ID", O.PrimaryKey, O.AutoInc)
  def name = column[String]("PRODUCT_NAME")
  def price = column[Double]("PRICE")
  def stock = column[Int]("STOCK")
  
  def * = (id.?, name, price, stock) <> (Product.tupled, Product.unapply)
}

case class OrderDetail(id: Option[Long], cartId: Long, productId: Int)

class OrderDetails(tag: Tag) extends Table[OrderDetail](tag, "ORDER_DETAIL") {
  def id = column[Long]("ORDER_DETAIL_ID", O.PrimaryKey, O.AutoInc)
  def cartId = column[Long]("CART_ID")
  def productId = column[Int]("PRODUCT_ID")
  
  def * = (id.?, cartId, productId) <> (OrderDetail.tupled, OrderDetail.unapply)
    
  def cart = foreignKey("CART", cartId, TableQuery[Orders])(_.id)
  def product = foreignKey("PRODUCT", productId, TableQuery[Products])(_.id)  
}