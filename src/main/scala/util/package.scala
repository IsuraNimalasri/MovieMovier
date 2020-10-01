import org.apache.spark.sql.types._

package object util {

//  MoviesRatings
val mRatingSchema = StructType(
  Array(
    StructField("tconst",StringType),
    StructField("averageRating",DoubleType),
    StructField("numVotes",LongType)
  )
)


}
