#! /bin/bash -e
# Additional wrapper, which adds custom environment options for the run

extra_java_opts=( \
  '-Djenkins.install.runSetupWizard=false' \
  '-Djenkins.model.Jenkins.slaveAgentPort=50000'
)

#
# init.groovy.d settings
#
# Active Directory plugin settings
export ACTIVE_DIRECTORY_DOMAIN=mycompany.com
export ACTIVE_DIRECTORY_SITE=
export ACTIVE_DIRECTORY_BIND_NAME=admin@mycompany.com
export ACTIVE_DIRECTORY_BIND_PASSWORD=123456
export ACTIVE_DIRECTORY_SERVER=ad.mycompany.net:1234

# Mailer plugin settings
export JENKINS_ADMIN_ADDRESS=builder@mycompany.com
export MAILER_REPLY_TO_ADDRESS=builder@mycompany.com
export MAILER_SMTP_HOST=mailer.mycompany.net
export MAILER_DEFAULT_SUFFIX=@mycompany.com

# emailext plugin settings
export EMAILEXT_SMTP_HOST=mailer.mycompany.net
export EMAILEXT_DEFAULT_REPLY_TO=builder@mycompany.com
export EMAILEXT_DEFAULT_SUFFIX=@mycompany.com
export EMAILEXT_DEFAULT_CONTENT_TYPE=text/html

# ssh-credentials settings
export SSHCRED_USENAME=scm
export SSHCRED_ID=scm
export SSHCRED_PASSPHRASE=
export SSHCRED_DESCRIPTION="SCM server credentials"

# Global pipeline library
export PLIB_REMOTE=ssh://scm.mycompany.net/pipeline-library.git
export PLIB_CRED_ID=scm
export PLIB_NAME=pipeline-library
export PLIB_DEFAULT_VERSION=master

# Artifactory settings
export ARTIFACTORY_CREDENTIALS_ID=artifactory
export ARTIFACTORY_CREDENTIALS_DESCRIPTION="Artifactory credentials"
export ARTIFACTORY_USERNAME=artifactory
export ARTIFACTORY_PASSWORD=123456
export ARTIFACTORY_SERVER_ID=1234567890qweasdzxc
export ARTIFACTORY_URL=https://artifactory.mycompany.net/artifactory

# HipChat settings
export HIPCHAT_CRED_ID=HipChat-API-Token
export HIPCHAT_CRED_DESCRIPTION="HipChat API Token"
export HIPCHAT_TOKEN=dummytokenstring
export HIPCHAT_SERVER=api.hipchat.com
export HIPCHAT_ROOM=Builder
export HIPCHAT_SENDAS=Builder

# Dummy SSH agent
export NODE_CRED_ID=scm
export NODE_NAME=builder.mycompany.net
export NODE_WS=/home/scm/jenkins
export NODE_HOST=builder.mycompany.net
export NODE_DESCRIPTION="Linux agent"
export NODE_NUM_EXECUTORS=2
export NODE_LABEL="Linux Centos"

# Dummy Parallels Desktop Cloud
export PDCLOUD_HOST_CRED_ID=scm
export PDCLOUD_HOST_NAME=pdcloud.mycompany.net
export PDCLOUD_HOST_LABEL=pdcloud
export PDCLOUD_HOST_WS=/Users/scm/jenkins2
export PDCLOUD_HOST_HOST=builder01.mycompany.net
export PDCLOUD_SLAVE_NAME=builder01.mycompany.net
export PDCLOUD_SLAVE_LABEL=OSX10.11.6
export PDCLOUD_SLAVE_WS=/Users/scm/jenkins2
export PDCLOUD_SLAVE_HOST=builder01.mycompany.net
export PDCLOUD_SLAVE_CRED_ID=scm

# SCM Sync plugin
export SCMSYNC_REMOTE=
#
# init.groovy.d settings
#

export JAVA_OPTS="$JAVA_OPTS ${extra_java_opts[@]}"

exec /usr/local/bin/jenkins.sh "$@"
