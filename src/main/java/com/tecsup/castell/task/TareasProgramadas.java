package com.tecsup.castell.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TareasProgramadas {

    // @Scheduled(cron = "30 2 * * * ?")
    @Scheduled(cron = "0 */1 * * * * ")
    public void generarRecordatorios() {
        System.out.println("PRUEBAS");
    }
}
