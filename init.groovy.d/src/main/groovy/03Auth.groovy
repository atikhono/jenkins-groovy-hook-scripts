import jenkins.model.Jenkins
import hudson.plugins.active_directory.ActiveDirectorySecurityRealm
import hudson.plugins.active_directory.GroupLookupStrategy

def env = System.getenv()

println("=== Install the Security Realm")

String domain = env['ACTIVE_DIRECTORY_DOMAIN']
String site = env['ACTIVE_DIRECTORY_SITE']
String bindName = env['ACTIVE_DIRECTORY_BIND_NAME']
String bindPassword = env['ACTIVE_DIRECTORY_BIND_PASSWORD']
String server = env['ACTIVE_DIRECTORY_SERVER']

def securityRealm = new ActiveDirectorySecurityRealm(domain, site, bindName, bindPassword, server, GroupLookupStrategy.AUTO)

Jenkins.instance.setSecurityRealm(securityRealm)
