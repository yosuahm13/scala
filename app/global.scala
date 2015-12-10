import play.api._
import models._
import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global


object Global extends GlobalSettings {
  
  override def onStart(app:Application) {
    println("Start App")
    
    val db = Database.forConfig("h2mem")
    val orders = TableQuery[Orders]
    val products = TableQuery[Products]
    val orderDetails = TableQuery[OrderDetails]
    
    val init = DBIO.seq(
        (orders.schema ++ products.schema ++ orderDetails.schema).create,
        
        products ++= Seq(
            new Product(None, "Green Dress", 128000.00, 2),
            new Product(None, "Purple Tank top", 78000.00, 5),
            new Product(None, "Sponge Bob T-Shirt", 67000.00, 2),
            new Product(None, "Pink Sweater", 153000.00, 1)
          ),
          
        orders ++= Seq(
            new Order(None, "Please send ASAP")
          ),
          
        orderDetails ++= Seq(
            new OrderDetail(None, 1, 2)
          )
      )
      println("Inject Data")
      val initRun = db.run(init)
      
      println("Init Success")
  }
}