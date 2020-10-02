import org.apache.spark.sql.types._

package object util {

//  MoviesRatings
val mRatingSchema = StructType(
  Array(
    StructField("tconst",StringType),
    StructField("averageRating",DoubleType),
    StructField("numVotes",LongType)
  ))

//  Movies Titles
var mTitlesSchema = StructType(
  Array(
      StructField("tconst",StringType),
      StructField("titleType",StringType),
      StructField("primaryTitle",StringType),
      StructField("originalTitle",StringType),
      StructField("isAdult",StringType),
      StructField("startYear",StringType),
      StructField("endYear",StringType),
      StructField("runtimeMinutes",StringType),
      StructField("genres",StringType)
    ))

}

//tconst:String,
//titleType:String,
//primaryTitle:String,
//originalTitle:String,
//isAdult:Option[Int],
//startYear:Option[Long],
//endYear:Option[Long],
//runtimeMinutes:Option[Long],
//genres:List[String]
