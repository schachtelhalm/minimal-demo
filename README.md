# minimal-demo

This is a minimal demo to produce a `Trailing junk on timestamp` error in a JUnit test with postgres 10 and spring-data-jdbc.

The project can be built successfully if you change the spring boot version in `build.gradle.kts` to `2.3.8.RELEASE`.

`./gradlew --stacktrace -i clean build`

With spring boot version `2.3.9.RELEASE` the build fails because of

`java.lang.NumberFormatException: Trailing junk on timestamp: 'T21:00:59.058035+01:00'`


The service under test fetches all rows from table `animal` that were updated after the given `OffsetDateTime`.
Query: `SELECT * FROM animal WHERE updated_at > ?`



    FAILURE: Build failed with an exception.

    Task :test FAILED

    AnimalRepositoryIntegrationTest > someTestMethod() FAILED
        org.springframework.dao.DataIntegrityViolationException: PreparedStatementCallback; SQL [SELECT * FROM animal WHERE updated_at > ?]; Bad value for type timestamp/date/time: {1}; nested exception is org.postgresql.util.PSQLException: Bad value for type timestamp/date/time: {1}
            at org.springframework.jdbc.support.SQLStateSQLExceptionTranslator.doTranslate(SQLStateSQLExceptionTranslator.java:104)
            at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:72)
            at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)
            at org.springframework.jdbc.support.AbstractFallbackSQLExceptionTranslator.translate(AbstractFallbackSQLExceptionTranslator.java:81)
            at org.springframework.jdbc.core.JdbcTemplate.translateException(JdbcTemplate.java:1443)
            at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:633)
            at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:669)
            at org.springframework.jdbc.core.JdbcTemplate.query(JdbcTemplate.java:694)
            at org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate.query(NamedParameterJdbcTemplate.java:176)
            at org.springframework.data.jdbc.repository.query.AbstractJdbcQuery.lambda$getQueryExecution$2(AbstractJdbcQuery.java:127)
            at org.springframework.data.jdbc.repository.query.StringBasedJdbcQuery.execute(StringBasedJdbcQuery.java:85)
            at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor$QueryMethodInvoker.invoke(QueryExecutorMethodInterceptor.java:195)
            at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.doInvoke(QueryExecutorMethodInterceptor.java:152)
            at org.springframework.data.repository.core.support.QueryExecutorMethodInterceptor.invoke(QueryExecutorMethodInterceptor.java:130)
            at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
            at org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:367)
            at org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:118)
            at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
            at org.springframework.dao.support.PersistenceExceptionTranslationInterceptor.invoke(PersistenceExceptionTranslationInterceptor.java:139)
            at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
            at org.springframework.aop.interceptor.ExposeInvocationInterceptor.invoke(ExposeInvocationInterceptor.java:95)
            at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)
            at org.springframework.aop.framework.JdkDynamicAopProxy.invoke(JdkDynamicAopProxy.java:212)
            at com.sun.proxy.$Proxy96.findByUpdatedAtGreaterThan(Unknown Source)
            at com.example.demo.AnimalRepositoryIntegrationTest.someTestMethod(AnimalRepositoryIntegrationTest.java:41)

        Caused by:
            org.postgresql.util.PSQLException: Bad value for type timestamp/date/time: {1}
                at org.postgresql.jdbc.TimestampUtils.parseBackendTimestamp(TimestampUtils.java:362)
                at org.postgresql.jdbc.TimestampUtils.toTimestamp(TimestampUtils.java:394)
                at org.postgresql.jdbc.PgPreparedStatement.setObject(PgPreparedStatement.java:634)
                at org.postgresql.jdbc.PgPreparedStatement.setObject(PgPreparedStatement.java:935)
                at com.zaxxer.hikari.pool.HikariProxyPreparedStatement.setObject(HikariProxyPreparedStatement.java)
                at org.springframework.jdbc.core.StatementCreatorUtils.setValue(StatementCreatorUtils.java:392)
                at org.springframework.jdbc.core.StatementCreatorUtils.setParameterValueInternal(StatementCreatorUtils.java:231)
                at org.springframework.jdbc.core.StatementCreatorUtils.setParameterValue(StatementCreatorUtils.java:146)
                at org.springframework.jdbc.core.PreparedStatementCreatorFactory$PreparedStatementCreatorImpl.setValues(PreparedStatementCreatorFactory.java:283)
                at org.springframework.jdbc.core.PreparedStatementCreatorFactory$PreparedStatementCreatorImpl.createPreparedStatement(PreparedStatementCreatorFactory.java:241)
                at org.springframework.jdbc.core.JdbcTemplate.execute(JdbcTemplate.java:615)
                ... 19 more

                Caused by:
                java.lang.NumberFormatException: Trailing junk on timestamp: 'T21:26:13.318881+01:00'
                    at org.postgresql.jdbc.TimestampUtils.parseBackendTimestamp(TimestampUtils.java:352)
                    ... 29 more
