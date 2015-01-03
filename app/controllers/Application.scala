package controllers

import models.Event

import play.api._
import play.api.mvc._
import play.api.libs.json.{JsValue, Json}

object Application extends Controller {

  def indexEvents = Action {
    val events = Event.all

    Ok(Json.toJson(events))
  }

}