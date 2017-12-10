/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jofelgarze.testweb;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.sql.DataSource;
import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;

/**
 *
 * @author Josue
 */
@Startup
@Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class InitializerBean {
    
    private static final String STAGE = "";
    private static final String CHANGELOG_FILE = "liquibase/changelog/db.changelog-master.xml";

    @Resource(lookup = "java:/Datasource/SakilaDS")
    private DataSource ds;

    @PostConstruct
    protected void bootstrap() {
        ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(getClass().getClassLoader());
        try (Connection connection = ds.getConnection()) {
            JdbcConnection jdbcConnection = new JdbcConnection(connection);
            Database db = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(jdbcConnection);

            Liquibase liquiBase = new Liquibase(CHANGELOG_FILE, resourceAccessor, db);
            //liquiBase.update(STAGE);
            liquiBase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException e) {
            String msg = e.getMessage();
        } 
    }
    
    
}
