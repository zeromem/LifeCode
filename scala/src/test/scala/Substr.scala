object Substr {
  def main(args: Array[String]): Unit = {
    val a = "a 1 as_av_sklfdj_gh_oueiwt"
    val b = if (a.indexOf('_', 100) == -1) a else a.substring(0, a.indexOf('_', 100))
    println(b)
  }
}
