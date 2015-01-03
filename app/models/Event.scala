package models

import dao.EventDao
import play.api.libs.json._
import org.joda.time.LocalTime

object Event {

  implicit val eventWrites: Writes[Event] = new Writes[Event] {
    def writes(event: Event) = Json.obj(
      "classId" -> event.eventId,
      "name" -> event.name,
      "studioId" -> event.studioId,
      "style" -> event.style,
      "day" -> event.day,
      "time" -> event.time.toString("HH:mm")
    )
  }

  def all: List[Event] = {
    EventDao.indexEvents
  }
}

case class Event(
              eventId: String,
              name: String,
              studioId: String,
              style: String,
              day: String,
              time: LocalTime
             ) {

}
