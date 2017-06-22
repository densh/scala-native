package java.security.cert

class CertificateFactory {
  def generateCertPath(certificates: java.util.List[_]): CertPath = ???
}

object CertificateFactory {
  def getInstance(name: String): CertificateFactory = ???
}
