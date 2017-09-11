#!/usr/bin/env groovy

import jenkins.model.*
import jenkins.plugins.git.GitSCMSource
import org.jenkinsci.plugins.workflow.libs.*

println("=== Configure Global Pipeline Libraries")

GlobalLibraries globalLibs = GlobalConfiguration.all().get(GlobalLibraries.class)

String remote = env['PLIB_REMOTE']
String credentialsId = env['PLIB_CRED_ID']
String name = env['PLIB_NAME']
String defaultVersion = env['PLIB_DEFAULT_VERSION']

GitSCMSource scm = new GitSCMSource(null, remote, credentialsId, "*", "", false)
LibraryRetriever libRetriever = new SCMSourceRetriever(scm)
LibraryConfiguration libConfig = new LibraryConfiguration(name, libRetriever)
libConfig.setDefaultVersion(defaultVersion)
libConfig.setImplicit(true)

List<LibraryConfiguration> libraries= new ArrayList<LibraryConfiguration>()
libraries.add(libConfig)

globalLibs.setLibraries(libraries)
