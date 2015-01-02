package dao

import models.Event

object EventDao {
  def fetchAllEvents: List[Event] = {

    // TODO
    List(
      Event("Ace", "Base", "Case", "Daisy"),
      Event("Alpha", "Bravo", "Charlie", "Delta")
    )
  }

}
