package models

import dao.EventDao
import play.api.libs.json._

object Event {

  implicit val eventWrites: Writes[Event] = new Writes[Event] {
    def writes(event: Event) = Json.obj(
      "classId" -> event.eventId,
      "name" -> event.name,
      "studioId" -> event.studioId,
      "style" -> event.style
    )
  }

  def all: List[Event] = {
    EventDao.fetchAllEvents
  }
}

case class Event(
              eventId: String,
              name: String,
              studioId: String,
              style: String
             ) {

}
