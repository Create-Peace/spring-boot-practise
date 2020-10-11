package com.ming.practise.common.config;

import org.hibernate.dialect.PostgreSQL95Dialect;

import java.sql.Types;

public class JsonbPostgresDialect extends PostgreSQL95Dialect {

    public JsonbPostgresDialect() {
        this.registerColumnType(Types.JAVA_OBJECT,"jsonb");
    }
}
