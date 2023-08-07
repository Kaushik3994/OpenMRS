package com.qa.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Log {
    //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger(Log.class);

    static {
        String filename = "./logs/automation_log_" + DateUtils.getCurrentDateTime(ZoneId.of("EST5EDT"), DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss")) + ".log";
        initializeYourLogger(filename, "[%-5level] %d{yyyy-MM-dd HH:mm:ss} - %msg%n");
    }

    public static void pass(String message) {
        Log.info("[PASS] " + message);
    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn(message);
    }


    public static void info(String message) {
        Log.info(message);

    }

     //Error Level Logs
    public static void error(String message) {
        Log.error(message);
    }

    public static void initializeYourLogger(String fileName, String pattern) {

        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();
        builder.setStatusLevel(Level.INFO);
        builder.setConfigurationName("DefaultFileLogger");

        // set the pattern layout and pattern
        LayoutComponentBuilder layoutBuilder = builder.newLayout("PatternLayout")
                .addAttribute("pattern", pattern);

        // create a file appender
        AppenderComponentBuilder appenderBuilder = builder.newAppender("LogToFile", "File")
                .addAttribute("fileName", fileName)
                .add(layoutBuilder);

        // create a console appender
        AppenderComponentBuilder consoleappenderBuilder = builder.newAppender("Console", "CONSOLE")
                .addAttribute("target", ConsoleAppender.Target.SYSTEM_OUT)
                .add(layoutBuilder);


        builder.add(appenderBuilder);
        builder.add(consoleappenderBuilder);

        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.INFO);

        rootLogger.add(builder.newAppenderRef("LogToFile"));
        rootLogger.add(builder.newAppenderRef("Console"));
        builder.add(rootLogger);
        Configurator.reconfigure(builder.build());
    }
}


