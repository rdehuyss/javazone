package org.jobrunr.javazone.demo1;

import org.jobrunr.javazone.common.Region;
import org.jobrunr.javazone.common.VikingRelaxRequestHandler.VikingRelaxRequest;
import org.jobrunr.javazone.common.VikingService;
import org.jobrunr.scheduling.JobRequestScheduler;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo1")
public class Demo1API {

    private final JobScheduler jobScheduler;
    private final JobRequestScheduler jobRequestScheduler;
    private final VikingService vikingService;

    public Demo1API(JobScheduler jobScheduler, JobRequestScheduler jobRequestScheduler, VikingService vikingService) {
        this.jobScheduler = jobScheduler;
        this.jobRequestScheduler = jobRequestScheduler;
        this.vikingService = vikingService;
    }

    @GetMapping("/enqueue-via-lambda")
    public String createJobViaLambda(@RequestParam(required = false, defaultValue = "chess") String tableGame, @RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        jobScheduler.enqueue(() -> vikingService.playSomeTableGamesAndDrinkSomeBeer(tableGame, beerType));
        return "ok";
    }

    @GetMapping("/enqueue-via-jobrequest")
    public String createJobViaJobRequest(@RequestParam(required = false, defaultValue = "chess") String tableGame, @RequestParam(required = false, defaultValue = "Duvel") String beerType) {
        jobRequestScheduler.enqueue(new VikingRelaxRequest(tableGame, beerType));
        return "ok";
    }

    @GetMapping("/do-expedition")
    public String doExpedition(@RequestParam(required = false, defaultValue = "belgium") Region region) {
        jobScheduler.enqueue(() -> vikingService.doExpedition(region));
        return "ok";
    }
}
