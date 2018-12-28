def call(body) {
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  node () {
    stage ('cleanws') {
      try {
        println "begin cleanws()"
        cleanWs()
        println "end cleanws()"
      } catch (Exception e) {
        currentBuild.result = 'FAILED'
        println 'checkmarx caught exception - '+e.toString()
      }
    }
  }
}
