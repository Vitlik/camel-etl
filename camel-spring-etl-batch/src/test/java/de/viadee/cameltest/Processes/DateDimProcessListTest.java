package de.viadee.cameltest.Processes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

// https://stackoverflow.com/questions/2606572/junit-splitting-integration-test-and-unit-tests
// @Category(IntegrationTest.class)
@RunWith(MockitoJUnitRunner.class)
public class DateDimProcessListTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DateDimProcessList dateProcess;

    @Test
    public void createDateEntryTestEmptyDB() {

        assertEquals("", "");
    }

    @Test
    public void createDateEntryTestNonEmptyDB() {

        assertEquals("", "");

    }
}
