package com.academy.mobile.ddt.tests.framework.bd.helper;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static com.academy.util.MatcherAssertExt.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class SubscriberBdHelper {
    private static final Logger LOG = LogManager.getLogger(SubscriberBdHelper.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM subscriber";

    private String jdbcDriver;
    private String jdbcUrl;

    private boolean bdMode = false;

    public SubscriberBdHelper(String jdbcDriver, String jdbcUrl) {
        this.jdbcDriver = jdbcDriver;
        this.jdbcUrl = jdbcUrl;
        init();
    }

    private void init() {
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            LOG.error("Error initializing jdbc connection. Details: {}", e.getMessage());
        }
    }

    public void setBdMode(boolean on) {
        bdMode = on;
    }

    public Entities<Subscriber> all() {
        Entities<Subscriber> subscribers = new Entities<>();
        try(Connection connection = DriverManager.getConnection(jdbcUrl)) {
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Subscriber subscriber = new Subscriber();
                subscriber.setId(rs.getLong("id"));
                subscriber.setFirstName(rs.getString("first_name"));
                subscriber.setLastName(rs.getString("last_name"));
                subscriber.setAge(rs.getInt("age"));
                subscriber.setGender(Gender.valueOf(rs.getString("gender").charAt(0)));

                subscribers.add(subscriber);
            }
        } catch (SQLException e) {
            LOG.error("Error reading from DB. Details: {}", e.getMessage());
        }

        return subscribers;
    }

    public void verifyEqualTo(Entities<Subscriber> expected) {
        if (!bdMode)
            return;

        assertThat(all(), equalTo(expected));
    }
}
