package no.nav.pensjon.selv.config;

import no.stelvio.common.context.support.ComponentIdHolder;
import org.springframework.context.annotation.Bean;

public class CfgPenContext {
    @Bean
    public ComponentIdHolder componentIdHolder(){
        return new ComponentIdHolder("PP01");
    }
//    	<bean id="componentIdHolder" class="no.stelvio.common.context.support.ComponentIdHolder" scope="singleton">
//		<constructor-arg value="PP01" />
//	</bean>
}
