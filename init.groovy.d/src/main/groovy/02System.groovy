#!/usr/bin/env groovy
import jenkins.CLI
import jenkins.security.s2m.AdminWhitelistRule
import jenkins.model.Jenkins

println("=== Disable Remoting CLI")
CLI.get().enabled = false

println("=== Enable agent to master security subsystem")
Jenkins.instance.getInjector().getInstance(AdminWhitelistRule.class).setMasterKillSwitch(false)
