package com.challengers.config;

import de.javakaffee.web.msm.MemcachedBackupSessionManager;
import org.apache.catalina.Context;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Malika(mxp134930) on 12/5/2015.
 */
@Configuration
@EnableCaching
public class MemCacheConfig {

    @Bean
    public EmbeddedServletContainerFactory tomcat() {
        return new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                MemcachedBackupSessionManager manager = new MemcachedBackupSessionManager();
                manager.setMemcachedNodes("n1:127.0.0.1:11211");
                manager.setRequestUriIgnorePattern(".*\\.(ico|png|gif|jpg|css|js)$");
                context.setManager(manager);
            }
        };
    }
}
