#!/usr/bin/env groovy
import jenkins.model.*
import com.cloudbees.plugins.credentials.SystemCredentialsProvider;
import com.cloudbees.plugins.credentials.domains.Domain;
import com.cloudbees.plugins.credentials.CredentialsScope;
import org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl;
import hudson.util.Secret

println ("=== Setup Hipchat notifier")

env = System.getenv()

String credentialsId = env['HIPCHAT_CRED_ID']
String description = env['HIPCHAT_CRED_DESCRIPTION']
String token = env['HIPCHAT_TOKEN']
String server = env['HIPCHAT_SERVER']
String room = env['HIPCHAT_ROOM']
String sendAs = env['HIPCHAT_SENDAS']

SystemCredentialsProvider.getInstance().addCredentials(Domain.global(), new StringCredentialsImpl(CredentialsScope.GLOBAL, credentialsId, description, Secret.fromString(token)))

def hipchat = Jenkins.getInstance().getDescriptorByType(jenkins.plugins.hipchat.HipChatNotifier.DescriptorImpl.class)
hipchat.setServer(server)
hipchat.setRoom(room)
hipchat.setSendAs(sendAs)
hipchat.setCredentialId(credentialsId)

hipchat.save()
