package no.nav.presentation.pensjon.common.webflow;

import org.springframework.webflow.conversation.impl.SessionBindingConversationManager;
import org.springframework.webflow.execution.repository.impl.DefaultFlowExecutionRepository;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.executor.FlowExecutorImpl;

/**
 * Configuration setter for changing the time period, lockTimeoutSeconds, that can elapse before a timeout occurs on an attempt
 * to acquire a conversation lock. The default timeout defined by Spring Web Flow is 30 seconds.
 * <p>
 * For using this configuration setter, create a bean as follows in the Spring Web Flow context file. The actual lockTimeoutSeconds value must be tweaked as needed.
 *
 * <pre>
 * &lt;bean id=&quot;webFlowLockTimeoutSetter&quot;
 *     class=&quot;no.nav.presentation.pensjon.common.webflow.WebFlowLockTimeoutSetter&quot;&gt;
 *   &lt;property name=&quot;flowExecutor&quot; ref=&quot;flowExecutor&quot;/&gt;
 *   &lt;property name=&quot;lockTimeoutSeconds&quot; value=&quot;120&quot;/&gt;
 * &lt;/bean&gt;
 * </pre>
 */
public class WebFlowLockTimeoutSetter {

    private FlowExecutor flowExecutor;

    public SessionBindingConversationManager getConversationManager() {
        FlowExecutorImpl flowExecutorImpl = (FlowExecutorImpl) flowExecutor;
        DefaultFlowExecutionRepository repository = (DefaultFlowExecutionRepository) flowExecutorImpl.getExecutionRepository();
        return (SessionBindingConversationManager) repository.getConversationManager();
    }

    public int getLockTimeoutSeconds() {
        return getConversationManager().getLockTimeoutSeconds();
    }

    public void setLockTimeoutSeconds(int lockTimeoutSeconds) {
        getConversationManager().setLockTimeoutSeconds(lockTimeoutSeconds);
    }

    public void setFlowExecutor(FlowExecutor flowExecutor) {
        this.flowExecutor = flowExecutor;
    }
}
