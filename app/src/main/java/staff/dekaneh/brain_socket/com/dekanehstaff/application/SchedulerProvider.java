package staff.dekaneh.brain_socket.com.dekanehstaff.application;

import io.reactivex.Scheduler;

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
