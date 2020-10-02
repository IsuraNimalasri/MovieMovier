package util

case class MoviesTitles(
                         tconst:String,
                         titleType:String,
                         primaryTitle:String,
                         originalTitle:String,
                         isAdult:String,
                         startYear:String,
                         endYear:String,
                         runtimeMinutes:String,
                         genres:List[String]
                       )
