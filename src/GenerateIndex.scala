//> using scala 3
//> using dep com.softwaremill.sttp.client3::core:3.9.0
//> using dep com.lihaoyi::ujson:3.1.3
//> using dep com.lihaoyi::os-lib:0.9.1
//> using options -Wunused:all -deprecation

object GenerateIndex {

  def main(args: Array[String]): Unit = {

    val output = "index.json"

    val correttoIndex0      = Corretto.fullIndex(GhToken.token)
    val graalvmLegacyIndex0 = GraalvmLegacy.fullIndex(GhToken.token)
    val graalvmIndex0       = Graalvm.fullIndex(GhToken.token)
    val oracleIndex0        = OracleJDKs.index()
    val adoptIndex0         = Temurin.fullIndex(GhToken.token)
    val zuluIndex0          = Zulu.index()
    val libericaIndex0      = Liberica.index()

    val json =
      (graalvmLegacyIndex0 + graalvmIndex0 + oracleIndex0 + adoptIndex0 + zuluIndex0 + libericaIndex0 + correttoIndex0).json
    val dest = os.Path(output, os.pwd)
    os.write.over(dest, json)
    System.err.println(s"Wrote $dest")
  }
}
