package org.example.server;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class EmbeddedServer {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("tomcat");
        tomcat.setPort(8083);
        tomcat.getConnector();
        tomcat.addWebapp("", new File("src/main/webapp").getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
