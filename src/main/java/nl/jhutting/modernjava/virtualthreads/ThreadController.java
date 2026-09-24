package nl.jhutting.modernjava.virtualthreads;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    private static final Logger log = LoggerFactory.getLogger(ThreadController.class);

    @GetMapping("/name")
    public String getThreadName() {
        return Thread.currentThread().toString();
    }

    @GetMapping("/load")
    public void doSomething() throws InterruptedException {
        log.info("hey, I'm doing something in thread: {}", Thread.currentThread());
        Thread.sleep(1000);
    }
}
