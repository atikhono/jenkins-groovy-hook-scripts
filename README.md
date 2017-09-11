# Jenkins Configuration-as-Code Demo

Jenkins configuration-as-code demo made with groovy hook scripts.

This is massively based on [oleg-nenashev/demo-jenkins-config-as-code](https://github.com/oleg-nenashev/demo-jenkins-config-as-code). Just adding more plugin configuration code snippets.

[Introduction to Groovy Hooks](https://wiki.jenkins.io/display/JENKINS/Groovy+Hook+Script)

[Base docker image](https://github.com/jenkinsci/docker)

# Configured Plugins

* Active Directory, as security realm (https://wiki.jenkins.io/display/JENKINS/Active+Directory+plugin)
* Credentials, SSH and Plain (https://wiki.jenkins.io/display/JENKINS/Credentials+Plugin)
* Mailer (https://wiki.jenkins.io/display/JENKINS/Mailer)
* Email-ext Plugin (https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin)
* HipChat (https://wiki.jenkins.io/display/JENKINS/HipChat+Plugin)
* Artifactory (https://wiki.jenkins.io/display/JENKINS/Artifactory+Plugin)
* Pipeline Shared Groovy Libraries Plugin (https://wiki.jenkins.io/display/JENKINS/Pipeline+Shared+Groovy+Libraries+Plugin)
* Build Failure Analyzer (https://wiki.jenkins.io/display/JENKINS/Build+Failure+Analyzer)
* SCM Sync configuration Plugin (https://wiki.jenkins.io/display/JENKINS/SCM+Sync+configuration+plugin)

Additionally, it shows how to configure a Linux slave and Parallels Desktop Cloud slave with SSH credentials. Hook scripts can be configured via environment variables set in jenkins2.sh.

# Building
```
docker build -t atikhono/jenkins-groovy-hook-scripts .
```
# Usage
```
docker run -p 8080:8080 -p 50000:50000 atikhono/jenkins-groovy-hook-scripts
```
