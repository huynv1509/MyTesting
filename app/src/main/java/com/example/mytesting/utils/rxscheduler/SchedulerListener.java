package com.example.mytesting.utils.rxscheduler;

import io.reactivex.Scheduler;

public interface SchedulerListener {
    Scheduler io();
    Scheduler ui();
    Scheduler computation();
}
