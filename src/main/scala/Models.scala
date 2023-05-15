/*
  As part of this application, we create a simple content rating exercise, from two topics:
    - tv-show-topic: where the entire TV show collection is produced
    - rating-topic: where ratings on the same show are produced
 */

// The below data classes correspond to the above two topics
case class Key(@AvroName("show_id") showId: String)
case class Rating(user: String, value: Short)
case class TvShow(platform: Platform, name: String, releaseYear: Int, imdb: Option[Double])
