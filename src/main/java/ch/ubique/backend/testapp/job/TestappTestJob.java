package ch.ubique.backend.testapp.job;

import ch.ubique.backend.job.Job;
import ch.ubique.backend.job.RegisteredJob;
import ch.ubique.backend.job.model.JobRunContext;
import ch.ubique.backend.job.model.JobRunState;
import ch.ubique.backend.job.model.properties.JobPropertiesConfig;
import ch.ubique.backend.job.model.properties.JobPropertyDefinitions;
import ch.ubique.backend.job.model.runner.conditions.JobRunnerTaint;
import ch.ubique.backend.job.model.triggers.FixedRateTrigger;
import ch.ubique.backend.job.model.triggers.JobTriggersConfig;
import ch.ubique.backend.job.model.triggers.RunOnStartupTrigger;

import jakarta.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Set;

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
                JobPropertyDefinitions.LAST_RUN_MAX_AGE_ALLOW_NO_RUN.createProperty(true),
                JobPropertyDefinitions.JOB_RUN_TAINTS.createProperty(
                        Set.of(JobRunnerTaint.from("testapp-instance"))));
    }

    @Override
    public JobTriggersConfig triggersConfig() {
        return JobTriggersConfig.fixed(
                new RunOnStartupTrigger(), new FixedRateTrigger(Duration.ofMinutes(10)));
    }
}
