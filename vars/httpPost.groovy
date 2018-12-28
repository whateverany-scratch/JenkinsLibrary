#!/bin/env groovy
//@GrabResolver(name='artifactory', root='http://xxx/artifactory/all', m2Compatible=true)
//@Grab('org.apache.commons:commons-lang3:3.8.1')
@Grab('io.github.http-builder-ng:http-builder-ng-apache:1.0.3')
//@Grab('ch.qos.logback:logback-classic:1.2.3')
//@Grab('org.slf4j:jcl-over-slf4j:1.7.25')

import static groovyx.net.http.ApacheHttpBuilder.configure
//import groovyx.net.http.ApacheEncoders
import static groovyx.net.http.MultipartContent.multipart

//import org.apache.commons.lang3.exception.ExceptionUtils

//import org.slf4j.LoggerFactory
//def log = LoggerFactory.getLogger(this.getClass().getName())

@NonCPS
def doPost (def uri, def path, def query) {
      def http = new ApacheHttpBuilder()
      http.configure() {
        request.uri = uri
        request.uri.path = path
        request.uri.query = query
        request.contentType = 'multipart/form-data'
        //request.encoder('multipart/form-data', ApacheEncoders.&multipart)
      }
    
      def result = http.post() { }
    
      println('result='+result)
}

def call(body) {
  def config = [:]
  body.resolveStrategy = Closure.DELEGATE_FIRST
  body.delegate = config
  body()

  stage ('httpPost') {
    try {
      println('begin httpPost(uri='+config.uri+',path='+config.path+',query='+config.query)')
      println('end httpPost()')
    } catch (Exception e) {
      currentBuild.result = 'FAILED'
      //println('publishDefects caught Exception - '+e.toString())
      //println('rootCause - '+ExceptionUtils.getRootCauseMessage(e))
      //println('stacktrace - '+ExceptionUtils.getRootCauseStackTrace(e))
    }
  }
}

