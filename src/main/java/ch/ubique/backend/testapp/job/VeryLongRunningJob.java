package ch.ubique.backend.testapp.job;

import ch.ubique.backend.core.utils.ProgressLogger;
import ch.ubique.backend.job.Job;
import ch.ubique.backend.job.RegisteredJob;
import ch.ubique.backend.job.model.JobRunContext;
import ch.ubique.backend.job.model.JobRunState;
import ch.ubique.backend.job.model.properties.JobPropertiesConfig;
import ch.ubique.backend.job.model.properties.JobPropertyDefinitions;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.stream.IntStream;

@RegisteredJob
public class VeryLongRunningJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(VeryLongRunningJob.class);

    @Override
    public JobRunState run(final JobRunContext jobRunContext) {
        logger.debug("Running job with context {}", jobRunContext);

        final var progressLogger = new ProgressLogger("VERY_SLOW_RUNNING_JOB: ", 1000);
        IntStream.range(0, 1000).forEach(i -> progressLogger.watch(() -> runJobIteration(i)));

        return JobRunState.DONE;
    }

    private void runJobIteration(final int i) {
        logger.info("Running job iteration {}, will sleep for 1 second", i);
        try {
            Thread.sleep(Duration.ofSeconds(1));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull JobPropertiesConfig propertiesConfig() {
        return JobPropertiesConfig.initial(
                JobPropertyDefinitions.LAST_RUN_MAX_AGE.createProperty(Duration.ofDays(10000)),
                JobPropertyDefinitions.LAST_RUN_MAX_AGE_ALLOW_NO_RUN.createProperty(true));
    }
}
