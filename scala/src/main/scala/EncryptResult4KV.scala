import java.io.FileWriter
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

import scala.io.Source

object EncryptResult4KV {
  private val KEY = "contactqqzeromem"
  private val AESTYPE = "AES/ECB/PKCS5Padding"

  def encrypt(keyStr: String, plainText: String): String = {
    val key = generateKey(keyStr)
    val cipher = Cipher.getInstance(AESTYPE)
    cipher.init(Cipher.ENCRYPT_MODE, key)
    val encrypt = cipher.doFinal(plainText.getBytes)
    Base64.getEncoder.encodeToString(encrypt)
  }

  def decrypt(keyStr: String, encryptData: String): String = {
    val key = generateKey(keyStr)
    val cipher = Cipher.getInstance(AESTYPE)
    cipher.init(Cipher.DECRYPT_MODE, key)
    val decrypt = cipher.doFinal(Base64.getDecoder.decode(encryptData))
    new String(decrypt).trim
  }

  @throws[Exception]
  private def generateKey(key: String) = try {
    val keySpec = new SecretKeySpec(key.getBytes, "AES")
    keySpec
  } catch {
    case e: Exception =>
      e.printStackTrace()
      throw e
  }

  def main(args: Array[String]): Unit = {
    val contactInfoPath = args(0)
    val contactListPath = args(1)
    val idMappingPath = args(2)
    val outputPath = args(3)
    val timestamp = contactInfoPath.split('.').last
    if (!timestamp.matches("\\d{14}")) {
      println(s"wrong file name! $contactInfoPath $contactListPath")
      sys.exit(1)
    }
    val date = timestamp.substring(0, 8)

    val infos = Source.fromFile(contactInfoPath).getLines()
    val lists = Source.fromFile(contactListPath).getLines()
    val ids = Source.fromFile(idMappingPath).getLines()

    val trimLists = lists.map(line =>
      if (line.indexOf('_', 150 * 60) == -1) {
        line
      } else {
        line.substring(0, line.indexOf('_', 150 * 60))
      }
    )

    val writer = new FileWriter(outputPath)
    val lines = (infos ++ trimLists ++ ids).toList
    val count = lines.size
    writer.write(s"smarteyes_${date}_count\t$count\n")
    lines.zipWithIndex.foreach {
      case (line, index) =>
        val k = s"smarteyes_${date}_$index"
        val v = encrypt(KEY, line)
        writer.write(s"$k\t$v\n")
    }
    writer.close()
  }
}