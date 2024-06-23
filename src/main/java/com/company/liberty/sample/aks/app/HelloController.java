package com.company.liberty.sample.aks.app;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 */
@Path("/hello")
@Singleton
public class HelloController {


    @Inject
    @ConfigProperty(name = "hello_messages_01",  defaultValue="hello message 01 not configured")
    private String helloMessages01;

    @Inject
    @ConfigProperty(name = "hello_messages_02",  defaultValue="hello message 02 not configured")
    private String helloMessages02;

    @Inject
    @ConfigProperty(name = "hello_messages_03",  defaultValue="hello message 03 not configured")
    private String helloMessages03;

    @Inject
    @ConfigProperty(name = "hello_messages_04",  defaultValue="hello message 04 not configured")
    private String helloMessages04;

    @Inject
    @ConfigProperty(name = "hello_messages_05",  defaultValue="hello message 05 not configured")
    private String helloMessages05;

    @GET
    public String sayHello() {
        String res = """
                    <html>
                        <head>
                            <title>Sample App - Config Externalization Samples</title>
                        </head>
                        <style>
                            li {
                                height: 50px;
                                padding-top: 10px;
                                padding-bottom: 10px;
                            }
                        </style>
                        <body>
                            <h2>Sample App - Config Externalization Samples</h2>
                            <ul>
                                <li>Message 01
                                    <ul><li>%s</li></ul>
                                </li>
                                <li>Message 02
                                    <ul><li>%s</li></ul>
                                </li>
                                <li>Message 03
                                    <ul><li>%s</li></ul>
                                </li>
                                <li>Message 04
                                    <ul><li>%s</li></ul>
                                </li>
                                <li>Message 05
                                    <ul><li>%s</li></ul>
                                </li>
                            </ul>
                        </body>
                    </html>
                    """.formatted(helloMessages01, helloMessages02, helloMessages03, helloMessages04, helloMessages05);
        return res;
    }
}
