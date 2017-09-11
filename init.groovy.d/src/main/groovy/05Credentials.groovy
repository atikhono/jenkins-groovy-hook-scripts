import com.cloudbees.plugins.credentials.SystemCredentialsProvider;
import com.cloudbees.plugins.credentials.domains.Domain;
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey;
import com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey.UsersPrivateKeySource;
import com.cloudbees.plugins.credentials.CredentialsScope;

println("=== Configure credentials");

env = System.getenv();

String username = env['SSHCRED_USENAME'];
String id = env['SSHCRED_ID'];
String passphrase = env['SSHCRED_PASSPHRASE'];
String description = env['SSHCRED_DESCRIPTION'];

SystemCredentialsProvider.getInstance().addCredentials(Domain.global(), new BasicSSHUserPrivateKey(CredentialsScope.GLOBAL, id, username, new UsersPrivateKeySource(), passphrase, description));
