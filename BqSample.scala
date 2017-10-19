package syucream

import com.spotify.scio.ContextAndArgs
import com.spotify.scio.bigquery._

import org.apache.beam.sdk.io.gcp.bigquery.BigQueryIO.Write.{CreateDisposition, WriteDisposition}
 
object BqSample {
  @BigQueryType.fromQuery(
    """
      |SELECT
      |  name, number
      |FROM
      |  [bigquery-public-data:usa_names.usa_1910_current]
      |WHERE
      |  year = 2015 AND gender = 'M'
    """.stripMargin)
  class USANames

  @BigQueryType.toTable
  case class InitialCount(initial: String, number: Long)

  def main(cmdlineArgs: Array[String]): Unit = {
    val (sc, args) = ContextAndArgs(cmdlineArgs)

    val extracted = sc.typedBigQuery[USANames]()

    val transformed = extracted
      .flatMap(_.name match {
        case Some(n: String) => if (n.length >= 1) Seq(n.slice(0, 1)) else Nil
        case _ => Nil
      })
      .countByValue
      .map(kv => InitialCount(kv._1, kv._2))

    transformed.saveAsTextFile("gs://temp_syucream_dataflow/logs")
    transformed.saveAsTypedBigQuery(
      "deep-wares-182115:test_syucream.logs",
      WriteDisposition.WRITE_APPEND,
      CreateDisposition.CREATE_IF_NEEDED
    )

    sc.close().waitUntilFinish()
  }
}
