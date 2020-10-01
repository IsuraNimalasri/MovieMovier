package moviesdatatreams

import org.apache.spark.sql.{DataFrame, SparkSession}
import util._
import org.apache.spark.sql.streaming.Trigger
import scala.concurrent.duration._
import org.apache.spark.sql.functions._


object MoviesRatingStream {

//  create a spark session for MovieMover --> MoviesRatingStraem
  var spark = SparkSession.builder()
    .appName("MovieMover")
    .master("local[2]")
    .getOrCreate()

  def readFromFiles(): Unit = {
//    Reading DF
    val mRatings:DataFrame = spark.readStream
      .format("csv")
      .option("header","true")
      .option("delimiter", "\t")
      .schema(mRatingSchema)
      .load(path = "src/main/data/MoviesRatings")

    val query = mRatings.writeStream
      .format("console")
      .outputMode("append")
      .start()

//    Wait to stream finished
    query.awaitTermination()

  }

  def main(args: Array[String]): Unit = {
    readFromFiles()
  }


}
