import jenkins.model.Jenkins
import com.parallels.desktopcloud.ParallelsDesktopCloud
import com.parallels.desktopcloud.ParallelsDesktopVM
import hudson.slaves.ComputerLauncher
import hudson.plugins.sshslaves.SSHLauncher
import hudson.plugins.sshslaves.verifiers.NonVerifyingKeyVerificationStrategy
import hudson.plugins.sshslaves.verifiers.SshHostKeyVerificationStrategy

println("=== Add Parallels Desktop Cloud slaves")

env = System.getenv()

String nodeCredentialID = env['PDCLOUD_HOST_CRED_ID']
SshHostKeyVerificationStrategy strategy = new NonVerifyingKeyVerificationStrategy()

Jenkins.instance.clouds.clear()

Jenkins.instance.clouds.add(
    new ParallelsDesktopCloud(
        env['PDCLOUD_HOST_NAME'],
        env['PDCLOUD_HOST_LABEL'],
        env['PDCLOUD_HOST_WS'],
        new SSHLauncher(env['PDCLOUD_HOST_HOST'], 22, nodeCredentialID, null, null, null, null, 30, 20, 10, strategy),
        false,
        [
            new ParallelsDesktopVM(
                env['PDCLOUD_SLAVE_NAME'],
                env['PDCLOUD_SLAVE_LABEL'],
                env['PDCLOUD_SLAVE_WS'],
                new SSHLauncher(env['PDCLOUD_SLAVE_HOST'], 22, env['PDCLOUD_SLAVE_CRED_ID'], null, null, null, null, 30, 20, 10, strategy),
                "Suspend"
            )
        ]
    )
)
