package org.jobrunr.javazone.common;

import org.jobrunr.jobs.lambdas.JobRequest;
import org.jobrunr.jobs.lambdas.JobRequestHandler;
import org.springframework.stereotype.Component;

@Component
public class VikingRelaxRequestHandler implements JobRequestHandler<VikingRelaxRequestHandler.VikingRelaxRequest> {

    @Override
    public void run(VikingRelaxRequest vikingRelaxRequest) throws Exception {
        System.out.println("Relaxing a viking by playing " + vikingRelaxRequest.tableGame + " and drinking " + vikingRelaxRequest.beerType);
    }

    public record VikingRelaxRequest(String tableGame, String beerType) implements JobRequest {

        @Override
        public Class<VikingRelaxRequestHandler> getJobRequestHandler() {
            return VikingRelaxRequestHandler.class;
        }
    }
}
