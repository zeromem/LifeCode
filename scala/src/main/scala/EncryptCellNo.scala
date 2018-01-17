import scala.io.Source
import java.io.FileWriter



object EncryptCellNo {
  "msc  bcp sec long  lat"
  def main(args: Array[String]): Unit = {
    val baseUrl = "http://10.4.46.242:8090/phoneEncrypt/PhoneEncrypt.html?req=%7B%22phone%22:%22_YOUR_INPUT%22,%22token%22:%22mzewmji4mtk5nda2mjiwnjyxmte3mze3mja5mzy1%22%7D"
    val r = scala.util.Random
    val writer = new FileWriter(args(0) + ".cell_no")
    var i = 0
    for(line <- Source.fromFile(args(0)).getLines()) {
      val arr = line.split('\t')
      val bcp10 = arr(1)
      val sec10 = arr(2)
      var bcp16 = bcp10.toInt.toHexString.toUpperCase
      bcp16 = "0" * (3 - bcp16.length) + bcp16
      val plainCellNo = bcp16 + sec10
      val url = baseUrl.replace("_YOUR_INPUT", plainCellNo)
      val res = Source.fromURL(url).mkString
      val encryptCellNo = res.substring(res.lastIndexOf(':') + 3, res.lastIndexOf('\\'))
      val output = arr(0) + "\t" + encryptCellNo + "\t" + arr(3) + "\t" + arr(4)
      writer.write(output + "\n")
      Thread.sleep(10 + r.nextInt(10))
      println(i + " " + plainCellNo + " " + output)
      i = i + 1
    }
    writer.close()
  }
}
