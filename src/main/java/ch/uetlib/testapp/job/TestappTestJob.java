package ch.uetlib.testapp.job;

import ch.uetlib.job.Job;
import ch.uetlib.job.RegisteredJob;
import ch.uetlib.job.model.JobRunContext;
import ch.uetlib.job.model.JobRunState;
import ch.uetlib.job.model.properties.JobPropertiesConfig;
import ch.uetlib.job.model.properties.JobPropertyDefinitions;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@RegisteredJob
public class TestappTestJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(TestappTestJob.class);

    @Override
    public JobRunState run(final JobRunContext jobRunContext) {
        logger.debug("Running job with context {}", jobRunContext);
        return JobRunState.DONE;
    }

    @Override
    public @NotNull JobPropertiesConfig propertiesConfig() {
        return JobPropertiesConfig.initial(
                JobPropertyDefinitions.LAST_RUN_MAX_AGE.createProperty(Duration.ofDays(10000)),
                JobPropertyDefinitions.LAST_RUN_MAX_AGE_ALLOW_NO_RUN.createProperty(true));
    }
}
