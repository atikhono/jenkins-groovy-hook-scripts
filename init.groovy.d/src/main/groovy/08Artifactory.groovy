#!/usr/bin/env groovy
import jenkins.model.*
import org.jfrog.hudson.*
import com.cloudbees.plugins.credentials.CredentialsProvider
import com.cloudbees.plugins.credentials.common.StandardUsernameCredentials
import com.cloudbees.plugins.credentials.SystemCredentialsProvider
import com.cloudbees.plugins.credentials.CredentialsScope
import com.cloudbees.plugins.credentials.domains.Domain
import com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl

println ("== Setup Artifactory")

env = System.getenv()

String credentialsId = env['ARTIFACTORY_CREDENTIALS_ID']
String description = env['ARTIFACTORY_CREDENTIALS_DESCRIPTION']
String username = env['ARTIFACTORY_USERNAME']
String password = env['ARTIFACTORY_PASSWORD']

String serverId = env['ARTIFACTORY_SERVER_ID']
String serverUrl = env['ARTIFACTORY_URL']

def descriptor = Jenkins.getInstance().getDescriptor("org.jfrog.hudson.ArtifactoryBuilder")

UsernamePasswordCredentialsImpl artifactoryCred = new UsernamePasswordCredentialsImpl(CredentialsScope.GLOBAL, credentialsId, description, username, password)
SystemCredentialsProvider.getInstance().addCredentials(Domain.global(), artifactoryCred)

CredentialsConfig credentials = new CredentialsConfig(null, null, credentialsId)

def server = [new ArtifactoryServer(
  serverId,
  serverUrl,
  credentials,
  credentials,
  1500,
  false,
  3)
]

descriptor.setArtifactoryServers(server)
descriptor.setUseCredentialsPlugin(true)
descriptor.save()
