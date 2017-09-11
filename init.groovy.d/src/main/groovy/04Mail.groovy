#!/usr/bin/env groovy
import jenkins.model.*

println("=== Setup mail")

env = System.getenv()

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setAdminAddress(env['JENKINS_ADMIN_ADDRESS'])
jenkinsLocationConfiguration.save()

def inst = Jenkins.getInstance()

// mailer plugin
def mail = inst.getDescriptor("hudson.tasks.Mailer")
mail.setReplyToAddress(env['MAILER_REPLY_TO_ADDRESS'])
mail.setSmtpHost(env['MAILER_SMTP_HOST'])
mail.setDefaultSuffix(env['MAILER_DEFAULT_SUFFIX'])
mail.save()

// email-ext plugin
def ext = inst.getDescriptorByType(hudson.plugins.emailext.ExtendedEmailPublisherDescriptor)
ext.smtpHost = env['EMAILEXT_SMTP_HOST']
ext.defaultReplyTo = env['EMAILEXT_DEFAULT_REPLY_TO']
ext.setDefaultSuffix(env['EMAILEXT_DEFAULT_SUFFIX'])
ext.defaultContentType = env['EMAILEXT_DEFAULT_CONTENT_TYPE']
ext.save()
