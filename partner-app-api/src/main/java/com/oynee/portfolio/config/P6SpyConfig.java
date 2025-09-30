package com.oynee.portfolio.config;

import com.p6spy.engine.spy.P6SpyOptions;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.util.Locale;

@Configuration
@Profile("local")
public class P6SpyConfig implements MessageFormattingStrategy {

    @PostConstruct
    public void setLogMessageFormat() {
        P6SpyOptions.getActiveInstance().setLogMessageFormat(this.getClass().getName());
    }

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {

        String returnSql = highlight(formatSql(sql));

        return String.format("[%s] | %d ms | %s", category, elapsed, returnSql);
    }

    private String highlight(String sql) {
        return FormatStyle.HIGHLIGHT.getFormatter().format(sql);
    }

    private boolean isDDL(String sql) {
        String convertedSql = sql.trim().toLowerCase(Locale.ROOT);
        return convertedSql.startsWith("create") || convertedSql.startsWith("alter") || convertedSql.startsWith("comment");
    }

    private boolean isDML(String sql) {
        String convertedSql = sql.trim().toLowerCase(Locale.ROOT);
        return convertedSql.startsWith("select") || convertedSql.startsWith("insert") || convertedSql.startsWith("update") || convertedSql.startsWith("delete");
    }

    private String formatSql(String sql) {
        if (isDDL(sql)) {
            return FormatStyle.DDL.getFormatter().format(sql);
        }
        if (isDML(sql)) {
            return FormatStyle.BASIC.getFormatter().format(sql);
        }

        return sql;
    }

}
