import jenkins.model.Jenkins
import hudson.slaves.DumbSlave
import hudson.model.Node.Mode
import hudson.plugins.sshslaves.SSHLauncher
import hudson.plugins.sshslaves.verifiers.NonVerifyingKeyVerificationStrategy
import hudson.plugins.sshslaves.verifiers.SshHostKeyVerificationStrategy
import hudson.slaves.RetentionStrategy

println("=== Add SSH slave")

env = System.getenv()

String nodeCredentialID = env['NODE_CRED_ID']
String nodeName = env['NODE_NAME']
String nodeWs = env['NODE_WS']
String nodeHost = env['NODE_HOST']
String nodeDescription = env['NODE_DESCRIPTION']
String nodeNumExecutors = env['NODE_NUM_EXECUTORS']
String nodeLabel = env['NODE_LABEL']

SshHostKeyVerificationStrategy strategy = new NonVerifyingKeyVerificationStrategy()
DumbSlave ds = new DumbSlave(
    nodeName,
    nodeWs,
    new SSHLauncher(nodeHost, 22, nodeCredentialID, null, null, null, null, 30, 20, 10, strategy)
)

ds.setNodeDescription(nodeDescription)
ds.setNumExecutors(nodeNumExecutors.toInteger())
ds.setLabelString(nodeLabel)
ds.setMode(Mode.NORMAL)
ds.setRetentionStrategy(new RetentionStrategy.Always())

Jenkins.instance.addNode(ds)
