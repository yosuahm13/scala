package repository

import slick.driver.H2Driver.api._
import scala.concurrent.ExecutionContext.Implicits.global

trait IRepository {
  val db = Database.forConfig("h2mem")
}