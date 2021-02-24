package ru.exprod.crm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.crm.service.SyncService;

@RestController
@RequestMapping("/api/manage")
public class ManageController {

    private final SyncService syncService;

    public ManageController(SyncService syncService) {
        this.syncService = syncService;
    }

    @RequestMapping("/sync")
    public void sync() {
        syncService.syncAll();
    }

    @RequestMapping("/test")
    public void test() {
        syncService.test();
    }

    @RequestMapping("/test2")
    public void test2() {
        syncService.test2();
    }
}
