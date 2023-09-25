package com.factory.common;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Sql(value = {
        "file:sql/data/v0_create_factory_data_schema.sql",
        "file:sql/data/v1_create_sensors_mean_tables.sql",
        "file:sql/data/v2_create_compressor_state_table.sql",
        "file:sql/data/v3_create_reports_tables.sql",

        "file:sql/users/v0_create_schema.sql",
        "file:sql/users/v1_create_users_table.sql",
        "file:sql/users/v2_create_roles_table.sql",
        "file:sql/users/v3_create_user_roles_table.sql"
})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SqlLoader {
}
