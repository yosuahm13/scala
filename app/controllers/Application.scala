package controllers

import play.api._
import play.api.mvc._
import javax.inject.Inject
import service.IApplicationService
import play.api.libs.json.Json
import models._
import scala.concurrent.ExecutionContext


class Application @Inject() (service: IApplicationService)(implicit ec: ExecutionContext) extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }
  
  def listItems(cart_id: Long) = Action.async {    
    service.getListItems(cart_id).map { x => Ok("Good") }    
  }
  
  def addItem(cart_id:Long, item_id:Int) = Action.async {
    service.addItem(cart_id, item_id).map { x => Ok("Success") }
  }
  
  def removeItem(cart_id:Long, item_id:Int) = Action.async {
    service.removeItem(cart_id, item_id).map { x => Ok("Success") }
  }

}
