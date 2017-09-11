FROM jenkins/jenkins:lts
MAINTAINER Anna Tikhonova <anna.m.tikhonova@gmail.com>
LABEL Description="This demo shows how to setup Jenkins Configuration-as-Code with Docker and Groovy Hook Scripts" Vendor="Anna Tikhonova" Version="0.1"

RUN /usr/local/bin/install-plugins.sh \
    scm-sync-configuration \
    artifactory \
    active-directory \
    credentials \
    ssh-credentials \
    build-failure-analyzer \
    ssh-slaves \
    plain-credentials \
    mailer \
    email-ext \
    hipchat \
    parallels-desktop \
    git \
    workflow-aggregator

COPY init.groovy.d/src/main/groovy/ /usr/share/jenkins/ref/init.groovy.d/

COPY jenkins2.sh /usr/local/bin/jenkins2.sh
ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins2.sh"]
