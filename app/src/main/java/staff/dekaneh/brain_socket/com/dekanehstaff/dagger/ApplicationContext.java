package staff.dekaneh.brain_socket.com.dekanehstaff.dagger;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface ApplicationContext { }
