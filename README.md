# JenkinsLibrary

JenkinsLibrary has been configured to use this GitHub repository for a Pipeline library. To include this library in Jenkins pipeline builds, add this line to the JenkinsFile:

`@Library('JenkinsLibrary@<RELEASE>')`

Where `<RELEASE>` is a valid release, tag or branch. e.g.

`@Library('JenkinsLibrary@0.1.0')`
