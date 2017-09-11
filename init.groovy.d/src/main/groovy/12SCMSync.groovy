import hudson.Plugin;
import jenkins.model.Jenkins;
import hudson.plugins.scm_sync_configuration.scms.ScmSyncGitSCM
import hudson.plugins.scm_sync_configuration.xstream.migration.DefaultSSCPOJO
import hudson.plugins.scm_sync_configuration.xstream.migration.ScmSyncConfigurationPOJO

println("=== Configure SCM Sync")

env = System.getenv()

String remote = env['SCMSYNC_REMOTE']
if (!remote) {
    return
}

Jenkins jenkins = Jenkins.getInstance();
Plugin scmSync = jenkins.getPlugin("scm-sync-configuration");

ScmSyncConfigurationPOJO config = new DefaultSSCPOJO();
config.setScm(new ScmSyncGitSCM());
config.setScmRepositoryUrl("scm:git:" + remote);
config.setDisplayStatus(true);
config.setCommitMessagePattern("[message]");
config.setManualSynchronizationIncludes([]);

scmSync.loadData(config);
scmSync.save();
scmSync.load();
