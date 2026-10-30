package ch.ubique.uetlib.testapp.job;

import ch.ubique.uetlib.core.utils.ProgressLogger;
import ch.ubique.uetlib.job.Job;
import ch.ubique.uetlib.job.RegisteredJob;
import ch.ubique.uetlib.job.model.JobRunContext;
import ch.ubique.uetlib.job.model.JobRunState;
import ch.ubique.uetlib.job.model.properties.JobPropertiesConfig;
import ch.ubique.uetlib.job.model.properties.JobPropertyDefinitions;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.util.stream.IntStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        } catch (final InterruptedException e) {
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
