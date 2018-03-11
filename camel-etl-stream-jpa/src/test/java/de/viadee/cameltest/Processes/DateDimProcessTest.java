package de.viadee.cameltest.Processes;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;

// https://stackoverflow.com/questions/2606572/junit-splitting-integration-test-and-unit-tests
// @Category(IntegrationTest.class)
@RunWith(MockitoJUnitRunner.class)
public class DateDimProcessTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private DateDimProcess dateProcess;

    @Test
    public void createDateEntryTestEmptyDB() {

        when(jdbcTemplate.queryForObject(Mockito.anyString(), Integer.class)).thenReturn(null);
    }

    @Test
    public void createDateEntryTestNonEmptyDB() {

    }
}
