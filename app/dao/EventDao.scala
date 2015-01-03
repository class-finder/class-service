package dao

import anorm._
import models.Event
import play.api.Play.current
import play.api.db.DB
import org.joda.time.LocalTime

import java.sql.Time

object EventDao {
  implicit def rowToTime: Column[Time] = Column.nonNull { (value, meta) =>
    val MetaDataItem(qualified, nullable, clazz) = meta
    value match {
      case d: Time => Right(d)
      case _ => Left(TypeDoesNotMatch("Cannot convert " + value + ":" + value.asInstanceOf[AnyRef].getClass + " to java.sql.Time for column " + qualified))
    }
  }


  def indexEvents: List[Event] = {

    DB.withConnection { implicit c =>
      val eventRows = SQL(
        """
          | SELECT
          |   HEX(`event`.`event_id`) AS x_event_id,
          |   `event`.`name`,
          |   HEX(`event`.`studio_id`) AS x_studio_id,
          |   `style`.`label`,
          |   `event`.`day`,
          |   `event`.`time`
          | FROM `event`
          | LEFT JOIN `style`
          |   ON `event`.`style_id`=`style`.`style_id`;
        """.stripMargin).apply()

      eventRows.map { row =>
        Event(
          eventId = row[String]("x_event_id"),
          name = row[String]("name"),
          studioId = row[String]("x_studio_id"),
          style = row[String]("label"),
          day = row[String]("day"),
          time = new LocalTime(row[Time]("time"))
        )
      }.force.toList
    }
  }

}
