package com.example.emtlab.jobs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j

public class MaterializedViewRefreshScheduler {
    @PersistenceContext
    private EntityManager entityManager;
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void refreshMaterializedView() {

        log.info("Refreshing materialized view...");
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW book_stats").executeUpdate();
        log.info("Refresh complete.");
    }
}
