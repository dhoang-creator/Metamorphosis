case class Key(@AvroName("show_id") showId: String)
case class Rating(user: String, value: Short)
case class TvShow(platform: Platform, name: String, releaseYear: Int, imdb: Option[Double])
