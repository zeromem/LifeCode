import scala.io.Source


object FileTest {
  def main(args: Array[String]): Unit = {
    val baseUrl = "http://10.4.46.242:8090/phoneEncrypt/PhoneEncrypt.html?req=%7B%22phone%22:%22_YOUR_INPUT%22,%22token%22:%22mzewmji4mtk5nda2mjiwnjyxmte3mze3mja5mzy1%22%7D"
    for(line <- Source.fromFile("/home/u_tel_hlwb_mqj/zuochuang/little").getLines()) {
      val url = baseUrl.replace("_YOUR_INPUT", line.split(',')(1))
      val res = Source.fromURL(url).mkString
      res.last
      line.split(',')(1)
    }
  }
}
