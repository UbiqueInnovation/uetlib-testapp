package ch.ubique.backend.job.testapp.job;

import ch.ubique.backend.job.Job;
import ch.ubique.backend.job.RegisteredJob;
import ch.ubique.backend.job.model.JobRunContext;
import ch.ubique.backend.job.model.JobRunState;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RegisteredJob
public class TestJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(TestJob.class);

    @Override
    public JobRunState run(final JobRunContext jobRunContext) {
        logger.debug("Running job with context {}", jobRunContext);
        return JobRunState.DONE;
    }
}
