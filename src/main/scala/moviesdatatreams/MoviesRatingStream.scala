package moviesdatatreams

import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.{Column,DataFrame, SparkSession}
import util._



object MoviesRatingStream {

//  create a spark session for MovieMover --> MoviesRatingStraem
  var spark = SparkSession.builder()
    .appName("MovieMover")
    .master("local[2]")
    .getOrCreate()

  val mTBasics:DataFrame = spark.read
//    .schema(mTitlesSchema)
    .option("inferSchema", true)
    .format("csv")
    .option("header","true")
    .option("delimiter", "\t")
    .load("src/main/data/MoviesTitleBasics")

  mTBasics.show(5)

  // joining static DFs
//  val joinCondition = guitarPlayers.col("band") === bands.col("id")
//  val guitaristsBands = guitarPlayers.join(bands, joinCondition, "inner")


  def mRatingFromFiles(): Unit = {
//    Reading DF
    val mRatings:DataFrame = spark.readStream
      .format("csv")
      .option("header","true")
      .option("delimiter", "\t")
      .schema(mRatingSchema)
      .load(path = "src/main/data/MoviesRatings")

//    more than 500 votes with SQL
//    mRatings.createOrReplaceTempView("ratings")
//    val populerMovies:DataFrame = spark.sql("SELECT * FROM ratings WHERE numVotes > 500")

    val populerMovies:DataFrame = mRatings.filter("numVotes > 500")
//    populerMovies.col("Value").as("Value")

    // joining static DFs
    val joinCondition = mTBasics.col("tconst") === populerMovies.col("tconst")
    val moviesRatings = mTBasics.join(populerMovies, joinCondition, "inner")

    val query = moviesRatings.writeStream
      .format("console")
      .outputMode("append")
      .start()

//    Wait to stream finished
    query.awaitTermination()

  }



  def main(args: Array[String]): Unit = {
    mRatingFromFiles()
  }


}
